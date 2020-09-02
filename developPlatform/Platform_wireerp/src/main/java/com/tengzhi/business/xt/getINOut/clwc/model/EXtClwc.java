package com.tengzhi.business.xt.getINOut.clwc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_clwc")
public class EXtClwc {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date wcRq;
    private String wcDept;
    private String wcMan;
    private String wcAdd;
    private String wcYc;
    private String wcYcsm;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date wcSjcc;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date wcSjhc;
    private String dataCorp;
    private String wcCp;
    private String wcDriver;
    private String wcSm;
    private String wcFlag;
    private String dataMan;
    private String bzNote;
    private String wcCclc;
    private String wcHclc;
    private String wcCcman;
    private String wcHcman;
    private String wcsqNote;

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Date getWcRq() { return wcRq; }

    public void setWcRq(Date wcRq) { this.wcRq = wcRq; }

    public String getWcDept() { return wcDept; }

    public void setWcDept(String wcDept) { this.wcDept = wcDept; }

    public String getWcMan() { return wcMan; }

    public void setWcMan(String wcMan) { this.wcMan = wcMan; }

    public String getWcAdd() { return wcAdd; }

    public void setWcAdd(String wcAdd) { this.wcAdd = wcAdd; }

    public String getWcYc() { return wcYc; }

    public void setWcYc(String wcYc) { this.wcYc = wcYc; }

    public String getWcYcsm() { return wcYcsm; }

    public void setWcYcsm(String wcYcsm) { this.wcYcsm = wcYcsm; }

    public Date getWcSjcc() { return wcSjcc; }

    public void setWcSjcc(Date wcSjcc) { this.wcSjcc = wcSjcc; }

    public Date getWcSjhc() { return wcSjhc; }

    public void setWcSjhc(Date wcSjhc) { this.wcSjhc = wcSjhc; }

    public String getDataCorp() { return dataCorp; }

    public void setDataCorp(String dataCorp) { this.dataCorp = dataCorp; }

    public String getWcCp() { return wcCp; }

    public void setWcCp(String wcCp) { this.wcCp = wcCp; }

    public String getWcDriver() { return wcDriver; }

    public void setWcDriver(String wcDriver) { this.wcDriver = wcDriver; }

    public String getWcSm() { return wcSm; }

    public void setWcSm(String wcSm) { this.wcSm = wcSm; }

    public String getWcFlag() { return wcFlag; }

    public void setWcFlag(String wcFlag) { this.wcFlag = wcFlag; }

    public String getDataMan() { return dataMan; }

    public void setDataMan(String dataMan) { this.dataMan = dataMan; }

    public String getBzNote() { return bzNote; }

    public void setBzNote(String bzNote) { this.bzNote = bzNote; }

    public String getWcCclc() { return wcCclc;}

    public void setWcCclc(String wcCclc) { this.wcCclc = wcCclc; }

    public String getWcHclc() { return wcHclc; }

    public void setWcHclc(String wcHclc) { this.wcHclc = wcHclc; }

    public String getWcCcman() { return wcCcman; }

    public void setWcCcman(String wcCcman) { this.wcCcman = wcCcman; }

    public String getWcHcman() { return wcHcman; }

    public void setWcHcman(String wcHcman) { this.wcHcman = wcHcman; }

    public String getWcsqNote() { return wcsqNote; }

    public void setWcsqNote(String wcsqNote) { this.wcsqNote = wcsqNote; }

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
