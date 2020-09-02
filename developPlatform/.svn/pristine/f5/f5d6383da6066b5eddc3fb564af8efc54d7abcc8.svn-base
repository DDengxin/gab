package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the init_m_sb_jt database table.
 * 
 */
@Entity
@Table(name="init_m_sb_jt")
@NamedQuery(name="InitMSbJt.findAll", query="SELECT i FROM InitMSbJt i")
public class InitMSbJt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="data_corp")
	private String dataCorp;

	@Column(name="data_date")
	private Timestamp dataDate;

	@Column(name="data_man")
	private String dataMan;

	@Column(name="machine_address")
	private String machineAddress;

	@Column(name="machine_dtype")
	private String machineDtype;

	@Column(name="machine_flag")
	private String machineFlag;

	@Column(name="machine_help")
	private String machineHelp;

	@Column(name="machine_id")
	private String machineId;

	@Column(name="machine_name")
	private String machineName;

	@Column(name="machine_no")
	private String machineNo;

	@Column(name="machine_process")
	private String machineProcess;

	@Column(name="machine_sm")
	private String machineSm;

	@Column(name="machine_warning")
	private String machineWarning;

	@Column(name="machine_workshop")
	private String machineWorkshop;

	@Column(name="machine_xtype")
	private String machineXtype;

	@Column(name="machine_ztype")
	private String machineZtype;

	@Id
	@Column(name="imp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long impId;

	private String dataType;

	@Basic
	@Column(name = "data_type")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getImpId() {
		return this.impId;
	}

	public void setImpId(Long impId) {
		this.impId = impId;
	}
	
	public InitMSbJt() {
	}

	public String getDataCorp() {
		return this.dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

	public Timestamp getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(Timestamp dataDate) {
		this.dataDate = dataDate;
	}

	public String getDataMan() {
		return this.dataMan;
	}

	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}

	public String getMachineAddress() {
		return this.machineAddress;
	}

	public void setMachineAddress(String machineAddress) {
		this.machineAddress = machineAddress;
	}

	public String getMachineDtype() {
		return this.machineDtype;
	}

	public void setMachineDtype(String machineDtype) {
		this.machineDtype = machineDtype;
	}

	public String getMachineFlag() {
		return this.machineFlag;
	}

	public void setMachineFlag(String machineFlag) {
		this.machineFlag = machineFlag;
	}

	public String getMachineHelp() {
		return this.machineHelp;
	}

	public void setMachineHelp(String machineHelp) {
		this.machineHelp = machineHelp;
	}

	public String getMachineId() {
		return this.machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return this.machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineNo() {
		return this.machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getMachineProcess() {
		return this.machineProcess;
	}

	public void setMachineProcess(String machineProcess) {
		this.machineProcess = machineProcess;
	}

	public String getMachineSm() {
		return this.machineSm;
	}

	public void setMachineSm(String machineSm) {
		this.machineSm = machineSm;
	}

	public String getMachineWarning() {
		return this.machineWarning;
	}

	public void setMachineWarning(String machineWarning) {
		this.machineWarning = machineWarning;
	}

	public String getMachineWorkshop() {
		return this.machineWorkshop;
	}

	public void setMachineWorkshop(String machineWorkshop) {
		this.machineWorkshop = machineWorkshop;
	}

	public String getMachineXtype() {
		return this.machineXtype;
	}

	public void setMachineXtype(String machineXtype) {
		this.machineXtype = machineXtype;
	}

	public String getMachineZtype() {
		return this.machineZtype;
	}

	public void setMachineZtype(String machineZtype) {
		this.machineZtype = machineZtype;
	}

}