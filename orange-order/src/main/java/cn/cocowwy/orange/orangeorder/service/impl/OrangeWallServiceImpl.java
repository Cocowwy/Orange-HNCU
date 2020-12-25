package cn.cocowwy.orange.orangeorder.service.impl;

import cn.cocowwy.orange.orangeorder.entity.OrangeWall;
import cn.cocowwy.orange.orangeorder.mapper.OrangeWallMapper;
import cn.cocowwy.orange.orangeorder.service.OrangeWallService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 橙愿墙信息表(OrangeWall)表服务实现类
 *
 * @author wangwy8@asiainfo.com
 * @since 2020-12-03 14:27:21
 */
@Service
public class OrangeWallServiceImpl extends ServiceImpl<OrangeWallMapper, OrangeWall> implements OrangeWallService {
    @Autowired
    private OrangeWallMapper orangeWallMapper;

    @Override
    public boolean save(OrangeWall dto) {
        return super.save(dto);
    }

}