package cn.cocowwy.orange.orangewall.controller;

import cn.cocowwy.orange.orangewall.fegin.FeginClientService;
import cn.cocowwy.orange.orangewall.fegin.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 校园墙服务的控制层
 * @author Cocowwy
 * @create 2021-04-04-23:12
 */
@RestController
@Slf4j
public class WallController {
    @Autowired
    FeginClientService service;

    @PostMapping("/wall/createWall")
    private Map<String, Object> createWall(Long userId, String title, String message, String imgURL) {
        // RPC调用判断用户是否可以创建墙信息
        User user= service.getUserMsg(201137016249L);
        // 入库
        System.out.println(user);
        return null;
    }
}
