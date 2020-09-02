package com.tengzhi.business.xt.getINOut.wlwp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_wlwp")
public class EXtWlwp {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rcRq;
    private String wpOrigin;
    private String wpType;
    private String jbDept;
    private String jbMan;
    private String rcSm;
    private String rcFlag;
    private String dataCorp;
    private String dataMan;

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Date getRcRq() { return rcRq; }

    public void setRcRq(Date rcRq) { this.rcRq = rcRq; }

    public String getWpOrigin() { return wpOrigin; }

    public void setWpOrigin(String wpOrigin) { this.wpOrigin = wpOrigin; }

    public String getWpType() { return wpType;}

    public void setWpType(String wpType) { this.wpType = wpType; }

    public String getJbDept() { return jbDept; }

    public void setJbDept(String jbDept) { this.jbDept = jbDept; }

    public String getJbMan() { return jbMan; }

    public void setJbMan(String jbMan) { this.jbMan = jbMan; }

    public String getRcSm() { return rcSm; }

    public void setRcSm(String rcSm) { this.rcSm = rcSm; }

    public String getRcFlag() { return rcFlag; }

    public void setRcFlag(String rcFlag) { this.rcFlag = rcFlag; }

    public String getDataCorp() { return dataCorp; }

    public void setDataCorp(String dataCorp) { this.dataCorp = dataCorp; }

    public String getDataMan() { return dataMan; }

    public void setDataMan(String dataMan) { this.dataMan = dataMan; }

}
