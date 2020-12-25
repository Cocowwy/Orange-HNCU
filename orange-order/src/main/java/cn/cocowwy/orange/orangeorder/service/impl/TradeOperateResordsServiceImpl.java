package cn.cocowwy.orange.orangeorder.service.impl;

import cn.cocowwy.orange.orangeorder.entity.TradeOperateResords;
import cn.cocowwy.orange.orangeorder.mapper.TradeOperateResordsMapper;
import cn.cocowwy.orange.orangeorder.service.TradeOperateResordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单操作记录表(TradeOperateResords)表服务实现类
 *
 * @author wangwy8@asiainfo.com
 * @since 2020-12-03 14:27:47
 */
@Service
public class TradeOperateResordsServiceImpl extends ServiceImpl<TradeOperateResordsMapper, TradeOperateResords> implements TradeOperateResordsService {
    @Autowired
    private TradeOperateResordsMapper tradeOperateResordsMapper;

    @Override
    public boolean save(TradeOperateResords dto) {
        return super.save(dto);
    }

}