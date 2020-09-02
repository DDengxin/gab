package com.tengzhi.business.mSbJt.model;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "m_sb_jt")
public class MSbJt {
	@Id
	private String machineId;
	private String machineNo,machineName,machineDtype,machineZtype,machineXtype,machineWorkshop,machineProcess,machineAddress,machineHelp,machineWarning,machineSm,machineFlag,dataMan,dataCorp,machineInspection;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date dataDate;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineDtype() {
		return machineDtype;
	}

	public void setMachineDtype(String machineDtype) {
		this.machineDtype = machineDtype;
	}

	public String getMachineZtype() {
		return machineZtype;
	}

	public void setMachineZtype(String machineZtype) {
		this.machineZtype = machineZtype;
	}

	public String getMachineXtype() {
		return machineXtype;
	}

	public void setMachineXtype(String machineXtype) {
		this.machineXtype = machineXtype;
	}

	public String getMachineWorkshop() {
		return machineWorkshop;
	}

	public void setMachineWorkshop(String machineWorkshop) {
		this.machineWorkshop = machineWorkshop;
	}

	public String getMachineProcess() {
		return machineProcess;
	}

	public void setMachineProcess(String machineProcess) {
		this.machineProcess = machineProcess;
	}

	public String getMachineAddress() {
		return machineAddress;
	}

	public void setMachineAddress(String machineAddress) {
		this.machineAddress = machineAddress;
	}

	public String getMachineHelp() {
		return machineHelp;
	}

	public void setMachineHelp(String machineHelp) {
		this.machineHelp = machineHelp;
	}

	public String getMachineWarning() {
		return machineWarning;
	}

	public void setMachineWarning(String machineWarning) {
		this.machineWarning = machineWarning;
	}

	public String getMachineSm() {
		return machineSm;
	}

	public void setMachineSm(String machineSm) {
		this.machineSm = machineSm;
	}

	public String getMachineFlag() {
		return machineFlag;
	}

	public void setMachineFlag(String machineFlag) {
		this.machineFlag = machineFlag;
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

	
	@Transient
	private String dtypename;
	
	@Transient
	private String ztypename;
	
	@Transient
	private String cjname;
	
	@Transient
	private String gxname;

	@Formula("(f_get_param_name('设备类型',machine_dtype,data_corp))")
	public String getDtypename() {
		return dtypename;
	}

	public void setDtypename(String dtypename) {
		this.dtypename = dtypename;
	}

	@Formula("(f_get_param_name('设备参数',machine_dtype,data_corp))")
	public String getZtypename() {
		return ztypename;
	}

	public void setZtypename(String ztypename) {
		this.ztypename = ztypename;
	}

	@Formula("(f_getparamname('GETTYPEBYRAMNAME', machine_workshop, '生产车间', '', '', data_corp))")
	public String getCjname() {
		return cjname;
	}

	public void setCjname(String cjname) {
		this.cjname = cjname;
	}

	@Formula("(f_getname('GETGXNAME', machine_process, '', ''))")
	public String getGxname() {
		return gxname;
	}

	public void setGxname(String gxname) {
		this.gxname = gxname;
	}


	public String getMachineInspection() {
		return machineInspection;
	}

	public void setMachineInspection(String machineInspection) {
		this.machineInspection = machineInspection;
	}
}
