package cn.cocowwy.orange.orangeorder.mapper;

import cn.cocowwy.orange.orangeorder.entity.Trade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表(Trade)表数据库访问层
 *
 * @author Cocowwy
 * @since 2020-12-03 14:27:42
 */
@Mapper
public interface TradeMapper extends BaseMapper<Trade> {

}