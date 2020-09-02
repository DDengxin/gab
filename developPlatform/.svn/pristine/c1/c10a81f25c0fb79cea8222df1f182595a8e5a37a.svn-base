package com.tengzhi.business.finance.capitalManager.capitalChange.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "e_f_voucher_capital_change")
public class EFVoucherCapitalChange {

  @Id
  private String ksid = UUID.randomUUID().toString();
  private String note;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date rq;
  private String fcardid;
  private String ftypeid;
  private String useDept;
  private String useMan;
  private String managerDept;
  private String newUseDept;
  private String newUseMan;
  private String putArea;
  private String newPutArea;
  private String dataCorp;
  private String man;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createtime;

  public String getKsid() {
    return ksid;
  }

  public void setKsid(String ksid) {
    this.ksid = ksid;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Date getRq() {
    return rq;
  }

  public void setRq(Date rq) {
    this.rq = rq;
  }

  public String getFcardid() {
    return fcardid;
  }

  public void setFcardid(String fcardid) {
    this.fcardid = fcardid;
  }

  public String getFtypeid() {
    return ftypeid;
  }

  public void setFtypeid(String ftypeid) {
    this.ftypeid = ftypeid;
  }

  public String getUseDept() {
    return useDept;
  }

  public void setUseDept(String useDept) {
    this.useDept = useDept;
  }

  public String getUseMan() {
    return useMan;
  }

  public void setUseMan(String useMan) {
    this.useMan = useMan;
  }

  public String getManagerDept() {
    return managerDept;
  }

  public void setManagerDept(String managerDept) {
    this.managerDept = managerDept;
  }

  public String getNewUseDept() {
    return newUseDept;
  }

  public void setNewUseDept(String newUseDept) {
    this.newUseDept = newUseDept;
  }

  public String getNewUseMan() {
    return newUseMan;
  }

  public void setNewUseMan(String newUseMan) {
    this.newUseMan = newUseMan;
  }

  public String getPutArea() {
    return putArea;
  }

  public void setPutArea(String putArea) {
    this.putArea = putArea;
  }

  public String getNewPutArea() {
    return newPutArea;
  }

  public void setNewPutArea(String newPutArea) {
    this.newPutArea = newPutArea;
  }

  public String getDataCorp() {
    return dataCorp;
  }

  public void setDataCorp(String dataCorp) {
    this.dataCorp = dataCorp;
  }

  public String getMan() {
    return man;
  }

  public void setMan(String man) {
    this.man = man;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  @Transient
  private String fnullname,fnumber,fname,ftypeidName,useDeptName,useManName,managerDeptName,newUseDeptName,newUseManName,manName,deviceType;

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

  public String getFtypeidName() {
    return ftypeidName;
  }

  public void setFtypeidName(String ftypeidName) {
    this.ftypeidName = ftypeidName;
  }

  public String getUseDeptName() {
    return useDeptName;
  }

  public void setUseDeptName(String useDeptName) {
    this.useDeptName = useDeptName;
  }

  public String getUseManName() {
    return useManName;
  }

  public void setUseManName(String useManName) {
    this.useManName = useManName;
  }

  public String getManagerDeptName() {
    return managerDeptName;
  }

  public void setManagerDeptName(String managerDeptName) {
    this.managerDeptName = managerDeptName;
  }

  public String getNewUseDeptName() {
    return newUseDeptName;
  }

  public void setNewUseDeptName(String newUseDeptName) {
    this.newUseDeptName = newUseDeptName;
  }

  public String getNewUseManName() {
    return newUseManName;
  }

  public void setNewUseManName(String newUseManName) {
    this.newUseManName = newUseManName;
  }

  public String getManName() {
    return manName;
  }

  public void setManName(String manName) {
    this.manName = manName;
  }

  public String getFnullname() {
    return fnullname;
  }

  public void setFnullname(String fnullname) {
    this.fnullname = fnullname;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }
}
