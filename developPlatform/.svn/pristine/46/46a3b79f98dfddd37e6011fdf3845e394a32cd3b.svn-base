package com.tengzhi.business.js.cpgy.scgx.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_sc_scgxmx")
@IdClass(value = MScScgxmx.MScScgxmx_PK.class)
public class MScScgxmx {
	
	@Id
	private String gxId,gxxmId;
	
	@Id
	private Integer gxxmOrd;
	
	private String gxxmName,gxxmValuetype,gxxmValuedo,gxxmSm;

	public String getGxId() {
		return gxId;
	}

	public void setGxId(String gxId) {
		this.gxId = gxId;
	}

	public Integer getGxxmOrd() {
		return gxxmOrd;
	}

	public void setGxxmOrd(Integer gxxmOrd) {
		this.gxxmOrd = gxxmOrd;
	}

	public String getGxxmName() {
		return gxxmName;
	}

	public void setGxxmName(String gxxmName) {
		this.gxxmName = gxxmName;
	}

	public String getGxxmValuetype() {
		return gxxmValuetype;
	}

	public void setGxxmValuetype(String gxxmValuetype) {
		this.gxxmValuetype = gxxmValuetype;
	}

	public String getGxxmValuedo() {
		return gxxmValuedo;
	}

	public void setGxxmValuedo(String gxxmValuedo) {
		this.gxxmValuedo = gxxmValuedo;
	}
	public String getGxxmSm() {
		return gxxmSm;
	}

	public void setGxxmSm(String gxxmSm) {
		this.gxxmSm = gxxmSm;
	}
	
	public String getGxxmId() {
		return gxxmId;
	}

	public void setGxxmId(String gxxmId) {
		this.gxxmId = gxxmId;
	}



public static class MScScgxmx_PK implements Serializable {
    	

		private String gxId,gxxmId;
		private Integer gxxmOrd;
		
		
		public String getGxId() {
			return gxId;
		}


		public void setGxId(String gxId) {
			this.gxId = gxId;
		}


		public String getGxxmId() {
			return gxxmId;
		}


		public void setGxxmId(String gxxmId) {
			this.gxxmId = gxxmId;
		}


		public Integer getGxxmOrd() {
			return gxxmOrd;
		}


		public void setGxxmOrd(Integer gxxmOrd) {
			this.gxxmOrd = gxxmOrd;
		}


		public MScScgxmx_PK(String gxId,Integer gxxmOrd,String gxxmId) {
			setGxId(gxId);
			setGxxmId(gxxmId);
			setGxxmOrd(gxxmOrd);
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((gxId == null) ? 0 : gxId.hashCode());
			result = prime * result + ((gxxmId == null) ? 0 : gxxmId.hashCode());
			result = prime * result + ((gxxmOrd == null) ? 0 : gxxmOrd.hashCode());
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
			MScScgxmx_PK other = (MScScgxmx_PK) obj;
			if (gxId == null) {
				if (other.gxId != null) {
                    return false;
                }
			} else if (!gxxmId.equals(other.gxxmId)) {
                return false;
            }
			if (gxxmOrd == null) {
                return other.gxxmOrd == null;
			} else {
                return gxxmOrd.equals(other.gxxmOrd);
            }
        }
    	
		public MScScgxmx_PK() {
			
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

	
	
	

}
