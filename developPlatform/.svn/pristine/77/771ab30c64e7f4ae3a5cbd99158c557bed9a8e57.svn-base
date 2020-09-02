package com.tengzhi.business.project.projectStage.projectLine.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "e_xm_xmlc")
public class EXmXmlc {

  @Id
  private String lcId = UUID.randomUUID().toString();
  private String lcNote;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date lcRq;
  private String lcXmId;
  private String lcTitle;
  private String lcXmStage;
  private String lcXmNode;
  private String lcMan;
  private String remarks;
  private String man;
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date createtime;
  private String dataCorp;
  private Integer mxId;


  public String getLcId() {
    return lcId;
  }

  public void setLcId(String lcId) {
    this.lcId = lcId;
  }


  public String getLcNote() {
    return lcNote;
  }

  public void setLcNote(String lcNote) {
    this.lcNote = lcNote;
  }

  public String getLcTitle() {
    return lcTitle;
  }

  public void setLcTitle(String lcTitle) {
    this.lcTitle = lcTitle;
  }


  public String getLcXmStage() {
    return lcXmStage;
  }

  public void setLcXmStage(String lcXmStage) {
    this.lcXmStage = lcXmStage;
  }


  public String getLcXmNode() {
    return lcXmNode;
  }

  public void setLcXmNode(String lcXmNode) {
    this.lcXmNode = lcXmNode;
  }


  public String getLcMan() {
    return lcMan;
  }

  public void setLcMan(String lcMan) {
    this.lcMan = lcMan;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  public String getMan() {
    return man;
  }

  public void setMan(String man) {
    this.man = man;
  }

  public Date getLcRq() {
    return lcRq;
  }

  public void setLcRq(Date lcRq) {
    this.lcRq = lcRq;
  }

  public String getLcXmId() {
    return lcXmId;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public Integer getMxId() {
    return mxId;
  }

  public void setMxId(Integer mxId) {
    this.mxId = mxId;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public void setLcXmId(String lcXmId) {
    this.lcXmId = lcXmId;
  }

  public String getDataCorp() {
    return dataCorp;
  }

  public void setDataCorp(String dataCorp) {
    this.dataCorp = dataCorp;
  }

  @Transient
  private String lcXmIdName,manName,lcXmStageName,lcXmNodeName,lcManName,_state;

  public String getLcManName() {
    return lcManName;
  }

  public void setLcManName(String lcManName) {
    this.lcManName = lcManName;
  }

  public String getLcXmNodeName() {
    return lcXmNodeName;
  }

  public void setLcXmNodeName(String lcXmNodeName) {
    this.lcXmNodeName = lcXmNodeName;
  }

  public String getLcXmStageName() {
    return lcXmStageName;
  }

  public void setLcXmStageName(String lcXmStageName) {
    this.lcXmStageName = lcXmStageName;
  }

  public String getManName() {
    return manName;
  }

  public void setManName(String manName) {
    this.manName = manName;
  }

  public String getLcXmIdName() {
    return lcXmIdName;
  }

  public void setLcXmIdName(String lcXmIdName) {
    this.lcXmIdName = lcXmIdName;
  }

  public String get_state() {
    return _state;
  }

  public void set_state(String _state) {
    this._state = _state;
  }
}
