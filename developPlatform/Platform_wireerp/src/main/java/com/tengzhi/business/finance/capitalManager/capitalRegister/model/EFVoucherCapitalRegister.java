package com.tengzhi.business.finance.capitalManager.capitalRegister.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "e_f_voucher_capital_register")
public class EFVoucherCapitalRegister {

  @Id
  private String fcardid;
  private String dataCorp;
  private String ftypeid,fdeptid,fnumber;
  private Integer fstate,flifeperiods,ffaacctitemid;
  private String fname;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date fbegindate;
  private String fremark;
  private String fdeprmethod;
  private BigInteger ffaacctid,fdepracctid,ffeeacctid,ftaxaccountid,ffacreditaccountid,fimpairacctid2,fimpairacctid,ffacleanaccountid;
  private BigDecimal forgval,fsalvagerate,fusageperiods,forgcredit,fdeprservice,fperdepreciation,ftaxamount,yjcz,qcjz;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createtime;
  private String man,deviceType,parentId;


  public String getFcardid() {
    return fcardid;
  }

  public void setFcardid(String fcardid) {
    this.fcardid = fcardid;
  }

  public String getDataCorp() {
    return dataCorp;
  }

  public void setDataCorp(String dataCorp) {
    this.dataCorp = dataCorp;
  }

  public String getFtypeid() {
    return ftypeid;
  }

  public void setFtypeid(String ftypeid) {
    this.ftypeid = ftypeid;
  }

  public String getFnumber() {
    return fnumber;
  }

  public void setFnumber(String fnumber) {
    this.fnumber = fnumber;
  }

  public String getFdeptid() {
    return fdeptid;
  }

  public void setFdeptid(String fdeptid) {
    this.fdeptid = fdeptid;
  }

  public Integer getFstate() {
    return fstate;
  }

  public void setFstate(Integer fstate) {
    this.fstate = fstate;
  }

  public Integer getFlifeperiods() {
    return flifeperiods;
  }

  public void setFlifeperiods(Integer flifeperiods) {
    this.flifeperiods = flifeperiods;
  }

  public Integer getFfaacctitemid() {
    return ffaacctitemid;
  }

  public void setFfaacctitemid(Integer ffaacctitemid) {
    this.ffaacctitemid = ffaacctitemid;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public Date getFbegindate() {
    return fbegindate;
  }

  public void setFbegindate(Date fbegindate) {
    this.fbegindate = fbegindate;
  }

  public String getFremark() {
    return fremark;
  }

  public void setFremark(String fremark) {
    this.fremark = fremark;
  }

  public String getFdeprmethod() {
    return fdeprmethod;
  }

  public void setFdeprmethod(String fdeprmethod) {
    this.fdeprmethod = fdeprmethod;
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

  public BigInteger getFfeeacctid() {
    return ffeeacctid;
  }

  public void setFfeeacctid(BigInteger ffeeacctid) {
    this.ffeeacctid = ffeeacctid;
  }

  public BigInteger getFtaxaccountid() {
    return ftaxaccountid;
  }

  public void setFtaxaccountid(BigInteger ftaxaccountid) {
    this.ftaxaccountid = ftaxaccountid;
  }

  public BigInteger getFfacreditaccountid() {
    return ffacreditaccountid;
  }

  public void setFfacreditaccountid(BigInteger ffacreditaccountid) {
    this.ffacreditaccountid = ffacreditaccountid;
  }

  public BigInteger getFimpairacctid2() {
    return fimpairacctid2;
  }

  public void setFimpairacctid2(BigInteger fimpairacctid2) {
    this.fimpairacctid2 = fimpairacctid2;
  }

  public BigInteger getFimpairacctid() {
    return fimpairacctid;
  }

  public void setFimpairacctid(BigInteger fimpairacctid) {
    this.fimpairacctid = fimpairacctid;
  }

  public BigInteger getFfacleanaccountid() {
    return ffacleanaccountid;
  }

  public void setFfacleanaccountid(BigInteger ffacleanaccountid) {
    this.ffacleanaccountid = ffacleanaccountid;
  }

  public BigDecimal getForgval() {
    return forgval;
  }

  public void setForgval(BigDecimal forgval) {
    this.forgval = forgval;
  }

  public BigDecimal getFsalvagerate() {
    return fsalvagerate;
  }

  public void setFsalvagerate(BigDecimal fsalvagerate) {
    this.fsalvagerate = fsalvagerate;
  }

  public BigDecimal getFusageperiods() {
    return fusageperiods;
  }

  public void setFusageperiods(BigDecimal fusageperiods) {
    this.fusageperiods = fusageperiods;
  }

  public BigDecimal getForgcredit() {
    return forgcredit;
  }

  public void setForgcredit(BigDecimal forgcredit) {
    this.forgcredit = forgcredit;
  }

  public BigDecimal getFdeprservice() {
    return fdeprservice;
  }

  public void setFdeprservice(BigDecimal fdeprservice) {
    this.fdeprservice = fdeprservice;
  }

  public BigDecimal getFperdepreciation() {
    return fperdepreciation;
  }

  public void setFperdepreciation(BigDecimal fperdepreciation) {
    this.fperdepreciation = fperdepreciation;
  }

  public BigDecimal getFtaxamount() {
    return ftaxamount;
  }

  public void setFtaxamount(BigDecimal ftaxamount) {
    this.ftaxamount = ftaxamount;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public String getMan() {
    return man;
  }

  public void setMan(String man) {
    this.man = man;
  }

  public BigDecimal getYjcz() {
    return yjcz;
  }

  public void setYjcz(BigDecimal yjcz) {
    this.yjcz = yjcz;
  }

  public BigDecimal getQcjz() {
    return qcjz;
  }

  public void setQcjz(BigDecimal qcjz) {
    this.qcjz = qcjz;
  }

  @Transient
  private String ftypeidName,fdeptidName,fdeprmethodName,fstateName,parentIdName;

  public String getFtypeidName() {
    return ftypeidName;
  }

  public void setFtypeidName(String ftypeidName) {
    this.ftypeidName = ftypeidName;
  }

  public String getFdeptidName() {
    return fdeptidName;
  }

  public void setFdeptidName(String fdeptidName) {
    this.fdeptidName = fdeptidName;
  }

  public String getFdeprmethodName() {
    return fdeprmethodName;
  }

  public void setFdeprmethodName(String fdeprmethodName) {
    this.fdeprmethodName = fdeprmethodName;
  }

  public String getFstateName() {
    return fstateName;
  }

  public void setFstateName(String fstateName) {
    this.fstateName = fstateName;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getParentIdName() {
    return parentIdName;
  }

  public void setParentIdName(String parentIdName) {
    this.parentIdName = parentIdName;
  }
}