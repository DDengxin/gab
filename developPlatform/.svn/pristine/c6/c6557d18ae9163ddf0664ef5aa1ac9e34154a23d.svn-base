package com.tengzhi.business.finance.complaint.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="e_pz_ksmx")
@IdClass(value = ComplaintMx.ComplaintMx_PK.class)
public class ComplaintMx {
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ksDate;
	private String ksNote;
	@Id
	@SequenceGenerator(name = "e_pz_ksmx_seq", sequenceName = "e_pz_ksmx_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "e_pz_ksmx_seq")
	private Integer ksId;
	private String ksDept;
	private String ksMan;
	private String ksFx;
	private String ksType;
	private String ksGj;
	private String ksCk;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ksCkdate;
	private String ksCkflag;
	public Date getKsDate() {
		return ksDate;
	}
	public void setKsDate(Date ksDate) {
		this.ksDate = ksDate;
	}
	public String getKsNote() {
		return ksNote;
	}
	public void setKsNote(String ksNote) {
		this.ksNote = ksNote;
	}
	public Integer getKsId() {
		return ksId;
	}
	public void setKsId(Integer ksId) {
		this.ksId = ksId;
	}
	public String getKsDept() {
		return ksDept;
	}
	public void setKsDept(String ksDept) {
		this.ksDept = ksDept;
	}
	public String getKsMan() {
		return ksMan;
	}
	public void setKsMan(String ksMan) {
		this.ksMan = ksMan;
	}
	public String getKsFx() {
		return ksFx;
	}
	public void setKsFx(String ksFx) {
		this.ksFx = ksFx;
	}
	public String getKsType() {
		return ksType;
	}
	public void setKsType(String ksType) {
		this.ksType = ksType;
	}
	public String getKsGj() {
		return ksGj;
	}
	public void setKsGj(String ksGj) {
		this.ksGj = ksGj;
	}
	public String getKsCk() {
		return ksCk;
	}
	public void setKsCk(String ksCk) {
		this.ksCk = ksCk;
	}
	public Date getKsCkdate() {
		return ksCkdate;
	}
	public void setKsCkdate(Date ksCkdate) {
		this.ksCkdate = ksCkdate;
	}
	public String getKsCkflag() {
		return ksCkflag;
	}
	public void setKsCkflag(String ksCkflag) {
		this.ksCkflag = ksCkflag;
	}
	
	public static class ComplaintMx_PK implements Serializable {
		private String ksNote;
		private Integer ksId;
		public String getKsNote() {
			return ksNote;
		}
		public void setKsNote(String ksNote) {
			this.ksNote = ksNote;
		}
		public Integer getKsId() {
			return ksId;
		}
		public void setKsId(Integer ksId) {
			this.ksId = ksId;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((ksId == null) ? 0 : ksId.hashCode());
			result = prime * result + ((ksNote == null) ? 0 : ksNote.hashCode());
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
			ComplaintMx_PK other = (ComplaintMx_PK) obj;
			if (ksId == null) {
				if (other.ksId != null) {
                    return false;
                }
			} else if (!ksId.equals(other.ksId)) {
                return false;
            }
			if (ksNote == null) {
                return other.ksNote == null;
			} else {
                return ksNote.equals(other.ksNote);
            }
        }
		public ComplaintMx_PK() {
			
		}
	}
}
