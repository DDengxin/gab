package com.tengzhi.business.system.workflow.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lqy 流程描述表的扩展表
 */
@Entity
@Table(name = "sys_workflow_condition")
public class Condition {
	private String id;
	private String conditionTargetTableName;
	private String conditionTargetFieldName;
	private String conditionExpression;
	private String conditionValue;
	private String conditionConnector;
	private String conditionName;
	private String conditionLinkTable;
	private Integer conditionOrder;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConditionTargetTableName() {
		return conditionTargetTableName;
	}

	public void setConditionTargetTableName(String conditionTargetTableName) {
		this.conditionTargetTableName = conditionTargetTableName;
	}

	public String getConditionTargetFieldName() {
		return conditionTargetFieldName;
	}

	public void setConditionTargetFieldName(String conditionTargetFieldName) {
		this.conditionTargetFieldName = conditionTargetFieldName;
	}

	public String getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionExpression(String conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public String getConditionConnector() {
		return conditionConnector;
	}

	public void setConditionConnector(String conditionConnector) {
		this.conditionConnector = conditionConnector;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getConditionLinkTable() {
		return conditionLinkTable;
	}

	public void setConditionLinkTable(String conditionLinkTable) {
		this.conditionLinkTable = conditionLinkTable;
	}

	public Integer getConditionOrder() {
		return conditionOrder;
	}

	public void setConditionOrder(Integer conditionOrder) {
		this.conditionOrder = conditionOrder;
	}

}
