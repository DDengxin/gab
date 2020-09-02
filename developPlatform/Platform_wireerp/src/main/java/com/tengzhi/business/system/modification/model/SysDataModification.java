package com.tengzhi.business.system.modification.model;

import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lgl
 */
@Entity
@Table(name = "sys_data_modification")
public class SysDataModification extends BaseModel {
    @Id
    private String modifyNote;
    private String targetTableName;
    private String targetType;
    private String targetNote;
    @Column(columnDefinition = "text")
    private String targetBeforeData;
    @Column(columnDefinition = "text")
    private String targetAfterData;
    private Date genTime;
    private String genUserId;
    private String modifyFlag;
    private String modifyType;
    private String dataCorp;
    private String targetModifyData;

    public String getTargetModifyData() {
        return targetModifyData;
    }

    public void setTargetModifyData(String targetModifyData) {
        this.targetModifyData = targetModifyData;
    }

    public String getModifyNote() {
        return modifyNote;
    }

    public void setModifyNote(String modifyNote) {
        this.modifyNote = modifyNote;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetNote() {
        return targetNote;
    }

    public void setTargetNote(String targetNote) {
        this.targetNote = targetNote;
    }

    public String getTargetBeforeData() {
        return targetBeforeData;
    }

    public void setTargetBeforeData(String targetBeforeData) {
        this.targetBeforeData = targetBeforeData;
    }

    public String getTargetAfterData() {
        return targetAfterData;
    }

    public void setTargetAfterData(String targetAfterData) {
        this.targetAfterData = targetAfterData;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public String getGenUserId() {
        return genUserId;
    }

    public void setGenUserId(String genUserId) {
        this.genUserId = genUserId;
    }

    public String getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(String modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }
}
