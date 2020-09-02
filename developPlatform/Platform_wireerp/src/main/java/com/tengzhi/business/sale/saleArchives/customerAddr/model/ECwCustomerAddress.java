package com.tengzhi.business.sale.saleArchives.customerAddr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_cw_customer_address")
public class ECwCustomerAddress extends BaseModel {

	private String customerId;
	private String address;
	private String contacts;
	
	private int sortOrd;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date genTime;
	private String genUserId;
	private String customerThreeName;
	
	private String contactMethod;
	private String dataCorp;
	@Id
	private String addressNote;

	public String getDataCorp() {
		return dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	public String getCustomerThreeName() {
		return customerThreeName;
	}
	public void setCustomerThreeName(String customerThreeName) {
		this.customerThreeName = customerThreeName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public int getSortOrd() {
		return sortOrd;
	}
	public void setSortOrd(int sortOrd) {
		this.sortOrd = sortOrd;
	}
	public Date getGenTime() {
		return genTime;
	}
	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}
	public String getGenUserId() {
		return genUserId;
	}
	public void setGenUserId(String genUserId) {
		this.genUserId = genUserId;
	}
	public String getAddressNote() {
		return addressNote;
	}
	public void setAddressNote(String addressNote) {
		this.addressNote = addressNote;
	}
	public String getContactMethod() {
		return contactMethod;
	}
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	
	
}
