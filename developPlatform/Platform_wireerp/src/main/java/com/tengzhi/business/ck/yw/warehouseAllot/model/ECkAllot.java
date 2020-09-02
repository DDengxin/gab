package com.tengzhi.business.ck.yw.warehouseAllot.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

@Entity
@Table(name = "e_ck_allot")
public class ECkAllot extends BaseModel {

	@Id
	private String  dbNote;
	private String dbCustomer,dbOriginalLib,dbPresentLib,dbPresentNote,dataMan,dataCorp,dbAct,dbType,dbFlag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dbRq;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date dataDate;
	public String getDbNote() {
		return dbNote;
	}
	public void setDbNote(String dbNote) {
		this.dbNote = dbNote;
	}
	public String getDbCustomer() {
		return dbCustomer;
	}
	public void setDbCustomer(String dbCustomer) {
		this.dbCustomer = dbCustomer;
	}
	public String getDbOriginalLib() {
		return dbOriginalLib;
	}
	public void setDbOriginalLib(String dbOriginalLib) {
		this.dbOriginalLib = dbOriginalLib;
	}
	public String getDbPresentLib() {
		return dbPresentLib;
	}
	public void setDbPresentLib(String dbPresentLib) {
		this.dbPresentLib = dbPresentLib;
	}
	public String getDbPresentNote() {
		return dbPresentNote;
	}
	public void setDbPresentNote(String dbPresentNote) {
		this.dbPresentNote = dbPresentNote;
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
	public Date getDbRq() {
		return dbRq;
	}
	public void setDbRq(Date dbRq) {
		this.dbRq = dbRq;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	public String getDbAct() {
		return dbAct;
	}
	public void setDbAct(String dbAct) {
		this.dbAct = dbAct;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	@Transient
	private BigDecimal outJs,outSl;
	public BigDecimal getOutJs() {
		return outJs;
	}
	public void setOutJs(BigDecimal outJs) {
		this.outJs = outJs;
	}
	public BigDecimal getOutSl() {
		return outSl;
	}
	public void setOutSl(BigDecimal outSl) {
		this.outSl = outSl;
	}
	public String getDbFlag() {
		return dbFlag;
	}
	public void setDbFlag(String dbFlag) {
		this.dbFlag = dbFlag;
	}
	
}
