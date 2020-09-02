package com.tengzhi.business.js.drawing.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "e_js_cpcode_tdgl")
public class EJsCpcodeTdgl {

  @Id
  private String note;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date rq;
  private String twName,code,twFile,twSm,dataMan,flag,dataCorp,customer;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataDate;

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getTwName() {
    return twName;
  }

  public void setTwName(String twName) {
    this.twName = twName;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getTwFile() {
    return twFile;
  }

  public void setTwFile(String twFile) {
    this.twFile = twFile;
  }


  public String getTwSm() {
    return twSm;
  }

  public void setTwSm(String twSm) {
    this.twSm = twSm;
  }


  public String getDataMan() {
    return dataMan;
  }

  public void setDataMan(String dataMan) {
    this.dataMan = dataMan;
  }

  public Date getRq() {
    return rq;
  }

  public void setRq(Date rq) {
    this.rq = rq;
  }

  public Date getDataDate() {
    return dataDate;
  }

  public void setDataDate(Date dataDate) {
    this.dataDate = dataDate;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }


  public String getDataCorp() {
    return dataCorp;
  }

  public void setDataCorp(String dataCorp) {
    this.dataCorp = dataCorp;
  }


  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return "EJsCpcodeTdgl{" +
            "note='" + note + '\'' +
            ", rq=" + rq +
            ", twName='" + twName + '\'' +
            ", code='" + code + '\'' +
            ", twFile='" + twFile + '\'' +
            ", twSm='" + twSm + '\'' +
            ", dataMan='" + dataMan + '\'' +
            ", flag='" + flag + '\'' +
            ", dataCorp='" + dataCorp + '\'' +
            ", customer='" + customer + '\'' +
            ", dataDate=" + dataDate +
            '}';
  }
}
