package com.tengzhi.business.mSbJt.inspectionRecord.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "m_sb_inspection")
public class MSbInspection extends BaseModel {

    @Id
    private Integer inspectionSid;

    private String inspectionNote,inspectionMachine,inspectionProject,
                inspectionCode,inspectionValue,dataMan,dataCorp;
    @Transient
    private String _state;
    @Transient
    private String machineName;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDate;

    public MSbInspection() {
    }

    public Integer getInspectionSid() {
        return inspectionSid;
    }

    public void setInspectionSid(Integer inspectionSid) {
        this.inspectionSid = inspectionSid;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getInspectionNote() {
        return inspectionNote;
    }

    public void setInspectionNote(String inspectionNote) {
        this.inspectionNote = inspectionNote;
    }

    public String getInspectionMachine() {
        return inspectionMachine;
    }

    public void setInspectionMachine(String inspectionMachine) {
        this.inspectionMachine = inspectionMachine;
    }

    public String getInspectionProject() {
        return inspectionProject;
    }

    public void setInspectionProject(String inspectionProject) {
        this.inspectionProject = inspectionProject;
    }

    public String getInspectionCode() {
        return inspectionCode;
    }

    public void setInspectionCode(String inspectionCode) {
        this.inspectionCode = inspectionCode;
    }

    public String getInspectionValue() {
        return inspectionValue;
    }

    public void setInspectionValue(String inspectionValue) {
        this.inspectionValue = inspectionValue;
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

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
}
