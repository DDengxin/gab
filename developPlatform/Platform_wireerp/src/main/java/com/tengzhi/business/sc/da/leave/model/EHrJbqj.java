package com.tengzhi.business.sc.da.leave.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
* Description:生产人员请假类
* @author: huangbiao
* @date: 2020/8/11 0011 14:20
* @param:
* @return:
*/
@Entity(name="e_hr_jbqj")
public class EHrJbqj {

  @Id
  private String jbqjId;
  private String qjNote;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date qjRq;
  private String workerId;
  private String qjDtype;
  private String qjZtype;
  private String qjXtype;
  private String qjSy;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date qjKrq;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm" )
  private Date qjDrq;
  private Double qjSc;
  private Double qjKq;
  private String qjFlag;
  private String qjMonth;
  private String dataMan;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private  Date dataDate;
  private String dataCorp;
  @Transient
  private String workerName;
  @Transient
  private String workerDeptName;

  public EHrJbqj() {
  }

  public String getJbqjId() {
    return jbqjId;
  }

  public void setJbqjId(String jbqjId) {
    this.jbqjId = jbqjId;
  }

  public String getQjNote() {
    return qjNote;
  }

  public void setQjNote(String qjNote) {
    this.qjNote = qjNote;
  }

  public Date getQjRq() {
    return qjRq;
  }

  public void setQjRq(Date qjRq) {
    this.qjRq = qjRq;
  }

  public String getWorkerId() {
    return workerId;
  }

  public void setWorkerId(String workerId) {
    this.workerId = workerId;
  }

  public String getQjDtype() {
    return qjDtype;
  }

  public void setQjDtype(String qjDtype) {
    this.qjDtype = qjDtype;
  }

  public String getQjZtype() {
    return qjZtype;
  }

  public void setQjZtype(String qjZtype) {
    this.qjZtype = qjZtype;
  }

  public String getQjXtype() {
    return qjXtype;
  }

  public void setQjXtype(String qjXtype) {
    this.qjXtype = qjXtype;
  }

  public String getQjSy() {
    return qjSy;
  }

  public void setQjSy(String qjSy) {
    this.qjSy = qjSy;
  }

  public Date getQjKrq() {
    return qjKrq;
  }

  public void setQjKrq(Date qjKrq) {
    this.qjKrq = qjKrq;
  }

  public Date getQjDrq() {
    return qjDrq;
  }

  public void setQjDrq(Date qjDrq) {
    this.qjDrq = qjDrq;
  }

  public Double getQjSc() {
    return qjSc;
  }

  public void setQjSc(Double qjSc) {
    this.qjSc = qjSc;
  }

  public Double getQjKq() {
    return qjKq;
  }

  public void setQjKq(Double qjKq) {
    this.qjKq = qjKq;
  }

  public String getQjFlag() {
    return qjFlag;
  }

  public void setQjFlag(String qjFlag) {
    this.qjFlag = qjFlag;
  }

  public String getQjMonth() {
    return qjMonth;
  }

  public void setQjMonth(String qjMonth) {
    this.qjMonth = qjMonth;
  }

  public String getDataMan() {
    return dataMan;
  }

  public void setDataMan(String dataMan) {
    this.dataMan = dataMan;
  }

  public Date getDataDate() {
    return dataDate;
  }

  public void setDataDate(Date dataDate) {
    this.dataDate = dataDate;
  }

  public String getDataCorp() {
    return dataCorp;
  }

  public void setDataCorp(String dataCorp) {
    this.dataCorp = dataCorp;
  }

  public String getWorkerName() {
    return workerName;
  }

  public void setWorkerName(String workerName) {
    this.workerName = workerName;
  }

  public String getWorkerDeptName() {
    return workerDeptName;
  }

  public void setWorkerDeptName(String workerDeptName) {
    this.workerDeptName = workerDeptName;
  }
}
