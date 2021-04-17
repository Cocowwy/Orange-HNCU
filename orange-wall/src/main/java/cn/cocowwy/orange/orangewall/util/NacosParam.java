package cn.cocowwy.orange.orangewall.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * 提供对Nacos上配置信息的读取
 * 该类只能通过注入的方式获取！！
 * 不然读取到的值为null
 * 并且取值仅通过getter的方式
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
     * 校园墙每日多少橙币
     */
    @Value("${config.wallMoney:5}")
    public Double wallMoney;
}
