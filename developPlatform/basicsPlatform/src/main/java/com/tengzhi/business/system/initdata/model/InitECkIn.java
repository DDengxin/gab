package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the init_e_ck_in database table.
 * 
 */
@Entity
@Table(name = "init_e_ck_in")
@NamedQuery(name = "InitECkIn.findAll", query = "SELECT i FROM InitECkIn i")
public class InitECkIn implements Serializable {
	private static final long serialVersionUID = 1L;

	private String brand;

	@Column(name = "data_corp")
	private String dataCorp;

	@Column(name = "data_date")
	private Timestamp dataDate;

	@Column(name = "data_man")
	private String dataMan;

	@Column(name = "in_act")
	private String inAct;

	@Column(name = "in_bpack")
	private String inBpack;

	@Column(name = "in_bz")
	private String inBz;

	@Column(name = "in_checkflag")
	private String inCheckflag;

	@Column(name = "in_cklib")
	private String inCklib;

	@Column(name = "in_code")
	private String inCode;

	@Column(name = "in_contract")
	private String inContract;

	@Column(name = "in_customer")
	private String inCustomer;

	@Column(name = "in_date")
	private Timestamp inDate;

	@Column(name = "in_flag")
	private String inFlag;

	@Column(name = "in_hs")
	private String inHs;

	@Column(name = "in_js")
	private BigDecimal inJs;

	@Column(name = "in_kfcode")
	private String inKfcode;

	@Column(name = "in_kfcontract")
	private String inKfcontract;

	@Column(name = "in_kw")
	private String inKw;

	@Column(name = "in_lib")
	private String inLib;

	@Column(name = "in_luono")
	private String inLuono;

	@Column(name = "in_lyr")
	private String inLyr;

	@Column(name = "in_man")
	private String inMan;

	@Column(name = "in_month")
	private String inMonth;

	@Column(name = "in_note")
	private String inNote;

	@Column(name = "in_original_pack")
	private String inOriginalPack;

	@Column(name = "in_pack")
	private String inPack;

	@Column(name = "in_ph")
	private String inPh;

	@Column(name = "in_price")
	private BigDecimal inPrice;

	@Column(name = "in_rate")
	private BigDecimal inRate;

	@Column(name = "in_remarks")
	private String inRemarks;

	@Column(name = "in_rq")
	private Timestamp inRq;

	@Column(name = "in_sl")
	private BigDecimal inSl;

	@Column(name = "in_tax")
	private BigDecimal inTax;

	@Column(name = "in_type")
	private String inType;

	@Column(name = "in_vnote")
	private String inVnote;

	@Column(name = "in_xh")
	private String inXh;

	@Column(name = "in_zl")
	private BigDecimal inZl;
	@Id
	@Column(name = "imp_id")
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

	public InitECkIn() {
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDataCorp() {
		return this.dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	public Timestamp getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(Timestamp dataDate) {
		this.dataDate = dataDate;
	}

	public String getDataMan() {
		return this.dataMan;
	}

	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}

	public String getInAct() {
		return this.inAct;
	}

	public void setInAct(String inAct) {
		this.inAct = inAct;
	}

	public String getInBpack() {
		return this.inBpack;
	}

	public void setInBpack(String inBpack) {
		this.inBpack = inBpack;
	}

	public String getInBz() {
		return this.inBz;
	}

	public void setInBz(String inBz) {
		this.inBz = inBz;
	}

	public String getInCheckflag() {
		return this.inCheckflag;
	}

	public void setInCheckflag(String inCheckflag) {
		this.inCheckflag = inCheckflag;
	}

	public String getInCklib() {
		return this.inCklib;
	}

	public void setInCklib(String inCklib) {
		this.inCklib = inCklib;
	}

	public String getInCode() {
		return this.inCode;
	}

	public void setInCode(String inCode) {
		this.inCode = inCode;
	}

	public String getInContract() {
		return this.inContract;
	}

	public void setInContract(String inContract) {
		this.inContract = inContract;
	}

	public String getInCustomer() {
		return this.inCustomer;
	}

	public void setInCustomer(String inCustomer) {
		this.inCustomer = inCustomer;
	}

	public Timestamp getInDate() {
		return this.inDate;
	}

	public void setInDate(Timestamp inDate) {
		this.inDate = inDate;
	}

	public String getInFlag() {
		return this.inFlag;
	}

	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	public String getInHs() {
		return this.inHs;
	}

	public void setInHs(String inHs) {
		this.inHs = inHs;
	}

	public BigDecimal getInJs() {
		return this.inJs;
	}

	public void setInJs(BigDecimal inJs) {
		this.inJs = inJs;
	}

	public String getInKfcode() {
		return this.inKfcode;
	}

	public void setInKfcode(String inKfcode) {
		this.inKfcode = inKfcode;
	}

	public String getInKfcontract() {
		return this.inKfcontract;
	}

	public void setInKfcontract(String inKfcontract) {
		this.inKfcontract = inKfcontract;
	}

	public String getInKw() {
		return this.inKw;
	}

	public void setInKw(String inKw) {
		this.inKw = inKw;
	}

	public String getInLib() {
		return this.inLib;
	}

	public void setInLib(String inLib) {
		this.inLib = inLib;
	}

	public String getInLuono() {
		return this.inLuono;
	}

	public void setInLuono(String inLuono) {
		this.inLuono = inLuono;
	}

	public String getInLyr() {
		return this.inLyr;
	}

	public void setInLyr(String inLyr) {
		this.inLyr = inLyr;
	}

	public String getInMan() {
		return this.inMan;
	}

	public void setInMan(String inMan) {
		this.inMan = inMan;
	}

	public String getInMonth() {
		return this.inMonth;
	}

	public void setInMonth(String inMonth) {
		this.inMonth = inMonth;
	}

	public String getInNote() {
		return this.inNote;
	}

	public void setInNote(String inNote) {
		this.inNote = inNote;
	}

	public String getInOriginalPack() {
		return this.inOriginalPack;
	}

	public void setInOriginalPack(String inOriginalPack) {
		this.inOriginalPack = inOriginalPack;
	}

	public String getInPack() {
		return this.inPack;
	}

	public void setInPack(String inPack) {
		this.inPack = inPack;
	}

	public String getInPh() {
		return this.inPh;
	}

	public void setInPh(String inPh) {
		this.inPh = inPh;
	}

	public BigDecimal getInPrice() {
		return this.inPrice;
	}

	public void setInPrice(BigDecimal inPrice) {
		this.inPrice = inPrice;
	}

	public BigDecimal getInRate() {
		return this.inRate;
	}

	public void setInRate(BigDecimal inRate) {
		this.inRate = inRate;
	}

	public String getInRemarks() {
		return this.inRemarks;
	}

	public void setInRemarks(String inRemarks) {
		this.inRemarks = inRemarks;
	}

	public Timestamp getInRq() {
		return this.inRq;
	}

	public void setInRq(Timestamp inRq) {
		this.inRq = inRq;
	}

	public BigDecimal getInSl() {
		return this.inSl;
	}

	public void setInSl(BigDecimal inSl) {
		this.inSl = inSl;
	}

	public BigDecimal getInTax() {
		return this.inTax;
	}

	public void setInTax(BigDecimal inTax) {
		this.inTax = inTax;
	}

	public String getInType() {
		return this.inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}

	public String getInVnote() {
		return this.inVnote;
	}

	public void setInVnote(String inVnote) {
		this.inVnote = inVnote;
	}

	public String getInXh() {
		return this.inXh;
	}

	public void setInXh(String inXh) {
		this.inXh = inXh;
	}

	public BigDecimal getInZl() {
		return this.inZl;
	}

	public void setInZl(BigDecimal inZl) {
		this.inZl = inZl;
	}

}