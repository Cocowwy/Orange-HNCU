package cn.cocowwy.orange.orangeorder.utils;

/**
 *@author Cocowwy
 *@create 2020-12-12-17:50
 */
public class ErrorMsg {
    // 非空Error信息  用户
    public static String ERROR_BLANK_USERNAME = "用户名不能为空";
    public static String ERROR_BLANK_PASSWORD = "密码不能为空";
    public static String ERROR_BLANK_NAME = "用户对外展示昵称不能为空";
    public static String ERROR_BLANK_ADDRESS1 = "验收地址不能为空不能为空";
    public static String ERROR_BLANK_SEX = "用户性别不能为空";
    public static String ERROR_BLANK_PHONE = "手机号码不能为空";
    public static String ERROR_BLANK_SCHOOLNUMBER = "校园卡卡号不能为空";
    public static String ERROR_BLANK_USERREALNAME = "用户真实姓名不能为空";
    public static String ERROR_BLANK_WXID = "可联系微信号不能为空";

    public static String ERROR_BLANK_CODE = "微信登录凭证code不能为空";

    // 非空Error信息 订单
    public static String ERROR_BLANK_TRADE_TITLE = "订单标题不能为空";
    public static String ERROR_BLANK_TRADE_CONTENT = "订单内容不能为空";
    public static String ERROR_BLANK_TRADE_TIPS = "订单小费不能空";
    public static String ERROR_BLANK_TRADE_ORDER_TYPE = "订单类型不能为空";
    public static String ERROR_BLANK_TRADE_CREATE_USER = "提交订单用户不能为空";


    // 订单状态
    public static String ERROR_OFFLINE_TRADE = "对不起，该订单已下线";

}