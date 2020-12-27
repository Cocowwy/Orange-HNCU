package cn.cocowwy.orange.orangeorder.service.impl;

import cn.cocowwy.orange.orangeorder.entity.UserDetails;
import cn.cocowwy.orange.orangeorder.mapper.UserDetailsMapper;
import cn.cocowwy.orange.orangeorder.service.UserDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户详细信息表(UserDetails)表服务实现类
 *
 * @author wangwy8@asiainfo.com
 * @since 2020-12-03 14:27:54
 */
@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserDetailsMapper, UserDetails> implements UserDetailsService {
    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public boolean save(UserDetails dto) {
        return super.save(dto);
    }

    @Override
    public List<UserDetails> queryByUserId(Long userId) {
        return this.list(Wrappers.<UserDetails>lambdaQuery().eq(UserDetails::getUserId, userId));
    }
}