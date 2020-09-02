package com.tengzhi.business.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tengzhi.base.tools.excel.Excel;

@Entity
@Table(name = "sys_demo_test")
public class Test {
	private String userId;
	@Excel(name = "姓名")
	private String nickName;
	@Excel(name = "性别")
	private String sex;
	@Excel(name = "密码")
	private String password;
	@Excel(name = "手机号码")
	private String mobile;
	@Excel(name = "邮箱")
	private String email;

	@Id
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
