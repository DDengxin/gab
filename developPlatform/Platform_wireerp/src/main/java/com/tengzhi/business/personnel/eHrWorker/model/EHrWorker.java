package com.tengzhi.business.personnel.eHrWorker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "e_hr_worker")
public class EHrWorker extends BaseModel {
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
    private Date workerInsuredDate;
    private Integer workerCardNumber;
    private BigDecimal workerWeight;
    private Integer workerShoesSize;

    private String workerOrganizationCorp,workerOrganizationDept,workerOrganizationDuty,workerOrgainizationPost,
            workerLaborType,workerLocal,workerProvince,workerCity,workerDistrict,workerSeniority,workerEntryType,
            workerInsuranceType,workerGroupCadres,workerGroupLevel,workerPoliticsStatus,workerCurrentAddress,
            workerEmergencyContact,workerShortNumber,workerWeChat,workerFixedLineTelephone,workerFaxNumber,
            workerPersonalPhone,workerBloodType,workerStature,workerClothingSize,workerCoatSize,
            workerPantsSize,workerMilitaryService,workerWedding,workerAttendance,workerWageScale,
            workerPaidMethod,workerWorkSchedules,workerBankType,workerDepositBank,workerWagesCardNumber
            ,workerSocialSecurityCard,workerAccidentInsurance,workerInsuredBase,workerOldId
            ,workerMajor,workerTrainingExperience;


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
    @Transient
    private String orgCorpName;
    @Transient
    private String orgDeptName;


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

    @Transient
    public String getOrgCorpName() { return orgCorpName; }

    public void setOrgCorpName(String orgCorpName) { this.orgCorpName = orgCorpName; }

    @Transient
    public String getOrgDeptName() { return orgDeptName; }

    public void setOrgDeptName(String orgDeptName) { this.orgDeptName = orgDeptName; }


    public String getWorkerOrganizationCorp() {
        return workerOrganizationCorp;
    }

    public void setWorkerOrganizationCorp(String workerOrganizationCorp) {
        this.workerOrganizationCorp = workerOrganizationCorp;
    }

    public String getWorkerOrganizationDept() {
        return workerOrganizationDept;
    }

    public void setWorkerOrganizationDept(String workerOrganizationDept) {
        this.workerOrganizationDept = workerOrganizationDept;
    }

    public String getWorkerOrganizationDuty() {
        return workerOrganizationDuty;
    }

    public void setWorkerOrganizationDuty(String workerOrganizationDuty) {
        this.workerOrganizationDuty = workerOrganizationDuty;
    }

    public String getWorkerOrgainizationPost() {
        return workerOrgainizationPost;
    }

    public void setWorkerOrgainizationPost(String workerOrgainizationPost) {
        this.workerOrgainizationPost = workerOrgainizationPost;
    }

    public String getWorkerLaborType() {
        return workerLaborType;
    }

    public void setWorkerLaborType(String workerLaborType) {
        this.workerLaborType = workerLaborType;
    }

    public String getWorkerLocal() {
        return workerLocal;
    }

    public void setWorkerLocal(String workerLocal) {
        this.workerLocal = workerLocal;
    }

    public String getWorkerProvince() {
        return workerProvince;
    }

    public void setWorkerProvince(String workerProvince) {
        this.workerProvince = workerProvince;
    }

    public String getWorkerCity() {
        return workerCity;
    }

    public void setWorkerCity(String workerCity) {
        this.workerCity = workerCity;
    }

    public String getWorkerDistrict() {
        return workerDistrict;
    }

    public void setWorkerDistrict(String workerDistrict) {
        this.workerDistrict = workerDistrict;
    }

    public String getWorkerSeniority() {
        return workerSeniority;
    }

    public void setWorkerSeniority(String workerSeniority) {
        this.workerSeniority = workerSeniority;
    }

    public String getWorkerEntryType() {
        return workerEntryType;
    }

    public void setWorkerEntryType(String workerEntryType) {
        this.workerEntryType = workerEntryType;
    }

    public String getWorkerInsuranceType() {
        return workerInsuranceType;
    }

    public void setWorkerInsuranceType(String workerInsuranceType) {
        this.workerInsuranceType = workerInsuranceType;
    }

    public String getWorkerGroupCadres() {
        return workerGroupCadres;
    }

    public void setWorkerGroupCadres(String workerGroupCadres) {
        this.workerGroupCadres = workerGroupCadres;
    }

    public String getWorkerGroupLevel() {
        return workerGroupLevel;
    }

    public void setWorkerGroupLevel(String workerGroupLevel) {
        this.workerGroupLevel = workerGroupLevel;
    }

    public String getWorkerPoliticsStatus() {
        return workerPoliticsStatus;
    }

    public void setWorkerPoliticsStatus(String workerPoliticsStatus) {
        this.workerPoliticsStatus = workerPoliticsStatus;
    }

    public String getWorkerCurrentAddress() {
        return workerCurrentAddress;
    }

    public void setWorkerCurrentAddress(String workerCurrentAddress) {
        this.workerCurrentAddress = workerCurrentAddress;
    }

    public String getWorkerEmergencyContact() {
        return workerEmergencyContact;
    }

    public void setWorkerEmergencyContact(String workerEmergencyContact) {
        this.workerEmergencyContact = workerEmergencyContact;
    }

    public String getWorkerShortNumber() {
        return workerShortNumber;
    }

    public void setWorkerShortNumber(String workerShortNumber) {
        this.workerShortNumber = workerShortNumber;
    }

    public String getWorkerWeChat() {
        return workerWeChat;
    }

    public void setWorkerWeChat(String workerWeChat) {
        this.workerWeChat = workerWeChat;
    }

    public String getWorkerFixedLineTelephone() {
        return workerFixedLineTelephone;
    }

    public void setWorkerFixedLineTelephone(String workerFixedLineTelephone) {
        this.workerFixedLineTelephone = workerFixedLineTelephone;
    }

    public String getWorkerFaxNumber() {
        return workerFaxNumber;
    }

    public void setWorkerFaxNumber(String workerFaxNumber) {
        this.workerFaxNumber = workerFaxNumber;
    }

    public String getWorkerPersonalPhone() {
        return workerPersonalPhone;
    }

    public void setWorkerPersonalPhone(String workerPersonalPhone) {
        this.workerPersonalPhone = workerPersonalPhone;
    }

    public String getWorkerBloodType() {
        return workerBloodType;
    }

    public void setWorkerBloodType(String workerBloodType) {
        this.workerBloodType = workerBloodType;
    }

    public String getWorkerStature() {
        return workerStature;
    }

    public void setWorkerStature(String workerStature) {
        this.workerStature = workerStature;
    }

    public BigDecimal getWorkerWeight() {
        return workerWeight;
    }

    public void setWorkerWeight(BigDecimal workerWeight) {
        this.workerWeight = workerWeight;
    }

    public String getWorkerClothingSize() {
        return workerClothingSize;
    }

    public void setWorkerClothingSize(String workerClothingSize) {
        this.workerClothingSize = workerClothingSize;
    }

    public String getWorkerCoatSize() {
        return workerCoatSize;
    }

    public void setWorkerCoatSize(String workerCoatSize) {
        this.workerCoatSize = workerCoatSize;
    }

    public String getWorkerPantsSize() {
        return workerPantsSize;
    }

    public void setWorkerPantsSize(String workerPantsSize) {
        this.workerPantsSize = workerPantsSize;
    }

    public Integer getWorkerShoesSize() {
        return workerShoesSize;
    }

    public void setWorkerShoesSize(Integer workerShoesSize) {
        this.workerShoesSize = workerShoesSize;
    }

    public String getWorkerMilitaryService() {
        return workerMilitaryService;
    }

    public void setWorkerMilitaryService(String workerMilitaryService) {
        this.workerMilitaryService = workerMilitaryService;
    }

    public String getWorkerWedding() {
        return workerWedding;
    }

    public void setWorkerWedding(String workerWedding) {
        this.workerWedding = workerWedding;
    }

    public String getWorkerAttendance() {
        return workerAttendance;
    }

    public void setWorkerAttendance(String workerAttendance) {
        this.workerAttendance = workerAttendance;
    }

    public String getWorkerWageScale() {
        return workerWageScale;
    }

    public void setWorkerWageScale(String workerWageScale) {
        this.workerWageScale = workerWageScale;
    }

    public String getWorkerPaidMethod() {
        return workerPaidMethod;
    }

    public void setWorkerPaidMethod(String workerPaidMethod) {
        this.workerPaidMethod = workerPaidMethod;
    }

    public String getWorkerWorkSchedules() {
        return workerWorkSchedules;
    }

    public void setWorkerWorkSchedules(String workerWorkSchedules) {
        this.workerWorkSchedules = workerWorkSchedules;
    }

    public String getWorkerBankType() {
        return workerBankType;
    }

    public void setWorkerBankType(String workerBankType) {
        this.workerBankType = workerBankType;
    }

    public String getWorkerDepositBank() {
        return workerDepositBank;
    }

    public void setWorkerDepositBank(String workerDepositBank) {
        this.workerDepositBank = workerDepositBank;
    }

    public String getWorkerWagesCardNumber() {
        return workerWagesCardNumber;
    }

    public void setWorkerWagesCardNumber(String workerWagesCardNumber) {
        this.workerWagesCardNumber = workerWagesCardNumber;
    }




    public String getWorkerSocialSecurityCard() {
        return workerSocialSecurityCard;
    }

    public void setWorkerSocialSecurityCard(String workerSocialSecurityCard) {
        this.workerSocialSecurityCard = workerSocialSecurityCard;
    }

    public String getWorkerAccidentInsurance() {
        return workerAccidentInsurance;
    }

    public void setWorkerAccidentInsurance(String workerAccidentInsurance) {
        this.workerAccidentInsurance = workerAccidentInsurance;
    }

    public String getWorkerInsuredBase() {
        return workerInsuredBase;
    }

    public void setWorkerInsuredBase(String workerInsuredBase) {
        this.workerInsuredBase = workerInsuredBase;
    }

    public String getWorkerOldId() {
        return workerOldId;
    }

    public void setWorkerOldId(String workerOldId) {
        this.workerOldId = workerOldId;
    }

    public Integer getWorkerCardNumber() {
        return workerCardNumber;
    }

    public void setWorkerCardNumber(Integer workerCardNumber) {
        this.workerCardNumber = workerCardNumber;
    }

    public String getWorkerMajor() {
        return workerMajor;
    }

    public void setWorkerMajor(String workerMajor) {
        this.workerMajor = workerMajor;
    }

    public String getWorkerTrainingExperience() {
        return workerTrainingExperience;
    }

    public void setWorkerTrainingExperience(String workerTrainingExperience) {
        this.workerTrainingExperience = workerTrainingExperience;
    }

    public Date getWorkerInsuredDate() {
        return workerInsuredDate;
    }

    public void setWorkerInsuredDate(Date workerInsuredDate) {
        this.workerInsuredDate = workerInsuredDate;
    }
}
