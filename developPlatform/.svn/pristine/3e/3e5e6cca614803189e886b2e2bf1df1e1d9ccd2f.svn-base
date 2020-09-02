package com.tengzhi.business.production.productionSite.siteTask.model;

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
import com.tengzhi.base.jpa.model.BaseModel;

@Entity
@Table(name = "m_sc_gclist_sl")
@IdClass(value =MScGclistSl.MScGclistSl_PK.class)
public class MScGclistSl extends BaseModel {
	
	@Id
	private String bgc,sgc,scrwNo,bgcGx,gxNote;
	
	private String bgcCt,wlCode,wlName,wlSize,wlPh,gcCl,vnote,dataCorp;
	
	private String doMan,wlType;
	
	private BigDecimal sgcJs,sgcSl,sgcZl;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date doDate;


	public String getBgc() {
		return bgc;
	}


	public void setBgc(String bgc) {
		this.bgc = bgc;
	}


	public String getSgc() {
		return sgc;
	}


	public void setSgc(String sgc) {
		this.sgc = sgc;
	}


	public String getBgcGx() {
		return bgcGx;
	}


	public void setBgcGx(String bgcGx) {
		this.bgcGx = bgcGx;
	}



	public String getBgcCt() {
		return bgcCt;
	}


	public void setBgcCt(String bgcCt) {
		this.bgcCt = bgcCt;
	}


	public String getScrwNo() {
		return scrwNo;
	}


	public void setScrwNo(String scrwNo) {
		this.scrwNo = scrwNo;
	}


	public String getDoMan() {
		return doMan;
	}


	public void setDoMan(String doMan) {
		this.doMan = doMan;
	}


	public String getWlType() {
		return wlType;
	}


	public void setWlType(String wlType) {
		this.wlType = wlType;
	}


	public BigDecimal getSgcJs() {
		return sgcJs;
	}


	public void setSgcJs(BigDecimal sgcJs) {
		this.sgcJs = sgcJs;
	}


	public BigDecimal getSgcSl() {
		return sgcSl;
	}


	public void setSgcSl(BigDecimal sgcSl) {
		this.sgcSl = sgcSl;
	}


	public BigDecimal getSgcZl() {
		return sgcZl;
	}


	public void setSgcZl(BigDecimal sgcZl) {
		this.sgcZl = sgcZl;
	}


	public Date getDoDate() {
		return doDate;
	}


	public void setDoDate(Date doDate) {
		this.doDate = doDate;
	}

	public String getWlCode() {
		return wlCode;
	}


	public void setWlCode(String wlCode) {
		this.wlCode = wlCode;
	}


	public String getWlName() {
		return wlName;
	}


	public void setWlName(String wlName) {
		this.wlName = wlName;
	}


	public String getWlSize() {
		return wlSize;
	}


	public void setWlSize(String wlSize) {
		this.wlSize = wlSize;
	}


	public String getWlPh() {
		return wlPh;
	}


	public void setWlPh(String wlPh) {
		this.wlPh = wlPh;
	}


	public String getGcCl() {
		return gcCl;
	}


	public void setGcCl(String gcCl) {
		this.gcCl = gcCl;
	}


	public String getVnote() {
		return vnote;
	}


	public void setVnote(String vnote) {
		this.vnote = vnote;
	}


	public String getDataCorp() {
		return dataCorp;
	}


	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	public String getGxNote() {
		return gxNote;
	}

	public void setGxNote(String gxNote) {
		this.gxNote = gxNote;
	}

	public static class MScGclistSl_PK implements Serializable {
    	
    	private String bgc,sgc,scrwNo,bgcGx;
    	@Temporal(TemporalType.TIMESTAMP)
    	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    	private Date doDate;
    	
		public String getBgc() {
			return bgc;
		}


		public void setBgc(String bgc) {
			this.bgc = bgc;
		}


		public String getSgc() {
			return sgc;
		}


		public void setSgc(String sgc) {
			this.sgc = sgc;
		}


		public String getScrwNo() {
			return scrwNo;
		}


		public void setScrwNo(String scrwNo) {
			this.scrwNo = scrwNo;
		}


		public String getBgcGx() {
			return bgcGx;
		}


		public void setBgcGx(String bgcGx) {
			this.bgcGx = bgcGx;
		}

		public Date getDoDate() {
			return doDate;
		}

		public void setDoDate(Date doDate) {
			this.doDate = doDate;
		}

		public MScGclistSl_PK(String sgc,String scrwNo,String bgcGx,Date doDate) {
			setSgc(sgc);
			setScrwNo(scrwNo);
			setBgcGx(bgcGx);
			setDoDate(doDate);
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((bgc == null) ? 0 : bgc.hashCode());
			result = prime * result + ((sgc == null) ? 0 : sgc.hashCode());
			result = prime * result + ((scrwNo == null) ? 0 : scrwNo.hashCode());
			result = prime * result + ((bgcGx == null) ? 0 : bgcGx.hashCode());
			result = prime * result + ((doDate == null) ? 0 : doDate.hashCode());
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
			MScGclistSl_PK other = (MScGclistSl_PK) obj;
			if (bgc == null) {
				if (other.bgc != null) {
                    return false;
                }
			} else if (!bgc.equals(other.bgc)) {
                return false;
            }
			if (sgc == null) {
				if (other.sgc != null) {
                    return false;
                }
			} else if (!sgc.equals(other.sgc)) {
                return false;
            }
			if (scrwNo == null) {
				if (other.scrwNo != null) {
                    return false;
                }
			} else if (!scrwNo.equals(scrwNo)) {
                return false;
            }
			if (bgcGx == null) {
				if (other.bgcGx != null) {
                    return false;
                }
			} else if (!bgcGx.equals(bgcGx)) {
                return false;
            }
			if (doDate == null) {
                return other.doDate == null;
			} else {
                return doDate.equals(doDate);
            }
        }
    	
		public MScGclistSl_PK() {
			
		}
    	
    }
	
}