package com.tengzhi.business.quality.qualityDetection.productDetection.model;

import java.io.Serializable;
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
@Table(name = "e_pz_jylist_jc")
@IdClass(EPzJylistJc.EPzJylistJc_PK.class)
public class EPzJylistJc extends BaseModel {

	@Id
    private String pch;

    @Id
    private String jybh;

    @Id
    private String xmCode;

    private String xmOrd;

    private String xmTvalue;
    private String xmValue;
    private String dataMan;
    private String dataCorp;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dataDate;

    @Transient
    private String _state;
    
    public String getPch() {
        return pch;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public String getJybh() {
        return jybh;
    }

    public void setJybh(String jybh) {
        this.jybh = jybh;
    }

    public String getXmCode() {
		return xmCode;
	}

	public void setXmCode(String xmCode) {
		this.xmCode = xmCode;
	}

	public String getXmOrd() {
		return xmOrd;
	}

	public void setXmOrd(String xmOrd) {
		this.xmOrd = xmOrd;
	}

	public String getXmTvalue() {
		return xmTvalue;
	}

	public void setXmTvalue(String xmTvalue) {
		this.xmTvalue = xmTvalue;
	}

	public String getXmValue() {
		return xmValue;
	}

	public void setXmValue(String xmValue) {
		this.xmValue = xmValue;
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


	public static class EPzJylistJc_PK implements Serializable {

        private String pch;
        private String jybh;
        private String xmCode;

        public String getPch() {
            return pch;
        }

        public void setPch(String pch) {
            this.pch = pch;
        }

        public String getJybh() {
            return jybh;
        }

        public void setJybh(String jybh) {
            this.jybh = jybh;
        }

        

        public String getXmCode() {
			return xmCode;
		}

		public void setXmCode(String xmCode) {
			this.xmCode = xmCode;
		}

		public EPzJylistJc_PK(String pch, String jybh, String xmCode) {
            this.pch = pch;
            this.jybh = jybh;
            this.xmCode = xmCode;
        }

       

        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((jybh == null) ? 0 : jybh.hashCode());
			result = prime * result + ((pch == null) ? 0 : pch.hashCode());
			result = prime * result + ((xmCode == null) ? 0 : xmCode.hashCode());
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
			EPzJylistJc_PK other = (EPzJylistJc_PK) obj;
			if (jybh == null) {
				if (other.jybh != null) {
                    return false;
                }
			} else if (!jybh.equals(other.jybh)) {
                return false;
            }
			if (pch == null) {
				if (other.pch != null) {
                    return false;
                }
			} else if (!pch.equals(other.pch)) {
                return false;
            }
			if (xmCode == null) {
                return other.xmCode == null;
			} else {
                return xmCode.equals(other.xmCode);
            }
        }

		public EPzJylistJc_PK() {

        }

    }
}
