package com.tengzhi.business.cg.yw.purchaseContract.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "e_cg_contract")
public class ECgContract {
	@Id
	private String htNo;
	private String htType,htSupplier,htInvoice,htUrgent,htQuality,htTransport,htSettlement,htSupplement,htFlag,dataMan,dataCorp,htBz,cgStype;
	private BigDecimal htTax,inRate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date htDate;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date dataRq;
	public String getHtNo() {
		return htNo;
	}
	public void setHtNo(String htNo) {
		this.htNo = htNo;
	}
	public String getHtType() {
		return htType;
	}
	public void setHtType(String htType) {
		this.htType = htType;
	}
	public String getHtSupplier() {
		return htSupplier;
	}
	public void setHtSupplier(String htSupplier) {
		this.htSupplier = htSupplier;
	}
	public String getHtInvoice() {
		return htInvoice;
	}
	public void setHtInvoice(String htInvoice) {
		this.htInvoice = htInvoice;
	}
	public String getHtUrgent() {
		return htUrgent;
	}
	public void setHtUrgent(String htUrgent) {
		this.htUrgent = htUrgent;
	}
	public String getHtQuality() {
		return htQuality;
	}
	public void setHtQuality(String htQuality) {
		this.htQuality = htQuality;
	}
	public String getHtTransport() {
		return htTransport;
	}
	public void setHtTransport(String htTransport) {
		this.htTransport = htTransport;
	}
	public String getHtSettlement() {
		return htSettlement;
	}
	public void setHtSettlement(String htSettlement) {
		this.htSettlement = htSettlement;
	}
	public String getHtSupplement() {
		return htSupplement;
	}
	public void setHtSupplement(String htSupplement) {
		this.htSupplement = htSupplement;
	}
	public String getHtFlag() {
		return htFlag;
	}
	public void setHtFlag(String htFlag) {
		this.htFlag = htFlag;
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
	public BigDecimal getHtTax() {
		return htTax;
	}
	public void setHtTax(BigDecimal htTax) {
		this.htTax = htTax;
	}
	public Date getHtDate() {
		return htDate;
	}
	public void setHtDate(Date htDate) {
		this.htDate = htDate;
	}
	public Date getDataRq() {
		return dataRq;
	}
	public void setDataRq(Date dataRq) {
		this.dataRq = dataRq;
	}
	public String getHtBz() {
		return htBz;
	}
	public void setHtBz(String htBz) {
		this.htBz = htBz;
	}
	public BigDecimal getInRate() {
		return inRate;
	}
	public void setInRate(BigDecimal inRate) {
		this.inRate = inRate;
	}
	public String getCgStype() {
		return cgStype;
	}
	public void setCgStype(String cgStype) {
		this.cgStype = cgStype;
	}
	
	@Formula("(f_getname('GETDWEXP',ht_supplier,'',data_corp))")
	private String htsuppliername;
	@Transient
	private String httypename,htinvoicename,hturgentname,datamanname;
	public String getHttypename() {
		return httypename;
	}
	public void setHttypename(String httypename) {
		this.httypename = httypename;
	}
	public String getHtinvoicename() {
		return htinvoicename;
	}
	public void setHtinvoicename(String htinvoicename) {
		this.htinvoicename = htinvoicename;
	}
	public String getHturgentname() {
		return hturgentname;
	}
	public void setHturgentname(String hturgentname) {
		this.hturgentname = hturgentname;
	}
	@Transient
	public String getHtsuppliername() {
		return htsuppliername;
	}
	public void setHtsuppliername(String htsuppliername) {
		this.htsuppliername = htsuppliername;
	}
	public String getDatamanname() {
		return datamanname;
	}
	public void setDatamanname(String datamanname) {
		this.datamanname = datamanname;
	}
	
	
	
}
