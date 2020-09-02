package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the init_sys_customer database table.
 * 
 */
@Entity
@Table(name="init_sys_customer")
@NamedQuery(name="InitSysCustomer.findAll", query="SELECT i FROM InitSysCustomer i")
public class InitSysCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="customer_account")
	private String customerAccount;

	@Column(name="customer_address")
	private String customerAddress;

	@Column(name="customer_bank")
	private String customerBank;

	@Column(name="customer_buyer")
	private String customerBuyer;

	@Column(name="customer_city")
	private String customerCity;

	@Column(name="customer_contact")
	private String customerContact;

	@Column(name="customer_contact_method")
	private String customerContactMethod;

	@Column(name="customer_day")
	private Integer customerDay;

	@Column(name="customer_exp")
	private String customerExp;

	@Column(name="customer_flag")
	private String customerFlag;

	@Column(name="customer_id")
	private String customerId;

	@Column(name="customer_je")
	private BigDecimal customerJe;

	@Column(name="customer_level")
	private String customerLevel;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="customer_name_en")
	private String customerNameEn;

	@Column(name="customer_province")
	private String customerProvince;

	@Column(name="customer_tax")
	private String customerTax;

	@Column(name="customer_type")
	private String customerType;

	@Column(name="customer_uid")
	private String customerUid;

	@Column(name="data_corp")
	private String dataCorp;

	@Column(name="data_man")
	private String dataMan;

	@Column(name="data_rq")
	private Timestamp dataRq;

	@Id
	@Column(name="imp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long impId;
	private String dataType;
	@Basic
	@Column(name = "data_type")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Long getImpId() {
		return this.impId;
	}

	public void setImpId(Long impId) {
		this.impId = impId;
	}
	
	public InitSysCustomer() {
	}

	public String getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerBank() {
		return this.customerBank;
	}

	public void setCustomerBank(String customerBank) {
		this.customerBank = customerBank;
	}

	public String getCustomerBuyer() {
		return this.customerBuyer;
	}

	public void setCustomerBuyer(String customerBuyer) {
		this.customerBuyer = customerBuyer;
	}

	public String getCustomerCity() {
		return this.customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerContact() {
		return this.customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerContactMethod() {
		return this.customerContactMethod;
	}

	public void setCustomerContactMethod(String customerContactMethod) {
		this.customerContactMethod = customerContactMethod;
	}

	public Integer getCustomerDay() {
		return this.customerDay;
	}

	public void setCustomerDay(Integer customerDay) {
		this.customerDay = customerDay;
	}

	public String getCustomerExp() {
		return this.customerExp;
	}

	public void setCustomerExp(String customerExp) {
		this.customerExp = customerExp;
	}

	public String getCustomerFlag() {
		return this.customerFlag;
	}

	public void setCustomerFlag(String customerFlag) {
		this.customerFlag = customerFlag;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getCustomerJe() {
		return this.customerJe;
	}

	public void setCustomerJe(BigDecimal customerJe) {
		this.customerJe = customerJe;
	}

	public String getCustomerLevel() {
		return this.customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNameEn() {
		return this.customerNameEn;
	}

	public void setCustomerNameEn(String customerNameEn) {
		this.customerNameEn = customerNameEn;
	}

	public String getCustomerProvince() {
		return this.customerProvince;
	}

	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}

	public String getCustomerTax() {
		return this.customerTax;
	}

	public void setCustomerTax(String customerTax) {
		this.customerTax = customerTax;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerUid() {
		return this.customerUid;
	}

	public void setCustomerUid(String customerUid) {
		this.customerUid = customerUid;
	}

	public String getDataCorp() {
		return this.dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	public String getDataMan() {
		return this.dataMan;
	}

	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}

	public Timestamp getDataRq() {
		return this.dataRq;
	}

	public void setDataRq(Timestamp dataRq) {
		this.dataRq = dataRq;
	}

}