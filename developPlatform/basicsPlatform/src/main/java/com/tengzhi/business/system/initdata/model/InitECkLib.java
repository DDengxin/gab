package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the init_e_ck_lib database table.
 * 
 */
@Entity
@Table(name="init_e_ck_lib")
@NamedQuery(name="InitECkLib.findAll", query="SELECT i FROM InitECkLib i")
public class InitECkLib implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="data_corp")
	private String dataCorp;

	@Column(name="data_date")
	private Timestamp dataDate;

	@Column(name="data_flag")
	private String dataFlag;

	@Column(name="data_man")
	private String dataMan;

	@Column(name="lib_id")
	private String libId;

	@Column(name="lib_index")
	private Integer libIndex;

	@Column(name="lib_name")
	private String libName;

	@Column(name="lib_name_en")
	private String libNameEn;

	@Column(name="lib_sm")
	private String libSm;

	@Column(name="lib_type")
	private String libType;

	@Column(name="lib_uid")
	private String libUid;

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
	public InitECkLib() {
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

	public String getDataFlag() {
		return this.dataFlag;
	}

	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getDataMan() {
		return this.dataMan;
	}

	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}

	public String getLibId() {
		return this.libId;
	}

	public void setLibId(String libId) {
		this.libId = libId;
	}

	public Integer getLibIndex() {
		return this.libIndex;
	}

	public void setLibIndex(Integer libIndex) {
		this.libIndex = libIndex;
	}

	public String getLibName() {
		return this.libName;
	}

	public void setLibName(String libName) {
		this.libName = libName;
	}

	public String getLibNameEn() {
		return this.libNameEn;
	}

	public void setLibNameEn(String libNameEn) {
		this.libNameEn = libNameEn;
	}

	public String getLibSm() {
		return this.libSm;
	}

	public void setLibSm(String libSm) {
		this.libSm = libSm;
	}

	public String getLibType() {
		return this.libType;
	}

	public void setLibType(String libType) {
		this.libType = libType;
	}

	public String getLibUid() {
		return this.libUid;
	}

	public void setLibUid(String libUid) {
		this.libUid = libUid;
	}

}