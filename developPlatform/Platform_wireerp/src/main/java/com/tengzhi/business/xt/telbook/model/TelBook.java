package com.tengzhi.business.xt.telbook.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;


@Entity
@Table(name = "e_hr_worker")
public class TelBook extends BaseModel {
    @NotBlank(message = "员工编码不能为空")
    private String workerId;
    private String workerName;
    private String workerSex;
    private String workerDept;
    private String workerDuty;
    private String workerPost;
    private String workerPhone;
    private String workerEmail;
    private String workerCorpid;
    private String workerFlag;
    private String workerOperator;
    private String workerRace;
    private String workerInsurance;
    private String workerAccount;
    private String workerRegistered;
    private String workerAddress;
    private String workerTerritory;
    private String workerCard;
    private String workerBackground;
    private String workerSchool;
    private String workerEducation;
    private String workerJob;
    private String workerExplain;
    private String workerHdry;
    /* private String deleteLogo; */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date workerTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workerHiredate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workerBirthday;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workerDimission;
    private Integer workerNumber;
    private String workerType;
    @Transient
    private String workerDeptName;
    @Transient
    private String workerCorpName;
    @Transient
    private String workerXlName;
    @Transient
    private String workerDutyName;
    @Transient
    private String workerPostName;
    @Transient
    private String workerInsuranceName;
    @Transient
    private String workerTypeName;


    @Id
    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerSex() {
        return workerSex;
    }

    public void setWorkerSex(String workerSex) {
        this.workerSex = workerSex;
    }

    public String getWorkerDept() {
        return workerDept;
    }

    public void setWorkerDept(String workerDept) {
        this.workerDept = workerDept;
    }

    public String getWorkerDuty() {
        return workerDuty;
    }

    public void setWorkerDuty(String workerDuty) {
        this.workerDuty = workerDuty;
    }

    public String getWorkerPost() {
        return workerPost;
    }

    public void setWorkerPost(String workerPost) {
        this.workerPost = workerPost;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }

    public String getWorkerCorpid() {
        return workerCorpid;
    }

    public void setWorkerCorpid(String workerCorpid) {
        this.workerCorpid = workerCorpid;
    }

    public String getWorkerFlag() {
        return workerFlag;
    }

    public void setWorkerFlag(String workerFlag) {
        this.workerFlag = workerFlag;
    }

    public String getWorkerOperator() {
        return workerOperator;
    }

    public void setWorkerOperator(String workerOperator) {
        this.workerOperator = workerOperator;
    }

    public String getWorkerRace() {
        return workerRace;
    }

    public void setWorkerRace(String workerRace) {
        this.workerRace = workerRace;
    }

    public String getWorkerInsurance() {
        return workerInsurance;
    }

    public void setWorkerInsurance(String workerInsurance) {
        this.workerInsurance = workerInsurance;
    }

    public String getWorkerAccount() {
        return workerAccount;
    }

    public void setWorkerAccount(String workerAccount) {
        this.workerAccount = workerAccount;
    }

    public String getWorkerRegistered() {
        return workerRegistered;
    }

    public void setWorkerRegistered(String workerRegistered) {
        this.workerRegistered = workerRegistered;
    }

    public String getWorkerAddress() {
        return workerAddress;
    }

    public void setWorkerAddress(String workerAddress) {
        this.workerAddress = workerAddress;
    }

    public String getWorkerTerritory() {
        return workerTerritory;
    }

    public void setWorkerTerritory(String workerTerritory) {
        this.workerTerritory = workerTerritory;
    }

    public String getWorkerCard() {
        return workerCard;
    }

    public void setWorkerCard(String workerCard) {
        this.workerCard = workerCard;
    }

    public String getWorkerBackground() {
        return workerBackground;
    }

    public void setWorkerBackground(String workerBackground) {
        this.workerBackground = workerBackground;
    }

    public String getWorkerSchool() {
        return workerSchool;
    }

    public void setWorkerSchool(String workerSchool) {
        this.workerSchool = workerSchool;
    }

    public String getWorkerEducation() {
        return workerEducation;
    }

    public void setWorkerEducation(String workerEducation) {
        this.workerEducation = workerEducation;
    }

    public String getWorkerJob() {
        return workerJob;
    }

    public void setWorkerJob(String workerJob) {
        this.workerJob = workerJob;
    }

    public String getWorkerExplain() {
        return workerExplain;
    }

    public void setWorkerExplain(String workerExplain) {
        this.workerExplain = workerExplain;
    }

    public String getWorkerHdry() {
        return workerHdry;
    }

    public void setWorkerHdry(String workerHdry) {
        this.workerHdry = workerHdry;
    }

    public Date getWorkerTime() {
        return workerTime;
    }

    public void setWorkerTime(Date workerTime) {
        this.workerTime = workerTime;
    }

    public Date getWorkerHiredate() {
        return workerHiredate;
    }

    public void setWorkerHiredate(Date workerHiredate) {
        this.workerHiredate = workerHiredate;
    }

    public Date getWorkerBirthday() {
        return workerBirthday;
    }

    public void setWorkerBirthday(Date workerBirthday) {
        this.workerBirthday = workerBirthday;
    }

    public Date getWorkerDimission() {
        return workerDimission;
    }

    public void setWorkerDimission(Date workerDimission) {
        this.workerDimission = workerDimission;
    }

    public Integer getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(Integer workerNumber) {
        this.workerNumber = workerNumber;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    @Transient
	public String getWorkerXlName() {
		return workerXlName;
	}

	public void setWorkerXlName(String workerXlName) {
		this.workerXlName = workerXlName;
	}
	@Transient
	public String getWorkerDutyName() {
		return workerDutyName;
	}

	public void setWorkerDutyName(String workerDutyName) {
		this.workerDutyName = workerDutyName;
	}
	@Transient
	public String getWorkerPostName() {
		return workerPostName;
	}

	public void setWorkerPostName(String workerPostName) {
		this.workerPostName = workerPostName;
	}
	@Transient
	public String getWorkerInsuranceName() {
		return workerInsuranceName;
	}

	public void setWorkerInsuranceName(String workerInsuranceName) {
		this.workerInsuranceName = workerInsuranceName;
	}
	@Transient
	public String getWorkerTypeName() {
		return workerTypeName;
	}

	public void setWorkerTypeName(String workerTypeName) {
		this.workerTypeName = workerTypeName;
	}

	@Transient
    public String getWorkerDeptName() {
        return workerDeptName;
    }

    public void setWorkerDeptName(String workerDeptName) {
        this.workerDeptName = workerDeptName;
    }

	@Transient
    public String getWorkerCorpName() {
        return workerCorpName;
    }

    public void setWorkerCorpName(String workerCorpName) {
        this.workerCorpName = workerCorpName;
    }
    

}
