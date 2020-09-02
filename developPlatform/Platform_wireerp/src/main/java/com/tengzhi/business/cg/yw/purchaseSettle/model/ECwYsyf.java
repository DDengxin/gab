package com.tengzhi.business.cg.yw.purchaseSettle.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "e_cw_ysyf")
@IdClass(value = ECwYsyf.ECwYsyf_PK.class)
public class ECwYsyf {
	@Id
	private String cwNote;

	private String cwItem;
	@Id
	private String cwCode;
	@Id
	private String cwAct;
	
	private String cwType,cwCk,cwBz,cwFph,cwFkh,cwSm,cwMonth,cwStype,cwDw,cwLib;
	private String dataMan,dataCorp,cwFlag;
	private BigDecimal cwSl,cwZl,cwPrice,cwSje,cwSe,cwHl,cwShl,cwJs;
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date cwRq,cwFkrq;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date dataDate;
	
	@Transient
	private String _state,id,inPack,cpcodeName,cpcodeSize,dwName;
	
	



	public String getCwNote() {
		return cwNote;
	}



	public void setCwNote(String cwNote) {
		this.cwNote = cwNote;
	}



	public String getCwItem() {
		return cwItem;
	}



	public void setCwItem(String cwItem) {
		this.cwItem = cwItem;
	}



	public String getCwCode() {
		return cwCode;
	}



	public void setCwCode(String cwCode) {
		this.cwCode = cwCode;
	}



	public String getCwAct() {
		return cwAct;
	}



	public void setCwAct(String cwAct) {
		this.cwAct = cwAct;
	}



	public String getCwType() {
		return cwType;
	}



	public void setCwType(String cwType) {
		this.cwType = cwType;
	}



	public String getCwCk() {
		return cwCk;
	}



	public void setCwCk(String cwCk) {
		this.cwCk = cwCk;
	}



	public String getCwBz() {
		return cwBz;
	}



	public void setCwBz(String cwBz) {
		this.cwBz = cwBz;
	}



	public String getCwFph() {
		return cwFph;
	}



	public void setCwFph(String cwFph) {
		this.cwFph = cwFph;
	}



	public String getCwFkh() {
		return cwFkh;
	}



	public void setCwFkh(String cwFkh) {
		this.cwFkh = cwFkh;
	}



	public String getCwSm() {
		return cwSm;
	}



	public void setCwSm(String cwSm) {
		this.cwSm = cwSm;
	}



	public String getCwMonth() {
		return cwMonth;
	}



	public void setCwMonth(String cwMonth) {
		this.cwMonth = cwMonth;
	}



	public String getCwStype() {
		return cwStype;
	}



	public void setCwStype(String cwStype) {
		this.cwStype = cwStype;
	}



	public String getCwDw() {
		return cwDw;
	}



	public void setCwDw(String cwDw) {
		this.cwDw = cwDw;
	}



	public String getDataMan() {
		return dataMan;
	}



	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}



	public String getDataCorp() {
		return dataCorp;
	}



	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}



	public BigDecimal getCwSl() {
		return cwSl;
	}



	public void setCwSl(BigDecimal cwSl) {
		this.cwSl = cwSl;
	}



	public BigDecimal getCwZl() {
		return cwZl;
	}



	public void setCwZl(BigDecimal cwZl) {
		this.cwZl = cwZl;
	}



	public BigDecimal getCwPrice() {
		return cwPrice;
	}



	public void setCwPrice(BigDecimal cwPrice) {
		this.cwPrice = cwPrice;
	}



	public BigDecimal getCwSje() {
		return cwSje;
	}



	public void setCwSje(BigDecimal cwSje) {
		this.cwSje = cwSje;
	}



	public BigDecimal getCwSe() {
		return cwSe;
	}



	public void setCwSe(BigDecimal cwSe) {
		this.cwSe = cwSe;
	}



	public BigDecimal getCwHl() {
		return cwHl;
	}



	public void setCwHl(BigDecimal cwHl) {
		this.cwHl = cwHl;
	}



	public BigDecimal getCwShl() {
		return cwShl;
	}



	public void setCwShl(BigDecimal cwShl) {
		this.cwShl = cwShl;
	}



	public BigDecimal getCwJs() {
		return cwJs;
	}



	public void setCwJs(BigDecimal cwJs) {
		this.cwJs = cwJs;
	}



	public Date getCwRq() {
		return cwRq;
	}



	public void setCwRq(Date cwRq) {
		this.cwRq = cwRq;
	}



	public Date getCwFkrq() {
		return cwFkrq;
	}



	public void setCwFkrq(Date cwFkrq) {
		this.cwFkrq = cwFkrq;
	}



	public Date getDataDate() {
		return dataDate;
	}



	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}



	public String get_state() {
		return _state;
	}



	public void set_state(String _state) {
		this._state = _state;
	}


	public String getId() {
		return id;
	}
	
	
	
	public void setId(String id) {
		this.id = id;
	}


	public String getCwFlag() {
		return cwFlag;
	}



	public void setCwFlag(String cwFlag) {
		this.cwFlag = cwFlag;
	}



	public String getInPack() {
		return inPack;
	}



	public void setInPack(String inPack) {
		this.inPack = inPack;
	}

	public String getDwName() {
		return dwName;
	}



	public void setDwName(String dwName) {
		this.dwName = dwName;
	}
	

	public String getCwLib() {
		return cwLib;
	}



	public void setCwLib(String cwLib) {
		this.cwLib = cwLib;
	}


	public static class ECwYsyf_PK implements Serializable {
		private static final long serialVersionUID = -172754250417288838L;
    	private String cwNote,cwItem,cwCode,cwAct;

		public String getCwNote() {
			return cwNote;
		}


		public void setCwNote(String cwNote) {
			this.cwNote = cwNote;
		}


		public String getCwItem() {
			return cwItem;
		}


		public void setCwItem(String cwItem) {
			this.cwItem = cwItem;
		}


		public String getCwCode() {
			return cwCode;
		}


		public void setCwCode(String cwCode) {
			this.cwCode = cwCode;
		}


		public String getCwAct() {
			return cwAct;
		}


		public void setCwAct(String cwAct) {
			this.cwAct = cwAct;
		}


		public ECwYsyf_PK(String cwNote,String cwItem,String cwAct,String cwCode) {
			setCwNote(cwNote);
			setCwItem(cwItem);
			setCwAct(cwAct);
			setCwCode(cwCode);
			
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cwNote == null) ? 0 : cwNote.hashCode());
			result = prime * result + ((cwItem == null) ? 0 : cwItem.hashCode());
			result = prime * result + ((cwAct == null) ? 0 : cwAct.hashCode());
			result = prime * result + ((cwCode == null) ? 0 : cwCode.hashCode());
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
			ECwYsyf_PK other = (ECwYsyf_PK) obj;
			if (cwNote == null) {
				if (other.cwNote != null) {
                    return false;
                }
			} else if (!cwNote.equals(other.cwNote)) {
                return false;
            }
			if (cwItem == null) {
				if (other.cwItem != null) {
                    return false;
                }
			} else if (!cwItem.equals(other.cwItem)) {
                return false;
            }
			if (cwAct == null) {
				if (other.cwAct != null) {
                    return false;
                }
			} else if (!cwAct.equals(other.cwAct)) {
                return false;
            }
			if (cwCode == null) {
                return other.cwCode == null;
			} else {
                return cwCode.equals(other.cwCode);
            }
        }
    	
		public ECwYsyf_PK() {
			
		}
    	
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


	@Transient
	private String cpcodeId,cpcodeFl,cpcodeBz,cpcodeXp,cpcodeCheck,cpcode01,cpcode02,cpcode03,cpcodeSizeEn;





	public String getCpcodeId() {
		return cpcodeId;
	}



	public void setCpcodeId(String cpcodeId) {
		this.cpcodeId = cpcodeId;
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



	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}



	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}
	
	@Transient
	private String cwactname,cwtypename,cwlibname,cwcustomername,bzname,cgStype;
	@Transient
	private BigDecimal je;

	public String getCwactname() {
		return cwactname;
	}

	public void setCwactname(String cwactname) {
		this.cwactname = cwactname;
	}

	public String getCwtypename() {
		return cwtypename;
	}

	public void setCwtypename(String cwtypename) {
		this.cwtypename = cwtypename;
	}

	public String getCwlibname() {
		return cwlibname;
	}

	public void setCwlibname(String cwlibname) {
		this.cwlibname = cwlibname;
	}

	public BigDecimal getJe() {
		return je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	public String getCwcustomername() {
		return cwcustomername;
	}

	public void setCwcustomername(String cwcustomername) {
		this.cwcustomername = cwcustomername;
	}



	public String getBzname() {
		return bzname;
	}



	public void setBzname(String bzname) {
		this.bzname = bzname;
	}



	public String getCgStype() {
		return cgStype;
	}



	public void setCgStype(String cgStype) {
		this.cgStype = cgStype;
	}
	
	
	
}