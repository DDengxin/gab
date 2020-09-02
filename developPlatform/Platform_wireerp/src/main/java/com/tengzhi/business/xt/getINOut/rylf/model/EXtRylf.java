package com.tengzhi.business.xt.getINOut.rylf.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="e_xt_rylf")
public class EXtRylf {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lfSqrq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lfRq;
    private String lfName;
    private String lfRs;
    private String lfIsyc;
    private String lfYcsm;
    private String lfFlag;
    private String lfSm;
    private String jdMan;
    private String jdDept;
    private String dataCorp;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getLfSqrq() {
        return lfSqrq;
    }

    public void setLfSqrq(Date lfSqrq) {
        this.lfSqrq = lfSqrq;
    }

    public Date getLfRq() {
        return lfRq;
    }

    public void setLfRq(Date lfRq) {
        this.lfRq = lfRq;
    }

    public String getLfName() {
        return lfName;
    }

    public void setLfName(String lfName) {
        this.lfName = lfName;
    }

    public String getLfRs() {
        return lfRs;
    }

    public void setLfRs(String lfRs) {
        this.lfRs = lfRs;
    }

    public String getLfIsyc() {
        return lfIsyc;
    }

    public void setLfIsyc(String lfIsyc) {
        this.lfIsyc = lfIsyc;
    }

    public String getLfYcsm() {
        return lfYcsm;
    }

    public void setLfYcsm(String lfYcsm) {
        this.lfYcsm = lfYcsm;
    }

    public String getLfFlag() {
        return lfFlag;
    }

    public void setLfFlag(String lfFlag) {
        this.lfFlag = lfFlag;
    }

    public String getLfSm() {
        return lfSm;
    }

    public void setLfSm(String lfSm) {
        this.lfSm = lfSm;
    }

    public String getJdMan() {
        return jdMan;
    }

    public void setJdMan(String jdMan) {
        this.jdMan = jdMan;
    }

    public String getJdDept() {
        return jdDept;
    }

    public void setJdDept(String jdDept) {
        this.jdDept = jdDept;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }
}
