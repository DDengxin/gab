package com.tengzhi.business.xt.getINOut.wpwc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_wpwc")
public class EXtWpwc {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ccRq;
    private String jbDept;
    private String jbMan;
    private String ccAdd;
    private String wcType;
    private String ccSm;
    private String ccFlag;
    private String dataCorp;
    private String mccMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date mccRq;
    private String mjcMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date mjcRq;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCcRq() {
        return ccRq;
    }

    public void setCcRq(Date ccRq) {
        this.ccRq = ccRq;
    }

    public String getJbDept() {
        return jbDept;
    }

    public void setJbDept(String jbDept) {
        this.jbDept = jbDept;
    }

    public String getJbMan() {
        return jbMan;
    }

    public void setJbMan(String jbMan) {
        this.jbMan = jbMan;
    }

    public String getCcAdd() {
        return ccAdd;
    }

    public void setCcAdd(String ccAdd) {
        this.ccAdd = ccAdd;
    }

    public String getWcType() {
        return wcType;
    }

    public void setWcType(String wcType) {
        this.wcType = wcType;
    }

    public String getCcSm() {
        return ccSm;
    }

    public void setCcSm(String ccSm) {
        this.ccSm = ccSm;
    }

    public String getCcFlag() {
        return ccFlag;
    }

    public void setCcFlag(String ccFlag) {
        this.ccFlag = ccFlag;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    public String getMccMan() {
        return mccMan;
    }

    public void setMccMan(String mccMan) {
        this.mccMan = mccMan;
    }

    public Date getMccRq() {
        return mccRq;
    }

    public void setMccRq(Date mccRq) {
        this.mccRq = mccRq;
    }

    public String getMjcMan() {
        return mjcMan;
    }

    public void setMjcMan(String mjcMan) {
        this.mjcMan = mjcMan;
    }

    public Date getMjcRq() {
        return mjcRq;
    }

    public void setMjcRq(Date mjcRq) {
        this.mjcRq = mjcRq;
    }

    @Transient
    private String deptName;
    @Transient
    private String manName;
    @Transient
    private String hcManName;
    @Transient
    private String corpExp;

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

    public String getHcManName() {
        return hcManName;
    }

    public void setHcManName(String hcManName) {
        this.hcManName = hcManName;
    }

    public String getCorpExp() {
        return corpExp;
    }

    public void setCorpExp(String corpExp) {
        this.corpExp = corpExp;
    }
}
