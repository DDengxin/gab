package com.tengzhi.business.sale.processProduct.processContract.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the e_xs_contract_detailed_wl database table.
 * 
 */
@Embeddable
public class EXsContractDetailedWlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ht_no")
	private String htNo;

	@Column(name="wl_code")
	private String wlCode;

	public EXsContractDetailedWlPK() {
	}
	public EXsContractDetailedWlPK(String htNo, String wlCode) {
		// TODO Auto-generated constructor stub
		this.htNo = htNo;
		this.wlCode = wlCode;
	}
	public String getHtNo() {
		return this.htNo;
	}
	public void setHtNo(String htNo) {
		this.htNo = htNo;
	}
	public String getWlCode() {
		return this.wlCode;
	}
	public void setWlCode(String wlCode) {
		this.wlCode = wlCode;
	}

	@Override
    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EXsContractDetailedWlPK)) {
			return false;
		}
		EXsContractDetailedWlPK castOther = (EXsContractDetailedWlPK)other;
		return 
			this.htNo.equals(castOther.htNo)
			&& this.wlCode.equals(castOther.wlCode);
	}

	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.htNo.hashCode();
		hash = hash * prime + this.wlCode.hashCode();
		
		return hash;
	}
}