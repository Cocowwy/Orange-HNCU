package cn.cocowwy.orange.orangeorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author Cocowwy
 * @since 2020-12-03 14:38:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName(value = "t_consume")
public class Consume extends Model {
    private static final long serialVersionUID = 737919570327167324L;

    /**
     * 用户唯一标识 id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 消费橙币数
     */
    @TableField("consumes")
    private Integer consumes;

    /**
     * 消费时间
     */
    @TableField("consume_time")
    private Integer consumeTime;

    /**
     * 0 购买上墙机会  1兑换派单次数  2购买VIP  3其他
     */
    @TableField("consume_tag")
    private String consumeTag;

    /**
     * 消费附带信息
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