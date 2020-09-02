package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the init_e_xs_contract_detailed database table.
 * 
 */
@Entity
@Table(name="init_e_xs_contract_detailed")
@NamedQuery(name="InitEXsContractDetailed.findAll", query="SELECT i FROM InitEXsContractDetailed i")
public class InitEXsContractDetailed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="data_corp")
	private String dataCorp;

	@Column(name="data_man")
	private String dataMan;

	@Column(name="data_rq")
	private Timestamp dataRq;

	@Column(name="ht_code")
	private String htCode;

	@Column(name="ht_customer_associated")
	private String htCustomerAssociated;

	@Column(name="ht_customer_associated_remarks")
	private String htCustomerAssociatedRemarks;

	@Column(name="ht_flag")
	private String htFlag;

	@Column(name="ht_id")
	private Integer htId;

	@Column(name="ht_je")
	private BigDecimal htJe;

	@Column(name="ht_jq")
	private Timestamp htJq;

	@Column(name="ht_mo")
	private String htMo;

	@Column(name="ht_no")
	private String htNo;

	@Column(name="ht_price")
	private BigDecimal htPrice;

	@Column(name="ht_requirements")
	private String htRequirements;

	@Column(name="ht_sl")
	private BigDecimal htSl;

	@Column(name="ht_sm")
	private String htSm;

	@Column(name="ht_standard")
	private String htStandard;

	@Column(name="sc_mo")
	private String scMo;

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
	public InitEXsContractDetailed() {
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

	public String getHtCode() {
		return this.htCode;
	}

	public void setHtCode(String htCode) {
		this.htCode = htCode;
	}

	public String getHtCustomerAssociated() {
		return this.htCustomerAssociated;
	}

	public void setHtCustomerAssociated(String htCustomerAssociated) {
		this.htCustomerAssociated = htCustomerAssociated;
	}

	public String getHtCustomerAssociatedRemarks() {
		return this.htCustomerAssociatedRemarks;
	}

	public void setHtCustomerAssociatedRemarks(String htCustomerAssociatedRemarks) {
		this.htCustomerAssociatedRemarks = htCustomerAssociatedRemarks;
	}

	public String getHtFlag() {
		return this.htFlag;
	}

	public void setHtFlag(String htFlag) {
		this.htFlag = htFlag;
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

	public String getHtMo() {
		return this.htMo;
	}

	public void setHtMo(String htMo) {
		this.htMo = htMo;
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

	public String getHtRequirements() {
		return this.htRequirements;
	}

	public void setHtRequirements(String htRequirements) {
		this.htRequirements = htRequirements;
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

	public String getHtStandard() {
		return this.htStandard;
	}

	public void setHtStandard(String htStandard) {
		this.htStandard = htStandard;
	}

	public String getScMo() {
		return this.scMo;
	}

	public void setScMo(String scMo) {
		this.scMo = scMo;
	}

}