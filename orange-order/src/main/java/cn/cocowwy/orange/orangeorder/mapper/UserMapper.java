package cn.cocowwy.orange.orangeorder.mapper;

import cn.cocowwy.orange.orangeorder.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户基本信息表 (User)表数据库访问层
 *
 * @author Cocowwy
 * @since 2020-12-03 14:28:20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}