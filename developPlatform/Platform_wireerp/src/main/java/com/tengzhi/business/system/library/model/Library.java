package com.tengzhi.business.system.library.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "e_xt_archive")
public class Library {
	private String arUuid;//uuid
	private String arNote;//单号
	private String arType;//类型
	private String arLocation;//区位
	private String arSecrecy;//密级
	private String arTitle;//标题
	private String arContent;//描述、内容
	private String arFileId;//文件id
	private String genUserId;//创建人
	private Timestamp genTime;//创建时间
	private Integer arVersion;//版本
	private boolean arNewest;//最新版本标识
	private String arModifyUserId;//修改人
	private Timestamp arModifyTime;//修改时间
	private String dataCorp;//公司账套	

	@Id
	public String getArUuid() {
		return arUuid;
	}
	public void setArUuid(String arUuid) {
		this.arUuid = arUuid;
	}
	public String getArNote() {
		return arNote;
	}
	public void setArNote(String arNote) {
		this.arNote = arNote;
	}
	public String getArType() {
		return arType;
	}
	public void setArType(String arType) {
		this.arType = arType;
	}
	public String getArLocation() {
		return arLocation;
	}
	public void setArLocation(String arLocation) {
		this.arLocation = arLocation;
	}
	public String getArSecrecy() {
		return arSecrecy;
	}
	public void setArSecrecy(String arSecrecy) {
		this.arSecrecy = arSecrecy;
	}
	public String getArTitle() {
		return arTitle;
	}
	public void setArTitle(String arTitle) {
		this.arTitle = arTitle;
	}
	public String getArContent() {
		return arContent;
	}
	public void setArContent(String arContent) {
		this.arContent = arContent;
	}
	public String getArFileId() {
		return arFileId;
	}
	public void setArFileId(String arFileId) {
		this.arFileId = arFileId;
	}
	public String getGenUserId() {
		return genUserId;
	}
	public void setGenUserId(String genUserId) {
		this.genUserId = genUserId;
	}
	public Timestamp getGenTime() {
		return genTime;
	}
	public void setGenTime(Timestamp genTime) {
		this.genTime = genTime;
	}
	public Integer getArVersion() {
		return arVersion;
	}
	public void setArVersion(Integer arVersion) {
		this.arVersion = arVersion;
	}
	public boolean isArNewest() {
		return arNewest;
	}
	public void setArNewest(boolean arNewest) {
		this.arNewest = arNewest;
	}
	public String getArModifyUserId() {
		return arModifyUserId;
	}
	public void setArModifyUserId(String arModifyUserId) {
		this.arModifyUserId = arModifyUserId;
	}
	public Timestamp getArModifyTime() {
		return arModifyTime;
	}
	public void setArModifyTime(Timestamp arModifyTime) {
		this.arModifyTime = arModifyTime;
	}
	public String getDataCorp() {
		return dataCorp;
	}
	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
}
