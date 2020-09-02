package com.tengzhi.business.xt.routine.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "e_xt_sjxg")
public class EXtSjxg {

	@Id
	private String sqNote;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date sqRq;
	private String sqDept,sqMan,sqType,sqSy,ywNote,sqFlag;

	public String getSqFlag() {
		return sqFlag;
	}

	public void setSqFlag(String sqFlag) {
		this.sqFlag = sqFlag;
	}

	public Date getSqRq() {
		return sqRq;
	}

	public void setSqRq(Date sqRq) {
		this.sqRq = sqRq;
	}

	public String getSqNote() {
		return sqNote;
	}

	public void setSqNote(String sqNote) {
		this.sqNote = sqNote;
	}

	public String getSqDept() {
		return sqDept;
	}

	public void setSqDept(String sqDept) {
		this.sqDept = sqDept;
	}

	public String getSqMan() {
		return sqMan;
	}

	public void setSqMan(String sqMan) {
		this.sqMan = sqMan;
	}

	public String getSqType() {
		return sqType;
	}

	public void setSqType(String sqType) {
		this.sqType = sqType;
	}

	public String getSqSy() {
		return sqSy;
	}

	public void setSqSy(String sqSy) {
		this.sqSy = sqSy;
	}

	public String getYwNote() {
		return ywNote;
	}

	public void setYwNote(String ywNote) {
		this.ywNote = ywNote;
	}
}
