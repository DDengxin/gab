package com.tengzhi.business.xt.dailyRoutine.expenseReport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "e_xt_expense_report")
public class EXtExpenseReport extends BaseModel {

    private String bzNote;
    private String bzMan,bzDept,bzCorp,bzExpenseType,bzCurrency,bzMethod,bzUnit,
           bzInvoiceType,bzRemarks,bzInformation,dataMan,dataCorp,bzInvoiceNumber;

    private BigDecimal bzInvoiceMoney,bzMoney,bzBalance,bzCostMoney;

    private Integer bzInvoiceAmount;
    @Id
    private String bzMo;
    private String bzCostCorp,bzCostDept,bzCostItemType,bzCostTypeValue,bzCostType,
            bzCostRemarks;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bzRq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date dataDate;


    @Transient
    private String _state;



    @Transient
    private String bzManName,bzUnitName;

    public String getBzNote() {
        return bzNote;
    }

    public void setBzNote(String bzNote) {
        this.bzNote = bzNote;
    }

    public String getBzMan() {
        return bzMan;
    }

    public void setBzMan(String bzMan) {
        this.bzMan = bzMan;
    }

    public String getBzDept() {
        return bzDept;
    }

    public void setBzDept(String bzDept) {
        this.bzDept = bzDept;
    }

    public String getBzExpenseType() {
        return bzExpenseType;
    }

    public void setBzExpenseType(String bzExpenseType) {
        this.bzExpenseType = bzExpenseType;
    }

    public String getBzCurrency() {
        return bzCurrency;
    }

    public void setBzCurrency(String bzCurrency) {
        this.bzCurrency = bzCurrency;
    }

    public String getBzMethod() {
        return bzMethod;
    }

    public void setBzMethod(String bzMethod) {
        this.bzMethod = bzMethod;
    }

    public String getBzUnit() {
        return bzUnit;
    }

    public void setBzUnit(String bzUnit) {
        this.bzUnit = bzUnit;
    }

    public String getBzInvoiceType() {
        return bzInvoiceType;
    }

    public void setBzInvoiceType(String bzInvoiceType) {
        this.bzInvoiceType = bzInvoiceType;
    }

    public BigDecimal getBzBalance() {
        return bzBalance;
    }

    public void setBzBalance(BigDecimal bzBalance) {
        this.bzBalance = bzBalance;
    }

    public String getBzRemarks() {
        return bzRemarks;
    }

    public void setBzRemarks(String bzRemarks) {
        this.bzRemarks = bzRemarks;
    }

    public String getBzInformation() {
        return bzInformation;
    }

    public void setBzInformation(String bzInformation) {
        this.bzInformation = bzInformation;
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

    public BigDecimal getBzCostMoney() {
        return bzCostMoney;
    }

    public void setBzCostMoney(BigDecimal bzCostMoney) {
        this.bzCostMoney = bzCostMoney;
    }

    public Integer getBzInvoiceAmount() {
        return bzInvoiceAmount;
    }

    public void setBzInvoiceAmount(Integer bzInvoiceAmount) {
        this.bzInvoiceAmount = bzInvoiceAmount;
    }

    public BigDecimal getBzInvoiceMoney() {
        return bzInvoiceMoney;
    }

    public void setBzInvoiceMoney(BigDecimal bzInvoiceMoney) {
        this.bzInvoiceMoney = bzInvoiceMoney;
    }

    public BigDecimal getBzMoney() {
        return bzMoney;
    }

    public void setBzMoney(BigDecimal bzMoney) {
        this.bzMoney = bzMoney;
    }

    public String getBzInvoiceNumber() {
        return bzInvoiceNumber;
    }

    public void setBzInvoiceNumber(String bzInvoiceNumber) {
        this.bzInvoiceNumber = bzInvoiceNumber;
    }

    public Date getBzRq() {
        return bzRq;
    }

    public void setBzRq(Date bzRq) {
        this.bzRq = bzRq;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getBzMo() {
        return bzMo;
    }

    public void setBzMo(String bzMo) {
        this.bzMo = bzMo;
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



    public String getBzCostRemarks() {
        return bzCostRemarks;
    }

    public void setBzCostRemarks(String bzCostRemarks) {
        this.bzCostRemarks = bzCostRemarks;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getBzManName() {
        return bzManName;
    }

    public void setBzManName(String bzManName) {
        this.bzManName = bzManName;
    }

    public String getBzUnitName() {
        return bzUnitName;
    }

    public void setBzUnitName(String bzUnitName) {
        this.bzUnitName = bzUnitName;
    }

    public String getBzCorp() {
        return bzCorp;
    }

    public void setBzCorp(String bzCorp) {
        this.bzCorp = bzCorp;
    }
}