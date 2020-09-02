package com.tengzhi.business.finance.voucher.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "e_f_voucher_certificateword")
public class CertificateWord {
	@Id
	//id
	private Long fgenerateid;  
	//  凭证字
	private String fname;
	//前缀
	private String fprefix;
	//
	private String finternalind;
	//数字
	private Double fdigit;
	//年
	private Double fyear;
	//月
	private Double fmonth;
	//日
	private Double fdate;
	//时间格式
	private Double fyearformat;
	//默认使用
	private Double fisdefault;
	//开始号
	private Double fbeginno;
	private Double fsortindex;
	private String dataCorp;
	private String dataMan;
	
	public Long getFgenerateid() {
		return fgenerateid;
	}
	public void setFgenerateid(Long fgenerateid) {
		this.fgenerateid = fgenerateid;
	}
	public String getFinternalind() {
		return finternalind;
	}
	public void setFinternalind(String finternalind) {
		this.finternalind = finternalind;
	}
	public String getFprefix() {
		return fprefix;
	}
	public void setFprefix(String fprefix) {
		this.fprefix = fprefix;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Double getFdigit() {
		return fdigit;
	}
	public void setFdigit(Double fdigit) {
		this.fdigit = fdigit;
	}
	public Double getFyear() {
		return fyear;
	}
	public void setFyear(Double fyear) {
		this.fyear = fyear;
	}
	public Double getFmonth() {
		return fmonth;
	}
	public void setFmonth(Double fmonth) {
		this.fmonth = fmonth;
	}
	public Double getFdate() {
		return fdate;
	}
	public void setFdate(Double fdate) {
		this.fdate = fdate;
	}
	public Double getFyearformat() {
		return fyearformat;
	}
	public void setFyearformat(Double fyearformat) {
		this.fyearformat = fyearformat;
	}
	public Double getFisdefault() {
		return fisdefault;
	}
	public void setFisdefault(Double fisdefault) {
		this.fisdefault = fisdefault;
	}
	public Double getFbeginno() {
		return fbeginno;
	}
	public void setFbeginno(Double fbeginno) {
		this.fbeginno = fbeginno;
	}
	public Double getFsortindex() {
		return fsortindex;
	}
	public void setFsortindex(Double fsortindex) {
		this.fsortindex = fsortindex;
	}

	public String getDataCorp() {
		return dataCorp;
	}
	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
	public String getDataMan() {
		return dataMan;
	}
	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}
	
	
	
}
