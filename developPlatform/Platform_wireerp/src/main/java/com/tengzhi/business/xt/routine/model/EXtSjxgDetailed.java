package com.tengzhi.business.xt.routine.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "e_xt_sjxg_mx")
@IdClass(value = EXtSjxgDetailed.EXtSjxgDetailed_PK.class)
public class EXtSjxgDetailed {

	@Id
	private String sqNote;
	@Id
	private String sqNoteMo;
	@Id
	private String sqCode;
	private String sqType;
	private String sqXm;
	private String sqYc;
	private String sqValue;
	private String sqSm;
	private String sqFlag;
	public String getSqNote() {
		return sqNote;
	}
	public void setSqNote(String sqNote) {
		this.sqNote = sqNote;
	}
	public String getSqNoteMo() {
		return sqNoteMo;
	}
	public void setSqNoteMo(String sqNoteMo) {
		this.sqNoteMo = sqNoteMo;
	}
	public String getSqCode() {
		return sqCode;
	}
	public void setSqCode(String sqCode) {
		this.sqCode = sqCode;
	}
	public String getSqType() {
		return sqType;
	}
	public void setSqType(String sqType) {
		this.sqType = sqType;
	}
	public String getSqXm() {
		return sqXm;
	}
	public void setSqXm(String sqXm) {
		this.sqXm = sqXm;
	}
	public String getSqYc() {
		return sqYc;
	}
	public void setSqYc(String sqYc) {
		this.sqYc = sqYc;
	}
	public String getSqValue() {
		return sqValue;
	}
	public void setSqValue(String sqValue) {
		this.sqValue = sqValue;
	}
	public String getSqSm() {
		return sqSm;
	}
	public void setSqSm(String sqSm) {
		this.sqSm = sqSm;
	}
	public String getSqFlag() {
		return sqFlag;
	}
	public void setSqFlag(String sqFlag) {
		this.sqFlag = sqFlag;
	}
	
public static class EXtSjxgDetailed_PK implements Serializable {
	 
    	private String sqNote;
    	private String sqNoteMo;
    	private String sqCode;
		public String getSqNote() {
			return sqNote;
		}
		public void setSqNote(String sqNote) {
			this.sqNote = sqNote;
		}
		public String getSqNoteMo() {
			return sqNoteMo;
		}
		public void setSqNoteMo(String sqNoteMo) {
			this.sqNoteMo = sqNoteMo;
		}
		public String getSqCode() {
			return sqCode;
		}
		public void setSqCode(String sqCode) {
			this.sqCode = sqCode;
		}
		public EXtSjxgDetailed_PK(String sqNote,String sqNoteMo) {
			setSqNote(sqNote);
			setSqNoteMo(sqNoteMo);
		}
		
		
    	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((sqCode == null) ? 0 : sqCode.hashCode());
			result = prime * result + ((sqNote == null) ? 0 : sqNote.hashCode());
			result = prime * result + ((sqNoteMo == null) ? 0 : sqNoteMo.hashCode());
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
			EXtSjxgDetailed_PK other = (EXtSjxgDetailed_PK) obj;
			if (sqCode == null) {
				if (other.sqCode != null) {
                    return false;
                }
			} else if (!sqCode.equals(other.sqCode)) {
                return false;
            }
			if (sqNote == null) {
				if (other.sqNote != null) {
                    return false;
                }
			} else if (!sqNote.equals(other.sqNote)) {
                return false;
            }
			if (sqNoteMo == null) {
                return other.sqNoteMo == null;
			} else {
                return sqNoteMo.equals(other.sqNoteMo);
            }
        }
		public EXtSjxgDetailed_PK() {
			
		}
    	
    }
}
