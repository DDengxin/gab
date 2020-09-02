package com.tengzhi.business.xt.getINOut.wpwc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="e_xt_wpwc_mx")
public class EXtWpwcMx {

    @Id
    private String sid;
    private String note;
    private String ccWp;
    private String wpSl;
    private String wpUnit;
    private String wpSm;
    private String isBack;
    private String hcMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hcRq;
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

    public String getCcWp() {
        return ccWp;
    }

    public void setCcWp(String ccWp) {
        this.ccWp = ccWp;
    }

    public String getWpSl() {
        return wpSl;
    }

    public void setWpSl(String wpSl) {
        this.wpSl = wpSl;
    }

    public String getWpUnit() {
        return wpUnit;
    }

    public void setWpUnit(String wpUnit) {
        this.wpUnit = wpUnit;
    }

    public String getWpSm() {
        return wpSm;
    }

    public void setWpSm(String wpSm) {
        this.wpSm = wpSm;
    }

    public String getIsBack() { return isBack; }

    public void setIsBack(String isBack) { this.isBack = isBack; }

    public String getHcMan() { return hcMan; }

    public void setHcMan(String hcMan) { this.hcMan = hcMan; }

    public Date getHcRq() { return hcRq; }

    public void setHcRq(Date hcRq) { this.hcRq = hcRq; }

    @Transient
    private String _state,id;

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
