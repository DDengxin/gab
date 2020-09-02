package com.tengzhi.business.xt.dailyRoutine.wcsq.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="e_xt_wcsq_zc")
public class EXtWcsqZc {

    @Id
    private String sid;
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq1;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq2;
    private String qzdd;
    private String zcfy;
    private String dataCorp;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRq1() {
        return rq1;
    }

    public void setRq1(Date rq1) {
        this.rq1 = rq1;
    }

    public Date getRq2() {
        return rq2;
    }

    public void setRq2(Date rq2) {
        this.rq2 = rq2;
    }

    public String getQzdd() {
        return qzdd;
    }

    public void setQzdd(String qzdd) {
        this.qzdd = qzdd;
    }

    public String getZcfy() {
        return zcfy;
    }

    public void setZcfy(String zcfy) {
        this.zcfy = zcfy;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Transient
    private String _state;

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

}
