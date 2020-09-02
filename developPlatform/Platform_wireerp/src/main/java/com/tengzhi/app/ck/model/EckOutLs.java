package com.tengzhi.app.ck.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "e_ck_out_ls")
public class EckOutLs implements Serializable {
    @Id
    private String outNote;
    @Id
    private String outPack;
    private String outCustomer,outAct,outCode,outBpack,outContract,outKfcontract,outKfcode,outLib,outCheckflag,outMonth,outType,outLuono,outVnote,outBz,outHs,outLyr;
    private String dataMan,dataCorp,outFlag,outMan,brand,outCklib;
    private BigDecimal outSl,outZl,outPrice,outTax,outRate,outJs;




    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outRq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date dataDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date outDate;

    private String dataAct;

    @Transient
    private String _state,id;
    public String getOutNote() {
        return outNote;
    }
    public void setOutNote(String outNote) {
        this.outNote = outNote;
    }
    public String getOutPack() {
        return outPack;
    }
    public void setOutPack(String outPack) {
        this.outPack = outPack;
    }
    public String getOutCustomer() {
        return outCustomer;
    }
    public void setOutCustomer(String outCustomer) {
        this.outCustomer = outCustomer;
    }
    public String getOutAct() {
        return outAct;
    }
    public void setOutAct(String outAct) {
        this.outAct = outAct;
    }
    public String getOutCode() {
        return outCode;
    }
    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }
    public String getOutBpack() {
        return outBpack;
    }
    public void setOutBpack(String outBpack) {
        this.outBpack = outBpack;
    }
    public String getOutContract() {
        return outContract;
    }
    public void setOutContract(String outContract) {
        this.outContract = outContract;
    }
    public String getOutKfcontract() {
        return outKfcontract;
    }
    public void setOutKfcontract(String outKfcontract) {
        this.outKfcontract = outKfcontract;
    }
    public String getOutKfcode() {
        return outKfcode;
    }
    public void setOutKfcode(String outKfcode) {
        this.outKfcode = outKfcode;
    }
    public String getOutLib() {
        return outLib;
    }
    public void setOutLib(String outLib) {
        this.outLib = outLib;
    }
    public String getOutCheckflag() {
        return outCheckflag;
    }
    public void setOutCheckflag(String outCheckflag) {
        this.outCheckflag = outCheckflag;
    }
    public String getOutMonth() {
        return outMonth;
    }
    public void setOutMonth(String outMonth) {
        this.outMonth = outMonth;
    }
    public String getOutType() {
        return outType;
    }
    public void setOutType(String outType) {
        this.outType = outType;
    }
    public String getOutLuono() {
        return outLuono;
    }
    public void setOutLuono(String outLuono) {
        this.outLuono = outLuono;
    }
    public String getOutVnote() {
        return outVnote;
    }
    public void setOutVnote(String outVnote) {
        this.outVnote = outVnote;
    }
    public String getOutBz() {
        return outBz;
    }
    public void setOutBz(String outBz) {
        this.outBz = outBz;
    }
    public String getOutHs() {
        return outHs;
    }
    public void setOutHs(String outHs) {
        this.outHs = outHs;
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
    public String getOutFlag() {
        return outFlag;
    }
    public void setOutFlag(String outFlag) {
        this.outFlag = outFlag;
    }




    public String getOutLyr() {
        return outLyr;
    }
    public void setOutLyr(String outLyr) {
        this.outLyr = outLyr;
    }
    public BigDecimal getOutSl() {
        return outSl;
    }
    public void setOutSl(BigDecimal outSl) {
        this.outSl = outSl;
    }
    public BigDecimal getOutZl() {
        return outZl;
    }
    public void setOutZl(BigDecimal outZl) {
        this.outZl = outZl;
    }
    public BigDecimal getOutPrice() {
        return outPrice;
    }
    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }
    public BigDecimal getOutTax() {
        return outTax;
    }
    public void setOutTax(BigDecimal outTax) {
        this.outTax = outTax;
    }
    public BigDecimal getOutRate() {
        return outRate;
    }
    public void setOutRate(BigDecimal outRate) {
        this.outRate = outRate;
    }
    public BigDecimal getOutJs() {
        return outJs;
    }
    public void setOutJs(BigDecimal outJs) {
        this.outJs = outJs;
    }
    public Date getOutRq() {
        return outRq;
    }
    public void setOutRq(Date outRq) {
        this.outRq = outRq;
    }
    public Date getDataDate() {
        return dataDate;
    }
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOutCklib() {
        return outCklib;
    }

    public void setOutCklib(String outCklib) {
        this.outCklib = outCklib;
    }

    public String getOutMan() {
        return outMan;
    }

    public void setOutMan(String outMan) {
        this.outMan = outMan;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getDataAct() {
        return dataAct;
    }

    public void setDataAct(String dataAct) {
        this.dataAct = dataAct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EckOutLs eckOutLs = (EckOutLs) o;
        return Objects.equals(outNote, eckOutLs.outNote) &&
                Objects.equals(outPack, eckOutLs.outPack) &&
                Objects.equals(outCustomer, eckOutLs.outCustomer) &&
                Objects.equals(outAct, eckOutLs.outAct) &&
                Objects.equals(outCode, eckOutLs.outCode) &&
                Objects.equals(outBpack, eckOutLs.outBpack) &&
                Objects.equals(outContract, eckOutLs.outContract) &&
                Objects.equals(outKfcontract, eckOutLs.outKfcontract) &&
                Objects.equals(outKfcode, eckOutLs.outKfcode) &&
                Objects.equals(outLib, eckOutLs.outLib) &&
                Objects.equals(outCheckflag, eckOutLs.outCheckflag) &&
                Objects.equals(outMonth, eckOutLs.outMonth) &&
                Objects.equals(outType, eckOutLs.outType) &&
                Objects.equals(outLuono, eckOutLs.outLuono) &&
                Objects.equals(outVnote, eckOutLs.outVnote) &&
                Objects.equals(outBz, eckOutLs.outBz) &&
                Objects.equals(outHs, eckOutLs.outHs) &&
                Objects.equals(outLyr, eckOutLs.outLyr) &&
                Objects.equals(dataMan, eckOutLs.dataMan) &&
                Objects.equals(dataCorp, eckOutLs.dataCorp) &&
                Objects.equals(outFlag, eckOutLs.outFlag) &&
                Objects.equals(outMan, eckOutLs.outMan) &&
                Objects.equals(brand, eckOutLs.brand) &&
                Objects.equals(outCklib, eckOutLs.outCklib) &&
                Objects.equals(outSl, eckOutLs.outSl) &&
                Objects.equals(outZl, eckOutLs.outZl) &&
                Objects.equals(outPrice, eckOutLs.outPrice) &&
                Objects.equals(outTax, eckOutLs.outTax) &&
                Objects.equals(outRate, eckOutLs.outRate) &&
                Objects.equals(outJs, eckOutLs.outJs) &&
                Objects.equals(outRq, eckOutLs.outRq) &&
                Objects.equals(dataDate, eckOutLs.dataDate) &&
                Objects.equals(outDate, eckOutLs.outDate) &&
                Objects.equals(dataAct, eckOutLs.dataAct) &&
                Objects.equals(_state, eckOutLs._state) &&
                Objects.equals(id, eckOutLs.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outNote, outPack, outCustomer, outAct, outCode, outBpack, outContract, outKfcontract, outKfcode, outLib, outCheckflag, outMonth, outType, outLuono, outVnote, outBz, outHs, outLyr, dataMan, dataCorp, outFlag, outMan, brand, outCklib, outSl, outZl, outPrice, outTax, outRate, outJs, outRq, dataDate, outDate, dataAct, _state, id);
    }
}
