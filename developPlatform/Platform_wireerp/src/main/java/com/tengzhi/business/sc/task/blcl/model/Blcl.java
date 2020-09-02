package com.tengzhi.business.sc.task.blcl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "m_sc_gclist_blcl")
public class Blcl extends BaseModel {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq;//	记录日期
    private String bgc;//在制工程
    private String blType;//	不良类型
    private String blXm;//	不良项目
    private String blQk;//	不良情况
    private String clDtype;//	原因大类
    private String clStype;//原因小类
    private String clQk;//	原因说明
    private String clDo;//	处理结果
    private String clMan;//	处理人员
    private String gcCj;//所属车间
    private String gcCt;//	所属车台
    private String gcScmo;//	所属任务
    private String gcMan;//	生产人员
    private String gcGx;//	生产工序
    private String dataMan;    //	操作人员
    private String gxNote;//	生产工序唯一标识
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date dataDate;     //	操作时间
    private String dataCorp;  //	所属公司

    @Id
    public String getBgc() {
        return bgc;
    }

    public void setBgc(String bgc) {
        this.bgc = bgc;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getBlType() {
        return blType;
    }

    public void setBlType(String blType) {
        this.blType = blType;
    }

    public String getBlXm() {
        return blXm;
    }

    public void setBlXm(String blXm) {
        this.blXm = blXm;
    }

    public String getBlQk() {
        return blQk;
    }

    public void setBlQk(String blQk) {
        this.blQk = blQk;
    }

    public String getClDtype() {
        return clDtype;
    }

    public void setClDtype(String clDtype) {
        this.clDtype = clDtype;
    }

    public String getClStype() {
        return clStype;
    }

    public void setClStype(String clStype) {
        this.clStype = clStype;
    }

    public String getClQk() {
        return clQk;
    }

    public void setClQk(String clQk) {
        this.clQk = clQk;
    }

    public String getClDo() {
        return clDo;
    }

    public void setClDo(String clDo) {
        this.clDo = clDo;
    }

    public String getClMan() {
        return clMan;
    }

    public void setClMan(String clMan) {
        this.clMan = clMan;
    }

    public String getGcCj() {
        return gcCj;
    }

    public void setGcCj(String gcCj) {
        this.gcCj = gcCj;
    }

    public String getGcCt() {
        return gcCt;
    }

    public void setGcCt(String gcCt) {
        this.gcCt = gcCt;
    }

    public String getGcScmo() {
        return gcScmo;
    }

    public void setGcScmo(String gcScmo) {
        this.gcScmo = gcScmo;
    }

    public String getGcMan() {
        return gcMan;
    }

    public void setGcMan(String gcMan) {
        this.gcMan = gcMan;
    }

    public String getGcGx() {
        return gcGx;
    }

    public void setGcGx(String gcGx) {
        this.gcGx = gcGx;
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

    public String getGxNote() {
        return gxNote;
    }

    public void setGxNote(String gxNote) {
        this.gxNote = gxNote;
    }
}
