package cn.cocowwy.orange.orangeorder.api.dto;

import cn.cocowwy.orange.orangeorder.entity.User;
import cn.cocowwy.orange.orangeorder.entity.UserDetails;
import lombok.*;

import java.io.Serializable;

/**
 *@author Cocowwy
 *@create 2020-12-12-13:42
 */
@Getter
@Setter
@ToString
@Builder
public class ILoginOpenServiceDTO {

    /**
     * 用户微信openId注册接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisteredWxRespDTO implements Serializable {
        private User user;
        private boolean result;
        private String message;
        private UserDetails userDetails;
    }

    /**
     * 用户微信openId登录接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IUserLoginWxRespDTO implements Serializable {
        private User user;
        private boolean result;
        private String message;
        private UserDetails userDetails;
    }

    /**
     * 用户登录接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginMesageRespDTO implements Serializable {
        private Boolean result;
        private User user;
    }

    /**
     * 用户注册接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegistered implements Serializable {
        // 是否可注册
        private Boolean result;

        // 备注信息
        private String message;

    }
}
