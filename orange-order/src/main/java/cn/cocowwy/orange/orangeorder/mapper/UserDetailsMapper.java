package cn.cocowwy.orange.orangeorder.mapper;

import cn.cocowwy.orange.orangeorder.entity.UserDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户详细信息表(UserDetails)表数据库访问层
 *
 * @author Cocowwy
 * @since 2020-12-03 14:27:54
 */
@Mapper
public interface UserDetailsMapper extends BaseMapper<UserDetails> {

}