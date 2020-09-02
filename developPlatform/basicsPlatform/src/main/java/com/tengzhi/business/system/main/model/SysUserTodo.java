package com.tengzhi.business.system.main.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author 王翔
 * @create 2020-05-06
 */
@Entity
@Table(name = "sys_user_todo")
public class SysUserTodo {
    private String id;
    private String userId;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date todoDate;
    private Timestamp genTime;
    private Timestamp modifyTime;
    private Boolean status;
    private String dataCorp;
    private String label;
    private Integer past;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPast() {
        return past;
    }

    public void setPast(Integer past) {
        this.past = past;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 128)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "todo_date", nullable = false)
    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    @Basic
    @Column(name = "gen_time", nullable = false)
    public Timestamp getGenTime() {
        return genTime;
    }

    public void setGenTime(Timestamp genTime) {
        this.genTime = genTime;
    }

    @Basic
    @Column(name = "modify_time", nullable = true)
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "data_corp", nullable = false, length = 10)
    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Basic
    @Column(name = "label", nullable = true, length = 255)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUserTodo that = (SysUserTodo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(todoDate, that.todoDate) &&
                Objects.equals(genTime, that.genTime) &&
                Objects.equals(modifyTime, that.modifyTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dataCorp, that.dataCorp) &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, content, todoDate, genTime, modifyTime, status, dataCorp, label);
    }
}
