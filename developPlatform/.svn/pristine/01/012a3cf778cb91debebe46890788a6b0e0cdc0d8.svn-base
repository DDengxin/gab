package com.tengzhi.business.js.cpbom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "e_js_cpcode_bom")
@IdClass(value = EJsCpcode.EJsCpcode_PK.class)
public class EJsCpcode {

	@Id
	private String cpcodeId;
	@Id
	private String cpcodeUid;
	@Id
	private String cpcodeBom;
	private String cpcodeType;
	private String cpcodeStype;
	private BigDecimal cpcodeBl;
	private BigDecimal cpcodeSh;
	private String cpcodeSm;
	private String dataMan;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataDate;
	private String dataCorp;
	private String cpcodeFlag;






	public String getCpcodeId() {
		return cpcodeId;
	}
	public void setCpcodeId(String cpcodeId) {
		this.cpcodeId = cpcodeId;
	}
	public String getCpcodeUid() {
		return cpcodeUid;
	}
	public void setCpcodeUid(String cpcodeUid) {
		this.cpcodeUid = cpcodeUid;
	}
	public String getCpcodeType() {
		return cpcodeType;
	}
	public void setCpcodeType(String cpcodeType) {
		this.cpcodeType = cpcodeType;
	}
	public String getCpcodeStype() {
		return cpcodeStype;
	}
	public void setCpcodeStype(String cpcodeStype) {
		this.cpcodeStype = cpcodeStype;
	}
	public BigDecimal getCpcodeBl() {
		return cpcodeBl;
	}
	public void setCpcodeBl(BigDecimal cpcodeBl) {
		this.cpcodeBl = cpcodeBl;
	}
	public BigDecimal getCpcodeSh() {
		return cpcodeSh;
	}
	public void setCpcodeSh(BigDecimal cpcodeSh) {
		this.cpcodeSh = cpcodeSh;
	}
	public String getCpcodeSm() {
		return cpcodeSm;
	}
	public void setCpcodeSm(String cpcodeSm) {
		this.cpcodeSm = cpcodeSm;
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
	public String getCpcodeBom() {
		return cpcodeBom;
	}
	public void setCpcodeBom(String cpcodeBom) {
		this.cpcodeBom = cpcodeBom;
	}
	public String getCpcodeFlag() {
		return cpcodeFlag;
	}
	public void setCpcodeFlag(String cpcodeFlag) {
		this.cpcodeFlag = cpcodeFlag;
	}



	public static class EJsCpcode_PK implements Serializable {
		private String cpcodeId;
		private String cpcodeUid;
		private String cpcodeBom;
		public String getCpcodeId() {
			return cpcodeId;
		}

		public void setCpcodeId(String cpcodeId) {
			this.cpcodeId = cpcodeId;
		}

		public String getCpcodeUid() {
			return cpcodeUid;
		}

		public void setCpcodeUid(String cpcodeUid) {
			this.cpcodeUid = cpcodeUid;
		}

		public String getCpcodeBom() {
			return cpcodeBom;
		}

		public void setCpcodeBom(String cpcodeBom) {
			this.cpcodeBom = cpcodeBom;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cpcodeBom == null) ? 0 : cpcodeBom.hashCode());
			result = prime * result + ((cpcodeId == null) ? 0 : cpcodeId.hashCode());
			result = prime * result + ((cpcodeUid == null) ? 0 : cpcodeUid.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
                return true;
            }
			if (obj == null) {
                return false;
            }
			if (getClass() != obj.getClass()) {
                return false;
            }
			EJsCpcode_PK other = (EJsCpcode_PK) obj;
			if (cpcodeBom == null) {
				if (other.cpcodeBom != null) {
                    return false;
                }
			} else if (!cpcodeBom.equals(other.cpcodeBom)) {
                return false;
            }
			if (cpcodeId == null) {
				if (other.cpcodeId != null) {
                    return false;
                }
			} else if (!cpcodeId.equals(other.cpcodeId)) {
                return false;
            }
			if (cpcodeUid == null) {
                return other.cpcodeUid == null;
			} else {
                return cpcodeUid.equals(other.cpcodeUid);
            }
        }

		public EJsCpcode_PK() {
			
		}
	}
}
