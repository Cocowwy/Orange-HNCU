package cn.cocowwy.orange.orangeorder.api.svc;

import cn.cocowwy.orange.orangeorder.api.dto.ITradeOpenServiceDTO;
import cn.cocowwy.orange.orangeorder.entity.Trade;


/**
 * 订单类对外提供接口
 *@author Cocowwy
 *@create 2020-12-12-17:25
 */
public interface ITradeOpenService {
    /**
     * 提供查询在线的订单
     * @param userId
     * @return
     */
    ITradeOpenServiceDTO.GetOnlineTradeRespDTO getOnlineTrade(Long userId);

    /**
     * 添加在线订单接口
     * @param trade
     * @return
     */
    ITradeOpenServiceDTO.AddOnLineTradeRespDTO addOnLineTrade(Trade trade);

    /**
     * 接单接口
     * @param tradeId
     * @param userId
     * @return
     */
    ITradeOpenServiceDTO.AcceptTradeRespDTO acceptTrade(Long tradeId, Long userId);

    /**
     * 查询订单记录，即已接单和未完成的派单接口
     * @param userId
     * @return
     */
    ITradeOpenServiceDTO.QueryTradeRecordsRespDTO queryTradeRecords(Long userId);

    /**
     * 订单完成接口
     * @param tradeId
     * @return
     */
    ITradeOpenServiceDTO.AccomplishTradeRespDTO accomplishTrade(Long tradeId);


    /**
     * 取消已接单
     * @param tradeId
     * @return
     */
    ITradeOpenServiceDTO.CancelAcceptTradeRespDTO cancelAcceptTrade(Long tradeId);

    /**
     * 取消已派单
     * @param tradeId
     * @return
     */
    ITradeOpenServiceDTO.CancelDistributeTradeRespDTO cancelDistributeTrade(Long tradeId);

    void reportTrade(Long tradeId, Long userId, String reson);
}
