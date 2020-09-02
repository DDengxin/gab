package com.tengzhi.business.xt.dailyRoutine.expenseReport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_expense_report_cost")
public class EXtExpenseReportCost extends BaseModel {

    @Id
    private String bzMo;
    private String bzNote,bzCostCorp,bzCostDept,bzCostItemType,bzCostTypeValue,bzCostType,
            bzCostMoney,bzCostRemarks,dataMan,dataCorp;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date dataDate;
    @Transient
    private String _state;

    public String getBzMo() {
        return bzMo;
    }

    public void setBzMo(String bzMo) {
        this.bzMo = bzMo;
    }

    public String getBzNote() {
        return bzNote;
    }

    public void setBzNote(String bzNote) {
        this.bzNote = bzNote;
    }

    public String getBzCostCorp() {
        return bzCostCorp;
    }

    public void setBzCostCorp(String bzCostCorp) {
        this.bzCostCorp = bzCostCorp;
    }

    public String getBzCostDept() {
        return bzCostDept;
    }

    public void setBzCostDept(String bzCostDept) {
        this.bzCostDept = bzCostDept;
    }

    public String getBzCostItemType() {
        return bzCostItemType;
    }

    public void setBzCostItemType(String bzCostItemType) {
        this.bzCostItemType = bzCostItemType;
    }

    public String getBzCostTypeValue() {
        return bzCostTypeValue;
    }

    public void setBzCostTypeValue(String bzCostTypeValue) {
        this.bzCostTypeValue = bzCostTypeValue;
    }

    public String getBzCostType() {
        return bzCostType;
    }

    public void setBzCostType(String bzCostType) {
        this.bzCostType = bzCostType;
    }

    public String getBzCostMoney() {
        return bzCostMoney;
    }

    public void setBzCostMoney(String bzCostMoney) {
        this.bzCostMoney = bzCostMoney;
    }

    public String getBzCostRemarks() {
        return bzCostRemarks;
    }

    public void setBzCostRemarks(String bzCostRemarks) {
        this.bzCostRemarks = bzCostRemarks;
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

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }
}
