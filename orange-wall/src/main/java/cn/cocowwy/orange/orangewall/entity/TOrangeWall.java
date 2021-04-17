package cn.cocowwy.orange.orangewall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.InputStream;
import java.time.LocalDateTime;


/**
 * @author Cocowwy
 * @since 2021-04-17 11:23:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName(value = "t_orange_wall")
@NoArgsConstructor
@AllArgsConstructor
public class TOrangeWall extends Model {
    private static final long serialVersionUID = 177720215976057447L;

    /**
     * 上墙唯一标识id
     */
    @TableField("wall_id")
    private Long wallId;

    /**
     * 创建者userid
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 题目
     */
    @TableField("title")
    private String title;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 到期时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 类型  0招新  1表白   2公告   3广告  4其它
     */
    @TableField("wall_tag")
    private String wallTag;

    /**
     * 图片地址

     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 图片
     */
    @TableField("image")
    private InputStream image;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 所花费的橙币数量
     */
    @TableField("consume")
    private Double consume;

    /**
     * 记录点赞的用户id 数组
     */
    @TableField("like")
    private String like;

    /**
     * 记录评论的用户信息  json  用户昵称  评论时间
     */
    @TableField("comment")
    private String comment;

    /**
     * 状态 0正常 1到期  2举报 3封禁
     */
    @TableField("status_tag")
    private String statusTag;

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

    /**
     * 预留字段1
     */
    @TableField("rsrv_str1")
    private String rsrvStr1;

}