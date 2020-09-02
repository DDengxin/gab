package com.tengzhi.business.xt.dailyRoutine.workPlan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: czf
 * @Date:2020-08-20 14:15
 */
@Entity
@Table(name = "e_xt_workplan")
public class EXtWorkplan {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startRq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endRq;
    private String gzType;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date gzRemind;
    private String gzAddress;
    private String gzRemarks;
    private String dataMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataDate;
    private String dataCorp;

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Date getStartRq() { return startRq; }

    public void setStartRq(Date startRq) { this.startRq = startRq; }

    public Date getEndRq() { return endRq; }

    public void setEndRq(Date endRq) { this.endRq = endRq; }

    public String getGzType() { return gzType; }

    public void setGzType(String gzType) { this.gzType = gzType; }

    public Date getGzRemind() { return gzRemind; }

    public void setGzRemind(Date gzRemind) { this.gzRemind = gzRemind; }

    public String getGzAddress() { return gzAddress; }

    public void setGzAddress(String gzAddress) { this.gzAddress = gzAddress; }

    public String getGzRemarks() { return gzRemarks; }

    public void setGzRemarks(String gzRemarks) { this.gzRemarks = gzRemarks; }

    public String getDataMan() { return dataMan; }

    public void setDataMan(String dataMan) { this.dataMan = dataMan; }

    public Date getDataDate() { return dataDate; }

    public void setDataDate(Date dataDate) { this.dataDate = dataDate; }

    public String getDataCorp() { return dataCorp; }

    public void setDataCorp(String dataCorp) { this.dataCorp = dataCorp; }
}
