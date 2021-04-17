package cn.cocowwy.orange.orangeorder.api.svc.impl;

import cn.cocowwy.orange.orangeorder.api.dto.ILoginOpenServiceDTO;
import cn.cocowwy.orange.orangeorder.api.svc.ILoginOpenService;
import cn.cocowwy.orange.orangeorder.entity.User;
import cn.cocowwy.orange.orangeorder.entity.UserDetails;
import cn.cocowwy.orange.orangeorder.service.UserDetailsService;
import cn.cocowwy.orange.orangeorder.service.UserService;
import cn.cocowwy.orange.orangeorder.utils.*;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 *@author Cocowwy
 *@create 2020-12-12-13:46
 */
@Service
@Slf4j
public class LoginOpenServiceImpl implements ILoginOpenService {
    @Autowired
    private UserService userService;

    @Autowired
    private NacosParam nacosParam;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 根据openId 用户登录接口
     * @param code
     * @return
     */
    @Override
    public ILoginOpenServiceDTO.IUserLoginWxRespDTO UserLoginWx(String code) {
        String json = WxOpenIdUtil.getOpenId(code);
        String openId = String.valueOf(JSONUtil.parse(json).getByPath("openid"));
        List<User> users = userService.queryUserByOpenId(openId);
        if (users.size() > 0) {
            List<UserDetails> userDetails = userDetailsService.queryByUserId(users.get(0).getUserId());
            return ILoginOpenServiceDTO.IUserLoginWxRespDTO
                    .builder()
                    .message("该用户已经注册！")
                    .result(true)
                    .userDetails(userDetails.get(0))
                    .user(users.get(0).setOpenId(null).setPassword(null))
                    .build();
        }

        return ILoginOpenServiceDTO.IUserLoginWxRespDTO
                .builder()
                .message("该用户未进行注册！")
                .user(User.builder().openId(openId).build())
                .result(false)
                .build();
    }

    /**
     * 根据openIdopenId 用户注册接口
     * @param user
     * @return
     */
    @Override
    public ILoginOpenServiceDTO.UserRegisteredWxRespDTO UserRegisteredWx(User user, String code) {
        Assert.notNull(code, ErrorMsg.ERROR_BLANK_CODE);
        String json = WxOpenIdUtil.getOpenId(code);
        String openId = String.valueOf(JSONUtil.parse(json).getByPath("openid"));
        Assert.isTrue(!openId.equals("null"), "用户注册openId获取失败！");
        user.setOpenId(openId);
        List<User> users = userService.queryUserByOpenId(user.getOpenId());
        if (users.size() > 0) {
            return ILoginOpenServiceDTO.UserRegisteredWxRespDTO
                    .builder()
                    .message("该用户已经注册！")
                    .result(false)
                    .build();
        }

        // 校验必填字段
        AuthCheckUtil.checkRegistered(user);

        // 校验其余信息  wx不能二次绑定
        List<User> userByWx = userService.querUserByWx(user.getWxId());
        if (userByWx.size() != 0) {
            return ILoginOpenServiceDTO.UserRegisteredWxRespDTO
                    .builder()
                    .result(false)
                    .message("该微信号已被其他账号绑定！")
                    .build();
        }

        // 初始化注册用户信息
        // 根据自动生成策略自动生成16位userid
        Long randomUserId = RandomStrategy.getRandomUserId();
        user.setUserId(randomUserId);
        user.setRegisteredTime(LocalDateTime.now());
        user.setDayTradeTimes(0);
        boolean save = userService.save(user);
        UserDetails details = UserDetails.builder()
                .userId(randomUserId)
                .userExp(0)
                .orangeMoney(0D)
                .authorityTag("0")
                .changeReason("注册账户")
                .createTimes(nacosParam.getDayTradeTimes())
                .build();

        boolean saveDet = userDetailsService.save(details);

        // 记录注册失败日志
        if (save == false || saveDet == false) {
            log.info("用户注册信息失败，用户注册提供信息为：" + user);
        }

        return ILoginOpenServiceDTO.UserRegisteredWxRespDTO
                .builder()
                .result(save)
                .message(save == true ? "用户注册成功" : "用户注册失败，请联系管管理员！")
                .user(user)
                .userDetails(details)
                .build();
    }

    /**
     * 用户登录接口  改为openId方式登录  该登录方式废除
     * 成功则返回用户基本信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public ILoginOpenServiceDTO.UserLoginMesageRespDTO UserLoginMesage(String username, String password) {
        AuthCheckUtil.checkLogin(username, password);
        List<User> users = userService.queryUser(username, password);
        if (users.size() > 0) {
            return ILoginOpenServiceDTO.UserLoginMesageRespDTO
                    .builder()
                    .user(users.get(0))
                    .result(true)
                    .build();
        }
        return ILoginOpenServiceDTO.UserLoginMesageRespDTO
                .builder()
                .result(false)
                .build();
    }

    /**
     * 改为openId方式  该方式废除
     * 用户注册业务接口
     * @param user
     * @return
     */
    @Override
    public ILoginOpenServiceDTO.UserRegistered UserRegistered(User user) {
        // 校验必填字段
        AuthCheckUtil.checkRegistered(user);

        List<User> users = userService.queryUserName(user.getUsername());

        // 校验是否被注册
        if (users.size() != 0) {
            return ILoginOpenServiceDTO.UserRegistered
                    .builder()
                    .result(false)
                    .message("该账号已被注册！")
                    .build();
        }

        // 校验其余信息  wx不能二次绑定
        List<User> userByWx = userService.querUserByWx(user.getWxId());
        if (userByWx.size() != 0) {
            return ILoginOpenServiceDTO.UserRegistered
                    .builder()
                    .result(false)
                    .message("该微信号已其他账号绑定！")
                    .build();
        }

        // 根据自动生成策略自动生成16位userid
        Long randomUserId = RandomStrategy.getRandomUserId();
        user.setUserId(randomUserId);

        boolean save = userService.save(user);

        // 记录注册失败日志
        if (save == false) {
            log.info("用户注册信息失败，用户注册提供信息为：" + user);
        }

        return ILoginOpenServiceDTO.UserRegistered
                .builder()
                .result(save)
                .message(save == true ? "用户注册成功" : "用户注册失败，请联系管管理员！")
                .build();
    }
}
