package com.tengzhi.business.system.params.model;

import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 王翔
 * @create 2020-04-26
 */
@Entity
@Table(name = "sys_param")
@IdClass(SysParams.SysParams_PK.class)
public class SysParams extends BaseModel {
    private String paramId;
    private String paramName;
    private String paramNameEn;
    private String paramType;
    private BigDecimal paramValue;
    private String paramValue1;
    private String paramValue2;
    private String paramValue3;
    private String paramMod;
    private String paramDetail;
    private String paramStatus;
    private String creatorId;
    private Timestamp creationTime;
    private String parentId;
    private String parentName;
    private String creator;
    private String orgId;
    private Timestamp modifyTime;
    private String modifierId;
    private String modifier;
    private Integer paramOrd;
    private String paramXtype;


    private String oldParamType;
    @Transient
    public String getOldParamType() {
        return oldParamType;
    }

    public void setOldParamType(String oldParamType) {
        this.oldParamType = oldParamType;
    }

    @Id
    @Column(name = "param_id", nullable = false, length = 600)
    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public Integer getParamOrd() {
        return paramOrd;
    }

    public void setParamOrd(Integer paramOrd) {
        this.paramOrd = paramOrd;
    }

    @Basic
    @Column(name = "param_name", nullable = true, length = 100)
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Basic
    @Column(name = "param_name_en", nullable = true, length = 100)
    public String getParamNameEn() {
        return paramNameEn;
    }

    public void setParamNameEn(String paramNameEn) {
        this.paramNameEn = paramNameEn;
    }

    @Id
    @Column(name = "param_type", nullable = true, length = 100)
    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    @Basic
    @Column(name = "param_value1", nullable = true, length = 100)
    public String getParamValue1() {
        return paramValue1;
    }

    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1;
    }

    @Basic
    @Column(name = "param_value2", nullable = true, length = 100)
    public String getParamValue2() {
        return paramValue2;
    }

    public void setParamValue2(String paramValue2) {
        this.paramValue2 = paramValue2;
    }

    @Basic
    @Column(name = "param_value3", nullable = true, length = 100)
    public String getParamValue3() {
        return paramValue3;
    }

    public void setParamValue3(String paramValue3) {
        this.paramValue3 = paramValue3;
    }

    @Basic
    public BigDecimal getParamValue() {
        return paramValue;
    }

    public void setParamValue(BigDecimal paramValue) {
        this.paramValue = paramValue;
    }

    @Basic
    @Column(name = "param_mod", nullable = true, length = 10)
    public String getParamMod() {
        return paramMod;
    }

    public void setParamMod(String paramMod) {
        this.paramMod = paramMod;
    }

    @Basic
    @Column(name = "param_detail", nullable = true, length = 100)
    public String getParamDetail() {
        return paramDetail;
    }

    public void setParamDetail(String paramDetail) {
        this.paramDetail = paramDetail;
    }

    @Basic
    @Column(name = "param_status", nullable = true, length = 10)
    public String getParamStatus() {
        return paramStatus;
    }

    public void setParamStatus(String paramStatus) {
        this.paramStatus = paramStatus;
    }

    @Basic
    @Column(name = "creator_id", nullable = true, length = 20)
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "creation_time", nullable = true)
    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "parent_id", nullable = true, length = 30)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "parent_name", nullable = true, length = 30)
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Basic
    @Column(name = "creator", nullable = true, length = 40)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "org_id", nullable = true, length = 600)
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
    @Column(name = "modifier_id", nullable = true, length = 600)
    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    @Basic
    @Column(name = "modifier", nullable = true, length = 30)
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Basic
    @Column(name = "param_xtype", nullable = true, length = 20)
    public String getParamXtype() {
        return paramXtype;
    }

    public void setParamXtype(String paramXtype) {
        this.paramXtype = paramXtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysParams sysParams = (SysParams) o;
        return Objects.equals(paramId, sysParams.paramId) &&
                Objects.equals(paramName, sysParams.paramName) &&
                Objects.equals(paramNameEn, sysParams.paramNameEn) &&
                Objects.equals(paramType, sysParams.paramType) &&
                Objects.equals(paramValue, sysParams.paramValue) &&
                Objects.equals(paramValue1, sysParams.paramValue1) &&
                Objects.equals(paramValue2, sysParams.paramValue2) &&
                Objects.equals(paramValue3, sysParams.paramValue3) &&
                Objects.equals(paramMod, sysParams.paramMod) &&
                Objects.equals(paramDetail, sysParams.paramDetail) &&
                Objects.equals(paramStatus, sysParams.paramStatus) &&
                Objects.equals(creatorId, sysParams.creatorId) &&
                Objects.equals(creationTime, sysParams.creationTime) &&
                Objects.equals(parentId, sysParams.parentId) &&
                Objects.equals(parentName, sysParams.parentName) &&
                Objects.equals(creator, sysParams.creator) &&
                Objects.equals(orgId, sysParams.orgId) &&
                Objects.equals(modifyTime, sysParams.modifyTime) &&
                Objects.equals(modifierId, sysParams.modifierId) &&
                Objects.equals(modifier, sysParams.modifier) &&
                Objects.equals(paramOrd, sysParams.paramOrd) &&
                Objects.equals(paramXtype, sysParams.paramXtype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paramId, paramName, paramNameEn, paramType, paramValue, paramValue1, paramValue2, paramValue3, paramMod, paramDetail, paramStatus, creatorId, creationTime, parentId, parentName, creator, orgId, modifyTime, modifierId, modifier, paramOrd, paramXtype);
    }

    public static class SysParams_PK implements Serializable {

        private String paramId;
        private String paramType;

        public String getParamId() {
            return paramId;
        }


        public void setParamId(String paramId) {
            this.paramId = paramId;
        }


        public String getParamType() {
            return paramType;
        }


        public void setParamType(String paramType) {
            this.paramType = paramType;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((paramId == null) ? 0 : paramId.hashCode());
            result = prime * result + ((paramType == null) ? 0 : paramType.hashCode());
            return result;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            SysParams_PK other = (SysParams_PK) obj;
            if (paramId == null) {
                if (other.paramId != null) {
                    return false;
                }
            } else if (!paramId.equals(other.paramId)) {
                return false;
            }
            if (paramType == null) {
                return other.paramType == null;
            } else {
                return paramType.equals(other.paramType);
            }
        }


        public SysParams_PK(String paramId, String paramType) {
            super();
            this.paramId = paramId;
            this.paramType = paramType;
        }


        public SysParams_PK() {
            super();
        }


    }


}
