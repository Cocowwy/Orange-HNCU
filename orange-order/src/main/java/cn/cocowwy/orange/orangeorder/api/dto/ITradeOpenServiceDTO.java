package cn.cocowwy.orange.orangeorder.api.dto;

import cn.cocowwy.orange.orangeorder.entity.Trade;
import cn.cocowwy.orange.orangeorder.entity.User;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 *@author Cocowwy
 *@create 2020-12-12-17:29
 */
public class ITradeOpenServiceDTO {
    /**
     * 查询在线订单接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetOnlineTradeRespDTO {
        private List<Trade> trades;
    }

    /**
     * 添加在线订单接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddOnLineTradeRespDTO {
        private Boolean result;
        private String message;

    }

    /**
     * 接单接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AcceptTradeRespDTO {
        private Trade trade;
        private User user;
    }

    /**
     * 接单几里路查询接口出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QueryTradeRecordsRespDTO {
        /**
         * 已接单
         */
        private List<Map<String, Object>> inTrade;
        /**
         * 派单
         */
        private List<Trade> outTrade;
    }

    /**
     * 订单完工出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccomplishTradeRespDTO {
        private Boolean result;
        private String message;
    }

    /**
     * 取消接单出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CancelAcceptTradeRespDTO {
        private Boolean result;
        private String message;
    }

    /**
     * 取消接单出参
     */
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CancelDistributeTradeRespDTO {
        private Boolean result;
        private String message;
    }
}
