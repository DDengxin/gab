package com.tengzhi.business.cg.yw.purchaseRequisition.model;

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
@Table(name = "e_cg_requisition")
@IdClass(value = EcgRequisition.EcgRequisition_PK.class)
public class EcgRequisition {
	@Id
	private String sqNote;
	@Id
	private String code;
	
	private String sqType,sqJjcd,sqDept,sqMan,sqSm,flag,dgnote,cgStype,dataMan,dataCorp,sqPurpose,brand;
	private BigDecimal sl,zl;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date sqRq,sqJq;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date dataDate;
	
	public String getCgStype() {
		return cgStype;
	}
	public void setCgStype(String cgStype) {
		this.cgStype = cgStype;
	}
	public String getSqNote() {
		return sqNote;
	}
	public void setSqNote(String sqNote) {
		this.sqNote = sqNote;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSqType() {
		return sqType;
	}
	public void setSqType(String sqType) {
		this.sqType = sqType;
	}
	public String getSqJjcd() {
		return sqJjcd;
	}
	public void setSqJjcd(String sqJjcd) {
		this.sqJjcd = sqJjcd;
	}
	public String getSqDept() {
		return sqDept;
	}
	public void setSqDept(String sqDept) {
		this.sqDept = sqDept;
	}
	public String getSqMan() {
		return sqMan;
	}
	public void setSqMan(String sqMan) {
		this.sqMan = sqMan;
	}
	public String getSqSm() {
		return sqSm;
	}
	public void setSqSm(String sqSm) {
		this.sqSm = sqSm;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDgnote() {
		return dgnote;
	}
	public void setDgnote(String dgnote) {
		this.dgnote = dgnote;
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
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	public BigDecimal getZl() {
		return zl;
	}
	public void setZl(BigDecimal zl) {
		this.zl = zl;
	}
	public Date getSqRq() {
		return sqRq;
	}
	public void setSqRq(Date sqRq) {
		this.sqRq = sqRq;
	}
	public Date getSqJq() {
		return sqJq;
	}
	public void setSqJq(Date sqJq) {
		this.sqJq = sqJq;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}

	public String getSqPurpose() {
		return sqPurpose;
	}
	public void setSqPurpose(String sqPurpose) {
		this.sqPurpose = sqPurpose;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}


public static class EcgRequisition_PK implements Serializable {
    	
    	private String sqNote;
    	private String code;
	
		public String getSqNote() {
			return sqNote;
		}
		public void setSqNote(String sqNote) {
			this.sqNote = sqNote;
		}
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public EcgRequisition_PK(String sqNote,String code) {
			setSqNote(sqNote);
			setCode(code);
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((sqNote == null) ? 0 : sqNote.hashCode());
			result = prime * result + ((code == null) ? 0 : code.hashCode());
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
			EcgRequisition_PK other = (EcgRequisition_PK) obj;
			if (sqNote== null) {
				if (other.sqNote != null) {
                    return false;
                }
			} else if (!sqNote.equals(other.sqNote)) {
                return false;
            }
			if (code == null) {
                return other.code == null;
			} else {
                return code.equals(other.code);
            }
        }
    	
		public EcgRequisition_PK() {
			
		}
    	
    }
	
	@Transient
	private String _state;

	public String get_state() {
		return _state;
	}
	public void set_state(String _state) {
		this._state = _state;
	}
	
	
	@Transient
	private String cpcodeId,cpcodeName,cpcodeSize,cpcodeFl,cpcodeBz,cpcodeXp,cpcodeCheck,cpcode01,cpcode02,cpcode03,cpcodeSizeEn,intypename;
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
	private String jjcdname,typename,deptname,sqmanname,datamanname;

	public String getJjcdname() {
		return jjcdname;
	}
	public void setJjcdname(String jjcdname) {
		this.jjcdname = jjcdname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getSqmanname() {
		return sqmanname;
	}
	public void setSqmanname(String sqmanname) {
		this.sqmanname = sqmanname;
	}
	public String getDatamanname() {
		return datamanname;
	}
	public void setDatamanname(String datamanname) {
		this.datamanname = datamanname;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}
	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}
	public String getIntypename() {
		return intypename;
	}
	public void setIntypename(String intypename) {
		this.intypename = intypename;
	}
	
	
	
}
