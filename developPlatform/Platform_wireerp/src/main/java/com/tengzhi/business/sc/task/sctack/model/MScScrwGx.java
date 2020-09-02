package com.tengzhi.business.sc.task.sctack.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_sc_scrw_gx")
public class MScScrwGx {
	@Id
	private String gxNote;
	private String scMo,gxId;
	private Integer gxOrd;
	
	private String gxName,gxCj,gxCt,gxJsyq,gyJysx,gxSxjj,gxXxjj,gxDojj,gxBq,gxStype,tcode,tname,tsize;

	private BigDecimal  gxJs,gxSl,gxZl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date planStartDate,planEndDate,startTime,endTime;

	public String getScMo() {
		return scMo;
	}

	public void setScMo(String scMo) {
		this.scMo = scMo;
	}

	public String getGxId() {
		return gxId;
	}

	public void setGxId(String gxId) {
		this.gxId = gxId;
	}

	

	public String getGxName() {
		return gxName;
	}

	public void setGxName(String gxName) {
		this.gxName = gxName;
	}

	public String getGxCj() {
		return gxCj;
	}

	public void setGxCj(String gxCj) {
		this.gxCj = gxCj;
	}

	public String getGxCt() {
		return gxCt;
	}

	public void setGxCt(String gxCt) {
		this.gxCt = gxCt;
	}


	public String getGxSxjj() {
		return gxSxjj;
	}

	public void setGxSxjj(String gxSxjj) {
		this.gxSxjj = gxSxjj;
	}

	public String getGxXxjj() {
		return gxXxjj;
	}

	public void setGxXxjj(String gxXxjj) {
		this.gxXxjj = gxXxjj;
	}

	public String getGxDojj() {
		return gxDojj;
	}

	public void setGxDojj(String gxDojj) {
		this.gxDojj = gxDojj;
	}

	public BigDecimal getGxJs() {
		return gxJs;
	}

	public void setGxJs(BigDecimal gxJs) {
		this.gxJs = gxJs;
	}

	public BigDecimal getGxSl() {
		return gxSl;
	}

	public void setGxSl(BigDecimal gxSl) {
		this.gxSl = gxSl;
	}

	public BigDecimal getGxZl() {
		return gxZl;
	}

	public void setGxZl(BigDecimal gxZl) {
		this.gxZl = gxZl;
	}

	public Integer getGxOrd() {
		return gxOrd;
	}

	public void setGxOrd(Integer gxOrd) {
		this.gxOrd = gxOrd;
	}

	public String getGxJsyq() {
		return gxJsyq;
	}

	public void setGxJsyq(String gxJsyq) {
		this.gxJsyq = gxJsyq;
	}

	public String getGyJysx() {
		return gyJysx;
	}

	public void setGyJysx(String gyJysx) {
		this.gyJysx = gyJysx;
	}

	public String getGxBq() {
		return gxBq;
	}

	public void setGxBq(String gxBq) {
		this.gxBq = gxBq;
	}

	public String getGxStype() {
		return gxStype;
	}

	public void setGxStype(String gxStype) {
		this.gxStype = gxStype;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsize() {
		return tsize;
	}

	public void setTsize(String tsize) {
		this.tsize = tsize;
	}





public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
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

	@Transient
	private String gyId,sxjjName,xxjjName,dojjName,bqname,stypename,ctname,code,cpcodeName,zzptname;


	public String getGyId() {
		return gyId;
	}

	public void setGyId(String gyId) {
		this.gyId = gyId;
	}

	public String getSxjjName() {
		return sxjjName;
	}

	public void setSxjjName(String sxjjName) {
		this.sxjjName = sxjjName;
	}

	public String getXxjjName() {
		return xxjjName;
	}

	public void setXxjjName(String xxjjName) {
		this.xxjjName = xxjjName;
	}

	public String getDojjName() {
		return dojjName;
	}

	public void setDojjName(String dojjName) {
		this.dojjName = dojjName;
	}

	public String getBqname() {
		return bqname;
	}

	public void setBqname(String bqname) {
		this.bqname = bqname;
	}

	public String getStypename() {
		return stypename;
	}

	public void setStypename(String stypename) {
		this.stypename = stypename;
	}

	public String getCtname() {
		return ctname;
	}

	public void setCtname(String ctname) {
		this.ctname = ctname;
	}
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	@Transient
	private BigDecimal wwsl,ywsl,blsl;


	public BigDecimal getWwsl() {
		return wwsl;
	}

	public void setWwsl(BigDecimal wwsl) {
		this.wwsl = wwsl;
	}

	public BigDecimal getYwsl() {
		return ywsl;
	}

	public void setYwsl(BigDecimal ywsl) {
		this.ywsl = ywsl;
	}

	public BigDecimal getBlsl() {
		return blsl;
	}

	public void setBlsl(BigDecimal blsl) {
		this.blsl = blsl;
	}

	public String getCpcodeName() {
		return cpcodeName;
	}

	public void setCpcodeName(String cpcodeName) {
		this.cpcodeName = cpcodeName;
	}

	public String getZzptname() {
		return zzptname;
	}

	public void setZzptname(String zzptname) {
		this.zzptname = zzptname;
	}


	@Transient
	private String _state;
	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String getGxNote() {
		return gxNote;
	}

	public void setGxNote(String gxNote) {
		this.gxNote = gxNote;
	}
}
