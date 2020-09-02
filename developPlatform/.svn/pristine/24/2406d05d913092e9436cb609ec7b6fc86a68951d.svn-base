package com.tengzhi.business.xt.dailyRoutine.expenseReport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "e_xt_expense_report_payment")
public class EXtExpenseReportPayment extends BaseModel {

    private String bzNote,bzPlanFlag,dataMan,dataCorp;
    @Id
    private String bzMo;
    private BigDecimal bzPlanMoney;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bzPlanRq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dataDate;

    @Transient
    private String _state;

    public String getBzNote() {
        return bzNote;
    }

    public void setBzNote(String bzNote) {
        this.bzNote = bzNote;
    }

    public String getBzPlanFlag() {
        return bzPlanFlag;
    }

    public void setBzPlanFlag(String bzPlanFlag) {
        this.bzPlanFlag = bzPlanFlag;
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

    public String getBzMo() {
        return bzMo;
    }

    public void setBzMo(String bzMo) {
        this.bzMo = bzMo;
    }

    public BigDecimal getBzPlanMoney() {
        return bzPlanMoney;
    }

    public void setBzPlanMoney(BigDecimal bzPlanMoney) {
        this.bzPlanMoney = bzPlanMoney;
    }

    public Date getBzPlanRq() {
        return bzPlanRq;
    }

    public void setBzPlanRq(Date bzPlanRq) {
        this.bzPlanRq = bzPlanRq;
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
