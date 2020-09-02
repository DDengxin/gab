package com.tengzhi.business.xt.dailyRoutine.sealApplication.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: czf
 * @Date:2020-08-20 18:59
 */
@Entity
@Table(name = "e_xt_seal_application")
public class EXtSealApplication {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq;
    private String useCorp;
    private String useDept;
    private String deliverDept;
    private String isOut;
    private String outAddr;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date estimatedTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date realTime;
    private String sealPage;
    private String sealType;
    private String useCategory;
    private String useDescription;
    private String flag;
    private String dataMan;
    private String sealCorp;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getUseCorp() {
        return useCorp;
    }

    public void setUseCorp(String useCorp) {
        this.useCorp = useCorp;
    }

    public String getUseDept() {
        return useDept;
    }

    public void setUseDept(String useDept) {
        this.useDept = useDept;
    }

    public String getDeliverDept() {
        return deliverDept;
    }

    public void setDeliverDept(String deliverDept) {
        this.deliverDept = deliverDept;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public String getOutAddr() {
        return outAddr;
    }

    public void setOutAddr(String outAddr) {
        this.outAddr = outAddr;
    }

    public Date getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Date estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Date getRealTime() {
        return realTime;
    }

    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    public String getSealPage() {
        return sealPage;
    }

    public void setSealPage(String sealPage) {
        this.sealPage = sealPage;
    }

    public String getSealType() {
        return sealType;
    }

    public void setSealType(String sealType) {
        this.sealType = sealType;
    }

    public String getUseCategory() {
        return useCategory;
    }

    public void setUseCategory(String useCategory) {
        this.useCategory = useCategory;
    }

    public String getUseDescription() {
        return useDescription;
    }

    public void setUseDescription(String useDescription) {
        this.useDescription = useDescription;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDataMan() {
        return dataMan;
    }

    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    public String getSealCorp() {
        return sealCorp;
    }

    public void setSealCorp(String sealCorp) {
        this.sealCorp = sealCorp;
    }
}
