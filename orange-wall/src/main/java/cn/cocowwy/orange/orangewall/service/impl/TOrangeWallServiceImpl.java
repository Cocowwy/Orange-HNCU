package cn.cocowwy.orange.orangewall.service.impl;

import cn.cocowwy.orange.orangewall.entity.TOrangeWall;
import cn.cocowwy.orange.orangewall.mapper.TOrangeWallMapper;
import cn.cocowwy.orange.orangewall.service.TOrangeWallService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 橙愿墙信息表(TOrangeWall)表服务实现类
 *
 * @author Cocowwy
 * @since 2021-04-16 23:58:18
 */
@Service
public class TOrangeWallServiceImpl extends ServiceImpl<TOrangeWallMapper, TOrangeWall> implements TOrangeWallService {
    @Autowired
    private TOrangeWallMapper tOrangeWallMapper;

    @Override
    public boolean save(TOrangeWall dto) {
        return super.save(dto);
    }

}