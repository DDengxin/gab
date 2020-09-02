package com.tengzhi.business.personnel.eHrWorker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_hr_worker_family")
public class EHrWorkerFamily extends BaseModel {

    @Id
    private Integer jtSid;
    private String workerId,jtWorkingRelationship,jtFamilyTies,jtName,jtContact,jtSocialSecurity,jtSocialSecurityBase;
    @Transient
    private String _state;

    private String dataMan,dataCorp;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataDate;

    public Integer getJtSid() {
        return jtSid;
    }

    public void setJtSid(Integer jtSid) {
        this.jtSid = jtSid;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getJtWorkingRelationship() {
        return jtWorkingRelationship;
    }

    public void setJtWorkingRelationship(String jtWorkingRelationship) {
        this.jtWorkingRelationship = jtWorkingRelationship;
    }

    public String getJtFamilyTies() {
        return jtFamilyTies;
    }

    public void setJtFamilyTies(String jtFamilyTies) {
        this.jtFamilyTies = jtFamilyTies;
    }

    public String getJtName() {
        return jtName;
    }

    public void setJtName(String jtName) {
        this.jtName = jtName;
    }

    public String getJtContact() {
        return jtContact;
    }

    public void setJtContact(String jtContact) {
        this.jtContact = jtContact;
    }

    public String getJtSocialSecurity() {
        return jtSocialSecurity;
    }

    public void setJtSocialSecurity(String jtSocialSecurity) {
        this.jtSocialSecurity = jtSocialSecurity;
    }

    public String getJtSocialSecurityBase() {
        return jtSocialSecurityBase;
    }

    public void setJtSocialSecurityBase(String jtSocialSecurityBase) {
        this.jtSocialSecurityBase = jtSocialSecurityBase;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getDataMan() {
        return dataMan;
    }

    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
