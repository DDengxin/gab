package com.tengzhi.business.xt.getINOut.wlwp.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_wlwp_mx")
public class EXtWlwpMx {

    @Id
    private String sid;
    private String note;
    private String wpName;
    private String wpSl;
    private String wpUnit;
    private String wpSm;
    private String isOut;
    private String ccMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ccRq;

    public String getSid() { return sid; }

    public void setSid(String sid) { this.sid = sid; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public String getWpName() { return wpName; }

    public void setWpName(String wpName) { this.wpName = wpName; }

    public String getWpSl() { return wpSl; }

    public void setWpSl(String wpSl) { this.wpSl = wpSl; }

    public String getWpUnit() { return wpUnit; }

    public void setWpUnit(String wpUnit) { this.wpUnit = wpUnit; }

    public String getWpSm() { return wpSm; }

    public void setWpSm(String wpSm) { this.wpSm = wpSm; }

    public String getIsOut() { return isOut; }

    public void setIsOut(String isOut) { this.isOut = isOut; }

    public String getCcMan() { return ccMan; }

    public void setCcMan(String ccMan) { this.ccMan = ccMan; }

    public Date getCcRq() { return ccRq; }

    public void setCcRq(Date ccRq) { this.ccRq = ccRq; }

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
