package cn.cocowwy.orange.orangeorder.controller;

import cn.cocowwy.orange.orangeorder.api.dto.ITradeOpenServiceDTO;
import cn.cocowwy.orange.orangeorder.api.svc.ITradeOpenService;
import cn.cocowwy.orange.orangeorder.entity.Trade;
import cn.cocowwy.orange.orangeorder.entity.User;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *@author Cocowwy
 *@create 2020-12-12-17:17
 */
@RestController
@Slf4j
public class TradeController {

    @Autowired
    ITradeOpenService tradeOpenService;

    /**
     * 得到所有在线订单
     * @param userId
     * @return
     */
    @PostMapping("/getTrades")
    public List<Trade> getTrades(@RequestParam("userId") Long userId) {
        ITradeOpenServiceDTO.GetOnlineTradeRespDTO onlineTrade = tradeOpenService.getOnlineTrade(userId);
        return onlineTrade.getTrades();
    }


    @PostMapping("/getTradesByUserId")
    public List<Map<String, Object>> getTradesByUserId(@RequestParam("userInfo") User user) {
        return null;
    }

    /**
     * 添加新的在线订单
     * @param trade
     * @return
     */
    @PostMapping("/addTradeOnLine")
    public Map<String, Object> addTrade(Trade trade) {
        ITradeOpenServiceDTO.AddOnLineTradeRespDTO addOnLineTradeRespDTO = tradeOpenService.addOnLineTrade(trade);
        return BeanUtil.beanToMap(addOnLineTradeRespDTO);
    }

    /**
     * 用户接单
     * @param tradeId 订单Id
     * @param userId  接单用户userId
     * @return
     */
    @PostMapping("/acceptTrade")
    public Map<String, Object> acceptTrade(Long tradeId, Long userId) {
        ITradeOpenServiceDTO.AcceptTradeRespDTO acceptTradeRespDTO = tradeOpenService.acceptTrade(tradeId, userId);
        return BeanUtil.beanToMap(acceptTradeRespDTO);
    }

    /**
     * 查询订单记录
     * @param userId
     * @return
     */
    @PostMapping("/queryTradeRecords")
    public Map<String, Object> queryTradeRecords(Long userId) {
        ITradeOpenServiceDTO.QueryTradeRecordsRespDTO queryTradeRecordsRespDTO = tradeOpenService.queryTradeRecords(userId);
        return BeanUtil.beanToMap(queryTradeRecordsRespDTO);
    }

    /**
     * 用户完成订单
     * @param tradeId
     * @return
     */
    @PostMapping("/accomplishTrade")
    public Map<String, Object> accomplishTrade(Long tradeId) {
        ITradeOpenServiceDTO.AccomplishTradeRespDTO accomplishTradeRespDTO = tradeOpenService.accomplishTrade(tradeId);
        return BeanUtil.beanToMap(accomplishTradeRespDTO);
    }

    /**
     * 用户取消已接单
     * @param tradeId
     * @return
     */
    @PostMapping("/cancelAcceptTrade")
    public Map<String, Object> cancelAcceptTrade(Long tradeId) {
        ITradeOpenServiceDTO.CancelAcceptTradeRespDTO cancelAcceptTrade = tradeOpenService.cancelAcceptTrade(tradeId);
        return BeanUtil.beanToMap(cancelAcceptTrade);
    }

    /**
     * 用户取消已派单
     * @param tradeId
     * @return
     */
    @PostMapping("/cancelDistributeTrade")
    public Map<String, Object> cancelDistributeTrade(Long tradeId) {
        ITradeOpenServiceDTO.CancelDistributeTradeRespDTO cancelDistributeTradeRespDTO = tradeOpenService.cancelDistributeTrade(tradeId);
        return BeanUtil.beanToMap(cancelDistributeTradeRespDTO);
    }
}
