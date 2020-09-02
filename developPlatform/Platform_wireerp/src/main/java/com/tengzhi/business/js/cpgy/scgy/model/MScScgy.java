package com.tengzhi.business.js.cpgy.scgy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_sc_scgy")
public class MScScgy {
	@Id
	private String gyId;
	
	private String gyName,gyCptype,gyCpname,gyCustomer,gyJsyq,gyJgzy,gyFlag,gyType,dataCorp;
	
	private Integer gyOrd;

	
	public String getGyId() {
		return gyId;
	}

	public void setGyId(String gyId) {
		this.gyId = gyId;
	}

	public String getGyName() {
		return gyName;
	}

	public void setGyName(String gyName) {
		this.gyName = gyName;
	}

	public String getGyCptype() {
		return gyCptype;
	}

	public void setGyCptype(String gyCptype) {
		this.gyCptype = gyCptype;
	}

	public String getGyCustomer() {
		return gyCustomer;
	}

	public void setGyCustomer(String gyCustomer) {
		this.gyCustomer = gyCustomer;
	}

	public String getDataCorp() {
		return dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	public String getGyJsyq() {
		return gyJsyq;
	}

	public void setGyJsyq(String gyJsyq) {
		this.gyJsyq = gyJsyq;
	}

	public String getGyJgzy() {
		return gyJgzy;
	}

	public void setGyJgzy(String gyJgzy) {
		this.gyJgzy = gyJgzy;
	}

	public String getGyFlag() {
		return gyFlag;
	}

	public void setGyFlag(String gyFlag) {
		this.gyFlag = gyFlag;
	}

	public String getGyType() {
		return gyType;
	}

	public void setGyType(String gyType) {
		this.gyType = gyType;
	}



	public String getGyCpname() {
		return gyCpname;
	}

	public void setGyCpname(String gyCpname) {
		this.gyCpname = gyCpname;
	}

	
	public Integer getGyOrd() {
		return gyOrd;
	}

	public void setGyOrd(Integer gyOrd) {
		this.gyOrd = gyOrd;
	}


	@Transient
	private String cptypename,customername,typename,cpname;

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCptypename() {
		return cptypename;
	}

	public void setCptypename(String cptypename) {
		this.cptypename = cptypename;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}

	
	
	
}
