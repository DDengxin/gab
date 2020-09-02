package com.tengzhi.business.finance.voucher.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "e_f_vocher_income")
public class otherIncome {
	@Id
	private String ksid;
	private String note, customer, stype, srxm, remarks, dataCorp;
	private Date rq, oprq;
	private Double srje;
	private String action;
	private String acountId, mend;
	private String customername, acountname;
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
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getSrxm() {
		return srxm;
	}
	public void setSrxm(String srxm) {
		this.srxm = srxm;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDataCorp() {
		return dataCorp;
	}
	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
	public Date getRq() {
		return rq;
	}
	public void setRq(Date rq) {
		this.rq = rq;
	}
	public Date getOprq() {
		return oprq;
	}
	public void setOprq(Date oprq) {
		this.oprq = oprq;
	}
	public Double getSrje() {
		return srje;
	}
	public void setSrje(Double srje) {
		this.srje = srje;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAcountId() {
		return acountId;
	}
	public void setAcountId(String acountId) {
		this.acountId = acountId;
	}
	public String getMend() {
		return mend;
	}
	public void setMend(String mend) {
		this.mend = mend;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getAcountname() {
		return acountname;
	}
	public void setAcountname(String acountname) {
		this.acountname = acountname;
	}
	
}
