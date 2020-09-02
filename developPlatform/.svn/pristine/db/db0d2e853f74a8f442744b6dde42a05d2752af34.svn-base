package com.tengzhi.business.system.email.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lqy
 *收件箱
 */
@Entity
@Table(name = "sys_email_inbox")
public class Inbox {
	private String id;
	private String sender;
	private String cc;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date sendTime;
	private String emailId;
	private String content;
	private Boolean containsAttachment;
	private Boolean haveRead;
	private String userId;
	private String title;
	private String creatorId;
	private String modifierId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creationTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date modifyTime;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getContainsAttachment() {
		return containsAttachment;
	}

	public void setContainsAttachment(Boolean containsAttachment) {
		this.containsAttachment = containsAttachment;
	}

	public Boolean getHaveRead() {
		return haveRead;
	}

	public void setHaveRead(Boolean haveRead) {
		this.haveRead = haveRead;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
