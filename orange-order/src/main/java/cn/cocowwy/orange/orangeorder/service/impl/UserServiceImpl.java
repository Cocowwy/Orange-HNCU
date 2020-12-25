package cn.cocowwy.orange.orangeorder.service.impl;

import cn.cocowwy.orange.orangeorder.entity.User;
import cn.cocowwy.orange.orangeorder.mapper.UserMapper;
import cn.cocowwy.orange.orangeorder.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户基本信息表 (User)表服务实现类
 *
 * @author Cocowwy
 * @since 2020-12-03 14:28:20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean save(User dto) {
        return super.save(dto);
    }

    @Override
    public List<User> queryUserByOpenId(String openId) {
        return this.list(Wrappers.<User>lambdaQuery()
                .eq(User::getOpenId, openId)
        );
    }

    @Override
    public List<User> queryUser(String username, String password) {
        return this.list(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password)
        );
    }

    @Override
    public List<User> queryUserName(String username) {
        return this.list(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username)
        );
    }

    @Override
    public List<User> querUserByWx(String wx) {
        return this.list(Wrappers.<User>lambdaQuery()
                .eq(User::getWxId, wx)
        );
    }

    @Override
    public List<User> queryByUserId(Long userId) {
        return this.list(Wrappers.<User>lambdaQuery()
                .eq(User::getUserId, userId)
        );
    }

}