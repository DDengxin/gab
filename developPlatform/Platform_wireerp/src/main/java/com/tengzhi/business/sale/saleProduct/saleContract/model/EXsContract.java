package com.tengzhi.business.sale.saleProduct.saleContract.model;

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
@Table(name = "e_xs_contract")
public class EXsContract extends BaseModel {

	@Id
	private String htNo;
	private String htType, htCustomer, htItemType, htCurrency, htValidity, htSettlement, htTransportCosts,
			htTransportMode, htSupplement, dataMan, dataCorp, htAddress, htRequirements, htFlag, htStype;
	private BigDecimal htTax;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date htDate;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date dataRq;

	private String htPublic;

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

	public String getHtCustomer() {
		return htCustomer;
	}

	public void setHtCustomer(String htCustomer) {
		this.htCustomer = htCustomer;
	}

	public String getHtItemType() {
		return htItemType;
	}

	public void setHtItemType(String htItemType) {
		this.htItemType = htItemType;
	}

	public String getHtCurrency() {
		return htCurrency;
	}

	public void setHtCurrency(String htCurrency) {
		this.htCurrency = htCurrency;
	}

	public String getHtValidity() {
		return htValidity;
	}

	public void setHtValidity(String htValidity) {
		this.htValidity = htValidity;
	}

	public String getHtTransportCosts() {
		return htTransportCosts;
	}

	public void setHtTransportCosts(String htTransportCosts) {
		this.htTransportCosts = htTransportCosts;
	}

	public String getHtTransportMode() {
		return htTransportMode;
	}

	public void setHtTransportMode(String htTransportMode) {
		this.htTransportMode = htTransportMode;
	}

	public String getHtSupplement() {
		return htSupplement;
	}

	public void setHtSupplement(String htSupplement) {
		this.htSupplement = htSupplement;
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

	public String getHtSettlement() {
		return htSettlement;
	}

	public void setHtSettlement(String htSettlement) {
		this.htSettlement = htSettlement;
	}

	public String getHtAddress() {
		return htAddress;
	}

	public void setHtAddress(String htAddress) {
		this.htAddress = htAddress;
	}

	public String getHtRequirements() {
		return htRequirements;
	}

	public void setHtRequirements(String htRequirements) {
		this.htRequirements = htRequirements;
	}

	@Transient
	private BigDecimal htSl, htJe;
	@Transient
	private String htCustomerName;

	public BigDecimal getHtSl() {
		return htSl;
	}

	public void setHtSl(BigDecimal htSl) {
		this.htSl = htSl;
	}

	public BigDecimal getHtJe() {
		return htJe;
	}

	public void setHtJe(BigDecimal htJe) {
		this.htJe = htJe;
	}

	public String getHtCustomerName() {
		return htCustomerName;
	}

	public void setHtCustomerName(String htCustomerName) {
		this.htCustomerName = htCustomerName;
	}

	public String getHtFlag() {
		return htFlag;
	}

	public void setHtFlag(String htFlag) {
		this.htFlag = htFlag;
	}

	public String getHtStype() {
		return htStype;
	}

	public void setHtStype(String htStype) {
		this.htStype = htStype;
	}

	public String getHtPublic() {
		return htPublic;
	}

	public void setHtPublic(String htPublic) {
		this.htPublic = htPublic;
	}
}
