package com.tengzhi.business.quality.qualityArchives.qualityCertificate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "e_pz_quality_certificate")
@IdClass(value=QualityCertificate.QualityCertificate_PK.class)
public class QualityCertificate extends BaseModel {
	
	@Id
	private String zmNote,zmLotNo;
	@Id
	private String zmCode;
	private String fileUuid,zmBrand,zmDeliveryNo,zmItem,zmItemDetailed,zmStandard,zmFlag,zmType,dataMan,dataCorp,zmCustomer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date zmRq;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date dataDate;

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	private BigDecimal zmJs,zmSl;

	public String getZmNote() {
		return zmNote;
	}

	public void setZmNote(String zmNote) {
		this.zmNote = zmNote;
	}

	public String getZmCode() {
		return zmCode;
	}

	public void setZmCode(String zmCode) {
		this.zmCode = zmCode;
	}

	public String getZmBrand() {
		return zmBrand;
	}

	public void setZmBrand(String zmBrand) {
		this.zmBrand = zmBrand;
	}

	public String getZmLotNo() {
		return zmLotNo;
	}

	public void setZmLotNo(String zmLotNo) {
		this.zmLotNo = zmLotNo;
	}

	public String getZmDeliveryNo() {
		return zmDeliveryNo;
	}

	public void setZmDeliveryNo(String zmDeliveryNo) {
		this.zmDeliveryNo = zmDeliveryNo;
	}

	public String getZmItem() {
		return zmItem;
	}

	public void setZmItem(String zmItem) {
		this.zmItem = zmItem;
	}

	public String getZmItemDetailed() {
		return zmItemDetailed;
	}

	public void setZmItemDetailed(String zmItemDetailed) {
		this.zmItemDetailed = zmItemDetailed;
	}

	public String getZmStandard() {
		return zmStandard;
	}

	public void setZmStandard(String zmStandard) {
		this.zmStandard = zmStandard;
	}

	public String getZmFlag() {
		return zmFlag;
	}

	public void setZmFlag(String zmFlag) {
		this.zmFlag = zmFlag;
	}

	public String getZmType() {
		return zmType;
	}

	public void setZmType(String zmType) {
		this.zmType = zmType;
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

	public String getZmCustomer() {
		return zmCustomer;
	}

	public void setZmCustomer(String zmCustomer) {
		this.zmCustomer = zmCustomer;
	}

	public Date getZmRq() {
		return zmRq;
	}

	public void setZmRq(Date zmRq) {
		this.zmRq = zmRq;
	}

	public Date getDataDate() {
		return dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}

	public BigDecimal getZmJs() {
		return zmJs;
	}

	public void setZmJs(BigDecimal zmJs) {
		this.zmJs = zmJs;
	}

	public BigDecimal getZmSl() {
		return zmSl;
	}

	public void setZmSl(BigDecimal zmSl) {
		this.zmSl = zmSl;
	}
	@Transient
	private String cpcodeName,cpcodeSize,cpcodeSizeEn,cpcodeXp,cpcodeFl;
	
	
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

	public String getCpcodeSizeEn() {
		return cpcodeSizeEn;
	}

	public void setCpcodeSizeEn(String cpcodeSizeEn) {
		this.cpcodeSizeEn = cpcodeSizeEn;
	}

	public String getCpcodeXp() {
		return cpcodeXp;
	}

	public void setCpcodeXp(String cpcodeXp) {
		this.cpcodeXp = cpcodeXp;
	}

	public String getCpcodeFl() {
		return cpcodeFl;
	}

	public void setCpcodeFl(String cpcodeFl) {
		this.cpcodeFl = cpcodeFl;
	}

	@Transient
	@Autowired
	private String _state;

	
	
	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}



	public static class QualityCertificate_PK implements Serializable {
		private String zmNote,zmLotNo;

		public String getZmNote() {
			return zmNote;
		}

		public void setZmNote(String zmNote) {
			this.zmNote = zmNote;
		}

		public String getZmLotNo() {
			return zmLotNo;
		}

		public void setZmLotNo(String zmLotNo) {
			this.zmLotNo = zmLotNo;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((zmLotNo == null) ? 0 : zmLotNo.hashCode());
			result = prime * result + ((zmNote == null) ? 0 : zmNote.hashCode());
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
			QualityCertificate_PK other = (QualityCertificate_PK) obj;
			if (zmLotNo == null) {
				if (other.zmLotNo != null) {
                    return false;
                }
			} else if (!zmLotNo.equals(other.zmLotNo)) {
                return false;
            }
			if (zmNote == null) {
                return other.zmNote == null;
			} else {
                return zmNote.equals(other.zmNote);
            }
        }
	
		public QualityCertificate_PK(String zmNote,String zmLotNo ) {
			setZmNote(zmNote);
			setZmLotNo(zmLotNo);
		}
		public QualityCertificate_PK( ) {
		}
	}
	
}
