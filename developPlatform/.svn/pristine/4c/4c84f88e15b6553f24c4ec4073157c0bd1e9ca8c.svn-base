package com.tengzhi.business.finance.capitalManager.capitalCheck.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "e_f_voucher_capital_check")
@IdClass(value = EFVoucherCapitalCheck.EFVoucherCapitalCheck_Id.class)
public class EFVoucherCapitalCheck {

  @Id
  private String fcardid;
  private String dataCorp;
  private String ftypeid;
  private String fnumber;
  private String fname;
  private String fdeptid;
  @Id
  private String note;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createtime;
  private String man;
  private String flag;
  @Id
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date rq;
  private String ftypeNumber,ftypeName;


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


  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }


  public String getFdeptid() {
    return fdeptid;
  }

  public void setFdeptid(String fdeptid) {
    this.fdeptid = fdeptid;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
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


  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }


  public Date getRq() {
    return rq;
  }

  public void setRq(Date rq) {
    this.rq = rq;
  }

  public String getFtypeNumber() {
    return ftypeNumber;
  }

  public void setFtypeNumber(String ftypeNumber) {
    this.ftypeNumber = ftypeNumber;
  }

  public String getFtypeName() {
    return ftypeName;
  }

  public void setFtypeName(String ftypeName) {
    this.ftypeName = ftypeName;
  }

  public static class EFVoucherCapitalCheck_Id implements Serializable {
    private Date rq;
    private String note;

    public Date getRq() {
      return rq;
    }

    public void setRq(Date rq) {
      this.rq = rq;
    }

    public String getNote() {
      return note;
    }

    public void setNote(String note) {
      this.note = note;
    }
  }

  @Transient
  private String ftypeidName,fdeptidName;

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
}
