package com.tengzhi.business.mSbJt.inspectionRecord.vo;

import com.tengzhi.business.mSbJt.inspectionRecord.model.MSbInspection;
import com.tengzhi.business.mSbJt.vo.MSbJtVo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.StreamSupport;

public class InspectionVo implements Serializable {

    MSbInspection headdata;
    List<MSbInspection> entitydata;

    public MSbInspection getHeaddata() {
        return headdata;
    }

    public void setHeaddata(MSbInspection headdata) {
        this.headdata = headdata;
    }

    public List<MSbInspection> getEntitydata() {
        return entitydata;
    }

    public void setEntitydata(List<MSbInspection> entitydata) {
        this.entitydata = entitydata;
    }
}
