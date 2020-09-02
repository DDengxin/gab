package com.tengzhi.business.finance.capitalManager.capitalType.model;

import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "e_f_voucher_capitaltype")
public class EFVoucherCapitaltype  extends BaseModel {

  @Id
  private String fid = UUID.randomUUID().toString();
  private String fnumber;
  private String fname;
  private Integer fdeprmethod2,fusageyear,flifeperiods;
  private BigDecimal fsalvagerate;
  private BigInteger ffaacctid,fdepracctid;
  private String fremark;
  private String dataCorp;



  @Transient
  private String fdeprmethod2Name,ffaacctidName,fdepracctidName;

  public String getFid() {
    return fid;
  }

  public void setFid(String fid) {
    this.fid = fid;
  }

  public String getFnumber() {
    return fnumber;
  }

  public void setFnumber(String fnumber) {
    this.fnumber = fnumber;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public Integer getFdeprmethod2() {
    return fdeprmethod2;
  }

  public void setFdeprmethod2(Integer fdeprmethod2) {
    this.fdeprmethod2 = fdeprmethod2;
  }

  public Integer getFusageyear() {
    return fusageyear;
  }

  public void setFusageyear(Integer fusageyear) {
    this.fusageyear = fusageyear;
  }

  public Integer getFlifeperiods() {
    return flifeperiods;
  }

  public void setFlifeperiods(Integer flifeperiods) {
    this.flifeperiods = flifeperiods;
  }

  public BigDecimal getFsalvagerate() {
    return fsalvagerate;
  }

  public void setFsalvagerate(BigDecimal fsalvagerate) {
    this.fsalvagerate = fsalvagerate;
  }

  public BigInteger getFfaacctid() {
    return ffaacctid;
  }

  public void setFfaacctid(BigInteger ffaacctid) {
    this.ffaacctid = ffaacctid;
  }

  public BigInteger getFdepracctid() {
    return fdepracctid;
  }

  public void setFdepracctid(BigInteger fdepracctid) {
    this.fdepracctid = fdepracctid;
  }

  public String getFremark() {
    return fremark;
  }

  public void setFremark(String fremark) {
    this.fremark = fremark;
  }

  public String getDataCorp() {
    return dataCorp;
  }

  public void setDataCorp(String dataCorp) {
    this.dataCorp = dataCorp;
  }

  public String getFdeprmethod2Name() {
    return fdeprmethod2Name;
  }

  public void setFdeprmethod2Name(String fdeprmethod2Name) {
    this.fdeprmethod2Name = fdeprmethod2Name;
  }

  public String getFfaacctidName() {
    return ffaacctidName;
  }

  public void setFfaacctidName(String ffaacctidName) {
    this.ffaacctidName = ffaacctidName;
  }

  public String getFdepracctidName() {
    return fdepracctidName;
  }

  public void setFdepracctidName(String fdepracctidName) {
    this.fdepracctidName = fdepracctidName;
  }
}
