package com.tengzhi.business.sc.da.cardAbnormal.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity(name = "e_hr_attendance_abnormal")
public class EHrAttendanceAbnormal {

  private String note;

  @Id
  private long sid;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date rq;
  private String workId;
  private String workName;
  private String workDept;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date kqSj;
  private String kqSm;
  private String flag;
  private String dataMan;
  private String dataCorp;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataDate;
  private String kqYy;
  private String kqType;

  public EHrAttendanceAbnormal() {
  }

  @Transient
  private String _state;

  public String get_state() {
    return _state;
  }
  public void set_state(String _state) {
    this._state = _state;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public long getSid() {
    return sid;
  }

  public void setSid(long sid) {
    this.sid = sid;
  }

  public Date getRq() {
    return rq;
  }

  public void setRq(Date rq) {
    this.rq = rq;
  }

  public String getWorkId() {
    return workId;
  }

  public void setWorkId(String workId) {
    this.workId = workId;
  }

  public String getWorkName() {
    return workName;
  }

  public void setWorkName(String workName) {
    this.workName = workName;
  }

  public String getWorkDept() {
    return workDept;
  }

  public void setWorkDept(String workDept) {
    this.workDept = workDept;
  }

  public Date getKqSj() {
    return kqSj;
  }

  public void setKqSj(Date kqSj) {
    this.kqSj = kqSj;
  }

  public String getKqSm() {
    return kqSm;
  }

  public void setKqSm(String kqSm) {
    this.kqSm = kqSm;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
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

  public Date getDataDate() {
    return dataDate;
  }

  public void setDataDate(Date dataDate) {
    this.dataDate = dataDate;
  }

  public String getKqYy() {
    return kqYy;
  }

  public void setKqYy(String kqYy) {
    this.kqYy = kqYy;
  }

  public String getKqType() {
    return kqType;
  }

  public void setKqType(String kqType) {
    this.kqType = kqType;
  }
}
