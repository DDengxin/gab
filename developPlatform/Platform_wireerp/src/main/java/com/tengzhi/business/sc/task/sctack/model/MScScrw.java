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
@Table(name = "m_sc_scrw")
@IdClass(value=MScScrw.MScScrw_PK.class)
public class MScScrw extends BaseModel {

	@Id
	private String scMo,htMo;
	
	private String code,scyq,scflag,dataMan,dataCorp,scGylx,scStype,standard,scCj,bzyl,tdyl,qtyl,lsyl,htCustomer,htNo;
	
	private BigDecimal scjs,scsl,sczl,sczq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date rq,scjq,sctqq,khjq,xtjq,tzjq,pdrq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date dataRq;

	public String getScMo() {
		return scMo;
	}

	public void setScMo(String scMo) {
		this.scMo = scMo;
	}

	public String getHtMo() {
		return htMo;
	}

	public void setHtMo(String htMo) {
		this.htMo = htMo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getScyq() {
		return scyq;
	}

	public void setScyq(String scyq) {
		this.scyq = scyq;
	}

	public String getScflag() {
		return scflag;
	}

	public void setScflag(String scflag) {
		this.scflag = scflag;
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

	public BigDecimal getScjs() {
		return scjs;
	}

	public void setScjs(BigDecimal scjs) {
		this.scjs = scjs;
	}

	public BigDecimal getScsl() {
		return scsl;
	}

	public void setScsl(BigDecimal scsl) {
		this.scsl = scsl;
	}

	public BigDecimal getSczl() {
		return sczl;
	}

	public void setSczl(BigDecimal sczl) {
		this.sczl = sczl;
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public Date getScjq() {
		return scjq;
	}

	public void setScjq(Date scjq) {
		this.scjq = scjq;
	}

	public Date getPdrq() {
		return pdrq;
	}

	public void setPdrq(Date pdrq) {
		this.pdrq = pdrq;
	}

	public String getBzyl() {
		return bzyl;
	}

	public void setBzyl(String bzyl) {
		this.bzyl = bzyl;
	}

	public String getTdyl() {
		return tdyl;
	}

	public void setTdyl(String tdyl) {
		this.tdyl = tdyl;
	}

	public String getQtyl() {
		return qtyl;
	}

	public void setQtyl(String qtyl) {
		this.qtyl = qtyl;
	}

	public String getLsyl() {
		return lsyl;
	}

	public void setLsyl(String lsyl) {
		this.lsyl = lsyl;
	}

	public String getHtCustomer() {
		return htCustomer;
	}

	public void setHtCustomer(String htCustomer) {
		this.htCustomer = htCustomer;
	}

	public Date getDataRq() {
		return dataRq;
	}

	public void setDataRq(Date dataRq) {
		this.dataRq = dataRq;
	}

	public Date getKhjq() {
		return khjq;
	}

	public void setKhjq(Date khjq) {
		this.khjq = khjq;
	}

	public Date getXtjq() {
		return xtjq;
	}

	public void setXtjq(Date xtjq) {
		this.xtjq = xtjq;
	}

	public Date getTzjq() {
		return tzjq;
	}

	public void setTzjq(Date tzjq) {
		this.tzjq = tzjq;
	}

	public String getScGylx() {
		return scGylx;
	}

	public void setScGylx(String scGylx) {
		this.scGylx = scGylx;
	}

	public String getScStype() {
		return scStype;
	}

	public void setScStype(String scStype) {
		this.scStype = scStype;
	}

	public String getHtNo() {
		return htNo;
	}

	public void setHtNo(String htNo) {
		this.htNo = htNo;
	}
	public static class MScScrw_PK implements Serializable{
		
		private String scMo,htMo;
		
		
		public String getScMo() {
			return scMo;
		}


		public void setScMo(String scMo) {
			this.scMo = scMo;
		}


		public String getHtMo() {
			return htMo;
		}


		public void setHtMo(String htMo) {
			this.htMo = htMo;
		}


		public MScScrw_PK(String scMo,String htMo) {
			setScMo(scMo);
			setHtMo(htMo);
			
		}
		
		public MScScrw_PK() {
			
		}
		
		@Override
		public int hashCode() {
			final int prime=31;
			int result=1;
			result =prime*result+((scMo==null)? 0:scMo.hashCode());
			result =prime*result +((htMo==null)? 0:htMo.hashCode());
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
			MScScrw_PK other = (MScScrw_PK) obj;
			if (scMo == null) {
				if (other.scMo != null) {
                    return false;
                }
			} else if (!scMo.equals(other.scMo)) {
                return false;
            }
			if (htMo == null) {
                return other.htMo == null;
			} else {
                return htMo.equals(other.htMo);
            }
        }
    	
	}
	
	@Transient
	private float htsl;

	public float getHtsl() {
		return htsl;
	}

	public void setHtsl(float htsl) {
		this.htsl = htsl;
	}
	
	@Transient
	private String cpcodeName,cpcodeFl,cpcodeBz,cpcodeSize,gylxname,stypename,cpcodeSizeEn,sizeAndSizeEn;

	public String getCpcodeName() {
		return cpcodeName;
	}

	public void setCpcodeName(String cpcodeName) {
		this.cpcodeName = cpcodeName;
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

	public String getCpcodeSize() {
		return cpcodeSize;
	}

	public void setCpcodeSize(String cpcodeSize) {
		this.cpcodeSize = cpcodeSize;
	}

	public String getGylxname() {
		return gylxname;
	}

	public void setGylxname(String gylxname) {
		this.gylxname = gylxname;
	}

	public String getStypename() {
		return stypename;
	}

	public void setStypename(String stypename) {
		this.stypename = stypename;
	}

	public BigDecimal getSczq() {
		return sczq;
	}

	public void setSczq(BigDecimal sczq) {
		this.sczq = sczq;
	}

	public Date getSctqq() {
		return sctqq;
	}

	public void setSctqq(Date sctqq) {
		this.sctqq = sctqq;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getScCj() {
		return scCj;
	}

	public void setScCj(String scCj) {
		this.scCj = scCj;
	}

	public String getSizeAndSizeEn() {
		return sizeAndSizeEn;
	}

	public void setSizeAndSizeEn(String sizeAndSizeEn) {
		this.sizeAndSizeEn = sizeAndSizeEn;
	}

	@Transient
	private  String size,_state;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}

	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}
}
