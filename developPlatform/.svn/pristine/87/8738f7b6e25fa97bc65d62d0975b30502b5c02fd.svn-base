package com.tengzhi.business.personnel.personnelTraining.trainingStatistics.service.impl;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.personnel.personnelTraining.trainingStatistics.dao.EHrtrainingStatisticsDao;
import com.tengzhi.business.personnel.personnelTraining.trainingStatistics.service.TrainingStatisticsService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Map;


@Service
@Transactional
public class TrainingStatisticsServiceImpl implements TrainingStatisticsService {

    @Autowired
    private EHrtrainingStatisticsDao eHrtrainingStatisticsDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;
    /**
     * 查询
     * @param baseDto
     * @return
     * */
    @Override
    public BasePage<Map<String, Object>> getList(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.contains("jl_stype",map.get("jlStype"));
            c.contains("jl_training_mode",map.get("jlTrainingMode"));
            c.le("jl_date",map.get("srchRq2"));
            c.ge("jl_date",map.get("srchRq1"));
        });
        String sql = "select jl_note,jl_date,jl_type,jl_plan_note,jl_title,f_get_param_name('培训类型',jl_stype,a.data_corp) stype_name,jl_stype,f_get_param_name('培训方式',jl_training_mode,a.data_corp)mode_name,jl_training_mode," +
                "jl_training_content,f_get_param_name('培训讲师',jl_training_lecturer,a.data_corp)lecturer_name,jl_training_lecturer,jl_training_address,jl_plan,jl_actual," +
                "jl_training_duration,jl_training_check,f_get_param_name('培训效果',jl_training_effect,a.data_corp)effect_name,jl_training_effect,jl_training_cost," +
                "(select line_primary from com_file f where f.line_primary= a.jl_attachment)jl_attachment,f_getname('GETUSERNAME',a.data_man,'',a.data_corp)data_man," +
                "jh_object,jh_ny,f_getname('GETCORPNAME',a.data_corp,'',a.data_corp)data_corp ,to_char(a.data_date,'yyyy-MM-dd')data_date from e_hr_training_record a left join e_hr_training_plan b on b.jh_note = a.jl_plan_note"+where;

        return eHrtrainingStatisticsDao.QueryMapPageList(baseDto, sql, true);
    }

    private String getSrchTopListSql(BaseDto dto) {
        Map<String, String> map = dto.getParamsMap();
        SessionUser securityUser=SessionUser.SessionUser();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.contains("jl_stype",map.get("jlStype"));
            c.contains("jl_training_mode",map.get("jlTrainingMode"));
            c.le("jl_date",map.get("srchRq2"));
            c.ge("jl_date",map.get("srchRq1"));
        });
        dto.setSortField("jl_date");
        dto.setSortOrder("DESC");
        String sql = "select jl_note,jl_date,jl_type,jl_plan_note,jl_title,f_get_param_name('培训类型',jl_stype,a.data_corp) stype_name,jl_stype,f_get_param_name('培训方式',jl_training_mode,a.data_corp)mode_name,jl_training_mode," +
                "jl_training_content,f_get_param_name('培训讲师',jl_training_lecturer,a.data_corp)lecturer_name,jl_training_lecturer,jl_training_address,jl_plan,jl_actual," +
                "jl_training_duration,jl_training_check,f_get_param_name('培训效果',jl_training_effect,a.data_corp)effect_name,jl_training_effect,jl_training_cost," +
                "(select line_primary from com_file f where f.line_primary= a.jl_attachment)jl_attachment,f_getname('GETUSERNAME',a.data_man,'',a.data_corp)data_man," +
                "jh_object,jh_ny,f_getname('GETCORPNAME',a.data_corp,'',a.data_corp)data_corp ,to_char(a.data_date,'yyyy-MM-dd')data_date from e_hr_training_record a left join e_hr_training_plan b on b.jh_note = a.jl_plan_note"+where;
        return sql;
    }
    /**
     * 导出Excel
     * @param request
     * @param response
     * */
    @Override
    public void getXls(HttpServletResponse response, HttpServletRequest request) {
        //获取ExcelUitl实例
        ExcelUtil util = ExcelUtil.getInstance();
        //初始化dto对象
        BaseDto dto = BaseDto.ValueOfRequest(request);
        //将导出的页面定义为0
        dto.setPageIndex(0);
        //查询数据并且生成Excel
        Map<String, String> map = dto.getParamsMap();
        String sql = this.getSrchTopListSql(dto);
        util.ExcelToWeb(request, "培训统计", response, eHrtrainingStatisticsDao.QueryToMap(sql));

    }
}
