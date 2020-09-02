package com.tengzhi.business.xt.notice.deptmeeting.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_deptmeeting")
public class DeptMeeting {
    @Id
    private String note;
    private String hyadd,hytitle,hydept,man,flag,data_corp,file,isgk;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHyadd() {
        return hyadd;
    }

    public void setHyadd(String hyadd) {
        this.hyadd = hyadd;
    }

    public String getIsgk() {
        return isgk;
    }

    public void setIsgk(String isgk) {
        this.isgk = isgk;
    }

    public String getHytitle() {
        return hytitle;
    }

    public void setHytitle(String hytitle) {
        this.hytitle = hytitle;
    }

    public String getHydept() {
        return hydept;
    }

    public void setHydept(String hydept) {
        this.hydept = hydept;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getData_corp() {
        return data_corp;
    }

    public void setData_corp(String data_corp) {
        this.data_corp = data_corp;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }
}
