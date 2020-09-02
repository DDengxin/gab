package com.tengzhi.business.personnel.personnelTraining.trainingNotice.vo;

import com.tengzhi.business.personnel.personnelTraining.trainingNotice.model.EHrTrainingNotice;

import java.io.Serializable;
import java.util.List;

public class TrainingNoticeVo implements Serializable {
    EHrTrainingNotice headdata;
    List<EHrTrainingNotice> entitydata;

    public EHrTrainingNotice getHeaddata() {
        return headdata;
    }

    public void setHeaddata(EHrTrainingNotice headdata) {
        this.headdata = headdata;
    }

    public List<EHrTrainingNotice> getEntitydata() {
        return entitydata;
    }

    public void setEntitydata(List<EHrTrainingNotice> entitydata) {
        this.entitydata = entitydata;
    }
}
