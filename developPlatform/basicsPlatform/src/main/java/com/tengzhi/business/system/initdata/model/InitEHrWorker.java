package com.tengzhi.business.system.initdata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the init_e_hr_worker database table.
 * 
 */
@Entity
@Table(name="init_e_hr_worker")
@NamedQuery(name="InitEHrWorker.findAll", query="SELECT i FROM InitEHrWorker i")
public class InitEHrWorker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="worker_account")
	private String workerAccount;

	@Column(name="worker_address")
	private String workerAddress;

	@Column(name="worker_background")
	private String workerBackground;

	@Column(name="worker_birthday")
	private Timestamp workerBirthday;

	@Column(name="worker_card")
	private String workerCard;

	@Column(name="worker_corpid")
	private String workerCorpid;

	@Column(name="worker_dept")
	private String workerDept;

	@Column(name="worker_dimission")
	private Timestamp workerDimission;

	@Column(name="worker_duty")
	private String workerDuty;

	@Column(name="worker_education")
	private String workerEducation;

	@Column(name="worker_email")
	private String workerEmail;

	@Column(name="worker_explain")
	private String workerExplain;

	@Column(name="worker_flag")
	private String workerFlag;

	@Column(name="worker_hdry")
	private String workerHdry;

	@Column(name="worker_hiredate")
	private Timestamp workerHiredate;

	@Column(name="worker_id")
	private String workerId;

	@Column(name="worker_insurance")
	private String workerInsurance;

	@Column(name="worker_job")
	private String workerJob;

	@Column(name="worker_name")
	private String workerName;

	@Column(name="worker_number")
	private Integer workerNumber;

	@Column(name="worker_operator")
	private String workerOperator;

	@Column(name="worker_phone")
	private String workerPhone;

	@Column(name="worker_post")
	private String workerPost;

	@Column(name="worker_race")
	private String workerRace;

	@Column(name="worker_registered")
	private String workerRegistered;

	@Column(name="worker_school")
	private String workerSchool;

	@Column(name="worker_sex")
	private String workerSex;

	@Column(name="worker_territory")
	private String workerTerritory;

	@Column(name="worker_time")
	private Timestamp workerTime;

	@Column(name="worker_type")
	private String workerType;

	@Id
	@Column(name="imp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long impId;
	private String dataType;
	@Column(name="data_corp")
	private String dataCorp;
	@Basic
	@Column(name = "data_type")


	public String getDataCorp() {
		return this.dataCorp;
	}

	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}

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
	public InitEHrWorker() {
	}

	public String getWorkerAccount() {
		return this.workerAccount;
	}

	public void setWorkerAccount(String workerAccount) {
		this.workerAccount = workerAccount;
	}

	public String getWorkerAddress() {
		return this.workerAddress;
	}

	public void setWorkerAddress(String workerAddress) {
		this.workerAddress = workerAddress;
	}

	public String getWorkerBackground() {
		return this.workerBackground;
	}

	public void setWorkerBackground(String workerBackground) {
		this.workerBackground = workerBackground;
	}

	public Timestamp getWorkerBirthday() {
		return this.workerBirthday;
	}

	public void setWorkerBirthday(Timestamp workerBirthday) {
		this.workerBirthday = workerBirthday;
	}

	public String getWorkerCard() {
		return this.workerCard;
	}

	public void setWorkerCard(String workerCard) {
		this.workerCard = workerCard;
	}

	public String getWorkerCorpid() {
		return this.workerCorpid;
	}

	public void setWorkerCorpid(String workerCorpid) {
		this.workerCorpid = workerCorpid;
	}

	public String getWorkerDept() {
		return this.workerDept;
	}

	public void setWorkerDept(String workerDept) {
		this.workerDept = workerDept;
	}

	public Timestamp getWorkerDimission() {
		return this.workerDimission;
	}

	public void setWorkerDimission(Timestamp workerDimission) {
		this.workerDimission = workerDimission;
	}

	public String getWorkerDuty() {
		return this.workerDuty;
	}

	public void setWorkerDuty(String workerDuty) {
		this.workerDuty = workerDuty;
	}

	public String getWorkerEducation() {
		return this.workerEducation;
	}

	public void setWorkerEducation(String workerEducation) {
		this.workerEducation = workerEducation;
	}

	public String getWorkerEmail() {
		return this.workerEmail;
	}

	public void setWorkerEmail(String workerEmail) {
		this.workerEmail = workerEmail;
	}

	public String getWorkerExplain() {
		return this.workerExplain;
	}

	public void setWorkerExplain(String workerExplain) {
		this.workerExplain = workerExplain;
	}

	public String getWorkerFlag() {
		return this.workerFlag;
	}

	public void setWorkerFlag(String workerFlag) {
		this.workerFlag = workerFlag;
	}

	public String getWorkerHdry() {
		return this.workerHdry;
	}

	public void setWorkerHdry(String workerHdry) {
		this.workerHdry = workerHdry;
	}

	public Timestamp getWorkerHiredate() {
		return this.workerHiredate;
	}

	public void setWorkerHiredate(Timestamp workerHiredate) {
		this.workerHiredate = workerHiredate;
	}

	public String getWorkerId() {
		return this.workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getWorkerInsurance() {
		return this.workerInsurance;
	}

	public void setWorkerInsurance(String workerInsurance) {
		this.workerInsurance = workerInsurance;
	}

	public String getWorkerJob() {
		return this.workerJob;
	}

	public void setWorkerJob(String workerJob) {
		this.workerJob = workerJob;
	}

	public String getWorkerName() {
		return this.workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public Integer getWorkerNumber() {
		return this.workerNumber;
	}

	public void setWorkerNumber(Integer workerNumber) {
		this.workerNumber = workerNumber;
	}

	public String getWorkerOperator() {
		return this.workerOperator;
	}

	public void setWorkerOperator(String workerOperator) {
		this.workerOperator = workerOperator;
	}

	public String getWorkerPhone() {
		return this.workerPhone;
	}

	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}

	public String getWorkerPost() {
		return this.workerPost;
	}

	public void setWorkerPost(String workerPost) {
		this.workerPost = workerPost;
	}

	public String getWorkerRace() {
		return this.workerRace;
	}

	public void setWorkerRace(String workerRace) {
		this.workerRace = workerRace;
	}

	public String getWorkerRegistered() {
		return this.workerRegistered;
	}

	public void setWorkerRegistered(String workerRegistered) {
		this.workerRegistered = workerRegistered;
	}

	public String getWorkerSchool() {
		return this.workerSchool;
	}

	public void setWorkerSchool(String workerSchool) {
		this.workerSchool = workerSchool;
	}

	public String getWorkerSex() {
		return this.workerSex;
	}

	public void setWorkerSex(String workerSex) {
		this.workerSex = workerSex;
	}

	public String getWorkerTerritory() {
		return this.workerTerritory;
	}

	public void setWorkerTerritory(String workerTerritory) {
		this.workerTerritory = workerTerritory;
	}

	public Timestamp getWorkerTime() {
		return this.workerTime;
	}

	public void setWorkerTime(Timestamp workerTime) {
		this.workerTime = workerTime;
	}

	public String getWorkerType() {
		return this.workerType;
	}

	public void setWorkerType(String workerType) {
		this.workerType = workerType;
	}

}