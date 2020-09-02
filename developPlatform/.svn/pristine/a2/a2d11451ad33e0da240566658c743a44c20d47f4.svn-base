package com.tengzhi.business.system.role.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "sys_role")
public class SysRole extends BaseModel {
    @NotBlank(message = "角色ID不能为空")
    private String roleId;
    private String parentId;
    private String roleName;
    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date genTime;
    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime;
    private String description;
    private Boolean isForbidden;
    private Integer roleOrd;
    //图表一
    private String dataChart;
    //图表二
    private String dataChartTwo;
    @NotBlank(message = "公司账套不得为空")
    private String dataCorp;

    @Id
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getParentId() {
        return parentId;
    }

    public Integer getRoleOrd() {
        return roleOrd;
    }

    public void setRoleOrd(Integer roleOrd) {
        this.roleOrd = roleOrd;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(Boolean isForbidden) {
        this.isForbidden = isForbidden;
    }

    public String getDataChart() {
        return dataChart;
    }

    public void setDataChart(String dataChart) {
        this.dataChart = dataChart;
    }

    public String getDataChartTwo() {
        return dataChartTwo;
    }

    public void setDataChartTwo(String dataChartTwo) {
        this.dataChartTwo = dataChartTwo;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }
}
