package cn.cocowwy.orange.orangeorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * @author Cocowwy
 * @since 2021-02-01 22:30:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName(value = "t_user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends Model {
    private static final long serialVersionUID = -49980848312488576L;

    /**
     * 用户唯一标识 id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 账户
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 小程序用户唯一标识id
     */
    @TableField("open_id")
    private String openId;

    /**
     * 头像地址url
     */
    @TableField("avatarUrl")
    private String avatarurl;

    /**
     * 用户对外展示昵称
     */
    @TableField("name")
    private String name;

    /**
     * 收货地址1
     */
    @TableField("address1")
    private String address1;

    /**
     * 收货地址2
     */
    @TableField("address2")
    private String address2;

    /**
     * 用户手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 校园卡卡号
     */
    @TableField("school_number")
    private String schoolNumber;

    /**
     * 备用联系手机号
     */
    @TableField("phone_bak")
    private String phoneBak;

    /**
     * 用户注册时间
     */
    @TableField("registered_time")
    private LocalDateTime registeredTime;

    /**
     * 用户销户时间
     */
    @TableField("cancellation_time")
    private LocalDateTime cancellationTime;

    /**
     * 用户封禁开始时间
     */
    @TableField("suspend_start_time")
    private LocalDateTime suspendStartTime;

    /**
     * 用户最后一次登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 用户封禁结束时间
     */
    @TableField("suspend_end_time")
    private LocalDateTime suspendEndTime;

    /**
     * 用户状态 0正常  1封禁  2注销
     */
    @TableField("status_tag")
    private String statusTag;

    /**
     * 用户每日订单的派发次数
     */
    @TableField("day_trade_times")
    private Integer dayTradeTimes;

    /**
     * 用户真实名称
     */
    @TableField("user_real_name")
    private String userRealName;

    /**
     * 用户可联系微信号
     */
    @TableField("wx_id")
    private String wxId;

    /**
     * 用户性别  0女 1男
     */
    @TableField("sex")
    private String sex;

    /**
     * 用户qq
     */
    @TableField("user_qq")
    private String userQq;

    /**
     * 预留字段2
     */
    @TableField("rsrv_str2")
    private String rsrvStr2;

    /**
     * 预留字段1
     */
    @TableField("rsrv_str1")
    private String rsrvStr1;

    /**
     * 预留字段3
     */
    @TableField("rsrv_str3")
    private String rsrvStr3;

}