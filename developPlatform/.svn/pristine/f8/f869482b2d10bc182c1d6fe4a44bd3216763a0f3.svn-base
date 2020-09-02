package com.tengzhi.business.xt.notice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xt_notice")
public class EXtNotice {
    @Id
    private String noticeNo;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date noticeRq;
    private String noticeTheme;
    private String noticeDept;
    private String noticeType;
    private String noticeContent;
    private String noticeFile;
    private String noticeFlag;
    private String dataMan;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDate;
    private String dataCorp;
    private Boolean deleteLogo;

    @Formula("(f_getname('GETDEPTNAME',data_man,null,data_corp))")
    private String noticeDeptName;
    @Formula("(f_getname('GETUSERNAME',data_man,null,data_corp))")
    private String dataManName;
    @Formula("(f_get_param_name('企业公文',notice_type,null,data_corp))")
    private String noticeTypeName;
    //非数据库映射字段，用于区分类型
    @Transient
    private  String  paramMod;

    public Boolean getDeleteLogo() {
        return deleteLogo;
    }

    public void setDeleteLogo(Boolean deleteLogo) {
        this.deleteLogo = deleteLogo;
    }

    @Transient
    public String getNoticeDeptName() {
        return noticeDeptName;
    }

    public void setNoticeDeptName(String noticeDeptName) {
        this.noticeDeptName = noticeDeptName;
    }

    @Transient
    public String getDataManName() {
        return dataManName;
    }

    public void setDataManName(String dataManName) {
        this.dataManName = dataManName;
    }
    @Transient
    public String getParamMod() {
		return paramMod;
	}

	public void setParamMod(String paramMod) {
		this.paramMod = paramMod;
	}

	@Transient
    public String getNoticeTypeName() {
        return noticeTypeName;
    }

    public void setNoticeTypeName(String noticeTypeName) {
        this.noticeTypeName = noticeTypeName;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public Date getNoticeRq() {
        return noticeRq;
    }

    public void setNoticeRq(Date noticeRq) {
        this.noticeRq = noticeRq;
    }

    public String getNoticeTheme() {
        return noticeTheme;
    }

    public void setNoticeTheme(String noticeTheme) {
        this.noticeTheme = noticeTheme;
    }

    public String getNoticeDept() {
        return noticeDept;
    }

    public void setNoticeDept(String noticeDept) {
        this.noticeDept = noticeDept;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeFile() {
        return noticeFile;
    }

    public void setNoticeFile(String noticeFile) {
        this.noticeFile = noticeFile;
    }

    public String getNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(String noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public String getDataMan() {
        return dataMan;
    }

    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

}
