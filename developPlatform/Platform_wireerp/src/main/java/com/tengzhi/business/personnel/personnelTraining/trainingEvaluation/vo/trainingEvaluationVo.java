package com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.vo;

import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.model.EHrTrainingEvaluationPersonnel;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.model.EHrTrainingEvaluationRecord;

import java.io.Serializable;
import java.util.List;

public class trainingEvaluationVo implements Serializable {
    EHrTrainingEvaluationRecord headdata;
    List<EHrTrainingEvaluationPersonnel> entitydata;
    public EHrTrainingEvaluationRecord getHeaddata() {
        return headdata;
    }

    public void setHeaddata(EHrTrainingEvaluationRecord headdata) {
        this.headdata = headdata;
    }

    public List<EHrTrainingEvaluationPersonnel> getEntitydata() {
        return entitydata;
    }
    public void setEntitydata(List<EHrTrainingEvaluationPersonnel> entitydata) {
        this.entitydata = entitydata;
    }
}
