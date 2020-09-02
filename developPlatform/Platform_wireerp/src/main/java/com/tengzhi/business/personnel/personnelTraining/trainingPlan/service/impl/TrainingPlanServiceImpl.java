package com.tengzhi.business.personnel.personnelTraining.trainingPlan.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.dao.TrainingPlanDao;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.model.EHrTrainingPlan;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.service.TrainingPlanService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class TrainingPlanServiceImpl implements TrainingPlanService {

    @Autowired
    private TrainingPlanDao trainingPlanDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public BasePage<Map<String, Object>> getList(BaseDto baseDto) {
        /*String sqlshere = " where data_corp in ('"+ SessionUser.SessionUser().getSysUser().getOrgIds().replaceAll(",", "','")+"') ";*/

        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd(c->{
            c.contains("jh_note",map.get("jhNote"));
            c.contains("jh_from",map.get("jhFrom"));
        });
        String sql = "select jh_ny,jh_note,jh_title,f_get_param_name('培训类型',jh_type,data_corp) jh_type,f_get_param_name('培训方式',jh_from,data_corp) jh_from,jh_object,jh_time,f_getname('GETDEPTNAME',jh_dept,'',data_corp)jh_dept,jh_training_content,jh_estimated_cost,f_get_param_name('培训级别',jh_training_level,data_corp) jh_training_level," +
                "jh_course_length,jh_training_address,jh_training_order," +
                "jh_flag,f_getname('GETUSERNAME',data_man,'',data_corp)data_man,f_getname('GETCORPNAME',data_corp,'',data_corp)data_corp,to_char(data_date,'yyyy-MM-dd')data_date  from e_hr_training_plan where jh_note not in (select jl_plan_note from e_hr_training_record )"+where;
        return trainingPlanDao.QueryMapPageList(baseDto, sql, true);
    }

    /**
     * 保存
     * @param eHrTrainingPlan
     * */
    @Override
    public EHrTrainingPlan saveData(EHrTrainingPlan eHrTrainingPlan) {
        SessionUser securityUser=SessionUser.SessionUser();
        eHrTrainingPlan.setDataDate(new Date());
        eHrTrainingPlan.setDataCorp(securityUser.getCorpId());
        eHrTrainingPlan.setDataMan(securityUser.getUsername());
        eHrTrainingPlan.setJhFlag("登记");
        eHrTrainingPlan.setJhNote(sysGenNoteService.getNote(EHrTrainingPlan.class,"JT","yymm",3));
    return trainingPlanDao.dynamicSave(eHrTrainingPlan, trainingPlanDao.findById(eHrTrainingPlan.getJhNote()).orElse(null));
    }


    /**
     * 获取对象数据
     * @param jhNote
     * */
    @Override
    public Map<String,Object> getByNote(String jhNote) {
        String sql = " select jh_ny,jh_note,jh_title,jh_type,jh_from,jh_object,jh_time,f_getname('GETDEPTNAME',jh_dept,'',data_corp)jh_dept_name,jh_dept,jh_training_content,jh_estimated_cost,jh_training_level," +
                "jh_course_length,jh_training_address,jh_training_order," +
                "data_man,data_corp,data_date  from e_hr_training_plan  where jh_note='"+jhNote+"' ";
        return trainingPlanDao.QueryToFirstMap(sql);
    }
    /**
     * 修改数据
     * @param eHrTrainingPlan
     * */
    @Override
    public Result updateData(EHrTrainingPlan eHrTrainingPlan) {
        trainingPlanDao.dynamicSave(eHrTrainingPlan, trainingPlanDao.findById(eHrTrainingPlan.getJhNote()).orElse(null));
        return Result.resultOk();
    }
    /**
    * 删除数据
    * @param jhNote
    * */
    @Override
    public void deleteByNote(String jhNote) {
        trainingPlanDao.deleteNote(jhNote);
    }

    /**
     * 状态判断
     * @param jhFlag
     * @param jhNote
     * */
    @Override
    public Result getJhFlag(String jhNote, String jhFlag) {
        String flagString=trainingPlanDao.getJhFlag(jhNote);
        if(jhFlag.equals(flagString)) {
            return  Result.resultOk("操作成功！");
        }
        return  Result.error("该单不是“"+jhFlag+"”状态,不能操作！");
    }

    @Override
    public Result setJhFlag(String jhNote, String jhFlag) {
        trainingPlanDao.setJhFlag(jhNote,jhFlag);
        return Result.resultOk("操作成功！");
    }
}
