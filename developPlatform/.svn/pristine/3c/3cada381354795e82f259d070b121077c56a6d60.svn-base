package com.tengzhi.business.js.cpgy.scgy.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_sc_scgymx")
@IdClass(value = MScScgymx.MScScgymx_PK.class)
public class MScScgymx {
	
	@Id
	private String gyId,gxId;
	
	@Id
	private Integer gxOrd;
	
	private String gxName,gyName,gxJsyq,gyJysx,gxSm,gxFlag;



public String getGyId() {
		return gyId;
	}

	public void setGyId(String gyId) {
		this.gyId = gyId;
	}

	public String getGxId() {
		return gxId;
	}

	public void setGxId(String gxId) {
		this.gxId = gxId;
	}

	public Integer getGxOrd() {
		return gxOrd;
	}

	public void setGxOrd(Integer gxOrd) {
		this.gxOrd = gxOrd;
	}

	public String getGxName() {
		return gxName;
	}

	public void setGxName(String gxName) {
		this.gxName = gxName;
	}

	public String getGxJsyq() {
		return gxJsyq;
	}

	public void setGxJsyq(String gxJsyq) {
		this.gxJsyq = gxJsyq;
	}


	public String getGyJysx() {
		return gyJysx;
	}

	public void setGyJysx(String gyJysx) {
		this.gyJysx = gyJysx;
	}

	public String getGxSm() {
		return gxSm;
	}

	public void setGxSm(String gxSm) {
		this.gxSm = gxSm;
	}

	public String getGxFlag() {
		return gxFlag;
	}

	public void setGxFlag(String gxFlag) {
		this.gxFlag = gxFlag;
	}
	

	public String getGyName() {
			return gyName;
		}

	public void setGyName(String gyName) {
		this.gyName = gyName;
	}


	public static class MScScgymx_PK implements Serializable {
    	

		private String gyId,gxId;
		private Integer gxOrd;
		
		

		public String getGyId() {
			return gyId;
		}


		public void setGyId(String gyId) {
			this.gyId = gyId;
		}


		public String getGxId() {
			return gxId;
		}


		public void setGxId(String gxId) {
			this.gxId = gxId;
		}


		public Integer getGxOrd() {
			return gxOrd;
		}


		public void setGxOrd(Integer gxOrd) {
			this.gxOrd = gxOrd;
		}


		public MScScgymx_PK(String gyId,Integer gxOrd,String gxId) {
			setGyId(gyId);
			setGxId(gxId);
			setGxOrd(gxOrd);
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((gyId == null) ? 0 : gyId.hashCode());
			result = prime * result + ((gxId == null) ? 0 : gxId.hashCode());
			result = prime * result + ((gxOrd == null) ? 0 : gxOrd.hashCode());
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
			MScScgymx_PK other = (MScScgymx_PK) obj;
			if (gyId == null) {
				if (other.gyId != null) {
                    return false;
                }
			} else if (!gxId.equals(other.gxId)) {
                return false;
            }
			if (gxOrd == null) {
                return other.gxOrd == null;
			} else {
                return gxOrd.equals(other.gxOrd);
            }
        }
    	
		public MScScgymx_PK() {
			
		}
    	
    }
	
	@Transient
	private String _state,valuetypename,valuedoname;
	
	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String getValuetypename() {
		return valuetypename;
	}

	public void setValuetypename(String valuetypename) {
		this.valuetypename = valuetypename;
	}

	public String getValuedoname() {
		return valuedoname;
	}

	public void setValuedoname(String valuedoname) {
		this.valuedoname = valuedoname;
	}
	
	@Transient
	private String scjs,scsl,sczl;


	public String getScjs() {
		return scjs;
	}

	public void setScjs(String scjs) {
		this.scjs = scjs;
	}

	public String getScsl() {
		return scsl;
	}

	public void setScsl(String scsl) {
		this.scsl = scsl;
	}

	public String getSczl() {
		return sczl;
	}

	public void setSczl(String sczl) {
		this.sczl = sczl;
	}

}
