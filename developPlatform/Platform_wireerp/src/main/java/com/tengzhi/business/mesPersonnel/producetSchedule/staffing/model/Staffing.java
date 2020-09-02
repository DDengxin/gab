package com.tengzhi.business.mesPersonnel.producetSchedule.staffing.model;

import java.io.Serializable;
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
@Table(name="m_ry_ygdg")
@IdClass(value = Staffing.Staffing_Id.class)
public class Staffing  extends BaseModel{

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date workRq;
	@Id
	private String workId;
	private String workName;
	private String workDept;
	private String workCj;
	private String workGx;
	@Id
	private String workBb;
	private String workCt;
	private String workGz;
	private String workSm;
	private String dataCorp;
	private String dataMan;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataDate;
	
	
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

	public String getWorkBb() {
		return workBb;
	}

	public void setWorkBb(String workBb) {
		this.workBb = workBb;
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

	public static class Staffing_Id implements Serializable{
		@Temporal(TemporalType.TIMESTAMP)
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date workRq;
		private String workId;
		private String workBb;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((workRq == null) ? 0 : workId.hashCode());
			result = prime * result + ((workId == null) ? 0 : workBb.hashCode());
			result = prime * result + ((workBb == null) ? 0 : workRq.hashCode());
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
			Staffing_Id other = (Staffing_Id) obj;
			if (workRq == null) {
				if (other.workRq != null) {
                    return false;
                }
			} else if (!workRq.equals(other.workRq)) {
                return false;
            }
			if (workId == null) {
				if (other.workId != null) {
                    return false;
                }
			} else if (!workId.equals(other.workId)) {
                return false;
            }
			if (workBb == null) {
                return other.workBb == null;
			} else {
                return workBb.equals(other.workBb);
            }
        }
	
		public Staffing_Id(Date workRq, String workId, String workBb) {
			super();
			this.workRq = workRq;
			this.workId = workId;
			this.workBb = workBb;
		}
		public Staffing_Id() {
		
		}
	}
}
