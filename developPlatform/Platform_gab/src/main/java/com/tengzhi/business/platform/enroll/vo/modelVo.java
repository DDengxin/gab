package com.tengzhi.business.platform.enroll.vo;

import com.tengzhi.business.platform.enroll.model.*;
import com.tengzhi.business.system.user.model.SysUser;
/**
 * 多种供应商注册，对应不同的表
 * @author lsh
 *
 */
public class modelVo {
private G_personal personal;
private  G_Supply supply;
private G_Expert expert;
private SysUser  sysuser;
private  Approval approval;
private GUserInfo  gUserInfo;
public  String  selectType;
public G_personal getPersonal() {
	return personal;
}


public void setPersonal(G_personal personal) {
	this.personal = personal;
}
public G_Supply getSupply() {
	return supply;
}
public void setSupply(G_Supply supply) {
	this.supply = supply;
}
public G_Expert getExpert() {
	return expert;
}
public void setExpert(G_Expert expert) {
	this.expert = expert;
}
public SysUser getSysuser() {
	return sysuser;
}
public void setSysuser(SysUser sysuser) {
	this.sysuser = sysuser;
}
public Approval getApproval() {
	return approval;
}
public void setApproval(Approval approval) {
	this.approval = approval;
}

public String getSelectType() {
	return selectType;
}
public void setSelectType(String selectType) {
	this.selectType = selectType;
}

public GUserInfo getgUserInfo() {
	return gUserInfo;
}

	public void setgUserInfo(GUserInfo gUserInfo) {
		this.gUserInfo = gUserInfo;
	}
}
