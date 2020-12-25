package cn.cocowwy.orange.orangeorder.utils;

import cn.cocowwy.orange.orangeorder.entity.Trade;
import cn.cocowwy.orange.orangeorder.entity.User;
import cn.hutool.core.lang.Assert;

/**
 * 认证校验类工具包
 *@author Cocowwy
 *@create 2020-12-12-16:52
 */
public class AuthCheckUtil {
    /**
     * 注册业务必填字段非空校验
     * @param user
     */
    public static void checkRegistered(User user) {
        // 校验用户必填字段
        Assert.notBlank(user.getUsername(), ErrorMsg.ERROR_BLANK_USERNAME);
        Assert.notBlank(user.getPassword(), ErrorMsg.ERROR_BLANK_PASSWORD);
        Assert.notBlank(user.getName(), ErrorMsg.ERROR_BLANK_NAME);
        Assert.notBlank(user.getAddress1(), ErrorMsg.ERROR_BLANK_ADDRESS1);
        Assert.notBlank(user.getSex(), ErrorMsg.ERROR_BLANK_SEX);
        Assert.notBlank(user.getPhone(), ErrorMsg.ERROR_BLANK_PHONE);
        Assert.notBlank(user.getSchoolNumber(), ErrorMsg.ERROR_BLANK_SCHOOLNUMBER);
        Assert.notBlank(user.getUserRealName(), ErrorMsg.ERROR_BLANK_USERREALNAME);
        Assert.notBlank(user.getWxId(), ErrorMsg.ERROR_BLANK_WXID);
    }

    /**
     * 登录校验用户名密码非空校验 gender
     * @param username
     * @param password
     */
    public static void checkLogin(String username, String password) {
        Assert.notBlank(username, ErrorMsg.ERROR_BLANK_USERNAME);
        Assert.notBlank(password, ErrorMsg.ERROR_BLANK_PASSWORD);
    }

    /**
     * 新增在线订单非空校验
     */
    public static void checkAddLogin(Trade trade) {
        Assert.notBlank(trade.getTitle(), ErrorMsg.ERROR_BLANK_TRADE_TITLE);
        Assert.notBlank(trade.getContent(), ErrorMsg.ERROR_BLANK_TRADE_CONTENT);
        Assert.notBlank(trade.getOrderType(), ErrorMsg.ERROR_BLANK_TRADE_ORDER_TYPE);
        Assert.notNull(trade.getCreateUser(), ErrorMsg.ERROR_BLANK_TRADE_CREATE_USER);
    }
}
