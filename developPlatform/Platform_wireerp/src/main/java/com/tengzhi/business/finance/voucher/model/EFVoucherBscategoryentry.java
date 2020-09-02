package com.tengzhi.business.finance.voucher.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "e_f_voucher_bscategoryentry")
public class EFVoucherBscategoryentry {
    private String dataCorp;
    private BigInteger fevbusid;
    private String fbindfield;
    private String fbindvalue;
    private Long fid;
    private String _state;
    @Transient
    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }
    @Basic
    @Column(name = "data_corp")
    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Basic
    @Column(name = "fevbusid")
    public BigInteger getFevbusid() {
        return fevbusid;
    }

    public void setFevbusid(BigInteger fevbusid) {
        this.fevbusid = fevbusid;
    }

    @Basic
    @Column(name = "fbindfield")
    public String getFbindfield() {
        return fbindfield;
    }

    public void setFbindfield(String fbindfield) {
        this.fbindfield = fbindfield;
    }

    @Basic
    @Column(name = "fbindvalue")
    public String getFbindvalue() {
        return fbindvalue;
    }

    public void setFbindvalue(String fbindvalue) {
        this.fbindvalue = fbindvalue;
    }

    @Id
    @Column(name = "fid")
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EFVoucherBscategoryentry that = (EFVoucherBscategoryentry) o;
        return fevbusid == that.fevbusid &&
                Objects.equals(dataCorp, that.dataCorp) &&
                Objects.equals(fbindfield, that.fbindfield) &&
                Objects.equals(fbindvalue, that.fbindvalue) &&
                Objects.equals(fid, that.fid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataCorp, fevbusid, fbindfield, fbindvalue, fid);
    }
}
