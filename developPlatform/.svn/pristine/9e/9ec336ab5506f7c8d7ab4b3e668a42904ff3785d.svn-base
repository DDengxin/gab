package com.tengzhi.IM.business.upload.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "im_file")
public class imFile extends BaseModel {

	private String uuid;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date upload_date;
	private String upload_user_id;
	private String file_name;
	private String file_path;
	private String file_sufixx;

	@Id
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	public String getUpload_user_id() {
		return upload_user_id;
	}

	public void setUpload_user_id(String upload_user_id) {
		this.upload_user_id = upload_user_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_sufixx() {
		return file_sufixx;
	}

	public void setFile_sufixx(String file_sufixx) {
		this.file_sufixx = file_sufixx;
	}


}
