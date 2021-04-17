package cn.cocowwy.orange.orangewall.controller;

import cn.cocowwy.orange.orangewall.entity.TOrangeWall;
import cn.cocowwy.orange.orangewall.fegin.FeginClientService;
import cn.cocowwy.orange.orangewall.fegin.dto.User;
import cn.cocowwy.orange.orangewall.fegin.dto.UserDetails;
import cn.cocowwy.orange.orangewall.service.TOrangeWallService;
import cn.cocowwy.orange.orangewall.util.NacosParam;
import cn.cocowwy.orange.orangewall.util.RandomStrategy;
import cn.cocowwy.orange.orangewall.util.RedisUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 校园墙服务的控制层
 * @author Cocowwy
 * @create 2021-04-04-23:12
 */
@RestController
@Slf4j
public class WallController {
    @Autowired
    NacosParam nacosParam;

    @Autowired
    FeginClientService service;

    @Autowired
    TOrangeWallService tOrangeWallService;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 创建校园墙
     * @param userId 用户唯一标识id
     * @param title 标题名
     * @param message 文本
     * @param imgURL imgURL
     * @param wallTag 标签
     * @param days 日期
     * @return
     */
    @PostMapping("/wall/createWall")
    private Map<String, Object> createWall(Long userId, String title, String message,
                                           String imgURL, String wallTag, Long days) {
        Map<String, Object> res = new HashMap<>();
        // RPC调用用户信息判断用户是否可以创建墙信息
        User user = service.getUserMsg(userId);
        UserDetails userDetails = service.getUserDetailsMessage(userId);
        if (user == null || userDetails == null) {
            res.put("result", "ERROR");
            res.put("message", "用户信息不存在");
            return res;
        }

        // 校验是否能够发墙的参数
        Double wallMoney = nacosParam.getWallMoney();
        if (wallMoney * days >= userDetails.getOrangeMoney()) {
            res.put("result", "ERROR");
            res.put("message", "用户橙币余额不足，请充值再试");
            return res;
        }

        // 入库
        Long wallId = RandomStrategy.getRandomWallId();
        TOrangeWall wall = TOrangeWall.builder()
                .wallId(wallId)
                .createUser(userId)
                .title(title)
                .content(message)
                .consume(nacosParam.getWallMoney())
                .statusTag("0")
                .imageUrl(imgURL)
                .wallTag(wallTag)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTimeUtil.offset(LocalDateTime.now(), days, ChronoUnit.DAYS))
                .build();
        boolean save = tOrangeWallService.save(wall);
        // 入redis
        String wallKey = RedisUtils.getRedisKey("onLineWall", wallId.toString());
        redisUtils.getJsonTemplate().opsForValue().set(wallKey, wall, days, TimeUnit.DAYS);

        if (save) {
            res.put("result", "SUCCESS");
            res.put("message", "校园墙发布成功");
        }
        return res;
    }

    /**
     * 获取校园墙信息
     * @param userId
     * @return
     */
    @PostMapping("/wall/getWallList")
    private List<Map<String, Object>> getWallList(Long userId) {
        log.info("userId为：" + userId + " 请求校园墙数据。");
        Set<String> keys = redisUtils.getJsonTemplate().keys("onLineWall" + "*");
        List<Map<String, Object>> results = new ArrayList<>();
        for (String key : keys) {
            TOrangeWall wall = (TOrangeWall) redisUtils.getJsonTemplate().opsForValue().get(key);
            results.add(BeanUtil.beanToMap(wall));
        }
        return results;
    }
}
