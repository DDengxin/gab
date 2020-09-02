package com.tengzhi.app.cf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "e_ck_pack_scan")

public class ECkPackScan {
    @Id
    private String pack;
    private String bpack,code,contract,dataMan,lib,kw,customer,flag,dataAct,newKw;
    private BigDecimal sl,js,zl;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:DD",timezone="GMT+8")
    private Date dataRq;

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getBpack() {
        return bpack;
    }

    public void setBpack(String bpack) {
        this.bpack = bpack;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getDataMan() {
        return dataMan;
    }

    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDataAct() {
        return dataAct;
    }

    public void setDataAct(String dataAct) {
        this.dataAct = dataAct;
    }

    public String getNewKw() {
        return newKw;
    }

    public void setNewKw(String newKw) {
        this.newKw = newKw;
    }

    public BigDecimal getSl() {
        return sl;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public BigDecimal getJs() {
        return js;
    }

    public void setJs(BigDecimal js) {
        this.js = js;
    }

    public BigDecimal getZl() {
        return zl;
    }

    public void setZl(BigDecimal zl) {
        this.zl = zl;
    }

    public Date getDataRq() {
        return dataRq;
    }

    public void setDataRq(Date dataRq) {
        this.dataRq = dataRq;
    }
}
