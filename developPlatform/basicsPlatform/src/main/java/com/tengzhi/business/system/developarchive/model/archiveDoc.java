package com.tengzhi.business.system.developarchive.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author zs
 * @create 2020-08-24
 */
@Entity
@Table(name = "sys_develop_archive")
public class archiveDoc {
    @Id
    private String uuid;
    private String parentUuid;
    private String bindId;
    private String title;
    private String content;
    private String genName;
    private Timestamp genTime;
    private Long platformVersion;
    private boolean state,isSuperView;
    private String module;
    private String type;
    private String  subclass;
    private String label;
    private String contentType;
    private String fileId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public Timestamp getGenTime() {
        return genTime;
    }

    public void setGenTime(Timestamp genTime) {
        this.genTime = genTime;
    }


    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }



    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(Long platformVersion) {
        this.platformVersion = platformVersion;
    }

    public boolean getIsSuperView() {
        return isSuperView;
    }

    public void setIsSuperView(boolean superView) {
        isSuperView = superView;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
