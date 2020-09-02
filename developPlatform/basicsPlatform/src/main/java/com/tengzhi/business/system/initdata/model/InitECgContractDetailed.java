package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;

import com.tengzhi.base.tools.excel.Excel;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the init_e_cg_contract_detailed database table.
 * 
 */
@Entity
@Table(name="init_e_cg_contract_detailed")
@NamedQuery(name="InitECgContractDetailed.findAll", query="SELECT i FROM InitECgContractDetailed i")
public class InitECgContractDetailed implements Serializable {
	private static final long serialVersionUID = 1L;

	private String brand;

	@Column(name="data_corp")
	private String dataCorp;

	@Column(name="data_man")
	private String dataMan;

	@Column(name="data_rq")
	private Timestamp dataRq;

	@Column(name="ht_cgnote")
	private String htCgnote;

	@Column(name="ht_code")
	private String htCode;

	@Column(name="ht_dgnote")
	private String htDgnote;

	@Column(name="ht_flag")
	private String htFlag;

	@Column(name="ht_hs")
	private String htHs;
    @Excel(name="aaa")
	@Column(name="ht_id")
	private Integer htId;

	@Column(name="ht_je")
	private BigDecimal htJe;

	@Column(name="ht_jq")
	private Timestamp htJq;

	@Column(name="ht_js")
	private BigDecimal htJs;

	@Column(name="ht_no")
	private String htNo;

	@Column(name="ht_price")
	private BigDecimal htPrice;

	@Column(name="ht_sl")
	private BigDecimal htSl;

	@Column(name="ht_sm")
	private String htSm;

	@Column(name="ht_sqnote")
	private String htSqnote;

	@Column(name="ht_zl")
	private BigDecimal htZl;
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
	public InitECgContractDetailed() {
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

	public String getHtCgnote() {
		return this.htCgnote;
	}

	public void setHtCgnote(String htCgnote) {
		this.htCgnote = htCgnote;
	}

	public String getHtCode() {
		return this.htCode;
	}

	public void setHtCode(String htCode) {
		this.htCode = htCode;
	}

	public String getHtDgnote() {
		return this.htDgnote;
	}

	public void setHtDgnote(String htDgnote) {
		this.htDgnote = htDgnote;
	}

	public String getHtFlag() {
		return this.htFlag;
	}

	public void setHtFlag(String htFlag) {
		this.htFlag = htFlag;
	}

	public String getHtHs() {
		return this.htHs;
	}

	public void setHtHs(String htHs) {
		this.htHs = htHs;
	}

	public Integer getHtId() {
		return this.htId;
	}

	public void setHtId(Integer htId) {
		this.htId = htId;
	}

	public BigDecimal getHtJe() {
		return this.htJe;
	}

	public void setHtJe(BigDecimal htJe) {
		this.htJe = htJe;
	}

	public Timestamp getHtJq() {
		return this.htJq;
	}

	public void setHtJq(Timestamp htJq) {
		this.htJq = htJq;
	}

	public BigDecimal getHtJs() {
		return this.htJs;
	}

	public void setHtJs(BigDecimal htJs) {
		this.htJs = htJs;
	}

	public String getHtNo() {
		return this.htNo;
	}

	public void setHtNo(String htNo) {
		this.htNo = htNo;
	}

	public BigDecimal getHtPrice() {
		return this.htPrice;
	}

	public void setHtPrice(BigDecimal htPrice) {
		this.htPrice = htPrice;
	}

	public BigDecimal getHtSl() {
		return this.htSl;
	}

	public void setHtSl(BigDecimal htSl) {
		this.htSl = htSl;
	}

	public String getHtSm() {
		return this.htSm;
	}

	public void setHtSm(String htSm) {
		this.htSm = htSm;
	}

	public String getHtSqnote() {
		return this.htSqnote;
	}

	public void setHtSqnote(String htSqnote) {
		this.htSqnote = htSqnote;
	}

	public BigDecimal getHtZl() {
		return this.htZl;
	}

	public void setHtZl(BigDecimal htZl) {
		this.htZl = htZl;
	}

}