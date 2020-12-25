package cn.cocowwy.orange.orangeorder.service;


import cn.cocowwy.orange.orangeorder.entity.Trade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 订单表(Trade)表服务实现类
 *
 * @author Cocowwy
 * @since 2020-12-03 14:27:42
 */
public interface TradeService extends IService<Trade> {
    boolean updateByTradeId(Long tradeId, Trade trade);

    List<Trade> qureyHisByUserId(Long userId);
}