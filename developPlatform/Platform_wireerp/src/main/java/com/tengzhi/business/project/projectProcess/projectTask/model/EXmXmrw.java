package com.tengzhi.business.project.projectProcess.projectTask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "e_xm_xmrw")
public class EXmXmrw {

    @Id
    private String rwNote;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rwRq,rwJhStart,rwJhEnd;
    private String rwType;
    private String rwResource;
    private String rwTitle;
    private String rwProject;
    private String rwStage;
    private String rwNode;
    private String rwJhMan;
    private BigDecimal rwJhGs,rwWcGs,rwGs;
    private String rwJjcd;
    private String rwRemarks;
    private String rwFile;
    private String rwJsMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date rwJsDate,rwKsDate,rwWcDate,rwKhDate,createtime;
    private String rwKsMan;
    private String rwWcResult;
    private String rwWcRemarks;
    private String rwKhMan;
    private String rwProcess;
    private String rwPz;
    private String rwKhRemarks;
    private String rwFbMan;
    private String rwFlag;
    private String dept;
    private String dataCorp;
    private String man;
    private String wcMan;
    private String rwCb;


    public String getRwNote() {
        return rwNote;
    }

    public void setRwNote(String rwNote) {
        this.rwNote = rwNote;
    }


    public Date getRwRq() {
        return rwRq;
    }

    public void setRwRq(Date rwRq) {
        this.rwRq = rwRq;
    }


    public String getRwType() {
        return rwType;
    }

    public void setRwType(String rwType) {
        this.rwType = rwType;
    }


    public String getRwResource() {
        return rwResource;
    }

    public void setRwResource(String rwResource) {
        this.rwResource = rwResource;
    }


    public String getRwTitle() {
        return rwTitle;
    }

    public void setRwTitle(String rwTitle) {
        this.rwTitle = rwTitle;
    }


    public String getRwProject() {
        return rwProject;
    }

    public void setRwProject(String rwProject) {
        this.rwProject = rwProject;
    }


    public String getRwStage() {
        return rwStage;
    }

    public void setRwStage(String rwStage) {
        this.rwStage = rwStage;
    }


    public String getRwNode() {
        return rwNode;
    }

    public void setRwNode(String rwNode) {
        this.rwNode = rwNode;
    }


    public String getRwJhMan() {
        return rwJhMan;
    }

    public void setRwJhMan(String rwJhMan) {
        this.rwJhMan = rwJhMan;
    }


    public Date getRwJhStart() {
        return rwJhStart;
    }



    public Date getRwJhEnd() {
        return rwJhEnd;
    }

    public void setRwJhEnd(Date rwJhEnd) {
        this.rwJhEnd = rwJhEnd;
    }


    public BigDecimal getRwJhGs() {
        return rwJhGs;
    }

    public void setRwJhGs(BigDecimal rwJhGs) {
        this.rwJhGs = rwJhGs;
    }


    public String getRwJjcd() {
        return rwJjcd;
    }

    public void setRwJjcd(String rwJjcd) {
        this.rwJjcd = rwJjcd;
    }


    public String getRwRemarks() {
        return rwRemarks;
    }

    public void setRwRemarks(String rwRemarks) {
        this.rwRemarks = rwRemarks;
    }


    public String getRwFile() {
        return rwFile;
    }

    public void setRwFile(String rwFile) {
        this.rwFile = rwFile;
    }


    public String getRwJsMan() {
        return rwJsMan;
    }

    public void setRwJsMan(String rwJsMan) {
        this.rwJsMan = rwJsMan;
    }


    public Date getRwJsDate() {
        return rwJsDate;
    }

    public void setRwJsDate(Date rwJsDate) {
        this.rwJsDate = rwJsDate;
    }


    public String getRwKsMan() {
        return rwKsMan;
    }

    public void setRwKsMan(String rwKsMan) {
        this.rwKsMan = rwKsMan;
    }


    public Date getRwKsDate() {
        return rwKsDate;
    }

    public void setRwKsDate(Date rwKsDate) {
        this.rwKsDate = rwKsDate;
    }


    public Date getRwWcDate() {
        return rwWcDate;
    }

    public void setRwWcDate(Date rwWcDate) {
        this.rwWcDate = rwWcDate;
    }


    public BigDecimal getRwWcGs() {
        return rwWcGs;
    }

    public void setRwWcGs(BigDecimal rwWcGs) {
        this.rwWcGs = rwWcGs;
    }


    public String getRwWcResult() {
        return rwWcResult;
    }

    public void setRwWcResult(String rwWcResult) {
        this.rwWcResult = rwWcResult;
    }


    public String getRwWcRemarks() {
        return rwWcRemarks;
    }

    public void setRwWcRemarks(String rwWcRemarks) {
        this.rwWcRemarks = rwWcRemarks;
    }


    public String getRwKhMan() {
        return rwKhMan;
    }

    public void setRwKhMan(String rwKhMan) {
        this.rwKhMan = rwKhMan;
    }


    public Date getRwKhDate() {
        return rwKhDate;
    }

    public void setRwKhDate(Date rwKhDate) {
        this.rwKhDate = rwKhDate;
    }


    public String getRwProcess() {
        return rwProcess;
    }

    public void setRwProcess(String rwProcess) {
        this.rwProcess = rwProcess;
    }


    public BigDecimal getRwGs() {
        return rwGs;
    }

    public void setRwGs(BigDecimal rwGs) {
        this.rwGs = rwGs;
    }


    public String getRwPz() {
        return rwPz;
    }

    public void setRwPz(String rwPz) {
        this.rwPz = rwPz;
    }


    public String getRwKhRemarks() {
        return rwKhRemarks;
    }

    public void setRwKhRemarks(String rwKhRemarks) {
        this.rwKhRemarks = rwKhRemarks;
    }


    public String getRwFbMan() {
        return rwFbMan;
    }

    public void setRwFbMan(String rwFbMan) {
        this.rwFbMan = rwFbMan;
    }


    public String getRwFlag() {
        return rwFlag;
    }

    public void setRwFlag(String rwFlag) {
        this.rwFlag = rwFlag;
    }


    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }


    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }


    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setRwJhStart(Date rwJhStart) {
        this.rwJhStart = rwJhStart;
    }

    public String getWcMan() {
        return wcMan;
    }

    public void setWcMan(String wcMan) {
        this.wcMan = wcMan;
    }

    @Transient
    private String rwTypeName,rwResourceName,rwProjectName,rwStageName,rwNodeName,rwJhManName,rwJjcdName,manName,rwJsManName;

    public String getRwTypeName() {
        return rwTypeName;
    }

    public void setRwTypeName(String rwTypeName) {
        this.rwTypeName = rwTypeName;
    }

    public String getRwResourceName() {
        return rwResourceName;
    }

    public void setRwResourceName(String rwResourceName) {
        this.rwResourceName = rwResourceName;
    }

    public String getRwJhManName() {
        return rwJhManName;
    }

    public void setRwJhManName(String rwJhManName) {
        this.rwJhManName = rwJhManName;
    }

    public String getRwNodeName() {
        return rwNodeName;
    }

    public void setRwNodeName(String rwNodeName) {
        this.rwNodeName = rwNodeName;
    }

    public String getRwStageName() {
        return rwStageName;
    }

    public void setRwStageName(String rwStageName) {
        this.rwStageName = rwStageName;
    }

    public String getRwProjectName() {
        return rwProjectName;
    }

    public void setRwProjectName(String rwProjectName) {
        this.rwProjectName = rwProjectName;
    }

    public String getRwJjcdName() {
        return rwJjcdName;
    }

    public void setRwJjcdName(String rwJjcdName) {
        this.rwJjcdName = rwJjcdName;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getRwJsManName() {
        return rwJsManName;
    }

    public void setRwJsManName(String rwJsManName) {
        this.rwJsManName = rwJsManName;
    }

    public String getRwCb() {
        return rwCb;
    }

    public void setRwCb(String rwCb) {
        this.rwCb = rwCb;
    }
}
