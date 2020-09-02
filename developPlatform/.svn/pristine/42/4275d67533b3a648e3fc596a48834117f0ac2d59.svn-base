package com.tengzhi.business.personnel.personnelTraining.trainingReport.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.file.FileUtil;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.model.EHrTrainingPlan;
import com.tengzhi.business.personnel.personnelTraining.trainingReport.dao.EHrTrainingReportDao;
import com.tengzhi.business.personnel.personnelTraining.trainingReport.model.EHrTrainingReport;
import com.tengzhi.business.personnel.personnelTraining.trainingReport.service.TrainingReportService;
import com.tengzhi.business.project.projectArchives.xmda.model.EXmXmda;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TrainingReportServiceImpl implements TrainingReportService {

    @Autowired
    private EHrTrainingReportDao eHrTrainingReportDao;
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
        String sql = "select jl_note,jl_date,jl_type,jl_plan_note,jl_title,f_get_param_name('培训类型',jl_stype,data_corp) stype_name,jl_stype,f_get_param_name('培训方式',jl_training_mode,data_corp)mode_name,jl_training_mode," +
                "jl_training_content,jl_training_lecturer,jl_training_address,jl_plan,jl_actual," +
                "jl_training_duration,jl_training_check,f_get_param_name('培训效果',jl_training_effect,data_corp)effect_name,jl_training_effect,jl_training_cost,(select line_primary from com_file f where f.line_primary= e.jl_attachment)jl_attachment,f_getname('GETUSERNAME',data_man,'',data_corp)data_man,f_getname('GETCORPNAME',data_corp,'',data_corp)data_corp ,to_char(data_date,'yyyy-MM-dd')data_date from e_hr_training_record e"+where;

        return eHrTrainingReportDao.QueryMapPageList(baseDto, sql, true);
    }
    /**
     * 根据Note获取对象数据
     * @param jlNote
     * */
    @Override
    public Map<String, Object> getByNote(String jlNote) {
        String sql = "select jl_note,jl_date,jl_type,jl_plan_note,jl_title,jl_stype,jl_training_mode ," +
                "jl_training_content,jl_training_Lecturer,jl_training_address,jl_plan,jl_actual," +
                "jl_training_duration,jl_training_check,f_get_param_name('培训效果',jl_training_effect,data_corp)effect_name,jl_training_effect,jl_training_cost,jl_attachment,f_getname('GETUSERNAME',data_man,'',data_corp)data_man,f_getname('GETCORPNAME',data_corp,'',data_corp)data_corp ,to_char(data_date,'yyyy-MM-dd')data_date from e_hr_training_record  where jl_note ='" + jlNote + "' ";
        return eHrTrainingReportDao.QueryToFirstMap(sql);
    }

    /**
     * 修改数据
     * @param eHrTrainingReport
     * */
    @Override
    public void update(EHrTrainingReport eHrTrainingReport) {
        eHrTrainingReportDao.dynamicSave(eHrTrainingReport, eHrTrainingReportDao.findById(eHrTrainingReport.getJlNote()).orElse(null));
    }
}
