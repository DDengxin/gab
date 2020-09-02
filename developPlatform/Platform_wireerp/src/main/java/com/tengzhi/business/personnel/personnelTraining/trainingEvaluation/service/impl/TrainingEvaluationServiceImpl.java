package com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.service.impl;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.dao.EHrTrainingEvaluationPersonnelDao;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.dao.EHrTrainingEvaluationRecordDao;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.model.EHrTrainingEvaluationPersonnel;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.service.TrainingEvaluationService;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.vo.trainingEvaluationVo;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.model.EHrTrainingPlan;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TrainingEvaluationServiceImpl implements TrainingEvaluationService {

    @Autowired
    private EHrTrainingEvaluationRecordDao eHrTrainingEvaluationRecordDao;
    @Autowired
    private EHrTrainingEvaluationPersonnelDao eHrTrainingEvaluationPersonnelDao;
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
                "jl_training_duration,jl_training_check,f_get_param_name('培训效果',jl_training_effect,data_corp)effect_name,jl_training_effect,jl_training_cost,f_getname('GETUSERNAME',data_man,'',data_corp)data_man,f_getname('GETCORPNAME',data_corp,'',data_corp)data_corp ,to_char(data_date,'yyyy-MM-dd')data_date from e_hr_training_record "+where;

        return eHrTrainingEvaluationRecordDao.QueryMapPageList(baseDto, sql, true);
    }
    /**
     * 根据Note获取对象数据
     * @param jlNote
     * */
    @Override
    public Map<String, Object> getByNote(String jlNote) {
        String sql = "select jl_note,jl_date,jl_type,jl_plan_note,jl_title,f_get_param_name('培训类型',jl_stype,data_corp) stype_name,jl_stype,f_get_param_name('培训方式',jl_training_mode,data_corp) mode_name,jl_training_mode ," +
                "jl_training_content,jl_training_Lecturer,jl_training_address,jl_plan,jl_actual," +
                "jl_training_duration,jl_training_check,f_get_param_name('培训效果',jl_training_effect,data_corp)effect_name,jl_training_effect,jl_training_cost,f_getname('GETUSERNAME',data_man,'',data_corp)data_man,f_getname('GETCORPNAME',data_corp,'',data_corp)data_corp ,to_char(data_date,'yyyy-MM-dd')data_date from e_hr_training_record  where jl_note ='" + jlNote + "' ";
        return eHrTrainingEvaluationRecordDao.QueryToFirstMap(sql);
    }

    /**
    * 修改
    * @param trainingEvaluationVo
     * */
    public Result update(trainingEvaluationVo trainingEvaluationVo) {
        List<EHrTrainingEvaluationPersonnel> addModel=new ArrayList<EHrTrainingEvaluationPersonnel>();
        List<EHrTrainingEvaluationPersonnel> modifyedModel=new ArrayList<EHrTrainingEvaluationPersonnel>();
        List<EHrTrainingEvaluationPersonnel> deteleModel=new ArrayList<EHrTrainingEvaluationPersonnel>();
        SessionUser securityUser = SessionUser.SessionUser();
        trainingEvaluationVo.getHeaddata().setDataMan(securityUser.getUserId());
        trainingEvaluationVo.getHeaddata().setDataCorp(securityUser.getCorpId());
        trainingEvaluationVo.getHeaddata().setDataDate(new Date());
        trainingEvaluationVo.getHeaddata().getJlTrainingEffect();
        trainingEvaluationVo.getHeaddata().getJlStype();
        //业务处理
        if(trainingEvaluationVo.getEntitydata().size()>0) {
            int id = Integer.parseInt(eHrTrainingEvaluationRecordDao.getSingleResult("select COALESCE(max(sid)+1,1)  from  e_hr_training_personnel"));
            for (EHrTrainingEvaluationPersonnel item : trainingEvaluationVo.getEntitydata()) {
                if (item.getSid()==null) {
                    item.setDataMan(trainingEvaluationVo.getHeaddata().getDataMan());
                    item.setDataDate(trainingEvaluationVo.getHeaddata().getDataDate());
                    item.setDataCorp(trainingEvaluationVo.getHeaddata().getDataCorp());
                    item.setSid(id);
                    id++;
                    addModel.add(item);
                } else if ("modified".equals(item.get_state())) {
                    modifyedModel.add(item);
                } else if ("removed".equals(item.get_state())) {
                    deteleModel.add(item);
                }
            }
        }
        //数据处理
        if(addModel.size()>0) {
            addModel.forEach( model -> {
                eHrTrainingEvaluationPersonnelDao.save(model);
            });
        }
        //修改
        if(modifyedModel.size()>0) {
            modifyedModel.forEach( model -> {
                eHrTrainingEvaluationPersonnelDao.dynamicSave(model, eHrTrainingEvaluationPersonnelDao.QueryListModel(EHrTrainingEvaluationPersonnel.class,"select * from e_hr_training_personnel where sid=" + model.getSid()+ " ").get(0));

            });
        }
        //删除
        if(deteleModel.size()>0){
            eHrTrainingEvaluationPersonnelDao.deleteAll(trainingEvaluationVo.getEntitydata());
        }
        //表头保存
        eHrTrainingEvaluationRecordDao.dynamicSave(trainingEvaluationVo.getHeaddata(), eHrTrainingEvaluationRecordDao.findById(trainingEvaluationVo.getHeaddata().getJlNote()).orElse(null));
        return Result.resultOk();
    }

    /**
     * 保存
     * @param trainingEvaluationVo
     * */
    @Override
    public Result save(trainingEvaluationVo trainingEvaluationVo) throws Exception {
        List<EHrTrainingEvaluationPersonnel> addModel=new ArrayList<EHrTrainingEvaluationPersonnel>();
        List<EHrTrainingEvaluationPersonnel> modifyedModel=new ArrayList<EHrTrainingEvaluationPersonnel>();
        List<EHrTrainingEvaluationPersonnel> deteleModel=new ArrayList<EHrTrainingEvaluationPersonnel>();
        SessionUser securityUser = SessionUser.SessionUser();
        trainingEvaluationVo.getHeaddata().setDataMan(securityUser.getUserId());
        trainingEvaluationVo.getHeaddata().setDataCorp(securityUser.getCorpId());
        trainingEvaluationVo.getHeaddata().setDataDate(new Date());
        trainingEvaluationVo.getHeaddata().getJlTrainingEffect();
        trainingEvaluationVo.getHeaddata().getJlStype();
        trainingEvaluationVo.getHeaddata().setJlNote(sysGenNoteService.getNote(EHrTrainingPlan.class,"CL","yymm",3));
        if(trainingEvaluationVo.getEntitydata().size()>0) {
            int id = Integer.parseInt(eHrTrainingEvaluationRecordDao.getSingleResult("select COALESCE(max(sid)+1,1)  from  e_hr_training_personnel"));
            for (EHrTrainingEvaluationPersonnel item : trainingEvaluationVo.getEntitydata()) {
                if (item.getSid()==null) {
                    item.setDataMan(trainingEvaluationVo.getHeaddata().getDataMan());
                    item.setDataDate(trainingEvaluationVo.getHeaddata().getDataDate());
                    item.setDataCorp(trainingEvaluationVo.getHeaddata().getDataCorp());
                    item.setRyNote(trainingEvaluationVo.getHeaddata().getJlPlanNote());
                    item.setSid(id);
                    id++;
                    addModel.add(item);
                } else if ("modified".equals(item.get_state())) {
                    modifyedModel.add(item);
                } else if ("removed".equals(item.get_state())) {
                    deteleModel.add(item);
                }
            }
        }
        //数据处理
        if(addModel.size()>0) {
            addModel.forEach( model -> {
                eHrTrainingEvaluationPersonnelDao.save(model);
            });
        }
        //修改
        if(modifyedModel.size()>0) {
            modifyedModel.forEach( model -> {
                eHrTrainingEvaluationPersonnelDao.dynamicSave(model, eHrTrainingEvaluationPersonnelDao.QueryListModel(EHrTrainingEvaluationPersonnel.class,"select * from e_hr_training_personnel where sid=" + model.getSid()+ " ").get(0));

            });
        }
        //删除
        if(deteleModel.size()>0){
            eHrTrainingEvaluationPersonnelDao.deleteAll(trainingEvaluationVo.getEntitydata());
        }
        //表头保存
        eHrTrainingEvaluationRecordDao.save(trainingEvaluationVo.getHeaddata());
        return Result.resultOk();
    }


    /**
     * 删除
     * @param jlNote
     * */
    @Override
    public void deleteByNote(String jlNote) {
        eHrTrainingEvaluationRecordDao.deleteByNote(jlNote);
    }

    /**
     * 刷新gird
     * @param baseDto
     * @return
     * */
    @Override
    public BasePage<Map<String, Object>> getRushGrid(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.eq("tz_note",map.get("tzNote"));
        });
        String sql="select tz_note ry_note,tz_work_id ry_worker_id,tz_work_name worker_name,ry_sign_remarks,f_get_param_name('判定参数',ry_check,b.data_corp)ry_check_name,f_get_param_name('考核状态',ry_check_flag,b.data_corp)ry_check_flag_name,ry_check_flag,ry_check_score,ry_check_remarks,sid,f_get_param_name('判定参数',ry_sign_in,b.data_corp)ry_sign_in_name,ry_sign_in,f_get_param_name('签到状态',ry_sign_flag,b.data_corp)ry_sign_flag_name,ry_sign_flag from e_hr_training_notice a left join e_hr_training_personnel b on a.tz_note = ry_note and a.tz_work_id = ry_worker_id  "+where;
        return eHrTrainingEvaluationRecordDao.QueryToMapPage(baseDto,sql);
    }


}
