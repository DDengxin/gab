package com.tengzhi.business.mSbJt.by.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

@Entity
@Table(name = "m_sb_sbwb")
public class MSbSbwb extends BaseModel{
	@Id
	@Column(name = "sbwb_note", nullable = false,length = 20)
	@NotBlank(message = "维保单号不能为空")
	private String sbwbNote;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date sbwbRq;
	@NotBlank(message = "设备编号不能为空")
	private String sbwbSbid;
	
	
	
	private String sbwbDept;
	private String sbwbType;
	private String sbwbDtype;
	private String sbwbXtype;
	private String sbwbSm;
	private String sbwbFlag;
	private String sbwbSman;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date sbwbJtime;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date sbwbPtime;
	private String sbwbPman;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date sbwbDtime;
	private String sbwbDman;
	private String sbwbDsm;
	private String sbwbKtype;
	private String dataMan;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataDate;
	private String dataCorp;
	
	public String getSbwbNote() {
		return sbwbNote;
	}
	public void setSbwbNote(String sbwbNote) {
		this.sbwbNote = sbwbNote;
	}
	public Date getSbwbRq() {
		return sbwbRq;
	}
	public void setSbwbRq(Date sbwbRq) {
		this.sbwbRq = sbwbRq;
	}
	public String getSbwbSbid() {
		return sbwbSbid;
	}
	public void setSbwbSbid(String sbwbSbid) {
		this.sbwbSbid = sbwbSbid;
	}
	public String getSbwbDept() {
		return sbwbDept;
	}
	public void setSbwbDept(String sbwbDept) {
		this.sbwbDept = sbwbDept;
	}
	public String getSbwbType() {
		return sbwbType;
	}
	public void setSbwbType(String sbwbType) {
		this.sbwbType = sbwbType;
	}
	public String getSbwbDtype() {
		return sbwbDtype;
	}
	public void setSbwbDtype(String sbwbDtype) {
		this.sbwbDtype = sbwbDtype;
	}
	public String getSbwbXtype() {
		return sbwbXtype;
	}
	public void setSbwbXtype(String sbwbXtype) {
		this.sbwbXtype = sbwbXtype;
	}
	public String getSbwbSm() {
		return sbwbSm;
	}
	public void setSbwbSm(String sbwbSm) {
		this.sbwbSm = sbwbSm;
	}
	public String getSbwbFlag() {
		return sbwbFlag;
	}
	public void setSbwbFlag(String sbwbFlag) {
		this.sbwbFlag = sbwbFlag;
	}
	public String getSbwbSman() {
		return sbwbSman;
	}
	public void setSbwbSman(String sbwbSman) {
		this.sbwbSman = sbwbSman;
	}
	public Date getSbwbJtime() {
		return sbwbJtime;
	}
	public void setSbwbJtime(Date sbwbJtime) {
		this.sbwbJtime = sbwbJtime;
	}
	public Date getSbwbPtime() {
		return sbwbPtime;
	}
	public void setSbwbPtime(Date sbwbPtime) {
		this.sbwbPtime = sbwbPtime;
	}
	public String getSbwbPman() {
		return sbwbPman;
	}
	public void setSbwbPman(String sbwbPman) {
		this.sbwbPman = sbwbPman;
	}
	public Date getSbwbDtime() {
		return sbwbDtime;
	}
	public void setSbwbDtime(Date sbwbDtime) {
		this.sbwbDtime = sbwbDtime;
	}
	public String getSbwbDman() {
		return sbwbDman;
	}
	public void setSbwbDman(String sbwbDman) {
		this.sbwbDman = sbwbDman;
	}
	public String getSbwbDsm() {
		return sbwbDsm;
	}
	public void setSbwbDsm(String sbwbDsm) {
		this.sbwbDsm = sbwbDsm;
	}
	public String getSbwbKtype() {
		return sbwbKtype;
	}
	public void setSbwbKtype(String sbwbKtype) {
		this.sbwbKtype = sbwbKtype;
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
	public String getDataCorp() {
		return dataCorp;
	}
	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
	
	@Transient
	private String deptname;
	@Transient
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	@Transient
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Transient
	private String usernamee;
	public String getUsernamee() {
		return usernamee;
	}
	public void setUsernamee(String usernamee) {
		this.usernamee = usernamee;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(dataCorp, dataDate, dataMan, sbwbDept, sbwbDman, sbwbDsm, sbwbDtime, sbwbDtype, sbwbFlag,
				sbwbJtime, sbwbKtype, sbwbNote, sbwbPman, sbwbPtime, sbwbRq, sbwbSbid, sbwbSm, sbwbSman, sbwbType,
				sbwbXtype);
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
		MSbSbwb other = (MSbSbwb) obj;
		return Objects.equals(dataCorp, other.dataCorp) && Objects.equals(dataDate, other.dataDate)
				&& Objects.equals(dataMan, other.dataMan) && Objects.equals(sbwbDept, other.sbwbDept)
				&& Objects.equals(sbwbDman, other.sbwbDman) && Objects.equals(sbwbDsm, other.sbwbDsm)
				&& Objects.equals(sbwbDtime, other.sbwbDtime) && Objects.equals(sbwbDtype, other.sbwbDtype)
				&& Objects.equals(sbwbFlag, other.sbwbFlag) && Objects.equals(sbwbJtime, other.sbwbJtime)
				&& Objects.equals(sbwbKtype, other.sbwbKtype) && Objects.equals(sbwbNote, other.sbwbNote)
				&& Objects.equals(sbwbPman, other.sbwbPman) && Objects.equals(sbwbPtime, other.sbwbPtime)
				&& Objects.equals(sbwbRq, other.sbwbRq) && Objects.equals(sbwbSbid, other.sbwbSbid)
				&& Objects.equals(sbwbSm, other.sbwbSm) && Objects.equals(sbwbSman, other.sbwbSman)
				&& Objects.equals(sbwbType, other.sbwbType) && Objects.equals(sbwbXtype, other.sbwbXtype);
	}
	
	//双表联查第二张表部分
	@Transient
	private String machineName;
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	
	@Transient
	private String machineNo;
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	
	@Transient
	private String jjcd;
	public String getJjcd() {
		return jjcd;
	}
	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}
	
	@Transient
	private String machineId;
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	
	
	
}
