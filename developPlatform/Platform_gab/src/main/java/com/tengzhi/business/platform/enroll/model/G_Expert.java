package com.tengzhi.business.platform.enroll.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * 专家表
 * @author lsh
 *
 */
@Entity
@Table(name = "g_expert")
public class G_Expert {
private String supplyName,expertNote,bindSupplyid,expertName,flag,idCardImg,content;
private  String idCard, introduce,mainJob,saleExperience,education,strengths,knowProduct,serviceScope,dialect,doIt,expertType,headImg,level,status,submitter,role,ageScope,skills;
private Long  age,jobAge ;
private Boolean isFrontline,isWeekEvection,isRemote,isGoAbroad,isPlatform;
	@JsonFormat(pattern="yyyy-MM-dd")
private Date effectiveDate;
	private   Date registerDate;
private  String  backCardImg;


	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getBackCardImg() {
		return backCardImg;
	}

	public void setBackCardImg(String backCardImg) {
		this.backCardImg = backCardImg;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Id
public String getExpertNote() {
	return expertNote;
}
public void setExpertNote(String expertNote) {
	this.expertNote = expertNote;
}

	public Long getJobAge() {
		return jobAge;
	}

	public void setJobAge(Long jobAge) {
		this.jobAge = jobAge;
	}

	public String getIntroduce() {
	return introduce;
}
public void setIntroduce(String introduce) {
	this.introduce = introduce;
}
public String getMainJob() {
	return mainJob;
}
public void setMainJob(String mainJob) {
	this.mainJob = mainJob;
}
public String getSaleExperience() {
	return saleExperience;
}
public void setSaleExperience(String saleExperience) {
	this.saleExperience = saleExperience;
}
public String getEducation() {
	return education;
}
public void setEducation(String education) {
	this.education = education;
}
public String getStrengths() {
	return strengths;
}
public void setStrengths(String strengths) {
	this.strengths = strengths;
}
public String getKnowProduct() {
	return knowProduct;
}
public void setKnowProduct(String knowProduct) {
	this.knowProduct = knowProduct;
}
public String getServiceScope() {
	return serviceScope;
}
public void setServiceScope(String serviceScope) {
	this.serviceScope = serviceScope;
}
public String getDialect() {
	return dialect;
}
public void setDialect(String dialect) {
	this.dialect = dialect;
}
public String getDoIt() {
	return doIt;
}
public void setDoIt(String doIt) {
	this.doIt = doIt;
}
public Long getAge() {
	return age;
}
public void setAge(Long age) {
	this.age = age;
}
public Boolean getIsFrontline() {
	return isFrontline;
}
public void setIsFrontline(Boolean isFrontline) {
	this.isFrontline = isFrontline;
}
public Boolean getIsWeekEvection() {
	return isWeekEvection;
}
public void setIsWeekEvection(Boolean isWeekEvection) {
	this.isWeekEvection = isWeekEvection;
}
public Boolean getIsRemote() {
	return isRemote;
}
public void setIsRemote(Boolean isRemote) {
	this.isRemote = isRemote;
}
public Boolean getIsGoAbroad() {
	return isGoAbroad;
}
public void setIsGoAbroad(Boolean isGoAbroad) {
	this.isGoAbroad = isGoAbroad;
}

public Date getRegisterDate() {
	return registerDate;
}
public void setRegisterDate(Date registerDate) {
	this.registerDate = registerDate;
}

public String getExpertType() {
	return expertType;
}
public void setExpertType(String expertType) {
	this.expertType = expertType;
}

public String getHeadImg() {
	return headImg;
}
public void setHeadImg(String headImg) {
	this.headImg = headImg;
}
	public String getIdCardImg() {
		return idCardImg;
	}
	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}

	public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}

public String getBindSupplyid() {
	return bindSupplyid;
}
public void setBindSupplyid(String bindSupplyid) {
	this.bindSupplyid = bindSupplyid;
}
public String getExpertName() {
	return expertName;
}
public void setExpertName(String expertName) {
	this.expertName = expertName;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getSubmitter() {
	return submitter;
}
public void setSubmitter(String submitter) {
	this.submitter = submitter;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public Boolean getIsPlatform() {
	return isPlatform;
}

public void setIsPlatform(Boolean isPlatform) {
	this.isPlatform = isPlatform;
}

public String getIdCard() {
	return idCard;
}

public void setIdCard(String idCard) {
	this.idCard = idCard;
}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAgeScope() {
		return ageScope;
	}

	public void setAgeScope(String ageScope) {
		this.ageScope = ageScope;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "G_Expert{" +
				"supplyName='" + supplyName + '\'' +
				", expertNote='" + expertNote + '\'' +
				", bindSupplyid='" + bindSupplyid + '\'' +
				", expertName='" + expertName + '\'' +
				", flag='" + flag + '\'' +
				", idCardImg='" + idCardImg + '\'' +
				", idCard='" + idCard + '\'' +
				", introduce='" + introduce + '\'' +
				", mainJob='" + mainJob + '\'' +
				", saleExperience='" + saleExperience + '\'' +
				", education='" + education + '\'' +
				", strengths='" + strengths + '\'' +
				", knowProduct='" + knowProduct + '\'' +
				", serviceScope='" + serviceScope + '\'' +
				", dialect='" + dialect + '\'' +
				", doIt='" + doIt + '\'' +
				", expertType='" + expertType + '\'' +
				", headImg='" + headImg + '\'' +
				", level='" + level + '\'' +
				", status='" + status + '\'' +
				", submitter='" + submitter + '\'' +
				", role='" + role + '\'' +
				", age=" + age +
				", jobAge=" + jobAge +
				", isFrontline=" + isFrontline +
				", isWeekEvection=" + isWeekEvection +
				", isRemote=" + isRemote +
				", isGoAbroad=" + isGoAbroad +
				", isPlatform=" + isPlatform +
				", registerDate=" + registerDate +
				'}';
	}
}
