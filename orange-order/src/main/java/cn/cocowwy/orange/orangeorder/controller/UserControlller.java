package cn.cocowwy.orange.orangeorder.controller;

import cn.cocowwy.orange.orangeorder.entity.User;
import cn.cocowwy.orange.orangeorder.service.UserService;
import cn.hutool.core.collection.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 用户信息查询接口
 * @author Cocowwy
 * @create 2021-04-04-23:41
 */
@RestController
@Slf4j
public class UserControlller {
    @Autowired
    UserService userService;

    @PostMapping("/getUserMessage")
    private User getUserMessage(@RequestBody Long userId) {
        System.out.println(userId);
        return CollectionUtils.lastElement(userService.queryByUserId(userId));
    }

}
