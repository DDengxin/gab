package com.tengzhi.business.mesGz.gzda1.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-27
 */
@Entity
@Table(name = "m_gz_mjda")
public class MGzMjda {
    private String mjNote;
    @Temporal(TemporalType.TIMESTAMP)
    private Date mjRq;
    private String mjCode;
    private String mjCpcode;
    
    private BigDecimal mjSl, mjCl, mjSc;
    private String mjAct;
    private String mjFlag;
    private String dataMan;
    private String mjKsize;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDate;
    private String dataCorp;
    private String mjStatus;
    private String lyPack;

    public MGzMjda() {
    }

    public String getMjAct() {
        return mjAct;
    }

    public void setMjAct(String mjAct) {
        this.mjAct = mjAct;
    }

    public MGzMjda(String mjNote, Date mjRq, String mjCode, String mjCpcode, BigDecimal mjSl, BigDecimal mjCl, BigDecimal mjSc, String mjFlag, String dataMan, Date dataDate, String dataCorp, String mjStatus,String mjAct) {
        this.mjNote = mjNote;
        this.mjRq = mjRq;
        this.mjCode = mjCode;
        this.mjCpcode = mjCpcode;
        this.mjSl = mjSl;
        this.mjCl = mjCl;
        this.mjSc = mjSc;
        this.mjFlag = mjFlag;
        this.dataMan = dataMan;
        this.dataDate = dataDate;
        this.dataCorp = dataCorp;
        this.mjStatus = mjStatus;
        this.mjAct = mjAct;
    }

    @Basic
    @Column(name = "mj_note", nullable = true, length = 255)
    public String getMjNote() {
        return mjNote;
    }

    public void setMjNote(String mjNote) {
        this.mjNote = mjNote;
    }

    @Basic
    @Column(name = "mj_rq", nullable = true)
    public Date getMjRq() {
        return mjRq;
    }

    public void setMjRq(Date mjRq) {
        this.mjRq = mjRq;
    }

    @Id
    @Column(name = "mj_code", nullable = false, length = 255)
    public String getMjCode() {
        return mjCode;
    }

    public void setMjCode(String mjCode) {
        this.mjCode = mjCode;
    }

    @Basic
    @Column(name = "mj_cpcode", nullable = true, length = 255)
    public String getMjCpcode() {
        return mjCpcode;
    }

    public void setMjCpcode(String mjCpcode) {
        this.mjCpcode = mjCpcode;
    }

   

    public BigDecimal getMjSl() {
		return mjSl;
	}

	public void setMjSl(BigDecimal mjSl) {
		this.mjSl = mjSl;
	}

	public BigDecimal getMjCl() {
		return mjCl;
	}

	public void setMjCl(BigDecimal mjCl) {
		this.mjCl = mjCl;
	}

	public BigDecimal getMjSc() {
		return mjSc;
	}

	public void setMjSc(BigDecimal mjSc) {
		this.mjSc = mjSc;
	}

	public String getLyPack() {
		return lyPack;
	}

	public void setLyPack(String lyPack) {
		this.lyPack = lyPack;
	}

	public String getMjKsize() {
		return mjKsize;
	}

	public void setMjKsize(String mjKsize) {
		this.mjKsize = mjKsize;
	}

	@Basic
    @Column(name = "mj_flag", nullable = true, length = 255)
    public String getMjFlag() {
        return mjFlag;
    }

    public void setMjFlag(String mjFlag) {
        this.mjFlag = mjFlag;
    }

    @Basic
    @Column(name = "data_man", nullable = true, length = 255)
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

    @Basic
    @Column(name = "data_corp", nullable = true, length = 255)
    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Basic
    @Column(name = "mj_status", nullable = true, length = 255)
    public String getMjStatus() {
        return mjStatus;
    }

    public void setMjStatus(String mjStatus) {
        this.mjStatus = mjStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MGzMjda mGzMjda = (MGzMjda) o;
        return Objects.equals(mjNote, mGzMjda.mjNote) &&
                Objects.equals(mjRq, mGzMjda.mjRq) &&
                Objects.equals(mjCode, mGzMjda.mjCode) &&
                Objects.equals(mjCpcode, mGzMjda.mjCpcode) &&
                Objects.equals(mjSl, mGzMjda.mjSl) &&
                Objects.equals(mjCl, mGzMjda.mjCl) &&
                Objects.equals(mjSc, mGzMjda.mjSc) &&
                Objects.equals(mjFlag, mGzMjda.mjFlag) &&
                Objects.equals(dataMan, mGzMjda.dataMan) &&
                Objects.equals(dataDate, mGzMjda.dataDate) &&
                Objects.equals(dataCorp, mGzMjda.dataCorp) &&
                Objects.equals(mjStatus, mGzMjda.mjStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mjNote, mjRq, mjCode, mjCpcode, mjSl, mjCl, mjSc, mjFlag, dataMan, dataDate, dataCorp, mjStatus);
    }
}
