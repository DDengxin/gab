package com.tengzhi.business.production.productionSite.productPacking.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;


@Entity
@Table(name = "m_sc_print_pack_temp")
public class MScPrintPackTemp extends BaseModel {

	@Id
	private String wlBpack;
	private String wlCode,wlName,wlSize,wlStandard,wlPack,dataMan;
	
	private BigDecimal wlSl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataRq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date wlScrq;

	public String getWlCode() {
		return wlCode;
	}

	public void setWlCode(String wlCode) {
		this.wlCode = wlCode;
	}

	public String getWlName() {
		return wlName;
	}

	public void setWlName(String wlName) {
		this.wlName = wlName;
	}

	public String getWlSize() {
		return wlSize;
	}

	public void setWlSize(String wlSize) {
		this.wlSize = wlSize;
	}

	
	public BigDecimal getWlSl() {
		return wlSl;
	}

	public void setWlSl(BigDecimal wlSl) {
		this.wlSl = wlSl;
	}

	public String getWlStandard() {
		return wlStandard;
	}

	public void setWlStandard(String wlStandard) {
		this.wlStandard = wlStandard;
	}

	public String getWlPack() {
		return wlPack;
	}

	public void setWlPack(String wlPack) {
		this.wlPack = wlPack;
	}

	public String getWlBpack() {
		return wlBpack;
	}

	public void setWlBpack(String wlBpack) {
		this.wlBpack = wlBpack;
	}

	public String getDataMan() {
		return dataMan;
	}

	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}

	public Date getDataRq() {
		return dataRq;
	}

	public void setDataRq(Date dataRq) {
		this.dataRq = dataRq;
	}

	public Date getWlScrq() {
		return wlScrq;
	}

	public void setWlScrq(Date wlScrq) {
		this.wlScrq = wlScrq;
	}
	
	
}
