package cn.cocowwy.orange.orangeorder.service.impl;

import cn.cocowwy.orange.orangeorder.entity.ReportResords;
import cn.cocowwy.orange.orangeorder.mapper.ReportResordsMapper;
import cn.cocowwy.orange.orangeorder.service.ReportResordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 举报记录表(ReportResords)表服务实现类
 *
 * @author wangwy8@asiainfo.com
 * @since 2020-12-03 14:27:34
 */
@Service
public class ReportResordsServiceImpl extends ServiceImpl<ReportResordsMapper, ReportResords> implements ReportResordsService {
    @Autowired
    private ReportResordsMapper reportResordsMapper;

    @Override
    public boolean save(ReportResords dto) {
        return super.save(dto);
    }

}