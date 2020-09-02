package com.tengzhi.business.js.product.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lqy 产品分类
 */
@Entity
@Table(name = "e_js_cpcode")
public class Jscpcode {
	private String cpcodeId;
	private String cpcodeName;
	private String cpcodeNameEn;
	private String cpcodeSize;
	private String cpcodeSizeEn;
	private String cpcodeFl;
	private String cpcodeFlid;
	private String cpcodeDp;
	private String cpcodeXp;
	private BigDecimal cpcodePb;
	private String cpcodeCheck;
	private String cpcode01;
	private String cpcode02;
	private String cpcode03;
	private String cpcodeFlag;
	private String dataMan;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataRq;
	private String dataCorp;
	private String cpcodeSm;
	private String cpcodeDunit;
	private String cpcodeBz;
	private String cpcodeType;
	private BigDecimal cpcodeLower;
	private BigDecimal cpcodeUpper;
	private Boolean cpcodeStockWarn;
	private String cpcodePhoto;
	private String cpcodeDescribe;

	public Jscpcode() {
	}

	@Override
	public String toString() {
		return "Jscpcode{" +
				"cpcodeId='" + cpcodeId + '\'' +
				", cpcodeName='" + cpcodeName + '\'' +
				", cpcodeNameEn='" + cpcodeNameEn + '\'' +
				", cpcodeSize='" + cpcodeSize + '\'' +
				", cpcodeSizeEn='" + cpcodeSizeEn + '\'' +
				", cpcodeFl='" + cpcodeFl + '\'' +
				", cpcodeFlid='" + cpcodeFlid + '\'' +
				", cpcodeDp='" + cpcodeDp + '\'' +
				", cpcodeXp='" + cpcodeXp + '\'' +
				", cpcodePb=" + cpcodePb +
				", cpcodeCheck='" + cpcodeCheck + '\'' +
				", cpcode01='" + cpcode01 + '\'' +
				", cpcode02='" + cpcode02 + '\'' +
				", cpcode03='" + cpcode03 + '\'' +
				", cpcodeFlag='" + cpcodeFlag + '\'' +
				", dataMan='" + dataMan + '\'' +
				", dataRq=" + dataRq +
				", dataCorp='" + dataCorp + '\'' +
				", cpcodeSm='" + cpcodeSm + '\'' +
				", cpcodeDunit='" + cpcodeDunit + '\'' +
				", cpcodeBz='" + cpcodeBz + '\'' +
				", cpcodeType='" + cpcodeType + '\'' +
				", cpcodeLower=" + cpcodeLower +
				", cpcodeUpper=" + cpcodeUpper +
				", cpcodeStockWarn=" + cpcodeStockWarn +
				", cpcodePhoto='" + cpcodePhoto + '\'' +
				", cpcodeDescribe='" + cpcodeDescribe + '\'' +
				", cgStype='" + cgStype + '\'' +
				'}';
	}

	public BigDecimal getCpcodeLower() {
		return cpcodeLower;
	}

	public void setCpcodeLower(BigDecimal cpcodeLower) {
		this.cpcodeLower = cpcodeLower;
	}

	public BigDecimal getCpcodeUpper() {
		return cpcodeUpper;
	}

	public void setCpcodeUpper(BigDecimal cpcodeUpper) {
		this.cpcodeUpper = cpcodeUpper;
	}

	public Boolean getCpcodeStockWarn() {
		return cpcodeStockWarn;
	}

	public void setCpcodeStockWarn(Boolean cpcodeStockWarn) {
		this.cpcodeStockWarn = cpcodeStockWarn;
	}


	public String getCpcodeBz() {
		return cpcodeBz;
	}

	public void setCpcodeBz(String cpcodeBz) {
		this.cpcodeBz = cpcodeBz;
	}

	public String getCpcodeSm() {
		return cpcodeSm;
	}

	public void setCpcodeSm(String cpcodeSm) {
		this.cpcodeSm = cpcodeSm;
	}

	public String getCpcodeDunit() {
		return cpcodeDunit;
	}

	public void setCpcodeDunit(String cpcodeDunit) {
		this.cpcodeDunit = cpcodeDunit;
	}

	@Id
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

	public String getCpcodeNameEn() {
		return cpcodeNameEn;
	}

	public void setCpcodeNameEn(String cpcodeNameEn) {
		this.cpcodeNameEn = cpcodeNameEn;
	}

	public String getCpcodeSize() {
		return cpcodeSize;
	}

	public void setCpcodeSize(String cpcodeSize) {
		this.cpcodeSize = cpcodeSize;
	}

	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}

	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}


	public String getCpcodeFl() {
		return cpcodeFl;
	}

	public void setCpcodeFl(String cpcodeFl) {
		this.cpcodeFl = cpcodeFl;
	}

	public String getCpcodeFlid() {
		return cpcodeFlid;
	}

	public void setCpcodeFlid(String cpcodeFlid) {
		this.cpcodeFlid = cpcodeFlid;
	}
	@Transient
	public String getCpcodeDp() {
		return cpcodeDp;
	}

	public void setCpcodeDp(String cpcodeDp) {
		this.cpcodeDp = cpcodeDp;
	}

	public String getCpcodeXp() {
		return cpcodeXp;
	}

	public void setCpcodeXp(String cpcodeXp) {
		this.cpcodeXp = cpcodeXp;
	}

	public BigDecimal getCpcodePb() {
		return cpcodePb;
	}

	public void setCpcodePb(BigDecimal cpcodePb) {
		this.cpcodePb = cpcodePb;
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

	public String getCpcodeFlag() {
		return cpcodeFlag;
	}

	public void setCpcodeFlag(String cpcodeFlag) {
		this.cpcodeFlag = cpcodeFlag;
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

	public String getDataCorp() {
		return dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	private String cgStype;

	@Transient
	public String getCgStype() {
		return cgStype;
	}

	public void setCgStype(String cgStype) {
		this.cgStype = cgStype;
	}

	public String getCpcodeType() {
		return cpcodeType;
	}

	public void setCpcodeType(String cpcodeType) {
		this.cpcodeType = cpcodeType;
	}

	public String getCpcodePhoto() {
		return cpcodePhoto;
	}

	public void setCpcodePhoto(String cpcodePhoto) {
		this.cpcodePhoto = cpcodePhoto;
	}

	public String getCpcodeDescribe() {
		return cpcodeDescribe;
	}

	public void setCpcodeDescribe(String cpcodeDescribe) {
		this.cpcodeDescribe = cpcodeDescribe;
	}
}
