package com.tengzhi.business.finance.voucher.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.business.system.initdata.model.SysDbDo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

//@NamedStoredProcedureQueries({
////管理列表
//        @NamedStoredProcedureQuery(name = "proc_solvedata", procedureName = "proc_solvedata", resultClasses = {SysDbDo.class},
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "datacorp", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "code", type = Integer.class),
//                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "message", type = String.class)
//
//                })
//})
@Entity
@Table(name = "e_f_voucher")
public class EFVoucher {
    private String dataCorp;
    private BigInteger fvoucherid;
    private Long fgroupid;
    private Integer fnumber;
    private String fvoucherno;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fdate;
    private Integer fyearperiod;
    private BigDecimal fdebittotal;
    private BigDecimal fcredittotal;
    private String fexplanation;
    private String fint4Ernalind;
    private String ftranstype;
    private String fusername;
    private Integer fischecked;
    private String fcheckname;
    private Integer fisposted;
    private Integer fattachments;
    private Long fownerid;
    private Long fcheckerid;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date fcreatetime;
    private String fuserno;
    private Integer fsdbid;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date fmodifytime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date fchecktime;

    @Basic
    @Column(name = "data_corp")
    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Id
    @Column(name = "fvoucherid")
    public BigInteger getFvoucherid() {
        return fvoucherid;
    }

    public void setFvoucherid(BigInteger fvoucherid) {
        this.fvoucherid = fvoucherid;
    }

    @Basic
    @Column(name = "fgroupid")
    public Long getFgroupid() {
        return fgroupid;
    }

    public void setFgroupid(Long fgroupid) {
        this.fgroupid = fgroupid;
    }

    @Basic
    @Column(name = "fnumber")
    public Integer getFnumber() {
        return fnumber;
    }

    public void setFnumber(Integer fnumber) {
        this.fnumber = fnumber;
    }

    @Basic
    @Column(name = "fvoucherno")
    public String getFvoucherno() {
        return fvoucherno;
    }

    public void setFvoucherno(String fvoucherno) {
        this.fvoucherno = fvoucherno;
    }

    @Basic
    @Column(name = "fdate")
    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    @Basic
    @Column(name = "fyearperiod")
    public Integer getFyearperiod() {
        return fyearperiod;
    }

    public void setFyearperiod(Integer fyearperiod) {
        this.fyearperiod = fyearperiod;
    }

    @Basic
    @Column(name = "fdebittotal")
    public BigDecimal getFdebittotal() {
        return fdebittotal;
    }

    public void setFdebittotal(BigDecimal fdebittotal) {
        this.fdebittotal = fdebittotal;
    }

    @Basic
    @Column(name = "fcredittotal")
    public BigDecimal getFcredittotal() {
        return fcredittotal;
    }

    public void setFcredittotal(BigDecimal fcredittotal) {
        this.fcredittotal = fcredittotal;
    }

    @Basic
    @Column(name = "fexplanation")
    public String getFexplanation() {
        return fexplanation;
    }

    public void setFexplanation(String fexplanation) {
        this.fexplanation = fexplanation;
    }

    @Basic
    @Column(name = "fint4ernalind")
    public String getFint4Ernalind() {
        return fint4Ernalind;
    }

    public void setFint4Ernalind(String fint4Ernalind) {
        this.fint4Ernalind = fint4Ernalind;
    }

    @Basic
    @Column(name = "ftranstype")
    public String getFtranstype() {
        return ftranstype;
    }

    public void setFtranstype(String ftranstype) {
        this.ftranstype = ftranstype;
    }

    @Basic
    @Column(name = "fusername")
    public String getFusername() {
        return fusername;
    }

    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    @Basic
    @Column(name = "fischecked")
    public Integer getFischecked() {
        return fischecked;
    }

    public void setFischecked(Integer fischecked) {
        this.fischecked = fischecked;
    }

    @Basic
    @Column(name = "fcheckname")
    public String getFcheckname() {
        return fcheckname;
    }

    public void setFcheckname(String fcheckname) {
        this.fcheckname = fcheckname;
    }

    @Basic
    @Column(name = "fisposted")
    public Integer getFisposted() {
        return fisposted;
    }

    public void setFisposted(Integer fisposted) {
        this.fisposted = fisposted;
    }

    @Basic
    @Column(name = "fattachments")
    public Integer getFattachments() {
        return fattachments;
    }

    public void setFattachments(Integer fattachments) {
        this.fattachments = fattachments;
    }

    @Basic
    @Column(name = "fownerid")
    public Long getFownerid() {
        return fownerid;
    }

    public void setFownerid(Long fownerid) {
        this.fownerid = fownerid;
    }

    @Basic
    @Column(name = "fcheckerid")
    public Long getFcheckerid() {
        return fcheckerid;
    }

    public void setFcheckerid(Long fcheckerid) {
        this.fcheckerid = fcheckerid;
    }

    @Basic
    @Column(name = "fcreatetime")
    public Date getFcreatetime() {
        return fcreatetime;
    }

    public void setFcreatetime(Date fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    @Basic
    @Column(name = "fuserno")
    public String getFuserno() {
        return fuserno;
    }

    public void setFuserno(String fuserno) {
        this.fuserno = fuserno;
    }

    @Basic
    @Column(name = "fsdbid")
    public Integer getFsdbid() {
        return fsdbid;
    }

    public void setFsdbid(Integer fsdbid) {
        this.fsdbid = fsdbid;
    }

    @Basic
    @Column(name = "fmodifytime")
    public Date getFmodifytime() {
        return fmodifytime;
    }

    public void setFmodifytime(Date fmodifytime) {
        this.fmodifytime = fmodifytime;
    }

    @Basic
    @Column(name = "fchecktime")
    public Date getFchecktime() {
        return fchecktime;
    }

    public void setFchecktime(Date fchecktime) {
        this.fchecktime = fchecktime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EFVoucher efVoucher = (EFVoucher) o;
        return Objects.equals(dataCorp, efVoucher.dataCorp) &&
                Objects.equals(fvoucherid, efVoucher.fvoucherid) &&
                Objects.equals(fgroupid, efVoucher.fgroupid) &&
                Objects.equals(fnumber, efVoucher.fnumber) &&
                Objects.equals(fvoucherno, efVoucher.fvoucherno) &&
                Objects.equals(fdate, efVoucher.fdate) &&
                Objects.equals(fyearperiod, efVoucher.fyearperiod) &&
                Objects.equals(fdebittotal, efVoucher.fdebittotal) &&
                Objects.equals(fcredittotal, efVoucher.fcredittotal) &&
                Objects.equals(fexplanation, efVoucher.fexplanation) &&
                Objects.equals(fint4Ernalind, efVoucher.fint4Ernalind) &&
                Objects.equals(ftranstype, efVoucher.ftranstype) &&
                Objects.equals(fusername, efVoucher.fusername) &&
                Objects.equals(fischecked, efVoucher.fischecked) &&
                Objects.equals(fcheckname, efVoucher.fcheckname) &&
                Objects.equals(fisposted, efVoucher.fisposted) &&
                Objects.equals(fattachments, efVoucher.fattachments) &&
                Objects.equals(fownerid, efVoucher.fownerid) &&
                Objects.equals(fcheckerid, efVoucher.fcheckerid) &&
                Objects.equals(fcreatetime, efVoucher.fcreatetime) &&
                Objects.equals(fuserno, efVoucher.fuserno) &&
                Objects.equals(fsdbid, efVoucher.fsdbid) &&
                Objects.equals(fmodifytime, efVoucher.fmodifytime) &&
                Objects.equals(fchecktime, efVoucher.fchecktime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataCorp, fvoucherid, fgroupid, fnumber, fvoucherno, fdate, fyearperiod, fdebittotal, fcredittotal, fexplanation, fint4Ernalind, ftranstype, fusername, fischecked, fcheckname, fisposted, fattachments, fownerid, fcheckerid, fcreatetime, fuserno, fsdbid, fmodifytime, fchecktime);
    }
}
