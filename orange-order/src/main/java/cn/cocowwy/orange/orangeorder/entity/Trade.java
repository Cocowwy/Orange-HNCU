package cn.cocowwy.orange.orangeorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * @author Cocowwy
 * @since 2020-12-03 14:37:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName(value = "t_trade")
@NoArgsConstructor
@AllArgsConstructor
public class Trade extends Model {
    private static final long serialVersionUID = -78724791243642734L;

    /**
     * 订单唯一标识id
     */
    @TableField("trade_id")
    private Long tradeId;

    /**
     * 订单创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 订单创建用户userId
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 订单状态 0正常 1已接 2完工 3取消 4超时 5举报 6封禁
     */
    @TableField("status_tag")
    private String statusTag;

    /**
     * 订单状态变更时间
     */
    @TableField("change_time")
    private LocalDateTime changeTime;

    /**
     * 接单用户userId
     */
    @TableField("accept_user")
    private Long acceptUser;

    /**
     * 接单用户时间
     */
    @TableField("accept_time")
    private LocalDateTime acceptTime;

    /**
     * 订单标题
     */
    @TableField("title")
    private String title;

    /**
     * 订单内容
     */
    @TableField("content")
    private String content;

    /**
     * 订单小费
     */
    @TableField("tips")
    private Integer tips;

    /**
     * 订单类型  0代拿快递   1食堂代买 2超市代买 3代拿 4询问 5其他
     */
    @TableField("order_type")
    private String orderType;

    /**
     * 订单派送地址
     */
    @TableField("address")
    private String address;

    /**
     * 订单期待完成时间
     */
    @TableField("expect_time")
    private LocalDateTime expectTime;

    /**
     * 订单预存活到期时间
     */
    @TableField("alive_time")
    private LocalDateTime aliveTime;

    /**
     * 订单期待完成小时
     */
    @TableField("do_hours")
    private Integer doHours;

    /**
     * 订单备注消息
     */
    @TableField("message")
    private String message;

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