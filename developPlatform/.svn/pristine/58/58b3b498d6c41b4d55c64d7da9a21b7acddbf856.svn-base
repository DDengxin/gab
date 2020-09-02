package com.tengzhi.business.xt.getINOut.wlcl.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="e_xt_wlcl")
public class EXtWlcl {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rcRq;
    private String dataCorp;
    private String rcCp;
    private String rcDriver;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date rcSjcc;
    private String rcRs;
    private String rcSm;
    private String rcFlag;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date rcSjrc;

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Date getRcRq() { return rcRq; }

    public void setRcRq(Date rcRq) { this.rcRq = rcRq; }

    public String getDataCorp() { return dataCorp; }

    public void setDataCorp(String dataCorp) { this.dataCorp = dataCorp; }

    public String getRcCp() { return rcCp; }

    public void setRcCp(String rcCp) { this.rcCp = rcCp; }

    public String getRcDriver() { return rcDriver; }

    public void setRcDriver(String rcDriver) { this.rcDriver = rcDriver; }

    public Date getRcSjcc() { return rcSjcc; }

    public void setRcSjcc(Date rcSjcc) { this.rcSjcc = rcSjcc; }

    public String getRcRs() { return rcRs; }

    public void setRcRs(String rcRs) { this.rcRs = rcRs; }

    public String getRcSm() { return rcSm; }

    public void setRcSm(String rcSm) { this.rcSm = rcSm; }

    public String getRcFlag() { return rcFlag; }

    public void setRcFlag(String rcFlag) { this.rcFlag = rcFlag; }

    public Date getRcSjrc() { return rcSjrc; }

    public void setRcSjrc(Date rcSjrc) { this.rcSjrc = rcSjrc; }
}
