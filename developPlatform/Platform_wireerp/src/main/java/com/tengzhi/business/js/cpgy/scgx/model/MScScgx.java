package com.tengzhi.business.js.cpgy.scgx.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_sc_scgx")
public class MScScgx {
	@Id
	private String gxId;
	
	private String gxName,gxType,gxCptype,gxJgyq,gxZysx,gxCj,gxUid,gxFlag,gxCt,gxSxjj,gxXxjj,GxDojj,gxStype,gxBq,dataCorp;
	
	private BigDecimal gxJgfy;
	
	private Integer gxOrd;

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

	public String getGxType() {
		return gxType;
	}

	public void setGxType(String gxType) {
		this.gxType = gxType;
	}

	public String getGxCptype() {
		return gxCptype;
	}

	public void setGxCptype(String gxCptype) {
		this.gxCptype = gxCptype;
	}

	public String getGxJgyq() {
		return gxJgyq;
	}

	public void setGxJgyq(String gxJgyq) {
		this.gxJgyq = gxJgyq;
	}

	public String getGxZysx() {
		return gxZysx;
	}

	public void setGxZysx(String gxZysx) {
		this.gxZysx = gxZysx;
	}

	public String getGxCj() {
		return gxCj;
	}

	public void setGxCj(String gxCj) {
		this.gxCj = gxCj;
	}

	public String getGxUid() {
		return gxUid;
	}

	public void setGxUid(String gxUid) {
		this.gxUid = gxUid;
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
		return GxDojj;
	}

	public void setGxDojj(String gxDojj) {
		GxDojj = gxDojj;
	}

	public String getGxFlag() {
		return gxFlag;
	}

	public void setGxFlag(String gxFlag) {
		this.gxFlag = gxFlag;
	}

	public BigDecimal getGxJgfy() {
		return gxJgfy;
	}

	public void setGxJgfy(BigDecimal gxJgfy) {
		this.gxJgfy = gxJgfy;
	}

	public Integer getGxOrd() {
		return gxOrd;
	}

	public void setGxOrd(Integer gxOrd) {
		this.gxOrd = gxOrd;
	}
	
	public String getGxStype() {
		return gxStype;
	}

	public void setGxStype(String gxStype) {
		this.gxStype = gxStype;
	}

	public String getGxBq() {
		return gxBq;
	}

	public void setGxBq(String gxBq) {
		this.gxBq = gxBq;
	}

	public String getDataCorp() {
		return dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	@Override
	public String toString() {
		return "MScScgx{" +
				"gxId='" + gxId + '\'' +
				", gxName='" + gxName + '\'' +
				", gxType='" + gxType + '\'' +
				", gxCptype='" + gxCptype + '\'' +
				", gxJgyq='" + gxJgyq + '\'' +
				", gxZysx='" + gxZysx + '\'' +
				", gxCj='" + gxCj + '\'' +
				", gxUid='" + gxUid + '\'' +
				", gxFlag='" + gxFlag + '\'' +
				", gxCt='" + gxCt + '\'' +
				", gxSxjj='" + gxSxjj + '\'' +
				", gxXxjj='" + gxXxjj + '\'' +
				", GxDojj='" + GxDojj + '\'' +
				", gxStype='" + gxStype + '\'' +
				", gxBq='" + gxBq + '\'' +
				", dataCorp='" + dataCorp + '\'' +
				", gxJgfy=" + gxJgfy +
				", gxOrd=" + gxOrd +
				", typename='" + typename + '\'' +
				", cptypename='" + cptypename + '\'' +
				", cjname='" + cjname + '\'' +
				", uidname='" + uidname + '\'' +
				", bqname='" + bqname + '\'' +
				", stypename='" + stypename + '\'' +
				", ctname='" + ctname + '\'' +
				", sxname='" + sxname + '\'' +
				", xxname='" + xxname + '\'' +
				", doname='" + doname + '\'' +
				'}';
	}

	@Transient
	private String typename,cptypename,cjname,uidname,bqname,stypename,ctname,sxname,xxname,doname;

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCptypename() {
		return cptypename;
	}

	public void setCptypename(String cptypename) {
		this.cptypename = cptypename;
	}

	public String getCjname() {
		return cjname;
	}

	public void setCjname(String cjname) {
		this.cjname = cjname;
	}

	public String getUidname() {
		return uidname;
	}

	public void setUidname(String uidname) {
		this.uidname = uidname;
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

	public String getSxname() {
		return sxname;
	}

	public void setSxname(String sxname) {
		this.sxname = sxname;
	}

	public String getXxname() {
		return xxname;
	}

	public void setXxname(String xxname) {
		this.xxname = xxname;
	}

	public String getDoname() {
		return doname;
	}

	public void setDoname(String doname) {
		this.doname = doname;
	}
	
	
	
	
	
}
