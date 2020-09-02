package com.tengzhi.business.xt.transaction.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_swsq")
public class Transaction {

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date swRq;
    @Id
    private String swNote;
    private String swDept;
    private String swMan;
    private String swType;
    private String swStype;
    private String swText;
    private String swFile;
    private String dataMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDate;
    private String dataCorp;
    private String swFlag;
    @Transient
    private String manName;

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public Date getSwRq() {
        return swRq;
    }

    public void setSwRq(Date swRq) {
        this.swRq = swRq;
    }

    public String getSwNote() {
        return swNote;
    }

    public void setSwNote(String swNote) {
        this.swNote = swNote;
    }

    public String getSwDept() {
        return swDept;
    }

    public void setSwDept(String swDept) {
        this.swDept = swDept;
    }

    public String getSwMan() {
        return swMan;
    }

    public void setSwMan(String swMan) {
        this.swMan = swMan;
    }

    public String getSwType() {
        return swType;
    }

    public void setSwType(String swType) {
        this.swType = swType;
    }

    public String getSwStype() {
        return swStype;
    }

    public void setSwStype(String swStype) {
        this.swStype = swStype;
    }

    public String getSwText() {
        return swText;
    }

    public void setSwText(String swText) {
        this.swText = swText;
    }

    public String getSwFile() {
        return swFile;
    }

    public void setSwFile(String swFile) {
        this.swFile = swFile;
    }

    public String getDataMan() {
        return dataMan;
    }

    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    public String getSwFlag() {
        return swFlag;
    }

    public void setSwFlag(String swFlag) {
        this.swFlag = swFlag;
    }
}
