package com.tengzhi.business.platform.specialist.need.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 	需求表
 * @author xutao
 *
 */
@Entity
@Table(name = "g_need")
public class G_Need {

	@Id
	private String needNote;
	private String cpcodeName;
	private String cpcodeSize;
	private String cpcodeSizeEn;
	private String cpcodeFl;
	private String cpcodeXp;
	private String introduce;
	private String apply;
	private BigDecimal dutyCycle;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	private String needProvince;
	private String needCity;
	private String needPlan;
	private String needImg;
	private String needFile;
	private String needFlag;
	private String applyMan;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date applyDate;
	private String applyCorp;
	private String dataMan;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataDate;
	private String dataCorp;
	private String needForm;
	private String provinceId;

	public String getNeedNote() {
		return needNote;
	}

	public void setNeedNote(String needNote) {
		this.needNote = needNote;
	}

	public String getCpcodeName() {
		return cpcodeName;
	}

	public void setCpcodeName(String cpcodeName) {
		this.cpcodeName = cpcodeName;
	}

	public String getCpcodeSize() {
		return cpcodeSize;
	}

	public void setCpcodeSize(String cpcodeSize) {
		this.cpcodeSize = cpcodeSize;
	}

	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}

	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}

	public String getCpcodeFl() {
		return cpcodeFl;
	}

	public void setCpcodeFl(String cpcodeFl) {
		this.cpcodeFl = cpcodeFl;
	}

	public String getCpcodeXp() {
		return cpcodeXp;
	}

	public void setCpcodeXp(String cpcodeXp) {
		this.cpcodeXp = cpcodeXp;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public BigDecimal getDutyCycle() {
		return dutyCycle;
	}

	public void setDutyCycle(BigDecimal dutyCycle) {
		this.dutyCycle = dutyCycle;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getNeedProvince() {
		return needProvince;
	}

	public void setNeedProvince(String needProvince) {
		this.needProvince = needProvince;
	}

	public String getNeedCity() {
		return needCity;
	}

	public void setNeedCity(String needCity) {
		this.needCity = needCity;
	}

	public String getNeedPlan() {
		return needPlan;
	}

	public void setNeedPlan(String needPlan) {
		this.needPlan = needPlan;
	}

	public String getNeedImg() {
		return needImg;
	}

	public void setNeedImg(String needImg) {
		this.needImg = needImg;
	}

	public String getNeedFile() {
		return needFile;
	}

	public void setNeedFile(String needFile) {
		this.needFile = needFile;
	}

	public String getNeedFlag() {
		return needFlag;
	}

	public void setNeedFlag(String needFlag) {
		this.needFlag = needFlag;
	}

	public String getApplyMan() {
		return applyMan;
	}

	public void setApplyMan(String applyMan) {
		this.applyMan = applyMan;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyCorp() {
		return applyCorp;
	}

	public void setApplyCorp(String applyCorp) {
		this.applyCorp = applyCorp;
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

	public String getNeedForm() {
		return needForm;
	}

	public void setNeedForm(String needForm) {
		this.needForm = needForm;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
}
