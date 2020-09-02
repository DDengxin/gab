package com.tengzhi.business.sc.pd.khgy.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 21:45
 * @Description:客户工艺
 */

@Entity
@Table(name = "m_sc_kfgylist")
public class MScKfgylist {
	@Id
	private String itemMo;
	private String masterId,tname,tsize,ttype,qdMin,qdMax,ylBj,ylDl,ylLs,gyYqsm;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date itemRq;

	public String getItemMo() {
		return itemMo;
	}

	public void setItemMo(String itemMo) {
		this.itemMo = itemMo;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
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

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getQdMin() {
		return qdMin;
	}

	public void setQdMin(String qdMin) {
		this.qdMin = qdMin;
	}

	public String getQdMax() {
		return qdMax;
	}

	public void setQdMax(String qdMax) {
		this.qdMax = qdMax;
	}

	public String getYlBj() {
		return ylBj;
	}

	public void setYlBj(String ylBj) {
		this.ylBj = ylBj;
	}

	public String getYlDl() {
		return ylDl;
	}

	public void setYlDl(String ylDl) {
		this.ylDl = ylDl;
	}

	public String getYlLs() {
		return ylLs;
	}

	public void setYlLs(String ylLs) {
		this.ylLs = ylLs;
	}

	public String getGyYqsm() {
		return gyYqsm;
	}

	public void setGyYqsm(String gyYqsm) {
		this.gyYqsm = gyYqsm;
	}

	public Date getItemRq() {
		return itemRq;
	}

	public void setItemRq(Date itemRq) {
		this.itemRq = itemRq;
	}

	@Transient
	private String bzyl,tdyl,qtyl,lsyl;

	public String getBzyl() {
		return bzyl;
	}

	public void setBzyl(String bzyl) {
		this.bzyl = bzyl;
	}

	public String getTdyl() {
		return tdyl;
	}

	public void setTdyl(String tdyl) {
		this.tdyl = tdyl;
	}

	public String getQtyl() {
		return qtyl;
	}

	public void setQtyl(String qtyl) {
		this.qtyl = qtyl;
	}

	public String getLsyl() {
		return lsyl;
	}

	public void setLsyl(String lsyl) {
		this.lsyl = lsyl;
	}

	@Transient
	private String scMo;

	public String getScMo() {
		return scMo;
	}

	public void setScMo(String scMo) {
		this.scMo = scMo;
	}
}
