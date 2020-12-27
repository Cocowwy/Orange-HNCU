package cn.cocowwy.orange.orangeorder.service;


import cn.cocowwy.orange.orangeorder.entity.User;
import cn.cocowwy.orange.orangeorder.entity.UserDetails;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 用户详细信息表(UserDetails)表服务实现类
 *
 * @author Cocowwy
 * @since 2020-12-03 14:27:54
 */
public interface UserDetailsService extends IService<UserDetails> {
    List<UserDetails>  queryByUserId(Long userId);
}