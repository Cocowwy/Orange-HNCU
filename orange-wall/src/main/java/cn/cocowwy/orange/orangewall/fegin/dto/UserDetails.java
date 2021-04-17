package cn.cocowwy.orange.orangewall.fegin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author Cocowwy
 * @since 2020-12-03 14:37:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName(value = "t_user_details")
public class UserDetails extends Model {
    private static final long serialVersionUID = -84760163147174999L;

    /**
     * 用户唯一标识 id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户经验值
     */
    @TableField("user_exp")
    private Integer userExp;

    /**
     * 用户橙币
     */
    @TableField("orange_money")
    private Double orangeMoney;

    /**
     * 用户身份 0普通用户 1VIP用户 2管理员用户 3开发者账号
     */
    @TableField("authority_tag")
    private String authorityTag;

    /**
     * 个性签名
     */
    @TableField("signature")
    private String signature;

    /**
     * 状态变更原因
     */
    @TableField("change_reason")
    private String changeReason;

    /**
     * 充值橙币总额
     */
    @TableField("recharges")
    private Integer recharges;

    /**
     * 所在学院
     */
    @TableField("college")
    private String college;

    /**
     * 班级
     */
    @TableField("class_id")
    private String classId;

    /**
     * 专业
     */
    @TableField("profession")
    private String profession;

    /**
     * 寝室号
     */
    @TableField("room")
    private Integer room;

    /**
     * 当日订单可创建次数
     */
    @TableField("create_times")
    private Integer createTimes;

    /**
     * 预留字段1
     */
    @TableField("rsrv_str1")
    private String rsrvStr1;

    /**
     * 预留字段2
     */
    @TableField("rsrv_str2")
    private String rsrvStr2;

    /**
     * 预留字段3
     */
    @TableField("rsrv_str3")
    private String rsrvStr3;

}