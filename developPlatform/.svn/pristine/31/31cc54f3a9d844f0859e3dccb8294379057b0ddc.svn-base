package com.tengzhi.business.system.developdoc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-08-14
 */
@Entity
@Table(name = "sys_develop_doc")
public class developDoc {
    @Id
    private String docId;
    private String docName;

    private String docMarkdown;
    private String docHtml;
    private String docFileId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date docTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date docModifyTime;
    private String createUserId;
    private String createUser;
    private String orgId;
    private String orgIdName;
    private String description;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocFileId() {
        return docFileId;
    }

    public void setDocFileId(String docFileId) {
        this.docFileId = docFileId;
    }

    public String getOrgIdName() {
        return orgIdName;
    }

    public void setOrgIdName(String orgIdName) {
        this.orgIdName = orgIdName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocMarkdown() {
        return docMarkdown;
    }

    public void setDocMarkdown(String docMarkdown) {
        this.docMarkdown = docMarkdown;
    }

    public String getDocHtml() {
        return docHtml;
    }

    public void setDocHtml(String docHtml) {
        this.docHtml = docHtml;
    }

    public Date getDocTime() {
        return docTime;
    }

    public void setDocTime(Date docTime) {
        this.docTime = docTime;
    }

    public Date getDocModifyTime() {
        return docModifyTime;
    }

    public void setDocModifyTime(Date docModifyTime) {
        this.docModifyTime = docModifyTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        developDoc that = (developDoc) o;
        return Objects.equals(docId, that.docId) &&
                Objects.equals(docName, that.docName) &&
                Objects.equals(docMarkdown, that.docMarkdown) &&
                Objects.equals(docHtml, that.docHtml) &&
                Objects.equals(docTime, that.docTime) &&
                Objects.equals(docModifyTime, that.docModifyTime) &&
                Objects.equals(createUserId, that.createUserId) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(orgId, that.orgId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docId, docName, docMarkdown, docHtml, docTime, docModifyTime, createUserId, createUser, orgId);
    }
}
