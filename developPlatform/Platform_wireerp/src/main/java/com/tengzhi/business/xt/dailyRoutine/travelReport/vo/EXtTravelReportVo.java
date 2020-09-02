package com.tengzhi.business.xt.dailyRoutine.travelReport.vo;

import com.tengzhi.business.xt.dailyRoutine.travelReport.model.EXtTravelReport;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: czf
 * @Date:2020-08-21 10:47
 */
public class EXtTravelReportVo implements Serializable {

    EXtTravelReport headdata;
    List<EXtTravelReport> enterydata;

    public EXtTravelReport getHeaddata() { return headdata; }

    public void setHeaddata(EXtTravelReport headdata) { this.headdata = headdata; }

    public List<EXtTravelReport> getEnterydata() { return enterydata; }

    public void setEnterydata(List<EXtTravelReport> enterydata) { this.enterydata = enterydata; }
}
