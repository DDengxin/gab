package com.tengzhi.workflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "act_tz_opinion")
public class Opinion {
	private String id;
	private String opinion;
	private String taskId;
	private String businessId;
	private String transactor;
	private String transactorId;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date handleTime;
	private Boolean hidden;
	private String processInstanceId;

	private String actId_;
	private String actName_;
	private String actType_;
	private String assignee_;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date startTime_;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date endTime_;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Transient
	public String getActId_() {
		return actId_;
	}

	public void setActId_(String actId_) {
		this.actId_ = actId_;
	}

	@Transient
	public String getActName_() {
		return actName_;
	}

	public void setActName_(String actName_) {
		this.actName_ = actName_;
	}

	@Transient
	public String getActType_() {
		return actType_;
	}

	public void setActType_(String actType_) {
		this.actType_ = actType_;
	}

	@Transient
	public String getAssignee_() {
		return assignee_;
	}

	public void setAssignee_(String assignee_) {
		this.assignee_ = assignee_;
	}

	@Transient
	public Date getStartTime_() {
		return startTime_;
	}

	public void setStartTime_(Date startTime_) {
		this.startTime_ = startTime_;
	}

	@Transient
	public Date getEndTime_() {
		return endTime_;
	}

	public void setEndTime_(Date endTime_) {
		this.endTime_ = endTime_;
	}

	public String getTransactorId() {
		return transactorId;
	}

	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}

}
