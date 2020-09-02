package com.tengzhi.business.system.workflow.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lqy 历史办理表
 */
@Entity
@Table(name = "sys_workflow_transact")
public class Transact {
	private String id;
	private String linkName;
	private String processId;
	private String linkId;
	private String transactor;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date startTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endTime;
	private String opinion;
	private String transactorId;
	private String businessId;
	private String signature;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getTransactorId() {
		return transactorId;
	}

	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Transact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transact(String linkId) {
		this.linkId = linkId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
