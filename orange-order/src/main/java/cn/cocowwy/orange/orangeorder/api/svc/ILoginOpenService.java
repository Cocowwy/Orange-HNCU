package cn.cocowwy.orange.orangeorder.api.svc;

import cn.cocowwy.orange.orangeorder.api.dto.ILoginOpenServiceDTO;
import cn.cocowwy.orange.orangeorder.entity.User;

/**
 * 提供登录注册相关的开放接口
 *@author Cocowwy
 *@create 2020-12-12-13:37
 */
public interface ILoginOpenService {

    /**
     * 根据code得到openId 用户登录接口
     * @param code
     * @return
     */
    ILoginOpenServiceDTO.IUserLoginWxRespDTO UserLoginWx(String code);


    /**
     * 根据openId 用户注册接口
     * @param user
     * @return
     */
    ILoginOpenServiceDTO.UserRegisteredWxRespDTO UserRegisteredWx(User user, String code);


    /**
     * 改为openId方式  该方式废除
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    ILoginOpenServiceDTO.UserLoginMesageRespDTO UserLoginMesage(String username, String password);


    /**
     * 改为openId方式  该方式废除
     * 用户注册接口
     * @param user
     * @return
     */
    ILoginOpenServiceDTO.UserRegistered UserRegistered(User user);

}
