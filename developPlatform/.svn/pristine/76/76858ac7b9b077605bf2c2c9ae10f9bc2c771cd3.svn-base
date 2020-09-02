package com.tengzhi.business.personnel.personnelTraining.trainingImplement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_hr_training_personnel")
public class EHrTrainingPersonnel  extends BaseModel {
    @Id
    private Integer sid;
    private String ryNote,ryWorkerId,rySignIn,
            rySignFlag,rySignRemarks,ryCheck,ryCheckFlag,
            ryCheckScore,ryCheckRemarks,dataMan,dataCorp;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" )
    private Date dataDate;
    @Transient
    private String _state;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getRyNote() {
        return ryNote;
    }

    public void setRyNote(String ryNote) {
        this.ryNote = ryNote;
    }

    public String getRyWorkerId() {
        return ryWorkerId;
    }

    public void setRyWorkerId(String ryWorkerId) {
        this.ryWorkerId = ryWorkerId;
    }

    public String getRySignIn() {
        return rySignIn;
    }

    public void setRySignIn(String rySignIn) {
        this.rySignIn = rySignIn;
    }

    public String getRySignFlag() {
        return rySignFlag;
    }

    public void setRySignFlag(String rySignFlag) {
        this.rySignFlag = rySignFlag;
    }

    public String getRySignRemarks() {
        return rySignRemarks;
    }

    public void setRySignRemarks(String rySignRemarks) {
        this.rySignRemarks = rySignRemarks;
    }

    public String getRyCheck() {
        return ryCheck;
    }

    public void setRyCheck(String ryCheck) {
        this.ryCheck = ryCheck;
    }

    public String getRyCheckFlag() {
        return ryCheckFlag;
    }

    public void setRyCheckFlag(String ryCheckFlag) {
        this.ryCheckFlag = ryCheckFlag;
    }

    public String getRyCheckScore() {
        return ryCheckScore;
    }

    public void setRyCheckScore(String ryCheckScore) {
        this.ryCheckScore = ryCheckScore;
    }

    public String getRyCheckRemarks() {
        return ryCheckRemarks;
    }

    public void setRyCheckRemarks(String ryCheckRemarks) {
        this.ryCheckRemarks = ryCheckRemarks;
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
