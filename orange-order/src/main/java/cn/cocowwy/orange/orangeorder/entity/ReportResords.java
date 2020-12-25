package cn.cocowwy.orange.orangeorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * @author Cocowwy
 * @since 2020-12-03 14:37:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName(value = "t_report_resords")
public class ReportResords extends Model {
    private static final long serialVersionUID = -46549854721935869L;

    /**
     * 举报者的用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 0举报订单  1举报上墙信息  2举报用户
     */
    @TableField("action")
    private String action;

    /**
     * 举报的订单id
     */
    @TableField("trade_id")
    private Long tradeId;

    /**
     * 举报的上墙的id
     */
    @TableField("wall_id")
    private Long wallId;

    /**
     * 被举报的用户id
     */
    @TableField("reported_user_id")
    private Long reportedUserId;

    /**
     * 举报时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * 0 未处理  1 已处理
     */
    @TableField("deal")
    private String deal;

    /**
     * 举报附带信息
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

}