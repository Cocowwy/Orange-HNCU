package cn.cocowwy.orange.orangeorder.api.svc.impl;

import cn.cocowwy.orange.orangeorder.api.dto.ITradeOpenServiceDTO;
import cn.cocowwy.orange.orangeorder.api.svc.ITradeOpenService;
import cn.cocowwy.orange.orangeorder.entity.ReportResords;
import cn.cocowwy.orange.orangeorder.entity.Trade;
import cn.cocowwy.orange.orangeorder.entity.User;
import cn.cocowwy.orange.orangeorder.service.ReportResordsService;
import cn.cocowwy.orange.orangeorder.service.TradeService;
import cn.cocowwy.orange.orangeorder.service.UserService;
import cn.cocowwy.orange.orangeorder.utils.*;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Assert;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 订单操作对外服务
 * @author Cocowwy
 * @create 2020-12-12-17:35
 */
@Log4j2
@Service
public class TradeOpenServiceImpl implements ITradeOpenService {
    @Autowired
    private NacosParam nacosParam;

    @Autowired
    private AutoSetDefault autoSetDefault;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportResordsService reportResordsService;

    /**
     * 得到在线订单列表
     * @param userId
     * @return
     */
    @Override
    public ITradeOpenServiceDTO.GetOnlineTradeRespDTO getOnlineTrade(Long userId) {
        // 读取Redis上全部online信息
        Set<String> keys = redisUtils.getJsonTemplate().keys("onLineTrade" + "*");
        List<Trade> returnList = new ArrayList<>();
        for (String key : keys) {
            Object o = redisUtils.getJsonTemplate().opsForValue().get(key);
            returnList.add((Trade) o);
        }

        //过滤出正常的订单
        returnList = returnList.stream()
                .filter(o -> "0".equals(o.getStatusTag()))
                .sorted((x, y) -> x.getCreateTime().compareTo(y.getCreateTime())).collect(Collectors.toList());

        returnList.forEach(i -> i.setDetailMessage(""));

        // 模糊化 并且格式化时间
        for (Trade trade : returnList) {
            trade.setRsrvStr1(LocalDateTimeUtil.format(trade.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            trade.setRsrvStr2(LocalDateTimeUtil.format(trade.getExpectTime(), "yyyy-MM-dd HH:mm:ss"));
            trade.setDetailMessage("");
        }

        return ITradeOpenServiceDTO.GetOnlineTradeRespDTO.builder().trades(returnList).build();
    }

    /**
     * 订单Online新增规则如下：
     * 使用redis的hash结构保存在线订单
     * key为订单号
     * 每次每笔订单会在redis存一份online版本，数据库存放一份记录版本
     * @param trade
     * @return
     */
    @Override
    public ITradeOpenServiceDTO.AddOnLineTradeRespDTO addOnLineTrade(Trade trade) {
        // 校验新增订单非空
        try {
            AuthCheckUtil.checkAddLogin(trade);
        } catch (Exception e) {
            // 处理异常信息
            return ITradeOpenServiceDTO.AddOnLineTradeRespDTO
                    .builder()
                    .result(false)
                    .message(e.getMessage())
                    .build();
        }

        // 新增默认值
        autoSetDefault.setTradeDefault(trade);
        // 设置订单期待完成时间
        trade.setExpectTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), trade.getDoHours(), ChronoUnit.HOURS));


        // 入redis  订单在redis上的保存时间从配置中心读取
        String key = RedisUtils.getRedisKey("onLineTrade", String.valueOf(trade.getTradeId()));
        redisUtils.getJsonTemplate().opsForValue().set(key, trade, nacosParam.getTradeAliveHours(), TimeUnit.HOURS);


        // 入数据库
        boolean save = tradeService.save(trade);
        if (!save) {
            return ITradeOpenServiceDTO.AddOnLineTradeRespDTO
                    .builder()
                    .result(false)
                    .message("订单入库不成功，请联系管理员！")
                    .build();
        }

        log.info("【用户：{} 创建订单成功，订单id为：{}，订单标题为：{}】", trade.getCreateUser(), trade.getTradeId(), trade.getTitle());
        return ITradeOpenServiceDTO.AddOnLineTradeRespDTO
                .builder()
                .result(true)
                .message("派单成功!订单编号为" + trade.getTradeId())
                .build();
    }

    /**
     * 用户在线接单接口
     * @param tradeId
     * @param userId
     * @return
     */
    @Override
    public ITradeOpenServiceDTO.AcceptTradeRespDTO acceptTrade(Long tradeId, Long userId) {
        //TODO 判断用户是否还能接单

        // redis新增接单信息 并且修改onLineTrade数据
        // 入redis
        String onLineTradekey = RedisUtils.getRedisKey("onLineTrade", String.valueOf(tradeId));
        String acceptTradekey = RedisUtils.getRedisKey("acceptTrade", String.valueOf(tradeId));
        Trade onLineTrade = (Trade) redisUtils.getJsonTemplate().opsForValue().get(onLineTradekey);
        Assert.notNull(onLineTrade, "对不起，该订单已下线！");
        // 删除online状态，新增acceptTrade订单
        // 订单创建者信息
        List<User> users = userService.queryByUserId(onLineTrade.getCreateUser());
        Assert.notNull(users.get(0), "对不起，该订单创建者信息有误！");

        // 填充信息
        onLineTrade.setStatusTag("1");
        onLineTrade.setAddress(onLineTrade.getAddress() == null ? users.get(0).getAddress1() : onLineTrade.getAddress());
        onLineTrade.setAcceptUser(userId);
        onLineTrade.setAcceptTime(LocalDateTimeUtil.now());
        onLineTrade.setChangeTime(LocalDateTimeUtil.now());

        // 删除online的  新增accept的
        redisUtils.getJsonTemplate().delete(onLineTradekey);
        redisUtils.getJsonTemplate().opsForValue().set(acceptTradekey, onLineTrade);

        // 修改数据库订单状态
        tradeService.updateByTradeId(tradeId, onLineTrade);

        // 返回信息  过滤掉用户敏感信息
        User user = new User();
        user.setName(users.get(0).getName());
        user.setSex(users.get(0).getSex());
        user.setAddress1(users.get(0).getAddress1());
        user.setAddress2(users.get(0).getAddress2());
        user.setPhone(users.get(0).getPhone());
        user.setUserRealName(users.get(0).getUserRealName());
        user.setWxId(users.get(0).getWxId());
        return ITradeOpenServiceDTO.AcceptTradeRespDTO.builder().trade(onLineTrade).user(user).build();
    }

    /**
     * 查询订单记录，即已接单和未完成的派单接口
     * @param userId
     * @return
     */
    @Override
    public ITradeOpenServiceDTO.QueryTradeRecordsRespDTO queryTradeRecords(Long userId) {
        // 未接单
        List<Trade> inTrades = new ArrayList<>();
        // 已接单
        List<Trade> outTrades = new ArrayList<>();

        //拿到redis上的接单信息
        Set<String> keys = redisUtils.getJsonTemplate().keys("acceptTrade" + "*");
        for (String key : keys) {
            // 这里必须判空，不然会报空指针
            Object o = redisUtils.getJsonTemplate().opsForValue().get(key);
            if (null != o) {
                inTrades.add((Trade) o);
            }
        }

        //用于给outTrade过滤已经派单的订单
        List<Trade> temp = new ArrayList<>();
        temp.addAll(inTrades);

        // 根据userId和状态进行过滤操作
        inTrades = inTrades.stream().filter(o -> userId.equals(o.getAcceptUser()) && "1".equals(o.getStatusTag())).collect(Collectors.toList());

        List<Map<String, Object>> inMap = new ArrayList<>();
        for (Trade inTrade : inTrades) {
            List<User> users = userService.queryByUserId(inTrade.getCreateUser());
            Assert.notNull(users.get(0), "该下单用户信息有误！");
            Map<String, Object> map = BeanUtil.beanToMap(inTrade);
            map.put("name", users.get(0).getName());
            map.put("address1", users.get(0).getAddress1());
            map.put("phone", users.get(0).getPhone());
            map.put("userRealName", users.get(0).getUserRealName());
            map.put("wxId", users.get(0).getWxId());
            inMap.add(map);
        }


        // 读取Redis上全部online信息
        // 未接单信息
        Set<String> keysOnLine = redisUtils.getJsonTemplate().keys("onLineTrade" + "*");
        for (String key : keysOnLine) {
            Trade trade = (Trade) redisUtils.getJsonTemplate().opsForValue().get(key);
            outTrades.add(trade);
        }

        // 根据userId和状态进行过滤操作  合并online里面的派单 和accept里面的派单
        outTrades = outTrades.stream().filter(o -> userId.equals(o.getCreateUser()) && "0".equals(o.getStatusTag())).collect(Collectors.toList());
        List<Trade> userOut = temp.stream().filter(o -> userId.equals(o.getCreateUser()) && "1".equals(o.getStatusTag())).collect(Collectors.toList());
        outTrades.addAll(userOut);

        //对派出订单  已被接的订单设置接单人的信息  使用预留字段塞入接单人信息
        for (Trade outTrade : outTrades) {
            List<User> users = userService.queryByUserId(outTrade.getAcceptUser());
            if (null == users || users.size() == 0) {
                continue;
            }
            User user = users.get(0);
            outTrade.setRsrvStr1(user.getName());
            outTrade.setRsrvStr2(user.getPhone());
            outTrade.setRsrvStr3(user.getWxId());
        }


        return ITradeOpenServiceDTO.QueryTradeRecordsRespDTO
                .builder()
                .inTrade(inMap)
                .outTrade(outTrades)
                .build();
    }

    /**
     * 订单完成接口
     * @param tradeId
     * @return
     */
    @Override
    public ITradeOpenServiceDTO.AccomplishTradeRespDTO accomplishTrade(Long tradeId) {

        // 修改状态 入库
        String acceptTradeKey = RedisUtils.getRedisKey("acceptTrade", String.valueOf(tradeId));
        Trade acceptTrade = (Trade) redisUtils.getByKey(acceptTradeKey);
        Assert.notNull(acceptTrade, ErrorMsg.ERROR_OFFLINE_TRADE);
        acceptTrade.setStatusTag("2");
        acceptTrade.setChangeTime(LocalDateTime.now());
        tradeService.updateByTradeId(acceptTrade.getTradeId(), acceptTrade);
        log.info("【用户：{} 完成接单，订单号为：{}】", acceptTrade.getAcceptUser(), tradeId);

        // redis上删除该信息
        redisUtils.rmvByKey(acceptTradeKey);

        return ITradeOpenServiceDTO.AccomplishTradeRespDTO
                .builder()
                .result(true)
                .message("订单完工！")
                .build();
    }

    /**
     * 取消已接单
     * @param tradeId
     * @return
     */
    @Override
    public ITradeOpenServiceDTO.CancelAcceptTradeRespDTO cancelAcceptTrade(Long tradeId) {
        // 变更状态 移除订单
        String acceptTradeKey = RedisUtils.getRedisKey("acceptTrade", String.valueOf(tradeId));
        Trade acceptTrade = (Trade) redisUtils.getByKey(acceptTradeKey);
        redisUtils.rmvByKey(acceptTradeKey);
        Assert.notNull(acceptTrade, ErrorMsg.ERROR_OFFLINE_TRADE);
        acceptTrade.setStatusTag("0");
        acceptTrade.setChangeTime(LocalDateTime.now());
        acceptTrade.setAcceptUser(null);

        // 重新入online
        // 过期时间为 (订单存活时间-订单创建时间)-(用户接单时间-订单创建时间)=订单存活时间-用户接单时间
        Long onlineTimes = acceptTrade.getAliveTime().toInstant(ZoneOffset.of("+8")).toEpochMilli()
                - acceptTrade.getAcceptTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String onLineTrade = RedisUtils.getRedisKey("onLineTrade", String.valueOf(tradeId));
        redisUtils.getJsonTemplate().opsForValue().set(onLineTrade, acceptTrade, onlineTimes, TimeUnit.MILLISECONDS);

        // 更新数据库状态
        tradeService.updateByTradeId(tradeId, acceptTrade);
        log.info("【用户：{} 取消已接订单：{}】", acceptTrade.getCreateUser(), tradeId);

        return ITradeOpenServiceDTO.CancelAcceptTradeRespDTO
                .builder()
                .result(true)
                .message("该订单已经取消")
                .build();
    }

    /**
     * 取消已派单
     * @param tradeId
     * @return
     */
    @Override
    public ITradeOpenServiceDTO.CancelDistributeTradeRespDTO cancelDistributeTrade(Long tradeId) {

        String onLineTrade = RedisUtils.getRedisKey("onLineTrade", String.valueOf(tradeId));
        Trade byKey = (Trade) redisUtils.getByKey(onLineTrade);
        // 不在online 可能在accept
        if (null == byKey) {
            String acceptTrade = RedisUtils.getRedisKey("acceptTrade", String.valueOf(tradeId));
            Trade acpt = (Trade) redisUtils.getByKey(acceptTrade);
            Assert.notNull(acpt, "该订单已失效或者不存在！");
            // 移除key 入库
            redisUtils.rmvByKey(acceptTrade);
            acpt.setStatusTag("3");
            acpt.setChangeTime(LocalDateTime.now());
            tradeService.updateByTradeId(acpt.getTradeId(), acpt);
            return ITradeOpenServiceDTO.CancelDistributeTradeRespDTO
                    .builder()
                    .result(true)
                    .message("订单号：" + tradeId + "已经成功移除！")
                    .build();
        }
        redisUtils.rmvByKey(onLineTrade);
        byKey.setStatusTag("3");
        byKey.setChangeTime(LocalDateTime.now());
        tradeService.updateByTradeId(byKey.getTradeId(), byKey);
        return ITradeOpenServiceDTO.CancelDistributeTradeRespDTO
                .builder()
                .result(true)
                .message("订单号：" + tradeId + "已经成功移除！")
                .build();
    }

    @Override
    public void reportTrade(Long tradeId, Long userId, String reson) {
        reportResordsService.save(ReportResords.builder()
                .tradeId(tradeId)
                .action("0")
                .userId(userId)
                .time(LocalDateTime.now())
                .deal("0")
                .message(reson)
                .build());
    }
}
