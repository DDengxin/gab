package com.tengzhi.business.cg.yw.purchaseReceiptNotice.model;

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

@Entity
@Table(name = "e_ck_receiving_notice")
@IdClass(ECkReceivingNotice.ECkReceivingNotice_PK.class)
public class ECkReceivingNotice {

	@Id
	private String shNote;
	
	@Id
	private String shCode;
	
	
	private String shCustomer,shLib,shContract,shMo,shAct,shType,shFlag,shBz,shHs;
	private BigDecimal shSl,shCeiling,shLower,shTax,shPrice;
	private String dataMan,dataCorp;

	private String htMo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date shRq;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:MM:DD",timezone="GMT+8")
	private Date dataRq;
	
	public String getShNote() {
		return shNote;
	}
	public void setShNote(String shNote) {
		this.shNote = shNote;
	}
	public String getShCustomer() {
		return shCustomer;
	}
	public void setShCustomer(String shCustomer) {
		this.shCustomer = shCustomer;
	}
	public String getShLib() {
		return shLib;
	}
	public void setShLib(String shLib) {
		this.shLib = shLib;
	}
	public String getShContract() {
		return shContract;
	}
	public void setShContract(String shContract) {
		this.shContract = shContract;
	}
	public String getShCode() {
		return shCode;
	}
	public void setShCode(String shCode) {
		this.shCode = shCode;
	}
	public String getShMo() {
		return shMo;
	}
	public void setShMo(String shMo) {
		this.shMo = shMo;
	}
	public String getShAct() {
		return shAct;
	}
	public void setShAct(String shAct) {
		this.shAct = shAct;
	}
	public String getShType() {
		return shType;
	}
	public void setShType(String shType) {
		this.shType = shType;
	}
	public BigDecimal getShSl() {
		return shSl;
	}
	public void setShSl(BigDecimal shSl) {
		this.shSl = shSl;
	}
	public BigDecimal getShCeiling() {
		return shCeiling;
	}
	public void setShCeiling(BigDecimal shCeiling) {
		this.shCeiling = shCeiling;
	}
	public BigDecimal getShLower() {
		return shLower;
	}
	public void setShLower(BigDecimal shLower) {
		this.shLower = shLower;
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
	public Date getShRq() {
		return shRq;
	}
	public void setShRq(Date shRq) {
		this.shRq = shRq;
	}

	public Date getDataRq() {
		return dataRq;
	}
	public void setDataRq(Date dataRq) {
		this.dataRq = dataRq;
	}
	public String getShBz() {
		return shBz;
	}
	public void setShBz(String shBz) {
		this.shBz = shBz;
	}
	public String getShHs() {
		return shHs;
	}
	public void setShHs(String shHs) {
		this.shHs = shHs;
	}
	public BigDecimal getShTax() {
		return shTax;
	}
	public void setShTax(BigDecimal shTax) {
		this.shTax = shTax;
	}
	public BigDecimal getShPrice() {
		return shPrice;
	}
	public void setShPrice(BigDecimal shPrice) {
		this.shPrice = shPrice;
	}

	public String getHtMo() {
		return htMo;
	}

	public void setHtMo(String htMo) {
		this.htMo = htMo;
	}

	@Transient
	private String _state,id;
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
	public String getShFlag() {
		return shFlag;
	}
	public void setShFlag(String shFlag) {
		this.shFlag = shFlag;
	}

	@Transient
	private String cpcodeName,cpcodeSize,cpcodeXp,cpcodeBz,cpcodeSizeEn,customername,libname,actname,cpcodeFl;
	@Transient
	private BigDecimal shJe;
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
	public String getCpcodeXp() {
		return cpcodeXp;
	}
	public void setCpcodeXp(String cpcodeXp) {
		this.cpcodeXp = cpcodeXp;
	}
	public String getCpcodeBz() {
		return cpcodeBz;
	}
	public void setCpcodeBz(String cpcodeBz) {
		this.cpcodeBz = cpcodeBz;
	}
	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}
	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getLibname() {
		return libname;
	}
	public void setLibname(String libname) {
		this.libname = libname;
	}
	public String getActname() {
		return actname;
	}
	public void setActname(String actname) {
		this.actname = actname;
	}
	public BigDecimal getShJe() {
		return shJe;
	}
	public void setShJe(BigDecimal shJe) {
		this.shJe = shJe;
	}

	public String getCpcodeFl() {
		return cpcodeFl;
	}
	public void setCpcodeFl(String cpcodeFl) {
		this.cpcodeFl = cpcodeFl;
	}

	public static class ECkReceivingNotice_PK implements Serializable {
    	
    	private String shNote;
    	private String shCode;
    	
		public String getShNote() {
			return shNote;
		}


		public void setShNote(String shNote) {
			this.shNote = shNote;
		}


		public String getShCode() {
			return shCode;
		}


		public void setShCode(String shCode) {
			this.shCode = shCode;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((shCode == null) ? 0 : shCode.hashCode());
			result = prime * result + ((shNote == null) ? 0 : shNote.hashCode());
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
			ECkReceivingNotice_PK other = (ECkReceivingNotice_PK) obj;
			if (shCode == null) {
				if (other.shCode != null) {
                    return false;
                }
			} else if (!shCode.equals(other.shCode)) {
                return false;
            }
			if (shNote == null) {
                return other.shNote == null;
			} else {
                return shNote.equals(other.shNote);
            }
        }


		public ECkReceivingNotice_PK(String shNote, String shCode) {
			super();
			this.shNote = shNote;
			this.shCode = shCode;
		}


		public ECkReceivingNotice_PK() {
			super();
		}


		
    	
    }
	
	
}
