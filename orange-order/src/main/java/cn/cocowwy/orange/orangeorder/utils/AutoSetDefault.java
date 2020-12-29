package cn.cocowwy.orange.orangeorder.utils;

import cn.cocowwy.orange.orangeorder.entity.Trade;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 该工具包用于自动设置默认值
 * 部分数据读取自 Nacos 配置中心
 * 只能通过注入的方式，不然拿到的配置值任然为null
 * @author Cocowwy
 * @create 2020-12-12-18:48
 */
@Component
@RefreshScope
public class AutoSetDefault {

    @Autowired
    NacosParam nacosParam;

    /**
     * 设置空字段的默认值
     * 使其满足存入数据库的条件
     * @return
     */
    public void  setTradeDefault(Trade trade) {
        // 初始化订单入库基本默认信息
        // 以下为系统默认
        trade.setTradeId(RandomStrategy.getRandomTradeId());
        trade.setCreateTime(LocalDateTime.now());
        trade.setOrderType("0");
        trade.setChangeTime(LocalDateTime.now());
        trade.setStatusTag("0");
        trade.setAliveTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), nacosParam.getTradeAliveHours(), ChronoUnit.HOURS));
//        trade.setExpectTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), nacosParam.getTradeLiveHours(), ChronoUnit.HOURS));
        // 以下优先用户设置信息
        trade.setTips(null == trade.getTips() ? nacosParam.getDefaultTips() : trade.getTips());
    }


}
