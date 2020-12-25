package cn.cocowwy.orange.orangeorder.controller;

import cn.cocowwy.orange.orangeorder.utils.NacosParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化参数控制层
 *@author Cocowwy
 *@create 2020-12-12-12:23
 */
@RestController
public class InitController {
    @Autowired
    NacosParam nacosParam;

    /**
     * 页面初始化加载信息---公告栏
     * @return
     */
    @PostMapping("/initWxBulletin")
    public Map<String, Object> initWxBulletin() {
        String bulletin = nacosParam.getBulletin();
        Map<String, Object> initMap = new HashMap<>();
        initMap.put("bulletin", bulletin);
        return initMap;
    }

}
