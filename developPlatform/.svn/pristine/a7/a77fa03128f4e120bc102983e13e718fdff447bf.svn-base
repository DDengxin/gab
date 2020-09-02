package com.tengzhi.business.system.workflow.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lqy 工作流待办表
 */
@Entity
@Table(name = "sys_workflow_backlog")
public class Backlog {
	private String id;
	private String businessId;
	private String title;
	private String module;
	private String remake;
	private String transactor;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date time;
	private String transactorId;
	private String handleUrl;
	private String linkName;
	private String processId;
	private String linkId;
	private String founder;
	private String founderId;
	private String submitUserId;
	private String processName;
	private String unitName;
	private String processClassify;
	private String processClassifyId;
	private String processModule;
	private String processSystemType;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTransactorId() {
		return transactorId;
	}

	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}

	public String getHandleUrl() {
		return handleUrl;
	}

	public void setHandleUrl(String handleUrl) {
		this.handleUrl = handleUrl;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public String getFounderId() {
		return founderId;
	}

	public void setFounderId(String founderId) {
		this.founderId = founderId;
	}

	public String getSubmitUserId() {
		return submitUserId;
	}

	public void setSubmitUserId(String submitUserId) {
		this.submitUserId = submitUserId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Transient
	public String getProcessClassify() {
		return processClassify;
	}

	public void setProcessClassify(String processClassify) {
		this.processClassify = processClassify;
	}

	@Transient
	public String getProcessClassifyId() {
		return processClassifyId;
	}

	public void setProcessClassifyId(String processClassifyId) {
		this.processClassifyId = processClassifyId;
	}

	@Transient
	public String getProcessModule() {
		return processModule;
	}

	public void setProcessModule(String processModule) {
		this.processModule = processModule;
	}

	@Transient
	public String getProcessSystemType() {
		return processSystemType;
	}

	public void setProcessSystemType(String processSystemType) {
		this.processSystemType = processSystemType;
	}

}
