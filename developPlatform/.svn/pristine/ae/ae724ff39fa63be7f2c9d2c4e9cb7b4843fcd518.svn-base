package com.tengzhi.business.project.projectArchives.projectTreams.model;

import com.tengzhi.base.jpa.model.BaseModel;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.*;

@Entity
@Table(name = "e_xm_xmxz")
public class EXmXmxz extends BaseModel {

    @Id
    private String xzId = UUID.randomUUID().toString();
    private String xmId;
    private String workId;
    private String workName;
    private String xmRole;
    private String workTel;
    private String workMail;
    private String workSm;
    private String dataCorp;

    public String getXmId() {
        return xmId;
    }

    public void setXmId(String xmId) {
        this.xmId = xmId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public String getXzId() {
        return xzId;
    }

    public void setXzId(String xzId) {
        this.xzId = xzId;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getXmRole() {
        return xmRole;
    }

    public void setXmRole(String xmRole) {
        this.xmRole = xmRole;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getWorkMail() {
        return workMail;
    }

    public void setWorkMail(String workMail) {
        this.workMail = workMail;
    }

    public String getWorkSm() {
        return workSm;
    }

    public void setWorkSm(String workSm) {
        this.workSm = workSm;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Transient
    private String workIdName,xmName,dataCorpName,xmRoleName;

    public String getWorkIdName() {
        return workIdName;
    }

    public void setWorkIdName(String workIdName) {
        this.workIdName = workIdName;
    }


    public String getXmName() {
        return xmName;
    }

    public void setXmName(String xmName) {
        this.xmName = xmName;
    }

    public String getDataCorpName() {
        return dataCorpName;
    }

    public void setDataCorpName(String dataCorpName) {
        this.dataCorpName = dataCorpName;
    }

    public String getXmRoleName() {
        return xmRoleName;
    }

    public void setXmRoleName(String xmRoleName) {
        this.xmRoleName = xmRoleName;
    }
}
