package cn.cocowwy.orange.orangeorder.service;


import cn.cocowwy.orange.orangeorder.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 用户基本信息表 (User)表服务实现类
 *
 * @author Cocowwy
 * @since 2020-12-03 14:28:20
 */
public interface UserService extends IService<User> {

    List<User> queryUserByOpenId(String openId);

    List<User> queryUser(String username, String password);

    List<User> queryUserName(String username);

    List<User> querUserByWx(String wx);

    List<User> queryByUserId(Long userId);
}