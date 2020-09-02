package com.tengzhi.business.project.projectArchives.xmda.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "e_xm_xmda")
    public class EXmXmda extends BaseModel {

    @Id
    private String xmId;

    private String xmName,xmType,xmXtype,xmDept,
            xmMan,xmSm,xmCode,xmFlag,dataMan,dataCorp;
    private String xmStage,xmCustomer,xmEnableFlag;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startDate,endDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDate;
    public EXmXmda() {
    }


    public String getXmId() {
        return xmId;
    }

    public String getXmName() {
        return xmName;
    }

    public String getXmType() {
        return xmType;
    }

    public String getXmXtype() {
        return xmXtype;
    }

    public String getXmDept() {
        return xmDept;
    }

    public String getXmMan() {
        return xmMan;
    }

    public String getXmSm() {
        return xmSm;
    }

    public String getXmCode() {
        return xmCode;
    }

    public String getXmFlag() {
        return xmFlag;
    }

    public String getDataMan() {
        return dataMan;
    }


    public String getDataCorp() {
        return dataCorp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setXmId(String xmId) {
        this.xmId = xmId;
    }

    public void setXmName(String xmName) {
        this.xmName = xmName;
    }

    public void setXmType(String xmType) {
        this.xmType = xmType;
    }

    public void setXmXtype(String xmXtype) {
        this.xmXtype = xmXtype;
    }

    public void setXmDept(String xmDept) {
        this.xmDept = xmDept;
    }

    public void setXmMan(String xmMan) {
        this.xmMan = xmMan;
    }

    public void setXmSm(String xmSm) {
        this.xmSm = xmSm;
    }

    public void setXmCode(String xmCode) {
        this.xmCode = xmCode;
    }

    public void setXmFlag(String xmFlag) {
        this.xmFlag = xmFlag;
    }

    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getDataDate() { return dataDate; }

    public void setDataDate(Date dataDate) { this.dataDate = dataDate; }

    @Override
    public String toString() {
        return "EXmXmda{" +
                "xmId='" + xmId + '\'' +
                ", xmName='" + xmName + '\'' +
                ", xmType='" + xmType + '\'' +
                ", xmXtype='" + xmXtype + '\'' +
                ", xmDept='" + xmDept + '\'' +
                ", xmMan='" + xmMan + '\'' +
                ", xmSm='" + xmSm + '\'' +
                ", xmCode='" + xmCode + '\'' +
                ", xmFlag='" + xmFlag + '\'' +
                ", dataMan='" + dataMan + '\'' +
                ", dataCorp='" + dataCorp + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dataDate=" + dataDate +
                '}';
    }

    @Transient
    private String dataCorpName,xmXtypeName,xmDeptName,xmTypeName,xmCustomerName,dataManName,xmManName,xmStageName;

    public String getDataCorpName() {
        return dataCorpName;
    }

    public void setDataCorpName(String dataCorpName) {
        this.dataCorpName = dataCorpName;
    }

    public String getXmXtypeName() {
        return xmXtypeName;
    }

    public void setXmXtypeName(String xmXtypeName) {
        this.xmXtypeName = xmXtypeName;
    }

    public String getXmDeptName() {
        return xmDeptName;
    }

    public void setXmDeptName(String xmDeptName) {
        this.xmDeptName = xmDeptName;
    }

    public String getXmStage() {
        return xmStage;
    }

    public void setXmStage(String xmStage) {
        this.xmStage = xmStage;
    }

    public String getXmCustomer() {
        return xmCustomer;
    }

    public void setXmCustomer(String xmCustomer) {
        this.xmCustomer = xmCustomer;
    }

    public String getXmEnableFlag() {
        return xmEnableFlag;
    }

    public void setXmEnableFlag(String xmEnableFlag) {
        this.xmEnableFlag = xmEnableFlag;
    }

    public String getXmTypeName() {
        return xmTypeName;
    }

    public void setXmTypeName(String xmTypeName) {
        this.xmTypeName = xmTypeName;
    }

    public String getXmCustomerName() {
        return xmCustomerName;
    }

    public void setXmCustomerName(String xmCustomerName) {
        this.xmCustomerName = xmCustomerName;
    }

    public String getDataManName() {
        return dataManName;
    }

    public void setDataManName(String dataManName) {
        this.dataManName = dataManName;
    }

    public String getXmManName() {
        return xmManName;
    }

    public void setXmManName(String xmManName) {
        this.xmManName = xmManName;
    }

    public String getXmStageName() {
        return xmStageName;
    }

    public void setXmStageName(String xmStageName) {
        this.xmStageName = xmStageName;
    }
}
