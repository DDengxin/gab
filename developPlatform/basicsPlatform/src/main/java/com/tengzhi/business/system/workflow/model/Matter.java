package com.tengzhi.business.system.workflow.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-03
 */
@Entity
@Table(name = "sys_workflow_applyfor")
public class Matter {
	private String id;
	private String businessId;
	private String title;
	private String founder;
	private String founderId;
	private Date time;
	private String processId;
	private String module;
	private String linkName;
	private String handleUrl;
	private String processType;
	private String transactor;
	private String transactorId;
	private String processClassify;
	private String processClassifyId;
	private String processModule;
	private String processSystemType;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public String getFounderId() {
		return founderId;
	}

	public void setFounderId(String founderId) {
		this.founderId = founderId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getHandleUrl() {
		return handleUrl;
	}

	public void setHandleUrl(String handleUrl) {
		this.handleUrl = handleUrl;
	}
	

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getTransactorId() {
		return transactorId;
	}

	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}
	@Transient
	public String getProcessClassify() {
		return processClassify;
	}

	public void setProcessClassify(String processClassify) {
		this.processClassify = processClassify;
	}

	@Transient
	public String getProcessClassifyId() {
		return processClassifyId;
	}

	public void setProcessClassifyId(String processClassifyId) {
		this.processClassifyId = processClassifyId;
	}

	@Transient
	public String getProcessModule() {
		return processModule;
	}

	public void setProcessModule(String processModule) {
		this.processModule = processModule;
	}

	@Transient
	public String getProcessSystemType() {
		return processSystemType;
	}

	public void setProcessSystemType(String processSystemType) {
		this.processSystemType = processSystemType;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
		if (o == null || getClass() != o.getClass()) {
            return false;
        }
		Matter that = (Matter) o;
		return Objects.equals(id, that.id) && Objects.equals(businessId, that.businessId)
				&& Objects.equals(title, that.title) && Objects.equals(founder, that.founder)
				&& Objects.equals(founderId, that.founderId) && Objects.equals(time, that.time)
				&& Objects.equals(processId, that.processId) && Objects.equals(module, that.module)
				&& Objects.equals(linkName, that.linkName) && Objects.equals(handleUrl, that.handleUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, businessId, title, founder, founderId, time, processId, module, linkName, handleUrl);
	}
}
