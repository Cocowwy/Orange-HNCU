package cn.cocowwy.orange.orangeorder.service.impl;

import cn.cocowwy.orange.orangeorder.entity.Trade;
import cn.cocowwy.orange.orangeorder.mapper.TradeMapper;
import cn.cocowwy.orange.orangeorder.service.TradeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单表(Trade)表服务实现类
 *
 * @author wangwy8@asiainfo.com
 * @since 2020-12-03 14:27:42
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements TradeService {
    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public boolean save(Trade dto) {
        return super.save(dto);
    }

    @Override
    public boolean updateByTradeId(Long tradeId,Trade trade) {
        return this.update(trade, Wrappers.<Trade>lambdaQuery().eq(Trade::getTradeId, tradeId));
    }

    @Override
    public List<Trade> qureyHisByUserId(Long userId) {
        return this.list( Wrappers.<Trade>lambdaQuery().eq(Trade::getAcceptUser, userId));
    }
}