package cn.cocowwy.orange.orangeorder.service.impl;

import cn.cocowwy.orange.orangeorder.entity.RechargeResords;
import cn.cocowwy.orange.orangeorder.mapper.RechargeResordsMapper;
import cn.cocowwy.orange.orangeorder.service.RechargeResordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 橙币充值记录表(RechargeResords)表服务实现类
 *
 * @author wangwy8@asiainfo.com
 * @since 2020-12-03 14:27:29
 */
@Service
public class RechargeResordsServiceImpl extends ServiceImpl<RechargeResordsMapper, RechargeResords> implements RechargeResordsService {
    @Autowired
    private RechargeResordsMapper rechargeResordsMapper;

    @Override
    public boolean save(RechargeResords dto) {
        return super.save(dto);
    }

}