package cn.cocowwy.orange.orangeorder.utils;

import cn.hutool.core.lang.UUID;

import java.time.LocalDateTime;

/**
 * 随机生成工具类
 *@author Cocowwy
 *@create 2020-12-12-13:13
 */
public class RandomStrategy {
    /**
     * 自定义长12位的唯一userId生成工具
     * 自定义规则：
     * 根据时间生成唯一userId
     * @return
     */
    public static Long getRandomUserId() {
        String year = String.valueOf(LocalDateTime.now().getYear()).substring(2, 4);
        // 2.生成uuid的hashCode值
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        // 3.有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return Long.valueOf(year + String.format("%010d", hashCodeV));
    }

    /**
     * 随机生成长为16位的订单Id
     * 自定义规则：
     * 根据时间生成唯一tradeId
     * @return
     */
    public static Long getRandomTradeId() {
        String year = String.valueOf(LocalDateTime.now().getYear()).substring(2, 4);
        // 2.生成uuid的hashCode值
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        // 3.有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        // 4.结果
        return Long.valueOf(year + String.format("%014d", hashCodeV));
    }

    /**
     * 随机生成长为12位的wallId
     * 自定义规则：
     * 根据时间生成唯一wallId
     * @return
     */
    public static Long getRandomWallId() {
        String year = String.valueOf(LocalDateTime.now().getYear()).substring(2, 4);
        // 2.生成uuid的hashCode值
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        // 3.有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return Long.valueOf(year + String.format("%010d", hashCodeV));
    }

}
