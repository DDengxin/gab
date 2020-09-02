package com.tengzhi.business.sc.pd.khgy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 21:45
 * @Description:客户工艺
 */
@Entity
@Table(name = "m_sc_kfgylist_gc")
public class MScKfgylistGc {
	
	@Id
	private String itemMo;

	private Integer scgyOrd;
	
	private String scgyGc,scgyCz,scgyKsize,scgyYqsm;


	public String getItemMo() {
		return itemMo;
	}

	public void setItemMo(String itemMo) {
		this.itemMo = itemMo;
	}

	public Integer getScgyOrd() {
		return scgyOrd;
	}

	public void setScgyOrd(Integer scgyOrd) {
		this.scgyOrd = scgyOrd;
	}

	public String getScgyGc() {
		return scgyGc;
	}

	public void setScgyGc(String scgyGc) {
		this.scgyGc = scgyGc;
	}

	public String getScgyCz() {
		return scgyCz;
	}

	public void setScgyCz(String scgyCz) {
		this.scgyCz = scgyCz;
	}

	public String getScgyKsize() {
		return scgyKsize;
	}

	public void setScgyKsize(String scgyKsize) {
		this.scgyKsize = scgyKsize;
	}

	public String getScgyYqsm() {
		return scgyYqsm;
	}

	public void setScgyYqsm(String scgyYqsm) {
		this.scgyYqsm = scgyYqsm;
	}

	@Transient
	private  String _state;

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}
}
