package com.tengzhi.business.finance.reportItem.model;

import com.tengzhi.business.mesGz.gzck.vo.EckOut;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "e_f_voucher_acctreportitem")
public class EFVoucherAcctreportitem {

  @Id
  private String sid = UUID.randomUUID().toString();
  private String dataCorp;
  private BigInteger freportid;
  private String freportitem;
  private BigInteger faccountid;
  private String ftype;
  private String fop;
  private BigInteger facctyearperiod;
  private BigInteger fcustomid;
  private BigInteger fsupplierid;
  private BigInteger fdeptid;
  private BigInteger fempid;
  private BigInteger finventoryid;
  private BigInteger fprojectid;
  private BigInteger fitemclassid;
  private BigInteger fitemid;
  private BigInteger flimited;


  public String getDataCorp() {
    return dataCorp;
  }

  public void setDataCorp(String dataCorp) {
    this.dataCorp = dataCorp;
  }


  public BigInteger getFreportid() {
    return freportid;
  }

  public void setFreportid(BigInteger freportid) {
    this.freportid = freportid;
  }


  public String getFreportitem() {
    return freportitem;
  }

  public void setFreportitem(String freportitem) {
    this.freportitem = freportitem;
  }


  public BigInteger getFaccountid() {
    return faccountid;
  }

  public void setFaccountid(BigInteger faccountid) {
    this.faccountid = faccountid;
  }


  public String getFtype() {
    return ftype;
  }

  public void setFtype(String ftype) {
    this.ftype = ftype;
  }


  public String getFop() {
    return fop;
  }

  public void setFop(String fop) {
    this.fop = fop;
  }


  public BigInteger getFacctyearperiod() {
    return facctyearperiod;
  }

  public void setFacctyearperiod(BigInteger facctyearperiod) {
    this.facctyearperiod = facctyearperiod;
  }


  public BigInteger getFcustomid() {
    return fcustomid;
  }

  public void setFcustomid(BigInteger fcustomid) {
    this.fcustomid = fcustomid;
  }


  public BigInteger getFsupplierid() {
    return fsupplierid;
  }

  public void setFsupplierid(BigInteger fsupplierid) {
    this.fsupplierid = fsupplierid;
  }


  public BigInteger getFdeptid() {
    return fdeptid;
  }

  public void setFdeptid(BigInteger fdeptid) {
    this.fdeptid = fdeptid;
  }


  public BigInteger getFempid() {
    return fempid;
  }

  public void setFempid(BigInteger fempid) {
    this.fempid = fempid;
  }


  public BigInteger getFinventoryid() {
    return finventoryid;
  }

  public void setFinventoryid(BigInteger finventoryid) {
    this.finventoryid = finventoryid;
  }


  public BigInteger getFprojectid() {
    return fprojectid;
  }

  public void setFprojectid(BigInteger fprojectid) {
    this.fprojectid = fprojectid;
  }


  public BigInteger getFitemclassid() {
    return fitemclassid;
  }

  public void setFitemclassid(BigInteger fitemclassid) {
    this.fitemclassid = fitemclassid;
  }


  public BigInteger getFitemid() {
    return fitemid;
  }

  public void setFitemid(BigInteger fitemid) {
    this.fitemid = fitemid;
  }


  public BigInteger getFlimited() {
    return flimited;
  }

  public void setFlimited(BigInteger flimited) {
    this.flimited = flimited;
  }



}
