package com.tengzhi.business.tooling.grantandrecover.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
@Entity
@Table(name = "m_gz_mjrecord")
@IdClass(MGzMjrecordPK.class)
public class MGzMjrecord {
    private String mjNote;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date mjRq;
    private String mjAct;
    private String mjCode;
    private String mjKsize;
    private String mjFlag;
    private String dataMan;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDate;
    private String dataCorp;
    private String mjRemarks;
    private String lyMan;
    private Integer mjSl;
    private String mjXksize;
    private String mjKid;
    private String lyDept;
    private String _state;
    private String lyDeptName;
    private String lyManName;
    private String dataManName;
    private String mjCpcode;
    private String lyPack;

    @Transient
    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getMjCpcode() {
        return mjCpcode;
    }

    public void setMjCpcode(String mjCpcode) {
        this.mjCpcode = mjCpcode;
    }

    public MGzMjrecord() {
    }

    public String getLyDeptName() {
        return lyDeptName;
    }

    public void setLyDeptName(String lyDeptName) {
        this.lyDeptName = lyDeptName;
    }

    public String getLyManName() {
        return lyManName;
    }

    public void setLyManName(String lyManName) {
        this.lyManName = lyManName;
    }

    public String getDataManName() {
        return dataManName;
    }

    public void setDataManName(String dataManName) {
        this.dataManName = dataManName;
    }

    public MGzMjrecord(String mjNote, Date mjRq, String mjAct, String mjCode, String mjKsize, String mjFlag, String dataMan, Date dataDate, String dataCorp, String mjRemarks, String lyMan, Integer mjSl, String mjXksize, String mjKid, String lyDept,String ly_dept_name,String ly_man_name,String data_man_name,String mj_cpcode) {
        this.mjNote = mjNote;
        this.mjRq = mjRq;
        this.mjAct = mjAct;
        this.mjCode = mjCode;
        this.mjKsize = mjKsize;
        this.mjFlag = mjFlag;
        this.dataMan = dataMan;
        this.dataDate = dataDate;
        this.dataCorp = dataCorp;
        this.mjRemarks = mjRemarks;
        this.lyMan = lyMan;
        this.mjSl = mjSl;
        this.mjXksize = mjXksize;
        this.mjKid = mjKid;
        this.lyDept = lyDept;
        this.lyDeptName=ly_dept_name;
        this.lyManName=ly_man_name;
        this.dataManName=data_man_name;
        this.mjCpcode=mj_cpcode;
    }

    @Id
    public String getMjNote() {
        return mjNote;
    }

    public void setMjNote(String mjNote) {
        this.mjNote = mjNote;
    }


    public Date getMjRq() {
        return mjRq;
    }

    public void setMjRq(Date mjRq) {
        this.mjRq = mjRq;
    }

    @Id
    public String getMjAct() {
        return mjAct;
    }

    public void setMjAct(String mjAct) {
        this.mjAct = mjAct;
    }

    @Id
    public String getMjCode() {
        return mjCode;
    }

    public void setMjCode(String mjCode) {
        this.mjCode = mjCode;
    }


    public String getMjKsize() {
        return mjKsize;
    }

    public void setMjKsize(String mjKsize) {
        this.mjKsize = mjKsize;
    }


    public String getMjFlag() {
        return mjFlag;
    }

    public void setMjFlag(String mjFlag) {
        this.mjFlag = mjFlag;
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


    public String getMjRemarks() {
        return mjRemarks;
    }

    public void setMjRemarks(String mjRemarks) {
        this.mjRemarks = mjRemarks;
    }


    public String getLyMan() {
        return lyMan;
    }

    public void setLyMan(String lyMan) {
        this.lyMan = lyMan;
    }


    public Integer getMjSl() {
        return mjSl;
    }

    public void setMjSl(Integer mjSl) {
        this.mjSl = mjSl;
    }

    public String getMjXksize() {
        return mjXksize;
    }

    public void setMjXksize(String mjXksize) {
        this.mjXksize = mjXksize;
    }


    public String getMjKid() {
        return mjKid;
    }

    public void setMjKid(String mjKid) {
        this.mjKid = mjKid;
    }


    public String getLyDept() {
        return lyDept;
    }

    public void setLyDept(String lyDept) {
        this.lyDept = lyDept;
    }

	public String getLyPack() {
		return lyPack;
	}

	public void setLyPack(String lyPack) {
		this.lyPack = lyPack;
	}


}