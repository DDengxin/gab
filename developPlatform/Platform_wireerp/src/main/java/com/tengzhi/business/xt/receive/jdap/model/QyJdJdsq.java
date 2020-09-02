package com.tengzhi.business.xt.receive.jdap.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="e_xt_jdap")
public class QyJdJdsq {
    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq;
    private String jddept;
    private String jdfzr;
    private String jddd;
    private String lfmd;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date jdrq1;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date jdrq2;
    private String fkDw;
    private String fkName;
    private Integer fkNum;
    private String jdyc;
    private String jtyq;
    private String ssyq;
    private String qtyq;
    private String qtzl;
    private String jdzg;
    private String bmnr;
    private String bmnrMx;
    private String sp;
    private String qtsp;
    private String wp;
    private String dzsb;
    private String qtwp;
    private String sm;
    private String flag;
    private String opman;
    private String corpId;
    @Transient
    private String jdrq;
    @Transient
    private String corpName;
    @Transient
    private String jddeptname;

    public String getJddeptname() {
        return jddeptname;
    }

    public void setJddeptname(String jddeptname) {
        this.jddeptname = jddeptname;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getJdrq() {
        return jdrq;
    }

    public void setJdrq(String jdrq) {
        this.jdrq = jdrq;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getJddept() {
        return jddept;
    }

    public void setJddept(String jddept) {
        this.jddept = jddept;
    }

    public String getJdfzr() {
        return jdfzr;
    }

    public void setJdfzr(String jdfzr) {
        this.jdfzr = jdfzr;
    }

    public String getJddd() {
        return jddd;
    }

    public void setJddd(String jddd) {
        this.jddd = jddd;
    }

    public String getLfmd() {
        return lfmd;
    }

    public void setLfmd(String lfmd) {
        this.lfmd = lfmd;
    }

    public Date getJdrq1() {
        return jdrq1;
    }

    public void setJdrq1(Date jdrq1) {
        this.jdrq1 = jdrq1;
    }

    public Date getJdrq2() {
        return jdrq2;
    }

    public void setJdrq2(Date jdrq2) {
        this.jdrq2 = jdrq2;
    }

    public String getFkDw() {
        return fkDw;
    }

    public void setFkDw(String fkDw) {
        this.fkDw = fkDw;
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    public Integer getFkNum() {
        return fkNum;
    }

    public void setFkNum(Integer fkNum) {
        this.fkNum = fkNum;
    }

    public String getJdyc() {
        return jdyc;
    }

    public void setJdyc(String jdyc) {
        this.jdyc = jdyc;
    }

    public String getJtyq() {
        return jtyq;
    }

    public void setJtyq(String jtyq) {
        this.jtyq = jtyq;
    }

    public String getSsyq() {
        return ssyq;
    }

    public void setSsyq(String ssyq) {
        this.ssyq = ssyq;
    }

    public String getQtyq() {
        return qtyq;
    }

    public void setQtyq(String qtyq) {
        this.qtyq = qtyq;
    }

    public String getQtzl() {
        return qtzl;
    }

    public void setQtzl(String qtzl) {
        this.qtzl = qtzl;
    }

    public String getJdzg() {
        return jdzg;
    }

    public void setJdzg(String jdzg) {
        this.jdzg = jdzg;
    }

    public String getBmnr() {
        return bmnr;
    }

    public void setBmnr(String bmnr) {
        this.bmnr = bmnr;
    }

    public String getBmnrMx() {
        return bmnrMx;
    }

    public void setBmnrMx(String bmnrMx) {
        this.bmnrMx = bmnrMx;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getQtsp() {
        return qtsp;
    }

    public void setQtsp(String qtsp) {
        this.qtsp = qtsp;
    }

    public String getWp() {
        return wp;
    }

    public void setWp(String wp) {
        this.wp = wp;
    }

    public String getDzsb() {
        return dzsb;
    }

    public void setDzsb(String dzsb) {
        this.dzsb = dzsb;
    }

    public String getQtwp() {
        return qtwp;
    }

    public void setQtwp(String qtwp) {
        this.qtwp = qtwp;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOpman() {
        return opman;
    }

    public void setOpman(String opman) {
        this.opman = opman;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
}
