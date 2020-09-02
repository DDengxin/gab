package com.tengzhi.business.sc.task.sctack.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

@Entity
@Table(name = "m_sc_scrw_wl")
@IdClass(value=MScScrwWl.MScScrwWl_PK.class)
public class MScScrwWl extends BaseModel {

	@Id
	private String scrwGx;
	@Id
	private String scrwMo,wlCode;
	
	private String wlType,wlPh,wlLib,wlTno,wlSm,impol,wlGxnote;
	
	private BigDecimal wlJs,wlSl,wlZl,bl;

	public String getScrwGx() {
		return scrwGx;
	}

	public void setScrwGx(String scrwGx) {
		this.scrwGx = scrwGx;
	}

	public String getScrwMo() {
		return scrwMo;
	}

	public void setScrwMo(String scrwMo) {
		this.scrwMo = scrwMo;
	}

	public String getWlCode() {
		return wlCode;
	}

	public void setWlCode(String wlCode) {
		this.wlCode = wlCode;
	}

	public String getWlType() {
		return wlType;
	}

	public void setWlType(String wlType) {
		this.wlType = wlType;
	}

	public String getWlPh() {
		return wlPh;
	}

	public void setWlPh(String wlPh) {
		this.wlPh = wlPh;
	}

	public String getWlLib() {
		return wlLib;
	}

	public void setWlLib(String wlLib) {
		this.wlLib = wlLib;
	}

	public String getWlTno() {
		return wlTno;
	}

	public void setWlTno(String wlTno) {
		this.wlTno = wlTno;
	}

	public String getWlSm() {
		return wlSm;
	}

	public void setWlSm(String wlSm) {
		this.wlSm = wlSm;
	}

	public BigDecimal getWlJs() {
		return wlJs;
	}

	public void setWlJs(BigDecimal wlJs) {
		this.wlJs = wlJs;
	}

	public BigDecimal getWlSl() {
		return wlSl;
	}

	public void setWlSl(BigDecimal wlSl) {
		this.wlSl = wlSl;
	}

	public BigDecimal getWlZl() {
		return wlZl;
	}

	public void setWlZl(BigDecimal wlZl) {
		this.wlZl = wlZl;
	}
	
	public BigDecimal getBl() {
		return bl;
	}

	public void setBl(BigDecimal bl) {
		this.bl = bl;
	}



	public static class MScScrwWl_PK implements Serializable{
		
		private String wlGxnote;
		
		private String scrwMo,wlCode;

		public String getWlGxnote() {
			return wlGxnote;
		}

		public void setWlGxnote(String wlGxnote) {
			this.wlGxnote = wlGxnote;
		}

		public String getScrwMo() {
			return scrwMo;
		}

		public void setScrwMo(String scrwMo) {
			this.scrwMo = scrwMo;
		}

		public String getWlCode() {
			return wlCode;
		}

		public void setWlCode(String wlCode) {
			this.wlCode = wlCode;
		}

		public MScScrwWl_PK(String wlGxnote,String scrwMo,String wlCode) {
			setWlGxnote(wlGxnote);
			setScrwMo(scrwMo);
			setWlCode(wlCode);
			
		}
		
		public MScScrwWl_PK() {
			
		}
		
		@Override
		public int hashCode() {
			final int prime=31;
			int result=1;
			result =prime*result+((wlGxnote==null)? 0:wlGxnote.hashCode());
			result =prime*result +((scrwMo==null)? 0:scrwMo.hashCode());
			result =prime*result+((wlCode==null)? 0:wlCode.hashCode());
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
			MScScrwWl_PK other = (MScScrwWl_PK) obj;
			if (wlGxnote == null) {
				if (other.wlGxnote != null) {
                    return false;
                }
			} else if (!wlGxnote.equals(other.wlGxnote)) {
                return false;
            }
			if (scrwMo == null) {
				if (other.scrwMo != null) {
                    return false;
                }
			} else if (!scrwMo.equals(other.scrwMo)) {
                return false;
            }
			if (wlCode == null) {
                return other.wlCode == null;
			} else {
                return wlCode.equals(other.wlCode);
            }
        }
    	
	}
	
	@Transient
	private String typename,libname,gxname;
	
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getLibname() {
		return libname;
	}

	public void setLibname(String libname) {
		this.libname = libname;
	}
	
	public String getGxname() {
		return gxname;
	}

	public void setGxname(String gxname) {
		this.gxname = gxname;
	}



	@Transient
	private String cpcodeId,cpcodeName,cpcodeSize,cpcodeFl,cpcodeBz,cpcodeXp,cpcodeCheck,cpcode01,cpcode02,cpcode03,cpcodeSizeEn;
	@Transient
	private BigDecimal cpcodePb;

	public String getCpcodeId() {
		return cpcodeId;
	}
	public void setCpcodeId(String cpcodeId) {
		this.cpcodeId = cpcodeId;
	}
	public String getCpcodeName() {
		return cpcodeName;
	}
	public void setCpcodeName(String cpcodeName) {
		this.cpcodeName = cpcodeName;
	}
	public String getCpcodeSize() {
		return cpcodeSize;
	}
	public void setCpcodeSize(String cpcodeSize) {
		this.cpcodeSize = cpcodeSize;
	}
	public String getCpcodeFl() {
		return cpcodeFl;
	}
	public void setCpcodeFl(String cpcodeFl) {
		this.cpcodeFl = cpcodeFl;
	}
	
	public String getCpcodeBz() {
		return cpcodeBz;
	}
	public void setCpcodeBz(String cpcodeBz) {
		this.cpcodeBz = cpcodeBz;
	}
	public String getCpcodeXp() {
		return cpcodeXp;
	}
	public void setCpcodeXp(String cpcodeXp) {
		this.cpcodeXp = cpcodeXp;
	}
	public String getCpcodeCheck() {
		return cpcodeCheck;
	}
	public void setCpcodeCheck(String cpcodeCheck) {
		this.cpcodeCheck = cpcodeCheck;
	}
	public String getCpcode01() {
		return cpcode01;
	}
	public void setCpcode01(String cpcode01) {
		this.cpcode01 = cpcode01;
	}
	public String getCpcode02() {
		return cpcode02;
	}
	public void setCpcode02(String cpcode02) {
		this.cpcode02 = cpcode02;
	}
	public String getCpcode03() {
		return cpcode03;
	}
	public void setCpcode03(String cpcode03) {
		this.cpcode03 = cpcode03;
	}
	public BigDecimal getCpcodePb() {
		return cpcodePb;
	}
	public void setCpcodePb(BigDecimal cpcodePb) {
		this.cpcodePb = cpcodePb;
	}
	
	
	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}
	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}

	public String getImpol() {
		return impol;
	}

	public void setImpol(String impol) {
		this.impol = impol;
	}

	@Transient
	private String _state;
	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String getWlGxnote() {
		return wlGxnote;
	}

	public void setWlGxnote(String wlGxnote) {
		this.wlGxnote = wlGxnote;
	}
}