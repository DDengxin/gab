/**
 * 
 */
package com.tengzhi.workflow.test.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "sys_employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7306067935779132478L;


	@Id
	private String id;

	// 职员姓名
	private String employeeName;

	// 请假天数
	private Integer day;
	// 请假原因
	private String  reason;
	// 请假原因
	private String  type;
	@Transient
	private String  taskid;
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	
}
