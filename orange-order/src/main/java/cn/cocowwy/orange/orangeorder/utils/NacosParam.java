package cn.cocowwy.orange.orangeorder.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * 提供对Nacos上配置信息的读取
 * 该类只能通过注入的方式获取！！
 * 不然读取到的值为null
 * 并且取值仅通过setter的方式
 * 只能够通过get的方式获取，注入之后直接取值也为null
 * 故该类所有字段必须以private的方式
 *@author Cocowwy
 *@create 2020-12-12-15:55
 */
@RefreshScope
@Getter
@Component
public class NacosParam {
    /**
     * 公告
     */
    @Value("${config.bulletin:欢迎来到橙愿！}")
    public String bulletin;

    /**
     * 小费金额
     */
    @Value("${config.defaultTips:0.5}")
    public Integer defaultTips;

    /**
     * 默认订单预存活时间
     */
    @Value("${config.tradeAliveHours:2}")
    private Long tradeAliveHours;

    /**
     * 订单期待完成时限
     */
    @Value("${config.tradeLiveHours:6}")
    private Long tradeLiveHours;
}
