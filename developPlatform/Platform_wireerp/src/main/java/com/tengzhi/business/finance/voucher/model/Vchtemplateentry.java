package com.tengzhi.business.finance.voucher.model;



import javax.persistence.*;

@Entity(name = "e_f_voucher_vchtemplateentry")
public class Vchtemplateentry {
    private String dataCorp;
    private String fvctemplateid;
    private Integer fentryid;
    private String fexplanation;
    private Long faccountid;
    private String fcur;
    private Short frate;
    private short fdc;
    private Short fcustomid;
    private Short fdeptid;
    private Short fsupplierid;
    private Short fempid;
    private Short finventoryid;
    private Short fqty;
    private Short fprice;
    private Long faccountid2;
    private String funit;
    private Long fprojectid;
    private String fvalue;
    private String fcashflowitem;
    private String fnumberfullname;
    private Integer fitemclassid;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;


    @Basic
    @Column(name = "data_corp")
    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Basic
    @Column(name = "fvctemplateid")
    public String getFvctemplateid() {
        return fvctemplateid;
    }

    public void setFvctemplateid(String fvctemplateid) {
        this.fvctemplateid = fvctemplateid;
    }

    @Basic
    @Column(name = "fentryid")
    public Integer getFentryid() {
        return fentryid;
    }

    public void setFentryid(Integer fentryid) {
        this.fentryid = fentryid;
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
    @Column(name = "faccountid")
    public Long getFaccountid() {
        return faccountid;
    }

    public void setFaccountid(Long faccountid) {
        this.faccountid = faccountid;
    }

    @Basic
    @Column(name = "fcur")
    public String getFcur() {
        return fcur;
    }

    public void setFcur(String fcur) {
        this.fcur = fcur;
    }

    @Basic
    @Column(name = "frate")
    public Short getFrate() {
        return frate;
    }

    public void setFrate(Short frate) {
        this.frate = frate;
    }

    @Basic
    @Column(name = "fdc")
    public short getFdc() {
        return fdc;
    }

    public void setFdc(short fdc) {
        this.fdc = fdc;
    }

    @Basic
    @Column(name = "fcustomid")
    public Short getFcustomid() {
        return fcustomid;
    }

    public void setFcustomid(Short fcustomid) {
        this.fcustomid = fcustomid;
    }

    @Basic
    @Column(name = "fdeptid")
    public Short getFdeptid() {
        return fdeptid;
    }

    public void setFdeptid(Short fdeptid) {
        this.fdeptid = fdeptid;
    }

    @Basic
    @Column(name = "fsupplierid")
    public Short getFsupplierid() {
        return fsupplierid;
    }

    public void setFsupplierid(Short fsupplierid) {
        this.fsupplierid = fsupplierid;
    }

    @Basic
    @Column(name = "fempid")
    public Short getFempid() {
        return fempid;
    }

    public void setFempid(Short fempid) {
        this.fempid = fempid;
    }

    @Basic
    @Column(name = "finventoryid")
    public Short getFinventoryid() {
        return finventoryid;
    }

    public void setFinventoryid(Short finventoryid) {
        this.finventoryid = finventoryid;
    }

    @Basic
    @Column(name = "fqty")
    public Short getFqty() {
        return fqty;
    }

    public void setFqty(Short fqty) {
        this.fqty = fqty;
    }

    @Basic
    @Column(name = "fprice")
    public Short getFprice() {
        return fprice;
    }

    public void setFprice(Short fprice) {
        this.fprice = fprice;
    }

    @Basic
    @Column(name = "faccountid2")
    public Long getFaccountid2() {
        return faccountid2;
    }

    public void setFaccountid2(Long faccountid2) {
        this.faccountid2 = faccountid2;
    }

    @Basic
    @Column(name = "funit")
    public String getFunit() {
        return funit;
    }

    public void setFunit(String funit) {
        this.funit = funit;
    }

    @Basic
    @Column(name = "fprojectid")
    public Long getFprojectid() {
        return fprojectid;
    }

    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    @Basic
    @Column(name = "fvalue")
    public String getFvalue() {
        return fvalue;
    }

    public void setFvalue(String fvalue) {
        this.fvalue = fvalue;
    }

    @Basic
    @Column(name = "fcashflowitem")
    public String getFcashflowitem() {
        return fcashflowitem;
    }

    public void setFcashflowitem(String fcashflowitem) {
        this.fcashflowitem = fcashflowitem;
    }

    @Basic
    @Column(name = "fnumberfullname")
    public String getFnumberfullname() {
        return fnumberfullname;
    }

    public void setFnumberfullname(String fnumberfullname) {
        this.fnumberfullname = fnumberfullname;
    }

    @Basic
    @Column(name = "fitemclassid")
    public Integer getFitemclassid() {
        return fitemclassid;
    }

    public void setFitemclassid(Integer fitemclassid) {
        this.fitemclassid = fitemclassid;
    }


    @Column(name = "fid")
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    @Transient
    private String _state;

    @Transient
    public String get_state() { return _state; }

    public void set_state(String _state) {
        this._state = _state;
    }
}
