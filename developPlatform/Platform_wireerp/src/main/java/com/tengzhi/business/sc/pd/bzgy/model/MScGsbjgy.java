package com.tengzhi.business.sc.pd.bzgy.model;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 10:06
 * @Description:公司标准工艺类
 */

@Entity
@Table(name="m_sc_gsbjgy")
public class MScGsbjgy {

	@Id
	private String gyId;
	private String gySyb,gyType,gyGgno,gyTlcode1,gyTlcode2,gyTlcode3,gySm,dataCorp;
	private BigDecimal gyMin,gyMax;

	public String getGyId() {
		return gyId;
	}

	public void setGyId(String gyId) {
		this.gyId = gyId;
	}

	public String getGySyb() {
		return gySyb;
	}

	public void setGySyb(String gySyb) {
		this.gySyb = gySyb;
	}

	public String getGyType() {
		return gyType;
	}

	public void setGyType(String gyType) {
		this.gyType = gyType;
	}

	public String getGyGgno() {
		return gyGgno;
	}

	public void setGyGgno(String gyGgno) {
		this.gyGgno = gyGgno;
	}

	public String getGyTlcode1() {
		return gyTlcode1;
	}

	public void setGyTlcode1(String gyTlcode1) {
		this.gyTlcode1 = gyTlcode1;
	}

	public String getGyTlcode2() {
		return gyTlcode2;
	}

	public void setGyTlcode2(String gyTlcode2) {
		this.gyTlcode2 = gyTlcode2;
	}

	public String getGyTlcode3() {
		return gyTlcode3;
	}

	public void setGyTlcode3(String gyTlcode3) {
		this.gyTlcode3 = gyTlcode3;
	}

	public String getGySm() {
		return gySm;
	}

	public void setGySm(String gySm) {
		this.gySm = gySm;
	}

	public BigDecimal getGyMin() {
		return gyMin;
	}

	public void setGyMin(BigDecimal gyMin) {
		this.gyMin = gyMin;
	}

	public BigDecimal getGyMax() {
		return gyMax;
	}

	public void setGyMax(BigDecimal gyMax) {
		this.gyMax = gyMax;
	}

	public String getDataCorp() {
		return dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
}
