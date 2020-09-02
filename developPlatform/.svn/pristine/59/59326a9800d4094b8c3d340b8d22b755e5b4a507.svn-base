package com.tengzhi.business.system.fileauth.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "e_xt_archive_r_right")
public class FileAuth {
	private String arNote;//档案单号
	private String linkDeptId;//关联部门
	private String linkUserId;//关联用户
	private String genUserId;//创建人
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp genTime;//创建时间
	
	@Id
	public String getArNote() {
		return arNote;
	}
	public void setArNote(String arNote) {
		this.arNote = arNote;
	}
	public String getLinkDeptId() {
		return linkDeptId;
	}
	public void setLinkDeptId(String linkDeptId) {
		this.linkDeptId = linkDeptId;
	}
	public String getLinkUserId() {
		return linkUserId;
	}
	public void setLinkUserId(String linkUserId) {
		this.linkUserId = linkUserId;
	}
	public String getGenUserId() {
		return genUserId;
	}
	public void setGenUserId(String genUserId) {
		this.genUserId = genUserId;
	}
	
	@Basic
	public Timestamp getGenTime() {
		return genTime;
	}
	public void setGenTime(Timestamp genTime) {
		this.genTime = genTime;
	}
	
}
