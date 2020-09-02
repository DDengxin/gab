package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the init_sys_dept database table.
 * 
 */
@Entity
@Table(name="init_sys_dept")
@NamedQuery(name="InitSysDept.findAll", query="SELECT i FROM InitSysDept i")
public class InitSysDept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="creation_time")
	private Timestamp creationTime;

	private String creator;

	@Column(name="creator_id")
	private String creatorId;

	@Column(name="delete_logo")
	private Boolean deleteLogo;

	@Column(name="dept_admin")
	private String deptAdmin;

	@Column(name="dept_id")
	private String deptId;

	@Column(name="dept_name")
	private String deptName;

	private String modifier;

	@Column(name="modifier_id")
	private String modifierId;

	@Column(name="modify_time")
	private Timestamp modifyTime;

	@Column(name="org_id")
	private String orgId;

	@Column(name="org_name")
	private String orgName;

	@Column(name="parent_id")
	private String parentId;

	@Column(name="parent_name")
	private String parentName;

	private Integer rank;

	private String remark;

	private Boolean state;
	private String dataType;

	@Column(name="tier_id")
	private String tierId;
	@Id
	@Column(name="imp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long impId;
	@Column(name="data_corp")
	private String dataCorp;

	@Basic
	@Column(name = "data_type")


	public String getDataCorp() {
		return this.dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}


	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Long getImpId() {
		return this.impId;
	}
	public InitSysDept() {
	}

	public Timestamp getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Boolean getDeleteLogo() {
		return this.deleteLogo;
	}

	public void setDeleteLogo(Boolean deleteLogo) {
		this.deleteLogo = deleteLogo;
	}

	public String getDeptAdmin() {
		return this.deptAdmin;
	}

	public void setDeptAdmin(String deptAdmin) {
		this.deptAdmin = deptAdmin;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierId() {
		return this.modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getTierId() {
		return this.tierId;
	}

	public void setTierId(String tierId) {
		this.tierId = tierId;
	}

}