package com.tengzhi.business.system.upload.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

@Entity
@Table(name = "com_file")
public class SysUpload extends BaseModel {

	private String uuid;
	private String parent_uuid;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date upload_date;
	private String upload_user_id;
	private String file_name;
	private String file_path;
	private String file_sufixx;
	private String line_primary;
	private String power;
	private String denydelete;
	private String denydownload;
	private String file_backup;
	private String converter_html;

	@Id
	@Column(name = "uuid", nullable = false, length = 32)
	@NotBlank(message = "部门ID不能为空")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Basic
	@Column(name = "parent_uuid", nullable = true, length = 32)
	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}

	@Basic
	@Column(name = "upload_date", nullable = true)
	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	@Basic
	@Column(name = "upload_user_id", nullable = true, length = 32)
	public String getUpload_user_id() {
		return upload_user_id;
	}

	public void setUpload_user_id(String upload_user_id) {
		this.upload_user_id = upload_user_id;
	}

	@Basic
	@Column(name = "file_name", nullable = true, length = 100)
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	@Basic
	@Column(name = "file_path", nullable = true, length = 255)
	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	@Basic
	@Column(name = "file_sufixx", nullable = true, length = 40)
	public String getFile_sufixx() {
		return file_sufixx;
	}

	public void setFile_sufixx(String file_sufixx) {
		this.file_sufixx = file_sufixx;
	}

	@Basic
	@Column(name = "line_primary", nullable = false, length = 40)
	public String getline_primary() {
		return line_primary;
	}

	public void setLine_primary(String line_primary) {
		this.line_primary = line_primary;
	}

	@Basic
	@Column(name = "power", nullable = true, length = 255)
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@Basic
	@Column(name = "denydelete", nullable = true, length = 255)
	public String getDenydelete() {
		return denydelete;
	}

	public void setDenydelete(String b) {
		this.denydelete = b;
	}

	@Basic
	@Column(name = "denydownload", nullable = false, length = 255)
	public String getDenydownload() {
		return denydownload;
	}

	public void setDenydownload(String denydownload) {
		this.denydownload = denydownload;
	}

	@Basic
	@Column(name = "file_backup", nullable = true, length = 255)
	public String getFile_backup() {
		return file_backup;
	}

	public void setFile_backup(String file_backup) {
		this.file_backup = file_backup;
	}

	@Basic
	@Column(name = "converter_html", nullable = true, length = 255)
	public String getConverter_html() {
		return converter_html;
	}

	public void setConverter_html(String converter_html) {
		this.converter_html = converter_html;
	}

}
