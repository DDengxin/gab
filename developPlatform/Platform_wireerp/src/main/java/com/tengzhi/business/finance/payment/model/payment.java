package com.tengzhi.business.finance.payment.model;

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
@Table(name="e_cw_sfk")
public class payment extends BaseModel{
	@Id
	private String sfkId;
	private String sfkNote;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date sfkRq;
	private String sfkDw;
	private String sfkDtype;
	private String sfkType;
	private String sfkBz;
	private String sfkCgtype;


	private BigDecimal sfkShl, sfkBzje, sfkYfje,sfkFkje;
	private String sfkFkfs;
	private String sfkMess;
	private String sfkFlag;
	private String sfkMonth;
	private String sfkXtype;
	private String sfkSm;
	private String dataMan;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataDate;
	private String dataCorp;
	@Transient
	private String sfkdwname;

	public String getSfkCgtype() {
		return sfkCgtype;
	}

	public void setSfkCgtype(String sfkCgtype) {
		this.sfkCgtype = sfkCgtype;
	}

	public String getSfkId() {
		return sfkId;
	}
	public void setSfkId(String sfkId) {
		this.sfkId = sfkId;
	}
	public String getSfkNote() {
		return sfkNote;
	}
	public void setSfkNote(String sfkNote) {
		this.sfkNote = sfkNote;
	}
	public Date getSfkRq() {
		return sfkRq;
	}
	public void setSfkRq(Date sfkRq) {
		this.sfkRq = sfkRq;
	}
	public String getSfkDw() {
		return sfkDw;
	}
	public void setSfkDw(String sfkDw) {
		this.sfkDw = sfkDw;
	}
	public String getSfkDtype() {
		return sfkDtype;
	}
	public void setSfkDtype(String sfkDtype) {
		this.sfkDtype = sfkDtype;
	}
	public String getSfkType() {
		return sfkType;
	}
	public void setSfkType(String sfkType) {
		this.sfkType = sfkType;
	}
	public String getSfkBz() {
		return sfkBz;
	}
	public void setSfkBz(String sfkBz) {
		this.sfkBz = sfkBz;
	}
	
	public BigDecimal getSfkShl() {
		return sfkShl;
	}
	public void setSfkShl(BigDecimal sfkShl) {
		this.sfkShl = sfkShl;
	}
	public BigDecimal getSfkBzje() {
		return sfkBzje;
	}
	public void setSfkBzje(BigDecimal sfkBzje) {
		this.sfkBzje = sfkBzje;
	}
	public BigDecimal getSfkYfje() {
		return sfkYfje;
	}
	public void setSfkYfje(BigDecimal sfkYfje) {
		this.sfkYfje = sfkYfje;
	}
	public BigDecimal getSfkFkje() {
		return sfkFkje;
	}
	public void setSfkFkje(BigDecimal sfkFkje) {
		this.sfkFkje = sfkFkje;
	}
	public String getSfkFkfs() {
		return sfkFkfs;
	}
	public void setSfkFkfs(String sfkFkfs) {
		this.sfkFkfs = sfkFkfs;
	}
	public String getSfkMess() {
		return sfkMess;
	}
	public void setSfkMess(String sfkMess) {
		this.sfkMess = sfkMess;
	}
	public String getSfkFlag() {
		return sfkFlag;
	}
	public void setSfkFlag(String sfkFlag) {
		this.sfkFlag = sfkFlag;
	}
	public String getSfkMonth() {
		return sfkMonth;
	}
	public void setSfkMonth(String sfkMonth) {
		this.sfkMonth = sfkMonth;
	}
	public String getSfkXtype() {
		return sfkXtype;
	}
	public void setSfkXtype(String sfkXtype) {
		this.sfkXtype = sfkXtype;
	}
	public String getSfkSm() {
		return sfkSm;
	}
	public void setSfkSm(String sfkSm) {
		this.sfkSm = sfkSm;
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
	@Transient
	public String getSfkdwname() {
		return sfkdwname;
	}
	public void setSfkdwname(String sfkdwname) {
		this.sfkdwname = sfkdwname;
	}
	
	
	
	
	
	
	
	

}
