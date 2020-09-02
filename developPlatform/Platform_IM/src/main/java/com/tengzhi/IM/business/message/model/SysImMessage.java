package com.tengzhi.IM.business.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

/**
 * @author lqy Im消息
 */
@Entity
@Table(name = "sys_im_message")
public class SysImMessage extends BaseModel {
	private String id;
	private String senduser;
	private String receiveuser;
	private String groupid;
	private String isread;
	private String type;
	private String content;
	private String createuser;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdate;
	@Transient
	private String ImFileId;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Transient
	public String getImFileId() {
		return ImFileId;
	}

	public void setImFileId(String imFileId) {
		ImFileId = imFileId;
	}

	public String getSenduser() {
		return senduser;
	}

	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}

	public String getReceiveuser() {
		return receiveuser;
	}

	public void setReceiveuser(String receiveuser) {
		this.receiveuser = receiveuser;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
