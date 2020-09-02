package com.tengzhi.business.finance.voucher.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "e_f_voucher_vchtemplate")
public class VchTemplate {

	private String ftempname;
	@Id
	private String fvctemplateid;
	private String fexplanation;
	private Long fischecked;
	private String fcheckname;
	private String fownerid;
	private String fusername;
	private Date fcreatetime;
	private Date fmodifytime;
	private String ftype;
	private String dataCorp;
	
	public String getFtempname() {
		return ftempname;
	}
	public void setFtempname(String ftempname) {
		this.ftempname = ftempname;
	}

	public String getFvctemplateid() {
		return fvctemplateid;
	}

	public void setFvctemplateid(String fvctemplateid) {
		this.fvctemplateid = fvctemplateid;
	}

	public String getFexplanation() {
		return fexplanation;
	}
	public void setFexplanation(String fexplanation) {
		this.fexplanation = fexplanation;
	}
	public Long getFischecked() {
		return fischecked;
	}
	public void setFischecked(Long fischecked) {
		this.fischecked = fischecked;
	}
	public String getFcheckname() {
		return fcheckname;
	}
	public void setFcheckname(String fcheckname) {
		this.fcheckname = fcheckname;
	}
	
	public String getFownerid() {
		return fownerid;
	}
	public void setFownerid(String fownerid) {
		this.fownerid = fownerid;
	}
	public String getFusername() {
		return fusername;
	}
	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	public Date getFcreatetime() {
		return fcreatetime;
	}
	public void setFcreatetime(Date fcreatetime) {
		this.fcreatetime = fcreatetime;
	}
	public Date getFmodifytime() {
		return fmodifytime;
	}
	public void setFmodifytime(Date fmodifytime) {
		this.fmodifytime = fmodifytime;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public String getDataCorp() {
		return dataCorp;
	}
	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
	
}
