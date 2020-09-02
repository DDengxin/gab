package com.tengzhi.business.personnel.personnelTraining.trainingReport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_hr_training_record")
public class EHrTrainingReport extends BaseModel {
    @Id
    private String jlNote;
    private String jlType,jlPlanNote,jlTitle,
            jlStype,jlTrainingMode,jlTrainingContent,
            jlTrainingLecturer,jlTrainingAddress,jlPlan,
            jlActual,jlTrainingDuration,jlTrainingCheck,
            jlTrainingEffect,jlTrainingCost,jlAttachment,
            dataMan,dataCorp;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date jlDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataDate;

    public EHrTrainingReport(){

    }
    public String getJlNote() {
        return jlNote;
    }

    public void setJlNote(String jlNote) {
        this.jlNote = jlNote;
    }

    public String getJlType() {
        return jlType;
    }

    public void setJlType(String jlType) {
        this.jlType = jlType;
    }

    public String getJlPlanNote() {
        return jlPlanNote;
    }

    public void setJlPlanNote(String jlPlanNote) {
        this.jlPlanNote = jlPlanNote;
    }

    public String getJlTitle() {
        return jlTitle;
    }

    public void setJlTitle(String jlTitle) {
        this.jlTitle = jlTitle;
    }

    public String getJlStype() {
        return jlStype;
    }

    public void setJlStype(String jlStype) {
        this.jlStype = jlStype;
    }

    public String getJlTrainingMode() {
        return jlTrainingMode;
    }

    public void setJlTrainingMode(String jlTrainingMode) {
        this.jlTrainingMode = jlTrainingMode;
    }

    public String getJlTrainingContent() {
        return jlTrainingContent;
    }

    public void setJlTrainingContent(String jlTrainingContent) {
        this.jlTrainingContent = jlTrainingContent;
    }

    public String getJlTrainingLecturer() {
        return jlTrainingLecturer;
    }

    public void setJlTrainingLecturer(String jlTrainingLecturer) {
        this.jlTrainingLecturer = jlTrainingLecturer;
    }

    public String getJlTrainingAddress() {
        return jlTrainingAddress;
    }

    public void setJlTrainingAddress(String jlTrainingAddress) {
        this.jlTrainingAddress = jlTrainingAddress;
    }

    public String getJlPlan() {
        return jlPlan;
    }

    public void setJlPlan(String jlPlan) {
        this.jlPlan = jlPlan;
    }

    public String getJlActual() {
        return jlActual;
    }

    public void setJlActual(String jlActual) {
        this.jlActual = jlActual;
    }

    public String getJlTrainingDuration() {
        return jlTrainingDuration;
    }

    public void setJlTrainingDuration(String jlTrainingDuration) {
        this.jlTrainingDuration = jlTrainingDuration;
    }

    public String getJlTrainingCheck() {
        return jlTrainingCheck;
    }

    public void setJlTrainingCheck(String jlTrainingCheck) {
        this.jlTrainingCheck = jlTrainingCheck;
    }

    public String getJlTrainingEffect() {
        return jlTrainingEffect;
    }

    public void setJlTrainingEffect(String jlTrainingEffect) {
        this.jlTrainingEffect = jlTrainingEffect;
    }

    public String getJlTrainingCost() {
        return jlTrainingCost;
    }

    public void setJlTrainingCost(String jlTrainingCost) {
        this.jlTrainingCost = jlTrainingCost;
    }

    public String getJlAttachment() {
        return jlAttachment;
    }

    public void setJlAttachment(String jlAttachment) {
        this.jlAttachment = jlAttachment;
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

    public Date getJlDate() {
        return jlDate;
    }

    public void setJlDate(Date jlDate) {
        this.jlDate = jlDate;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
