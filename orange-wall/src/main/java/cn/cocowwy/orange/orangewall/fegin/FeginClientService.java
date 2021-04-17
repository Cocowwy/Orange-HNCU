package cn.cocowwy.orange.orangewall.fegin;

import cn.cocowwy.orange.orangewall.fegin.dto.User;
import cn.cocowwy.orange.orangewall.fegin.dto.UserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 校园墙服务调用外部接口Fegin
 * @author Cocowwy
 * @create 2021-04-04-23:06
 */
@Component
// 所需要调用的接口的服务名
@FeignClient("orange-api")
public interface FeginClientService {

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @PostMapping("/getUserMessage")
    User getUserMsg(Long userId);


    /**
     * 获取用户详细信息接口
     * @param userId
     * @return
     */
    @PostMapping("/getUserDetailsMessage")
     UserDetails getUserDetailsMessage(Long userId);
}
