package com.tengzhi.business.personnel.personnelTraining.trainingPlan.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.model.EHrTrainingPlan;

import java.util.Map;

public interface TrainingPlanService extends BaseService<EHrTrainingPlan, String> {

    /**
     *
     * 培训计划列表
     * @param baseDto
     * @return
     */
    BasePage<Map<String,Object>> getList(BaseDto baseDto);

    /**
     * 保存数据
     * @param eHrTrainingPlan
     * @return
     */
    EHrTrainingPlan saveData(EHrTrainingPlan eHrTrainingPlan);

    Map<String,Object> getByNote(String jhNote);

    Result updateData(EHrTrainingPlan eHrTrainingPlan);

    void deleteByNote(String jhNote);

    /**
     * 状态切换
     * @param jhNote
     * @param JhFlag
     * @return
     * */
    Result getJhFlag(String jhNote, String JhFlag);

    Result setJhFlag(String jhNote, String jhFlag);

}
