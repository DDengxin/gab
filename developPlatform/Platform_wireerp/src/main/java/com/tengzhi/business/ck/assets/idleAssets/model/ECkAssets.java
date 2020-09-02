package com.tengzhi.business.ck.assets.idleAssets.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: czf
 * @Date:2020-08-19 8:52
 */
@Entity
@Table(name = "e_ck_assets")
public class ECkAssets {

    @Id
    private String sid;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date zcRq;
    private String zcNo;
    private String zcName;
    private String zcKsize;
    private String zcSl;
    private String zcPrice;
    private String zcJe;
    private String zcSm;
    private String zcFrom;
    private String zcFlag;
    private String zcFile;
    private String dataCorp;
    private String dataMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataDate;
    private String zcType;

    public String getSid() { return sid; }

    public void setSid(String sid) { this.sid = sid; }

    public Date getZcRq() { return zcRq; }

    public void setZcRq(Date zcRq) { this.zcRq = zcRq; }

    public String getZcNo() { return zcNo; }

    public void setZcNo(String zcNo) { this.zcNo = zcNo; }

    public String getZcName() { return zcName; }

    public void setZcName(String zcName) { this.zcName = zcName; }

    public String getZcKsize() { return zcKsize; }

    public void setZcKsize(String zcKsize) { this.zcKsize = zcKsize; }

    public String getZcSl() { return zcSl; }

    public void setZcSl(String zcSl) { this.zcSl = zcSl; }

    public String getZcPrice() { return zcPrice; }

    public void setZcPrice(String zcPrice) { this.zcPrice = zcPrice; }

    public String getZcJe() { return zcJe; }

    public void setZcJe(String zcJe) { this.zcJe = zcJe; }

    public String getZcSm() { return zcSm; }

    public void setZcSm(String zcSm) { this.zcSm = zcSm; }

    public String getZcFrom() { return zcFrom; }

    public void setZcFrom(String zcFrom) { this.zcFrom = zcFrom; }

    public String getZcFlag() { return zcFlag; }

    public void setZcFlag(String zcFlag) { this.zcFlag = zcFlag; }

    public String getZcFile() { return zcFile; }

    public void setZcFile(String zcFile) { this.zcFile = zcFile; }

    public String getDataCorp() { return dataCorp; }

    public void setDataCorp(String dataCorp) { this.dataCorp = dataCorp; }

    public String getDataMan() { return dataMan; }

    public void setDataMan(String dataMan) { this.dataMan = dataMan; }

    public Date getDataDate() { return dataDate; }

    public void setDataDate(Date dataDate) { this.dataDate = dataDate; }

    public String getZcType() { return zcType; }

    public void setZcType(String zcType) { this.zcType = zcType; }
}
