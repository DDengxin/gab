package com.tengzhi.business.finance.constituent.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;
import com.tengzhi.business.finance.constituent.model.EPzJylistLh;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author lgl
 * 业务-品质-检验任务
 */
@Entity
@Table(name = "e_pz_jylist")
public class EPzJyList extends BaseModel {


    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq;
    @Id
    private String pch;
    private String code;
    private String scNo;
    private String tph;
    private String pchType;
    private String codeType;
    private String stype;
    private BigDecimal tjs;
    private BigDecimal tsl;
    private BigDecimal tzl;
    private String note;

    @Transient
    private String dataMan,jybm;
    
    public String getDataMan() {
		return dataMan;
	}

	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}

	public String getJybm() {
		return jybm;
	}

	public void setJybm(String jybm) {
		this.jybm = jybm;
	}

	/**
     * @OneToMany 一对多关系指定
     * targetEntity:指定需要关联的实体
     * mappedBy:关联到那张表
     */
  /*  @OneToMany(targetEntity =EPzJylistLh.class,  mappedBy = "pch",fetch = FetchType.EAGER)
    public List<EPzJylistLh> ePzJylistLhList;

    public List<EPzJylistLh> getePzJylistLhList() {
        return ePzJylistLhList;
    }

    public void setePzJylistLhList(List<EPzJylistLh> ePzJylistLhList) {
        this.ePzJylistLhList = ePzJylistLhList;
    }
*/
    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getPch() {
        return pch;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScNo() {
        return scNo;
    }

    public void setScNo(String scNo) {
        this.scNo = scNo;
    }

    public String getTph() {
        return tph;
    }

    public void setTph(String tph) {
        this.tph = tph;
    }

    public String getPchType() {
        return pchType;
    }

    public void setPchType(String pchType) {
        this.pchType = pchType;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public BigDecimal getTjs() {
        return tjs;
    }

    public void setTjs(BigDecimal tjs) {
        this.tjs = tjs;
    }

    public BigDecimal getTsl() {
        return tsl;
    }

    public void setTsl(BigDecimal tsl) {
        this.tsl = tsl;
    }

    public BigDecimal getTzl() {
        return tzl;
    }

    public void setTzl(BigDecimal tzl) {
        this.tzl = tzl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
