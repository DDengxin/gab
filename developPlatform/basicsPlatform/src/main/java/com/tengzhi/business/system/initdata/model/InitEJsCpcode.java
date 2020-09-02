package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the init_e_js_cpcode database table.
 * 
 */
@Entity
@Table(name="init_e_js_cpcode")
@NamedQuery(name="InitEJsCpcode.findAll", query="SELECT i FROM InitEJsCpcode i")
public class InitEJsCpcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cpcode_bz")
	private String cpcodeBz;

	@Column(name="cpcode_check")
	private String cpcodeCheck;

	@Column(name="cpcode_dunit")
	private String cpcodeDunit;

	@Column(name="cpcode_fl")
	private String cpcodeFl;

	@Column(name="cpcode_flag")
	private String cpcodeFlag;

	@Column(name="cpcode_flid")
	private String cpcodeFlid;

	@Column(name="cpcode_id")
	private String cpcodeId;

	@Column(name="cpcode_lower")
	private BigDecimal cpcodeLower;

	@Column(name="cpcode_name")
	private String cpcodeName;

	@Column(name="cpcode_name_en")
	private String cpcodeNameEn;

	@Column(name="cpcode_pb")
	private BigDecimal cpcodePb;

	@Column(name="cpcode_size")
	private String cpcodeSize;

	@Column(name="cpcode_size_en")
	private String cpcodeSizeEn;

	@Column(name="cpcode_sm")
	private String cpcodeSm;

	@Column(name="cpcode_stock_warn")
	private Boolean cpcodeStockWarn;

	@Column(name="cpcode_type")
	private String cpcodeType;

	@Column(name="cpcode_upper")
	private BigDecimal cpcodeUpper;

	@Column(name="cpcode_xp")
	private String cpcodeXp;

	private String cpcode01;

	private String cpcode02;

	private String cpcode03;

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
	public InitEJsCpcode() {
	}

	public String getCpcodeBz() {
		return this.cpcodeBz;
	}

	public void setCpcodeBz(String cpcodeBz) {
		this.cpcodeBz = cpcodeBz;
	}

	public String getCpcodeCheck() {
		return this.cpcodeCheck;
	}

	public void setCpcodeCheck(String cpcodeCheck) {
		this.cpcodeCheck = cpcodeCheck;
	}

	public String getCpcodeDunit() {
		return this.cpcodeDunit;
	}

	public void setCpcodeDunit(String cpcodeDunit) {
		this.cpcodeDunit = cpcodeDunit;
	}

	public String getCpcodeFl() {
		return this.cpcodeFl;
	}

	public void setCpcodeFl(String cpcodeFl) {
		this.cpcodeFl = cpcodeFl;
	}

	public String getCpcodeFlag() {
		return this.cpcodeFlag;
	}

	public void setCpcodeFlag(String cpcodeFlag) {
		this.cpcodeFlag = cpcodeFlag;
	}

	public String getCpcodeFlid() {
		return this.cpcodeFlid;
	}

	public void setCpcodeFlid(String cpcodeFlid) {
		this.cpcodeFlid = cpcodeFlid;
	}

	public String getCpcodeId() {
		return this.cpcodeId;
	}

	public void setCpcodeId(String cpcodeId) {
		this.cpcodeId = cpcodeId;
	}

	public BigDecimal getCpcodeLower() {
		return this.cpcodeLower;
	}

	public void setCpcodeLower(BigDecimal cpcodeLower) {
		this.cpcodeLower = cpcodeLower;
	}

	public String getCpcodeName() {
		return this.cpcodeName;
	}

	public void setCpcodeName(String cpcodeName) {
		this.cpcodeName = cpcodeName;
	}

	public String getCpcodeNameEn() {
		return this.cpcodeNameEn;
	}

	public void setCpcodeNameEn(String cpcodeNameEn) {
		this.cpcodeNameEn = cpcodeNameEn;
	}

	public BigDecimal getCpcodePb() {
		return this.cpcodePb;
	}

	public void setCpcodePb(BigDecimal cpcodePb) {
		this.cpcodePb = cpcodePb;
	}

	public String getCpcodeSize() {
		return this.cpcodeSize;
	}

	public void setCpcodeSize(String cpcodeSize) {
		this.cpcodeSize = cpcodeSize;
	}

	public String getCpcodeSizeEn() {
		return this.cpcodeSizeEn;
	}

	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}

	public String getCpcodeSm() {
		return this.cpcodeSm;
	}

	public void setCpcodeSm(String cpcodeSm) {
		this.cpcodeSm = cpcodeSm;
	}

	public Boolean getCpcodeStockWarn() {
		return this.cpcodeStockWarn;
	}

	public void setCpcodeStockWarn(Boolean cpcodeStockWarn) {
		this.cpcodeStockWarn = cpcodeStockWarn;
	}

	public String getCpcodeType() {
		return this.cpcodeType;
	}

	public void setCpcodeType(String cpcodeType) {
		this.cpcodeType = cpcodeType;
	}

	public BigDecimal getCpcodeUpper() {
		return this.cpcodeUpper;
	}

	public void setCpcodeUpper(BigDecimal cpcodeUpper) {
		this.cpcodeUpper = cpcodeUpper;
	}

	public String getCpcodeXp() {
		return this.cpcodeXp;
	}

	public void setCpcodeXp(String cpcodeXp) {
		this.cpcodeXp = cpcodeXp;
	}

	public String getCpcode01() {
		return this.cpcode01;
	}

	public void setCpcode01(String cpcode01) {
		this.cpcode01 = cpcode01;
	}

	public String getCpcode02() {
		return this.cpcode02;
	}

	public void setCpcode02(String cpcode02) {
		this.cpcode02 = cpcode02;
	}

	public String getCpcode03() {
		return this.cpcode03;
	}

	public void setCpcode03(String cpcode03) {
		this.cpcode03 = cpcode03;
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