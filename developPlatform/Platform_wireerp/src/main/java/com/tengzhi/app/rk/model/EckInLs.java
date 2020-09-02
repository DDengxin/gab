package com.tengzhi.app.rk.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "e_ck_in_ls")
public class EckInLs implements Serializable {
    @Id
    private String inNote;
    @Id
    private String inPack;
    private String inCustomer,inAct,inCode,inBpack,inContract,inKfcontract,inKfcode,inLib,inCheckflag,inMonth,inType,inLuono,inVnote,inBz,inHs,inLyr;
    private String dataMan,dataCorp,inFlag,inMan,brand,inCklib;
    private BigDecimal inSl,inZl,inPrice,inTax,inRate,inJs;




    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inRq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date dataDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date inDate;

    private String dataAct;
    private String inKw;

    @Transient
    private String _state,id;

    public String getInNote() {
        return inNote;
    }

    public void setInNote(String inNote) {
        this.inNote = inNote;
    }

    public String getInPack() {
        return inPack;
    }

    public void setInPack(String inPack) {
        this.inPack = inPack;
    }

    public String getInCustomer() {
        return inCustomer;
    }

    public void setInCustomer(String inCustomer) {
        this.inCustomer = inCustomer;
    }

    public String getInAct() {
        return inAct;
    }

    public void setInAct(String inAct) {
        this.inAct = inAct;
    }

    public String getInCode() {
        return inCode;
    }

    public void setInCode(String inCode) {
        this.inCode = inCode;
    }

    public String getInBpack() {
        return inBpack;
    }

    public void setInBpack(String inBpack) {
        this.inBpack = inBpack;
    }

    public String getInContract() {
        return inContract;
    }

    public void setInContract(String inContract) {
        this.inContract = inContract;
    }

    public String getInKfcontract() {
        return inKfcontract;
    }

    public void setInKfcontract(String inKfcontract) {
        this.inKfcontract = inKfcontract;
    }

    public String getInKfcode() {
        return inKfcode;
    }

    public void setInKfcode(String inKfcode) {
        this.inKfcode = inKfcode;
    }

    public String getInLib() {
        return inLib;
    }

    public void setInLib(String inLib) {
        this.inLib = inLib;
    }

    public String getInCheckflag() {
        return inCheckflag;
    }

    public void setInCheckflag(String inCheckflag) {
        this.inCheckflag = inCheckflag;
    }

    public String getInMonth() {
        return inMonth;
    }

    public void setInMonth(String inMonth) {
        this.inMonth = inMonth;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public String getInLuono() {
        return inLuono;
    }

    public void setInLuono(String inLuono) {
        this.inLuono = inLuono;
    }

    public String getInVnote() {
        return inVnote;
    }

    public void setInVnote(String inVnote) {
        this.inVnote = inVnote;
    }

    public String getInBz() {
        return inBz;
    }

    public void setInBz(String inBz) {
        this.inBz = inBz;
    }

    public String getInHs() {
        return inHs;
    }

    public void setInHs(String inHs) {
        this.inHs = inHs;
    }

    public String getInLyr() {
        return inLyr;
    }

    public void setInLyr(String inLyr) {
        this.inLyr = inLyr;
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

    public String getInFlag() {
        return inFlag;
    }

    public void setInFlag(String inFlag) {
        this.inFlag = inFlag;
    }

    public String getInMan() {
        return inMan;
    }

    public void setInMan(String inMan) {
        this.inMan = inMan;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getInCklib() {
        return inCklib;
    }

    public void setInCklib(String inCklib) {
        this.inCklib = inCklib;
    }

    public BigDecimal getInSl() {
        return inSl;
    }

    public void setInSl(BigDecimal inSl) {
        this.inSl = inSl;
    }

    public BigDecimal getInZl() {
        return inZl;
    }

    public void setInZl(BigDecimal inZl) {
        this.inZl = inZl;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public BigDecimal getInTax() {
        return inTax;
    }

    public void setInTax(BigDecimal inTax) {
        this.inTax = inTax;
    }

    public BigDecimal getInRate() {
        return inRate;
    }

    public void setInRate(BigDecimal inRate) {
        this.inRate = inRate;
    }

    public BigDecimal getInJs() {
        return inJs;
    }

    public void setInJs(BigDecimal inJs) {
        this.inJs = inJs;
    }

    public Date getInRq() {
        return inRq;
    }

    public void setInRq(Date inRq) {
        this.inRq = inRq;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataAct() {
        return dataAct;
    }

    public void setDataAct(String dataAct) {
        this.dataAct = dataAct;
    }

    public String getInKw() {
        return inKw;
    }

    public void setInKw(String inKw) {
        this.inKw = inKw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EckInLs eckInLs = (EckInLs) o;
        return Objects.equals(inNote, eckInLs.inNote) &&
                Objects.equals(inPack, eckInLs.inPack) &&
                Objects.equals(inCustomer, eckInLs.inCustomer) &&
                Objects.equals(inAct, eckInLs.inAct) &&
                Objects.equals(inCode, eckInLs.inCode) &&
                Objects.equals(inBpack, eckInLs.inBpack) &&
                Objects.equals(inContract, eckInLs.inContract) &&
                Objects.equals(inKfcontract, eckInLs.inKfcontract) &&
                Objects.equals(inKfcode, eckInLs.inKfcode) &&
                Objects.equals(inLib, eckInLs.inLib) &&
                Objects.equals(inCheckflag, eckInLs.inCheckflag) &&
                Objects.equals(inMonth, eckInLs.inMonth) &&
                Objects.equals(inType, eckInLs.inType) &&
                Objects.equals(inLuono, eckInLs.inLuono) &&
                Objects.equals(inVnote, eckInLs.inVnote) &&
                Objects.equals(inBz, eckInLs.inBz) &&
                Objects.equals(inHs, eckInLs.inHs) &&
                Objects.equals(inLyr, eckInLs.inLyr) &&
                Objects.equals(dataMan, eckInLs.dataMan) &&
                Objects.equals(dataCorp, eckInLs.dataCorp) &&
                Objects.equals(inFlag, eckInLs.inFlag) &&
                Objects.equals(inMan, eckInLs.inMan) &&
                Objects.equals(brand, eckInLs.brand) &&
                Objects.equals(inCklib, eckInLs.inCklib) &&
                Objects.equals(inSl, eckInLs.inSl) &&
                Objects.equals(inZl, eckInLs.inZl) &&
                Objects.equals(inPrice, eckInLs.inPrice) &&
                Objects.equals(inTax, eckInLs.inTax) &&
                Objects.equals(inRate, eckInLs.inRate) &&
                Objects.equals(inJs, eckInLs.inJs) &&
                Objects.equals(inRq, eckInLs.inRq) &&
                Objects.equals(dataDate, eckInLs.dataDate) &&
                Objects.equals(inDate, eckInLs.inDate) &&
                Objects.equals(dataAct, eckInLs.dataAct) &&
                Objects.equals(inKw, eckInLs.inKw) &&
                Objects.equals(_state, eckInLs._state) &&
                Objects.equals(id, eckInLs.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inNote, inPack, inCustomer, inAct, inCode, inBpack, inContract, inKfcontract, inKfcode, inLib, inCheckflag, inMonth, inType, inLuono, inVnote, inBz, inHs, inLyr, dataMan, dataCorp, inFlag, inMan, brand, inCklib, inSl, inZl, inPrice, inTax, inRate, inJs, inRq, dataDate, inDate, dataAct, inKw, _state, id);
    }
}

