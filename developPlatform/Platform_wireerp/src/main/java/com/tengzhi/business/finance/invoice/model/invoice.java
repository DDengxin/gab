package com.tengzhi.business.finance.invoice.model;

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
@Table(name="e_cw_fp")
public class invoice extends BaseModel{
	@Id
	private String fpId;
	private String fpNote;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date fpRq;
	private String fpDw;
	private String fpDtype;
	private String fpType;
	private BigDecimal fpSl, fpSe, fpJe,fpSje;
	private String fpFlag;
	private String fpMonth;
	private String fpXtype;
	private String fpSm;
	private String dataMan;
	private String fpBz;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataDate;
	private String dataCorp;
	private String fpCgtype;
	@Transient
	private String fplx;
	@Transient
	private String fpbzs;
	@Transient
	private String fpdws;
	
	public String getFpId() {
		return fpId;
	}
	public void setFpId(String fpId) {
		this.fpId = fpId;
	}
	public String getFpNote() {
		return fpNote;
	}
	public void setFpNote(String fpNote) {
		this.fpNote = fpNote;
	}
	public Date getFpRq() {
		return fpRq;
	}
	public void setFpRq(Date fpRq) {
		this.fpRq = fpRq;
	}
	public String getFpDw() {
		return fpDw;
	}
	public void setFpDw(String fpDw) {
		this.fpDw = fpDw;
	}
	public String getFpDtype() {
		return fpDtype;
	}
	public void setFpDtype(String fpDtype) {
		this.fpDtype = fpDtype;
	}
	public String getFpType() {
		return fpType;
	}
	public void setFpType(String fpType) {
		this.fpType = fpType;
	}
	
	public BigDecimal getFpSl() {
		return fpSl;
	}
	public void setFpSl(BigDecimal fpSl) {
		this.fpSl = fpSl;
	}
	public BigDecimal getFpSe() {
		return fpSe;
	}
	public void setFpSe(BigDecimal fpSe) {
		this.fpSe = fpSe;
	}
	public BigDecimal getFpJe() {
		return fpJe;
	}
	public void setFpJe(BigDecimal fpJe) {
		this.fpJe = fpJe;
	}
	public BigDecimal getFpSje() {
		return fpSje;
	}
	public void setFpSje(BigDecimal fpSje) {
		this.fpSje = fpSje;
	}
	public String getFpFlag() {
		return fpFlag;
	}
	public void setFpFlag(String fpFlag) {
		this.fpFlag = fpFlag;
	}
	public String getFpMonth() {
		return fpMonth;
	}
	public void setFpMonth(String fpMonth) {
		this.fpMonth = fpMonth;
	}
	public String getFpXtype() {
		return fpXtype;
	}
	public void setFpXtype(String fpXtype) {
		this.fpXtype = fpXtype;
	}
	public String getFpSm() {
		return fpSm;
	}
	public void setFpSm(String fpSm) {
		this.fpSm = fpSm;
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
	public String getFpBz() {
		return fpBz;
	}
	public void setFpBz(String fpBz) {
		this.fpBz = fpBz;
	}
	@Transient
	public String getFplx() {
		return fplx;
	}
	public void setFplx(String fplx) {
		this.fplx = fplx;
	}
	@Transient
	public String getFpbzs() {
		return fpbzs;
	}
	public void setFpbzs(String fpbzs) {
		this.fpbzs = fpbzs;
	}
	@Transient
	public String getFpdws() {
		return fpdws;
	}
	public void setFpdws(String fpdws) {
		this.fpdws = fpdws;
	}
	public String getFpCgtype() {
		return fpCgtype;
	}
	public void setFpCgtype(String fpCgtype) {
		this.fpCgtype = fpCgtype;
	}
	
	
	

}
