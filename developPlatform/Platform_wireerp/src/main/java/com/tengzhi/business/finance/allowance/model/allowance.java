package com.tengzhi.business.finance.allowance.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

@Entity
@Table(name="e_cw_ysyf")
public class allowance extends BaseModel{
	@Id
	private String cwNote;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date cwRq;
	private String  cwAct;
	private String cwItem;
	private String cwCode;
	private String cwType;
	private BigDecimal cwJs;
	private BigDecimal cwSl;
	private BigDecimal cwZl;
	private BigDecimal cwPrice;
	private BigDecimal cwSje;
	private BigDecimal cwSe;
	private String cwCk;
	private String cwBz;
	private BigDecimal cwHl;
	private BigDecimal cwShl;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date cwFkrq;
	private String cwFph;
	private String cwFkh;
	private String cwSm;
	private String cwMonth;
	private String cwFlag;
	private String dataMan;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataDate;
	private String dataCorp;
	private String cwStype;
	private String cwDw;
	@Transient
	private String _state;
	@Transient
	private String cpcodeName;
	@Transient
	private String cpcodeSize;
	@Transient
	private BigDecimal cwWse;
	@Transient
	private String cwbzs;
	@Transient
	private String cwdws;
	@Transient
	private String cwBzName;
	@Transient
	private String cwCodeName;
	@Transient
	private String cwActName;
	public String getCwNote() {
		return cwNote;
	}
	public void setCwNote(String cwNote) {
		this.cwNote = cwNote;
	}
	public Date getCwRq() {
		return cwRq;
	}
	public void setCwRq(Date cwRq) {
		this.cwRq = cwRq;
	}
	public String getCwAct() {
		return cwAct;
	}
	public void setCwAct(String cwAct) {
		this.cwAct = cwAct;
	}
	public String getCwItem() {
		return cwItem;
	}
	public void setCwItem(String cwItem) {
		this.cwItem = cwItem;
	}
	public String getCwCode() {
		return cwCode;
	}
	public void setCwCode(String cwCode) {
		this.cwCode = cwCode;
	}
	public String getCwType() {
		return cwType;
	}
	public void setCwType(String cwType) {
		this.cwType = cwType;
	}
	public BigDecimal getCwJs() {
		return cwJs;
	}
	public void setCwJs(BigDecimal cwJs) {
		this.cwJs = cwJs;
	}
	public BigDecimal getCwSl() {
		return cwSl;
	}
	public void setCwSl(BigDecimal cwSl) {
		this.cwSl = cwSl;
	}
	public BigDecimal getCwZl() {
		return cwZl;
	}
	public void setCwZl(BigDecimal cwZl) {
		this.cwZl = cwZl;
	}
	public BigDecimal getCwPrice() {
		return cwPrice;
	}
	public void setCwPrice(BigDecimal cwPrice) {
		this.cwPrice = cwPrice;
	}
	public BigDecimal getCwSje() {
		return cwSje;
	}
	public void setCwSje(BigDecimal cwSje) {
		this.cwSje = cwSje;
	}
	public BigDecimal getCwSe() {
		return cwSe;
	}
	public void setCwSe(BigDecimal cwSe) {
		this.cwSe = cwSe;
	}
	public String getCwCk() {
		return cwCk;
	}
	public void setCwCk(String cwCk) {
		this.cwCk = cwCk;
	}
	public String getCwBz() {
		return cwBz;
	}
	public void setCwBz(String cwBz) {
		this.cwBz = cwBz;
	}
	public BigDecimal getCwHl() {
		return cwHl;
	}
	public void setCwHl(BigDecimal cwHl) {
		this.cwHl = cwHl;
	}
	public BigDecimal getCwShl() {
		return cwShl;
	}
	public void setCwShl(BigDecimal cwShl) {
		this.cwShl = cwShl;
	}
	public Date getCwFkrq() {
		return cwFkrq;
	}
	public void setCwFkrq(Date cwFkrq) {
		this.cwFkrq = cwFkrq;
	}
	public String getCwFph() {
		return cwFph;
	}
	public void setCwFph(String cwFph) {
		this.cwFph = cwFph;
	}
	public String getCwFkh() {
		return cwFkh;
	}
	public void setCwFkh(String cwFkh) {
		this.cwFkh = cwFkh;
	}
	public String getCwSm() {
		return cwSm;
	}
	public void setCwSm(String cwSm) {
		this.cwSm = cwSm;
	}
	public String getCwMonth() {
		return cwMonth;
	}
	public void setCwMonth(String cwMonth) {
		this.cwMonth = cwMonth;
	}
	public String getCwFlag() {
		return cwFlag;
	}
	public void setCwFlag(String cwFlag) {
		this.cwFlag = cwFlag;
	}
	public String getDataMan() {
		return dataMan;
	}
	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	public String getDataCorp() {
		return dataCorp;
	}
	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
	public String getCwStype() {
		return cwStype;
	}
	public void setCwStype(String cwStype) {
		this.cwStype = cwStype;
	}
	public String getCwDw() {
		return cwDw;
	}
	public void setCwDw(String cwDw) {
		this.cwDw = cwDw;
	}
	@Transient
	public String get_state() {
		return _state;
	}
	public void set_state(String _state) {
		this._state = _state;
	}
	@Transient
	public String getCpcodeName() {
		return cpcodeName;
	}
	public void setCpcodeName(String cpcodeName) {
		this.cpcodeName = cpcodeName;
	}
	@Transient
	public String getCpcodeSize() {
		return cpcodeSize;
	}
	public void setCpcodeSize(String cpcodeSize) {
		this.cpcodeSize = cpcodeSize;
	}
	@Transient
	public BigDecimal getCwWse() {
		return cwWse;
	}
	public void setCwWse(BigDecimal cwWse) {
		this.cwWse = cwWse;
	}
	@Transient
	public String getCwbzs() {
		return cwbzs;
	}
	public void setCwbzs(String cwbzs) {
		this.cwbzs = cwbzs;
	}
	@Transient
	public String getCwdws() {
		return cwdws;
	}
	public void setCwdws(String cwdws) {
		this.cwdws = cwdws;
	}
	@Transient
	public String getCwBzName() {
		return cwBzName;
	}
	public void setCwBzName(String cwBzName) {
		this.cwBzName = cwBzName;
	}
	@Transient
	public String getCwCodeName() {
		return cwCodeName;
	}
	public void setCwCodeName(String cwCodeName) {
		this.cwCodeName = cwCodeName;
	}
	@Transient
	public String getCwActName() {
		return cwActName;
	}
	public void setCwActName(String cwActName) {
		this.cwActName = cwActName;
	}
	
	
	
	
	
	
	
	
	

}
