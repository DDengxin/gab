package com.tengzhi.business.cg.yw.purchaseReceipt.model;

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

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "e_ck_in")
@IdClass(value = ECkIn.ECkIn_PK.class)
public class ECkIn {
	@Id
	private String inNote;
	@Id
	private String inPack;
	private String inCustomer,inAct,inCode,inBpack,inContract,inKfcontract,inKfcode,inLib,inCheckflag,inMonth,inType,inLuono,inVnote,inBz,inHs;
	private String dataMan,dataCorp,inFlag,brand,inCklib,inLyr,inMan,inPh,inXh,inKw,inRemarks;
	private BigDecimal inSl,inZl,inPrice,inTax,inRate,inJs;

	private String inOriginalPack;

	private String inContractDetailed;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date inRq;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date dataDate,inDate;
	
	@Transient
	private String _state,id;
	
	
	
	public String getInNote() {
		return inNote;
	}



	public void setInNote(String inNote) {
		this.inNote = inNote;
	}



	public String getInPack() {
		return inPack;
	}



	public void setInPack(String inPack) {
		this.inPack = inPack;
	}



	public String getInCustomer() {
		return inCustomer;
	}



	public void setInCustomer(String inCustomer) {
		this.inCustomer = inCustomer;
	}



	public String getInAct() {
		return inAct;
	}



	public void setInAct(String inAct) {
		this.inAct = inAct;
	}



	public String getInCode() {
		return inCode;
	}



	public void setInCode(String inCode) {
		this.inCode = inCode;
	}



	public String getInBpack() {
		return inBpack;
	}



	public void setInBpack(String inBpack) {
		this.inBpack = inBpack;
	}



	public String getInContract() {
		return inContract;
	}



	public void setInContract(String inContract) {
		this.inContract = inContract;
	}



	public String getInKfcontract() {
		return inKfcontract;
	}



	public void setInKfcontract(String inKfcontract) {
		this.inKfcontract = inKfcontract;
	}



	public String getInKfcode() {
		return inKfcode;
	}



	public void setInKfcode(String inKfcode) {
		this.inKfcode = inKfcode;
	}


	public String getInLib() {
		return inLib;
	}



	public void setInLib(String inLib) {
		this.inLib = inLib;
	}



	public String getInCheckflag() {
		return inCheckflag;
	}



	public void setInCheckflag(String inCheckflag) {
		this.inCheckflag = inCheckflag;
	}



	public String getInLuono() {
		return inLuono;
	}



	public void setInLuono(String inLuono) {
		this.inLuono = inLuono;
	}



	public String getInType() {
		return inType;
	}



	public void setInType(String inType) {
		this.inType = inType;
	}


	public String getInVnote() {
		return inVnote;
	}



	public void setInVnote(String inVnote) {
		this.inVnote = inVnote;
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



	public BigDecimal getInJs() {
		return inJs;
	}



	public void setInJs(BigDecimal inJs) {
		this.inJs = inJs;
	}



	public BigDecimal getInSl() {
		return inSl;
	}



	public void setInSl(BigDecimal inSl) {
		this.inSl = inSl;
	}



	public BigDecimal getInZl() {
		return inZl;
	}



	public void setInZl(BigDecimal inZl) {
		this.inZl = inZl;
	}



	public BigDecimal getInPrice() {
		return inPrice;
	}



	public void setInPrice(BigDecimal inPrice) {
		this.inPrice = inPrice;
	}



	public Date getInRq() {
		return inRq;
	}



	public void setInRq(Date inRq) {
		this.inRq = inRq;
	}



	public Date getDataDate() {
		return dataDate;
	}



	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}


	public String getInBz() {
		return inBz;
	}



	public void setInBz(String inBz) {
		this.inBz = inBz;
	}



	public BigDecimal getInTax() {
		return inTax;
	}



	public void setInTax(BigDecimal inTax) {
		this.inTax = inTax;
	}



	public BigDecimal getInRate() {
		return inRate;
	}



	public void setInRate(BigDecimal inRate) {
		this.inRate = inRate;
	}



	public String get_state() {
		return _state;
	}



	public void set_state(String _state) {
		this._state = _state;
	}



public String getInFlag() {
		return inFlag;
	}



	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}



public String getInMonth() {
		return inMonth;
	}



	public void setInMonth(String inMonth) {
		this.inMonth = inMonth;
	}



public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getInCklib() {
		return inCklib;
	}



	public void setInCklib(String inCklib) {
		this.inCklib = inCklib;
	}



	public String getInLyr() {
		return inLyr;
	}



	public void setInLyr(String inLyr) {
		this.inLyr = inLyr;
	}



	public String getInPh() {
		return inPh;
	}



	public void setInPh(String inPh) {
		this.inPh = inPh;
	}



	public String getInXh() {
		return inXh;
	}



	public void setInXh(String inXh) {
		this.inXh = inXh;
	}

	public String getInKw() {
		return inKw;
	}



	public void setInKw(String inKw) {
		this.inKw = inKw;
	}

	public String getInOriginalPack() {
		return inOriginalPack;
	}

	public void setInOriginalPack(String inOriginalPack) {
		this.inOriginalPack = inOriginalPack;
	}
	

	public String getInRemarks() {
		return inRemarks;
	}



	public void setInRemarks(String inRemarks) {
		this.inRemarks = inRemarks;
	}

	public String getInContractDetailed() {
		return inContractDetailed;
	}

	public void setInContractDetailed(String inContractDetailed) {
		this.inContractDetailed = inContractDetailed;
	}

	public static class ECkIn_PK implements Serializable {
    	
    	private String inNote;
    	private String inPack;
    	
		
		public String getInNote() {
			return inNote;
		}


		public void setInNote(String inNote) {
			this.inNote = inNote;
		}


		public String getInPack() {
			return inPack;
		}


		public void setInPack(String inPack) {
			this.inPack = inPack;
		}


	


		public ECkIn_PK(String inNote,String inPack) {
			setInNote(inNote);
			setInPack(inPack);
			
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((inNote == null) ? 0 : inNote.hashCode());
			result = prime * result + ((inPack == null) ? 0 : inPack.hashCode());
			
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
			ECkIn_PK other = (ECkIn_PK) obj;
			if (inNote == null) {
				if (other.inNote != null) {
                    return false;
                }
			} else if (!inNote.equals(other.inNote)) {
                return false;
            }
			if (inPack == null) {
                return other.inPack == null;
			} else {
                return inPack.equals(other.inPack);
            }
        }
    	
		public ECkIn_PK() {
			
		}
    	
    }



public String getId() {
	return id;
}



public void setId(String id) {
	this.id = id;
}



public String getInHs() {
	return inHs;
}



public void setInHs(String inHs) {
	this.inHs = inHs;
}

@Transient
private String cpcodeId,cpcodeName,cpcodeSize,cpcodeFl,cpcodeBz,cpcodeXp,cpcodeCheck,cpcode01,cpcode02,cpcode03,cpcodeSizeEn,cpcodeFlname;
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

@Transient
private String cgStype;



public String getCgStype() {
	return cgStype;
}



public void setCgStype(String cgStype) {
	this.cgStype = cgStype;
}

@Transient
private String inactname,inbzname,inlibname,datamanname,inmanname;

@Formula("(f_getname('GETDWEXP',in_customer,'',data_corp))")
private String incustomername;




public String getInactname() {
	return inactname;
}



public void setInactname(String inactname) {
	this.inactname = inactname;
}



public String getInbzname() {
	return inbzname;
}



public void setInbzname(String inbzname) {
	this.inbzname = inbzname;
}


@Transient
public String getIncustomername() {
	return incustomername;
}



public void setIncustomername(String incustomername) {
	this.incustomername = incustomername;
}



public String getInlibname() {
	return inlibname;
}



public void setInlibname(String inlibname) {
	this.inlibname = inlibname;
}



public String getDatamanname() {
	return datamanname;
}



public void setDatamanname(String datamanname) {
	this.datamanname = datamanname;
}



public String getCpcodeSizeEn() {
	return cpcodeSizeEn;
}



public void setCpcodeSizeEn(String cpcodeSizeEn) {
	this.cpcodeSizeEn = cpcodeSizeEn;
}


public String getInMan() {
	return inMan;
}



public void setInMan(String inMan) {
	this.inMan = inMan;
}



public Date getInDate() {
	return inDate;
}



public void setInDate(Date inDate) {
	this.inDate = inDate;
}



public String getInmanname() {
	return inmanname;
}



public void setInmanname(String inmanname) {
	this.inmanname = inmanname;
}

@Transient
private String rksl,intypename;
@Transient
private BigDecimal inje;



public String getRksl() {
	return rksl;
}



public void setRksl(String rksl) {
	this.rksl = rksl;
}



public String getIntypename() {
	return intypename;
}



public void setIntypename(String intypename) {
	this.intypename = intypename;
}



public BigDecimal getInje() {
	return inje;
}



public void setInje(BigDecimal inje) {
	this.inje = inje;
}



public String getCpcodeFlname() {
	return cpcodeFlname;
}



public void setCpcodeFlname(String cpcodeFlname) {
	this.cpcodeFlname = cpcodeFlname;
}





	
}
