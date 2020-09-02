package com.tengzhi.business.finance.voucher.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "e_f_voucher_bscategory")
public class EFVoucherBscategory {
    private String dataCorp;
    private BigInteger fevbusid;
    private String fname;
    private String ftype;
    private Integer fisbill;
    private Integer fisqm;
    private Integer famount;
    private String ftable;
    private String funit;
    private String ftaxrate;
    private String fsum;
    private String ftax;
    private String fmoney;
    private String fprice;
    private String fqty;
    private String fsupplierid;
    private String fprojectid;
    private String fitemid;
    private String fitemclassid;
    private String finventoryid;
    private String fempid;
    private String fdeptid;
    private String fcustomid;
    private String fcur;

    @Basic
    @Column(name = "data_corp")
    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Id
    @Column(name = "fevbusid")
    public BigInteger getFevbusid() {
        return fevbusid;
    }

    public void setFevbusid(BigInteger fevbusid) {
        this.fevbusid = fevbusid;
    }

    @Basic
    @Column(name = "fname")
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Basic
    @Column(name = "ftype")
    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    @Basic
    @Column(name = "fisbill")
    public Integer getFisbill() {
        return fisbill;
    }

    public void setFisbill(Integer fisbill) {
        this.fisbill = fisbill;
    }

    @Basic
    @Column(name = "fisqm")
    public Integer getFisqm() {
        return fisqm;
    }

    public void setFisqm(Integer fisqm) {
        this.fisqm = fisqm;
    }

    @Basic
    @Column(name = "famount")
    public Integer getFamount() {
        return famount;
    }

    public void setFamount(Integer famount) {
        this.famount = famount;
    }

    @Basic
    @Column(name = "ftable")
    public String getFtable() {
        return ftable;
    }

    public void setFtable(String ftable) {
        this.ftable = ftable;
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
    @Column(name = "ftaxrate")
    public String getFtaxrate() {
        return ftaxrate;
    }

    public void setFtaxrate(String ftaxrate) {
        this.ftaxrate = ftaxrate;
    }

    @Basic
    @Column(name = "fsum")
    public String getFsum() {
        return fsum;
    }

    public void setFsum(String fsum) {
        this.fsum = fsum;
    }

    @Basic
    @Column(name = "ftax")
    public String getFtax() {
        return ftax;
    }

    public void setFtax(String ftax) {
        this.ftax = ftax;
    }

    @Basic
    @Column(name = "fmoney")
    public String getFmoney() {
        return fmoney;
    }

    public void setFmoney(String fmoney) {
        this.fmoney = fmoney;
    }

    @Basic
    @Column(name = "fprice")
    public String getFprice() {
        return fprice;
    }

    public void setFprice(String fprice) {
        this.fprice = fprice;
    }

    @Basic
    @Column(name = "fqty")
    public String getFqty() {
        return fqty;
    }

    public void setFqty(String fqty) {
        this.fqty = fqty;
    }

    @Basic
    @Column(name = "fsupplierid")
    public String getFsupplierid() {
        return fsupplierid;
    }

    public void setFsupplierid(String fsupplierid) {
        this.fsupplierid = fsupplierid;
    }

    @Basic
    @Column(name = "fprojectid")
    public String getFprojectid() {
        return fprojectid;
    }

    public void setFprojectid(String fprojectid) {
        this.fprojectid = fprojectid;
    }

    @Basic
    @Column(name = "fitemid")
    public String getFitemid() {
        return fitemid;
    }

    public void setFitemid(String fitemid) {
        this.fitemid = fitemid;
    }

    @Basic
    @Column(name = "fitemclassid")
    public String getFitemclassid() {
        return fitemclassid;
    }

    public void setFitemclassid(String fitemclassid) {
        this.fitemclassid = fitemclassid;
    }

    @Basic
    @Column(name = "finventoryid")
    public String getFinventoryid() {
        return finventoryid;
    }

    public void setFinventoryid(String finventoryid) {
        this.finventoryid = finventoryid;
    }

    @Basic
    @Column(name = "fempid")
    public String getFempid() {
        return fempid;
    }

    public void setFempid(String fempid) {
        this.fempid = fempid;
    }

    @Basic
    @Column(name = "fdeptid")
    public String getFdeptid() {
        return fdeptid;
    }

    public void setFdeptid(String fdeptid) {
        this.fdeptid = fdeptid;
    }

    @Basic
    @Column(name = "fcustomid")
    public String getFcustomid() {
        return fcustomid;
    }

    public void setFcustomid(String fcustomid) {
        this.fcustomid = fcustomid;
    }

    @Basic
    @Column(name = "fcur")
    public String getFcur() {
        return fcur;
    }

    public void setFcur(String fcur) {
        this.fcur = fcur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EFVoucherBscategory that = (EFVoucherBscategory) o;
        return fevbusid == that.fevbusid &&
                Objects.equals(dataCorp, that.dataCorp) &&
                Objects.equals(fname, that.fname) &&
                Objects.equals(ftype, that.ftype) &&
                Objects.equals(fisbill, that.fisbill) &&
                Objects.equals(fisqm, that.fisqm) &&
                Objects.equals(famount, that.famount) &&
                Objects.equals(ftable, that.ftable) &&
                Objects.equals(funit, that.funit) &&
                Objects.equals(ftaxrate, that.ftaxrate) &&
                Objects.equals(fsum, that.fsum) &&
                Objects.equals(ftax, that.ftax) &&
                Objects.equals(fmoney, that.fmoney) &&
                Objects.equals(fprice, that.fprice) &&
                Objects.equals(fqty, that.fqty) &&
                Objects.equals(fsupplierid, that.fsupplierid) &&
                Objects.equals(fprojectid, that.fprojectid) &&
                Objects.equals(fitemid, that.fitemid) &&
                Objects.equals(fitemclassid, that.fitemclassid) &&
                Objects.equals(finventoryid, that.finventoryid) &&
                Objects.equals(fempid, that.fempid) &&
                Objects.equals(fdeptid, that.fdeptid) &&
                Objects.equals(fcustomid, that.fcustomid) &&
                Objects.equals(fcur, that.fcur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataCorp, fevbusid, fname, ftype, fisbill, fisqm, famount, ftable, funit, ftaxrate, fsum, ftax, fmoney, fprice, fqty, fsupplierid, fprojectid, fitemid, fitemclassid, finventoryid, fempid, fdeptid, fcustomid, fcur);
    }
}
