package com.tengzhi.business.sc.da.rydg.model;
/**
 * 单位档案表实体类
 * Gxl
 * */

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
@Entity
@Table(name="m_ry_ygdg")
@IdClass(value = MRyYgdg.MRyYgdg_PK.class)
public class MRyYgdg {

	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date workRq;
	@Id
	private String workId;
	@Id
	private String workDept;
	
	private String workName,workBb,workCj,workGx,workCt,workGz,workSm,dataCorp,dataMan;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date dataDate;

	public Date getWorkRq() {
		return workRq;
	}

	public void setWorkRq(Date workRq) {
		this.workRq = workRq;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkBb() {
		return workBb;
	}

	public void setWorkBb(String workBb) {
		this.workBb = workBb;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkDept() {
		return workDept;
	}

	public void setWorkDept(String workDept) {
		this.workDept = workDept;
	}

	public String getWorkCj() {
		return workCj;
	}

	public void setWorkCj(String workCj) {
		this.workCj = workCj;
	}

	public String getWorkGx() {
		return workGx;
	}

	public void setWorkGx(String workGx) {
		this.workGx = workGx;
	}

	public String getWorkCt() {
		return workCt;
	}

	public void setWorkCt(String workCt) {
		this.workCt = workCt;
	}

	public String getWorkGz() {
		return workGz;
	}

	public void setWorkGz(String workGz) {
		this.workGz = workGz;
	}

	public String getWorkSm() {
		return workSm;
	}

	public void setWorkSm(String workSm) {
		this.workSm = workSm;
	}
	

	public String getDataCorp() {
			return dataCorp;
		}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}


	public String getDataMan() {
		return dataMan;
	}

	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}

	public Date getDataDate() {
		return dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}


public static class MRyYgdg_PK implements Serializable {
    	
		private String workId;
		private String workDept;
		
		public void setWorkId(String workId) {
			this.workId = workId;
		}
		public String getWorkDept() {
			return workDept;
		}
		public void setWorkDept(String workDept) {
			this.workDept = workDept;
		}
		public MRyYgdg_PK(String workId,String workDept) {
			setWorkId(workId);
			setWorkDept(workDept);
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((workId == null) ? 0 : workId.hashCode());
			result = prime * result + ((workDept == null) ? 0 : workDept.hashCode());
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
			MRyYgdg_PK other = (MRyYgdg_PK) obj;
			if (workId == null) {
				if (other.workId != null) {
                    return false;
                }
			} else if (!workId.equals(other.workId)) {
                return false;
            }
			if (workDept == null) {
                return other.workDept == null;
			} else {
                return workDept.equals(other.workDept);
            }
        }
    	
		public MRyYgdg_PK() {
			
		}
    	
    }
	
	@Transient
	private String deptname,cjname,gxname,bbname,gzname,ctname;

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getCjname() {
		return cjname;
	}

	public void setCjname(String cjname) {
		this.cjname = cjname;
	}

	public String getGxname() {
		return gxname;
	}

	public void setGxname(String gxname) {
		this.gxname = gxname;
	}

	public String getBbname() {
		return bbname;
	}

	public void setBbname(String bbname) {
		this.bbname = bbname;
	}

	public String getGzname() {
		return gzname;
	}

	public void setGzname(String gzname) {
		this.gzname = gzname;
	}
	
	@Transient
	private String _state;

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String getCtname() {
		return ctname;
	}

	public void setCtname(String ctname) {
		this.ctname = ctname;
	}
	
	
}
