package com.tengzhi.business.xt.dailyRoutine.wcsq.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="e_xt_wcsq")
public class EXtWcsq {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date wcRq;
    private String wcDept;
    private String wcMan;
    private String dataMan;
    private String wcType;
    private String wcAdd;
    private String wcAddsm;
    private String wcIszc;
    private String wcYc;
    private String wcYcsm;
    private String wcSm;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date wcTcrq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date wcThrq;
    private String wcLc;
    private String wcFy;
    private String wcFysm;
    private String wcGzap;
    private String wcZcsm;
    private String wcFlag;
    private String dataCorp;
    private String wcMcck;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date wcMcrq;
    private String wcMhck;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date wcMhrq;
    private String wcPcnote;
    private String wcBznote;
    private String wcSc;

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Date getWcRq() { return wcRq; }

    public void setWcRq(Date wcRq) {this.wcRq = wcRq; }

    public String getWcDept() { return wcDept; }

    public void setWcDept(String wcDept) { this.wcDept = wcDept; }

    public String getWcMan() { return wcMan; }

    public void setWcMan(String wcMan) { this.wcMan = wcMan; }

    public String getDataMan() { return dataMan; }

    public void setDataMan(String dataMan) { this.dataMan = dataMan; }

    public String getWcType() { return wcType; }

    public void setWcType(String wcType) { this.wcType = wcType; }

    public String getWcAdd() { return wcAdd; }

    public void setWcAdd(String wcAdd) { this.wcAdd = wcAdd; }

    public String getWcAddsm() { return wcAddsm; }

    public void setWcAddsm(String wcAddsm) { this.wcAddsm = wcAddsm; }

    public String getWcIszc() { return wcIszc; }

    public void setWcIszc(String wcIszc) { this.wcIszc = wcIszc; }

    public String getWcYc() { return wcYc; }

    public void setWcYc(String wcYc) { this.wcYc = wcYc; }

    public String getWcYcsm() { return wcYcsm; }

    public void setWcYcsm(String wcYcsm) { this.wcYcsm = wcYcsm; }

    public String getWcSm() { return wcSm; }

    public void setWcSm(String wcSm) { this.wcSm = wcSm; }

    public Date getWcTcrq() { return wcTcrq; }

    public void setWcTcrq(Date wcTcrq) { this.wcTcrq = wcTcrq; }

    public Date getWcThrq() { return wcThrq; }

    public void setWcThrq(Date wcThrq) { this.wcThrq = wcThrq; }

    public String getWcLc() { return wcLc; }

    public void setWcLc(String wcLc) { this.wcLc = wcLc; }

    public String getWcFy() { return wcFy; }

    public void setWcFy(String wcFy) { this.wcFy = wcFy; }

    public String getWcFysm() { return wcFysm; }

    public void setWcFysm(String wcFysm) { this.wcFysm = wcFysm; }

    public String getWcGzap() { return wcGzap; }

    public void setWcGzap(String wcGzap) { this.wcGzap = wcGzap; }

    public String getWcZcsm() { return wcZcsm; }

    public void setWcZcsm(String wcZcsm) { this.wcZcsm = wcZcsm; }

    public String getWcFlag() { return wcFlag; }

    public void setWcFlag(String wcFlag) { this.wcFlag = wcFlag; }

    public String getDataCorp() { return dataCorp; }

    public void setDataCorp(String dataCorp) { this.dataCorp = dataCorp; }

    public String getWcMcck() { return wcMcck; }

    public void setWcMcck(String wcMcck) { this.wcMcck = wcMcck; }

    public Date getWcMcrq() { return wcMcrq; }

    public void setWcMcrq(Date wcMcrq) { this.wcMcrq = wcMcrq; }

    public String getWcMhck() { return wcMhck; }

    public void setWcMhck(String wcMhck) { this.wcMhck = wcMhck; }

    public Date getWcMhrq() { return wcMhrq; }

    public void setWcMhrq(Date wcMhrq) { this.wcMhrq = wcMhrq; }

    public String getWcPcnote() { return wcPcnote; }

    public void setWcPcnote(String wcPcnote) { this.wcPcnote = wcPcnote; }

    public String getWcBznote() { return wcBznote; }

    public void setWcBznote(String wcBznote) { this.wcBznote = wcBznote; }

    public String getWcSc() { return wcSc; }

    public void setWcSc(String wcSc) { this.wcSc = wcSc; }

    @Transient
    private String deptName;
    @Transient
    private String manName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }
}

