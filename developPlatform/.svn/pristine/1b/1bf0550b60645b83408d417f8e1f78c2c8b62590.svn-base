package com.tengzhi.business.cg.da.sysCustomer.model;
/**
 * 单位档案表实体类
 * Gxl
 * */

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="sys_customer")
public class SysCustomer {
	@Id
	private String customerId;
	private String customerName,customerNameEn,customerExp,customerBuyer,customerType,customerProvince
			,customerCity,customerAddress,customerContact,customerBank,customerAccount,customerUid,customerFlag,dataMan,dataCorp,customerContactMethod;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataRq;
	private BigDecimal customerJe;
	private Integer customerDay;
	private String customerLevel;
	@Transient
	private String customerbuyername;
	@Transient
	private String datamanname;
	@Transient
	private String lastcustomername;
	@Transient
	private String customerprovincename;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerExp() {
		return customerExp;
	}
	public void setCustomerExp(String customerExp) {
		this.customerExp = customerExp;
	}
	public String getCustomerBuyer() {
		return customerBuyer;
	}
	public void setCustomerBuyer(String customerBuyer) {
		this.customerBuyer = customerBuyer;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerProvince() {
		return customerProvince;
	}
	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}
	public String getCustomerBank() {
		return customerBank;
	}
	public void setCustomerBank(String customerBank) {
		this.customerBank = customerBank;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCustomerUid() {
		return customerUid;
	}
	public void setCustomerUid(String customerUid) {
		this.customerUid = customerUid;
	}
	public String getCustomerFlag() {
		return customerFlag;
	}
	public void setCustomerFlag(String customerFlag) {
		this.customerFlag = customerFlag;
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
	public Date getDataRq() {
		return dataRq;
	}
	public void setDataRq(Date dataRq) {
		this.dataRq = dataRq;
	}
	public String getCustomerNameEn() {
		return customerNameEn;
	}
	public void setCustomerNameEn(String customerNameEn) {
		this.customerNameEn = customerNameEn;
	}
	public BigDecimal getCustomerJe() {
		return customerJe;
	}
	public void setCustomerJe(BigDecimal customerJe) {
		this.customerJe = customerJe;
	}
	
	public Integer getCustomerDay() {
		return customerDay;
	}
	public void setCustomerDay(Integer customerDay) {
		this.customerDay = customerDay;
	}
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	
	@Transient
	public String getCustomerbuyername() {
		return customerbuyername;
	}
	public void setCustomerbuyername(String customerbuyername) {
		this.customerbuyername = customerbuyername;
	}
	@Transient
	public String getDatamanname() {
		return datamanname;
	}
	public void setDatamanname(String datamanname) {
		this.datamanname = datamanname;
	}
	
	@Transient
	public String getCustomerprovincename() {
		return customerprovincename;
	}
	public void setCustomerprovincename(String customerprovincename) {
		this.customerprovincename = customerprovincename;
	}
	@Transient
	public String getLastcustomername() {
		return lastcustomername;
	}
	public void setLastcustomername(String lastcustomername) {
		this.lastcustomername = lastcustomername;
	}
	public String getCustomerContactMethod() {
		return customerContactMethod;
	}
	public void setCustomerContactMethod(String customerContactMethod) {
		this.customerContactMethod = customerContactMethod;
	}
	
	
	
	
	
	
	
}
