package com.tengzhi.business.sale.processProduct.processContract.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the e_xs_contract_detailed_wl database table.
 * 
 */
@Entity
@Table(name="e_xs_contract_detailed_wl")
@NamedQuery(name="EXsContractDetailedWl.findAll", query="SELECT e FROM EXsContractDetailedWl e")
public class EXsContractDetailedWl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EXsContractDetailedWlPK id;

	@Column(name="ht_mo")
	private String htMo;

	@Column(name="wl_js")
	private BigDecimal wlJs;

	@Column(name="wl_ph")
	private String wlPh;

	@Column(name="wl_sh")
	private BigDecimal wlSh;

	@Column(name="wl_sl")
	private BigDecimal wlSl;

	@Column(name="wl_sm")
	private String wlSm;

	@Column(name="wl_type")
	private String wlType;

	@Column(name="wl_zl")
	private BigDecimal wlZl;

	@Transient
	private String _state,  htNo, wlCode;
	
	public EXsContractDetailedWl() {
	}

	public EXsContractDetailedWlPK getId() {
		return this.id;
	}

	public void setId(EXsContractDetailedWlPK id) {
		this.id = id;
	}

	public String getHtMo() {
		return this.htMo;
	}

	public void setHtMo(String htMo) {
		this.htMo = htMo;
	}

	public BigDecimal getWlJs() {
		return this.wlJs;
	}

	public void setWlJs(BigDecimal wlJs) {
		this.wlJs = wlJs;
	}

	public String getWlPh() {
		return this.wlPh;
	}

	public void setWlPh(String wlPh) {
		this.wlPh = wlPh;
	}

	public BigDecimal getWlSh() {
		return this.wlSh;
	}

	public void setWlSh(BigDecimal wlSh) {
		this.wlSh = wlSh;
	}

	public BigDecimal getWlSl() {
		return this.wlSl;
	}

	public void setWlSl(BigDecimal wlSl) {
		this.wlSl = wlSl;
	}

	public String getWlSm() {
		return this.wlSm;
	}

	public void setWlSm(String wlSm) {
		this.wlSm = wlSm;
	}

	public String getWlType() {
		return this.wlType;
	}

	public void setWlType(String wlType) {
		this.wlType = wlType;
	}

	public BigDecimal getWlZl() {
		return this.wlZl;
	}

	public void setWlZl(BigDecimal wlZl) {
		this.wlZl = wlZl;
	}

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String getHtNo() {
		return htNo;
	}

	public void setHtNo(String htNo) {
		this.htNo = htNo;
	}

	public String getWlCode() {
		return wlCode;
	}

	public void setWlCode(String wlCode) {
		this.wlCode = wlCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transient
	private String cpcodeId, cpcodeName, cpcodeSize, cpcodeFl, cpcodeBz, cpcodeXp, cpcodeCheck, cpcode01, cpcode02,
			cpcode03, cpcodeSizeEn;

	public String getCpcodeId() {
		return cpcodeId;
	}

	public void setCpcodeId(String cpcodeId) {
		this.cpcodeId = cpcodeId;
	}

	public String getCpcodeName() {
		return cpcodeName;
	}

	public void setCpcodeName(String cpcodeName) {
		this.cpcodeName = cpcodeName;
	}

	public String getCpcodeSize() {
		return cpcodeSize;
	}

	public void setCpcodeSize(String cpcodeSize) {
		this.cpcodeSize = cpcodeSize;
	}

	public String getCpcodeFl() {
		return cpcodeFl;
	}

	public void setCpcodeFl(String cpcodeFl) {
		this.cpcodeFl = cpcodeFl;
	}

	public String getCpcodeBz() {
		return cpcodeBz;
	}

	public void setCpcodeBz(String cpcodeBz) {
		this.cpcodeBz = cpcodeBz;
	}

	public String getCpcodeXp() {
		return cpcodeXp;
	}

	public void setCpcodeXp(String cpcodeXp) {
		this.cpcodeXp = cpcodeXp;
	}

	public String getCpcodeCheck() {
		return cpcodeCheck;
	}

	public void setCpcodeCheck(String cpcodeCheck) {
		this.cpcodeCheck = cpcodeCheck;
	}

	public String getCpcode01() {
		return cpcode01;
	}

	public void setCpcode01(String cpcode01) {
		this.cpcode01 = cpcode01;
	}

	public String getCpcode02() {
		return cpcode02;
	}

	public void setCpcode02(String cpcode02) {
		this.cpcode02 = cpcode02;
	}

	public String getCpcode03() {
		return cpcode03;
	}

	public void setCpcode03(String cpcode03) {
		this.cpcode03 = cpcode03;
	}

	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}

	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}
	
	
}