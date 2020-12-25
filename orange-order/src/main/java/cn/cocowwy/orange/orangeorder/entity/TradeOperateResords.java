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
 * @since 2020-12-03 14:37:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName(value = "t_trade_operate_resords")
public class TradeOperateResords extends Model {
    private static final long serialVersionUID = -42671832348503117L;

    /**
     * 订单唯一标识id
     */
    @TableField("trade_id")
    private Long tradeId;

    /**
     * 操作用户唯一标识 id
     */
    @TableField("operate_user")
    private Long operateUser;

    /**
     * 操作时间
     */
    @TableField("operate_time")
    private LocalDateTime operateTime;

    /**
     * 操作标记 0接单 1派单 2完成订单 3取消已派单 4举报订单 5取消已接单 6封禁
     */
    @TableField("operate_tag")
    private String operateTag;

    /**
     * 操作附带信息
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