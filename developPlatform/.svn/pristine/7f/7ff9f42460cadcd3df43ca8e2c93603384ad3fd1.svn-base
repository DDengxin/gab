package com.tengzhi.business.js.tlcz.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: czf
 * @Date:2020-08-20 9:19
 */
@Entity
@Table(name = "e_js_tlcz")
public class EJsTlcz {

    @Id
    private String code;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq;
    private String tlcz;
    private String czLb;
    private String czThfl;
    private String czStype;
    private String cd;
    private String gccz;
    private String sm;
    private String flag;
    private String dataMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataDate;
    private String dataCorp;

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public Date getRq() { return rq; }

    public void setRq(Date rq) { this.rq = rq; }

    public String getTlcz() { return tlcz; }

    public void setTlcz(String tlcz) { this.tlcz = tlcz; }

    public String getCzLb() { return czLb; }

    public void setCzLb(String czLb) { this.czLb = czLb; }

    public String getCzThfl() { return czThfl; }

    public void setCzThfl(String czThfl) { this.czThfl = czThfl; }

    public String getCzStype() { return czStype; }

    public void setCzStype(String czStype) { this.czStype = czStype; }

    public String getCd() { return cd; }

    public void setCd(String cd) { this.cd = cd; }

    public String getGccz() { return gccz; }

    public void setGccz(String gccz) { this.gccz = gccz; }

    public String getSm() { return sm; }

    public void setSm(String sm) { this.sm = sm; }

    public String getFlag() { return flag; }

    public void setFlag(String flag) { this.flag = flag; }

    public String getDataMan() { return dataMan; }

    public void setDataMan(String dataMan) { this.dataMan = dataMan; }

    public Date getDataDate() { return dataDate; }

    public void setDataDate(Date dataDate) { this.dataDate = dataDate; }

    public String getDataCorp() { return dataCorp; }

    public void setDataCorp(String dataCorp) { this.dataCorp = dataCorp; }
}
