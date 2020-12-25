package cn.cocowwy.orange.orangeorder.mapper;

import cn.cocowwy.orange.orangeorder.entity.Consume;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消费记录表(Consume)表数据库访问层
 *
 * @author Cocowwy
 * @since 2020-12-03 14:27:15
 */
@Mapper
public interface ConsumeMapper extends BaseMapper<Consume> {

}