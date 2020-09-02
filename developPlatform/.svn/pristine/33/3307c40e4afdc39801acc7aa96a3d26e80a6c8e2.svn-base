package com.tengzhi.business.system.workflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lqy 工作流待流程主表
 */
@Entity
@Table(name = "sys_workflow_describe")
public class Describe {

	private String id;
	private String processName;
	private String processDescribe;
	private String processClassify;
	private String processClassifyId;
	private String version;
	private String state;
	private String processModule;
	private String dataCorp;
	private String creator;
	private String creatorId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creationTime;
	private String processSystemType;
	private String workflowTableId;
	private Integer coutsl;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessDescribe() {
		return processDescribe;
	}

	public void setProcessDescribe(String processDescribe) {
		this.processDescribe = processDescribe;
	}

	public String getProcessClassify() {
		return processClassify;
	}

	public void setProcessClassify(String processClassify) {
		this.processClassify = processClassify;
	}

	public String getProcessClassifyId() {
		return processClassifyId;
	}

	public void setProcessClassifyId(String processClassifyId) {
		this.processClassifyId = processClassifyId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProcessModule() {
		return processModule;
	}

	public void setProcessModule(String processModule) {
		this.processModule = processModule;
	}

	public String getDataCorp() {
		return dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getProcessSystemType() {
		return processSystemType;
	}

	public void setProcessSystemType(String processSystemType) {
		this.processSystemType = processSystemType;
	}

	public String getWorkflowTableId() {
		return workflowTableId;
	}

	public void setWorkflowTableId(String workflowTableId) {
		this.workflowTableId = workflowTableId;
	}

	@Transient
	public Integer getCoutsl() {
		return coutsl;
	}

	public void setCoutsl(Integer coutsl) {
		this.coutsl = coutsl;
	}

}
