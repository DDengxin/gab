package com.tengzhi.workflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.security.common.model.SecurityUser;

@Entity
@Table(name = "act_tz_backlog")
public class Backlog {
	private String id;
	private String businessId;
	private String title;
	private String module;
	private String remake;
	private String transactor;
	private String transactorId;
	private String handleUrl;
	private String processKey;
	private String  processInstanceId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date time;
	private String taskId;
	private String name_;
	private String userId_;
	private String procDefId_;
	

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

	public Backlog(String title, String module,String handleUrl,SecurityUser securityUser) {
		this.module = module;
		this.title = title;
		this.handleUrl=handleUrl;
		this.transactor=securityUser.getNickName();
		this.transactorId=securityUser.getJobNumber();
	}

	public Backlog(String title, String module, String remake) {
		this.module = module;
		this.title = title;
		this.remake = remake;
		this.time=new Date();
	}

	public Backlog() {
	}

	@Transient
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Transient
	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Transient
	public String getUserId_() {
		return userId_;
	}

	public void setUserId_(String userId_) {
		this.userId_ = userId_;
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

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	
	@Transient
	public String getProcDefId_() {
		return procDefId_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

}
