package com.tengzhi.business.personnel.personnelTraining.trainingNotice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_hr_training_notice")
public class EHrTrainingNotice extends BaseModel {
    @Id
    private  Integer tzSid;
    private  String tzNote,tzWorkId,tzWorkName,tzWorkDept,tzAddress,dataMan,dataCorp,tzTrainingPlanNote;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tzTrainingTime;
    @Transient
    private String _state;

    public EHrTrainingNotice() {

    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getTzTrainingPlanNote() {
        return tzTrainingPlanNote;
    }

    public void setTzTrainingPlanNote(String tzTrainingPlanNote) {
        this.tzTrainingPlanNote = tzTrainingPlanNote;
    }

    public Integer getTzSid() {
        return tzSid;
    }

    public void setTzSid(Integer tzSid) {
        this.tzSid = tzSid;
    }

    public String getTzNote() {
        return tzNote;
    }

    public void setTzNote(String tzNote) {
        this.tzNote = tzNote;
    }

    public String getTzWorkId() {
        return tzWorkId;
    }

    public void setTzWorkId(String tzWorkId) {
        this.tzWorkId = tzWorkId;
    }

    public String getTzWorkName() {
        return tzWorkName;
    }

    public void setTzWorkName(String tzWorkName) {
        this.tzWorkName = tzWorkName;
    }

    public String getTzWorkDept() {
        return tzWorkDept;
    }

    public void setTzWorkDept(String tzWorkDept) {
        this.tzWorkDept = tzWorkDept;
    }

    public String getTzAddress() {
        return tzAddress;
    }

    public void setTzAddress(String tzAddress) {
        this.tzAddress = tzAddress;
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

    public Date getTzTrainingTime() {
        return tzTrainingTime;
    }

    public void setTzTrainingTime(Date tzTrainingTime) {
        this.tzTrainingTime = tzTrainingTime;
    }
}
