package com.tengzhi.business.finance.constituent.model;

import com.tengzhi.base.jpa.model.BaseModel;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 王翔
 * @create 2020-04-18
 */
@Entity
@Table(name = "e_pz_jylist_lh")
@IdClass(EPzJylistLh.EPzJylistLh_PK.class)
public class EPzJylistLh extends BaseModel {
    @Id
    private String pch;
    @Id
    private String jybh;

    private String xmOrd;
    @Id
    private String xmCode;
    private String xmTvalue;
    private String xmValue;
    private String dataMan;
    private Date dataDate;
    private String dataCorp;
    private String fileId;
    @Transient
    private String xmCodeName;

    @Formula("(f_get_param_name(jybh,xm_code,data_corp))")
    public String getXmCodeName() {
        return xmCodeName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setXmCodeName(String xmCodeName) {
        this.xmCodeName = xmCodeName;
    }

    public String getPch() {
        return pch;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public String getJybh() {
        return jybh;
    }

    public void setJybh(String jybh) {
        this.jybh = jybh;
    }

    public String getXmOrd() {
        return xmOrd;
    }

    public void setXmOrd(String xmOrd) {
        this.xmOrd = xmOrd;
    }

    public String getXmCode() {
        return xmCode;
    }

    public void setXmCode(String xmCode) {
        this.xmCode = xmCode;
    }

    public String getXmTvalue() {
        return xmTvalue;
    }

    public void setXmTvalue(String xmTvalue) {
        this.xmTvalue = xmTvalue;
    }

    public String getXmValue() {
        return xmValue;
    }

    public void setXmValue(String xmValue) {
        this.xmValue = xmValue;
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

    /**
     * 联合主键
     */
    public static class EPzJylistLh_PK implements Serializable {

        private String pch;
        private String jybh;
        private String xmCode;

        public String getPch() {
            return pch;
        }

        public void setPch(String pch) {
            this.pch = pch;
        }

        public String getJybh() {
            return jybh;
        }

        public void setJybh(String jybh) {
            this.jybh = jybh;
        }

        public String getXmCode() {
            return xmCode;
        }

        public void setXmCode(String xmCode) {
            this.xmCode = xmCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EPzJylistLh_PK that = (EPzJylistLh_PK) o;
            return Objects.equals(pch, that.pch) &&
                    Objects.equals(jybh, that.jybh) &&
                    Objects.equals(xmCode, that.xmCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pch, jybh, xmCode);
        }
    }
}
