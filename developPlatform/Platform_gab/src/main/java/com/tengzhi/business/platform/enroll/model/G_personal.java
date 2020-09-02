package com.tengzhi.business.platform.enroll.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人表
 *
 * @author lsh
 */
@Entity
@Table(name = "g_personal")
public class G_personal {
    private String district,content,personImg,address;
    private String customerExp;
    private String personNote;
    private String supplyName, supplyImg, lastyearMoney, idCard, name, mainJob, numberScope, DeliveryDateScope, accuracyRequire, spaceRequire, weightRequire, autoRequire, targetScope, explain, headImg, personName, status, submitter, role, supplyLevel, supplyUid;
    private Boolean isPublic, isPlatform;
    private int supplyDay;
    private BigDecimal supplyJe;
    @Transient
    private String password;

    private Boolean isProxy;


    private Date registerDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private  Date effectiveDate;
    @Column(name = "is_long_term")
    @JsonProperty(value = "isLongTerm")
    private  Boolean isLongTerm;


    private  String  province,city,area;
  //联系信息字段，后面补充
    private  String telphone,concatPerson,website,email,workTelephone,fax;

    //

    public Boolean getIsLongTerm() {
        return isLongTerm;
    }

    public void setIsLongTerm(Boolean longTerm) {
        isLongTerm = longTerm;
    }

    public Boolean IsLongTerm() {
        return isLongTerm;
    }

    public Boolean getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(Boolean isProxy) {
        this.isProxy = isProxy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }



    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    //is_proxy


    public String getPersonImg() {
        return personImg;
    }

    public void setPersonImg(String personImg) {
        this.personImg = personImg;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCustomerExp() {
        return customerExp;
    }

    public void setCustomerExp(String customerExp) {
        this.customerExp = customerExp;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    @Id
    public String getPersonNote() {
        return personNote;
    }

    public void setPersonNote(String personNote) {
        this.personNote = personNote;
    }

    public String getSupplyImg() {
        return supplyImg;
    }

    public void setSupplyImg(String supplyImg) {
        this.supplyImg = supplyImg;
    }

    public String getLastyearMoney() {
        return lastyearMoney;
    }

    public void setLastyearMoney(String lastyearMoney) {
        this.lastyearMoney = lastyearMoney;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainJob() {
        return mainJob;
    }

    public void setMainJob(String mainJob) {
        this.mainJob = mainJob;
    }

    public String getNumberScope() {
        return numberScope;
    }

    public void setNumberScope(String numberScope) {
        this.numberScope = numberScope;
    }

    public String getDeliveryDateScope() {
        return DeliveryDateScope;
    }

    public void setDeliveryDateScope(String deliveryDateScope) {
        DeliveryDateScope = deliveryDateScope;
    }

    public String getAccuracyRequire() {
        return accuracyRequire;
    }

    public void setAccuracyRequire(String accuracyRequire) {
        this.accuracyRequire = accuracyRequire;
    }

    public String getSpaceRequire() {
        return spaceRequire;
    }

    public void setSpaceRequire(String spaceRequire) {
        this.spaceRequire = spaceRequire;
    }

    public String getWeightRequire() {
        return weightRequire;
    }

    public void setWeightRequire(String weightRequire) {
        this.weightRequire = weightRequire;
    }

    public String getAutoRequire() {
        return autoRequire;
    }

    public void setAutoRequire(String autoRequire) {
        this.autoRequire = autoRequire;
    }

    public String getTargetScope() {
        return targetScope;
    }

    public void setTargetScope(String targetScope) {
        this.targetScope = targetScope;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }



    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public String getSupplyLevel() {
        return supplyLevel;
    }

    public void setSupplyLevel(String supplyLevel) {
        this.supplyLevel = supplyLevel;
    }

    public String getSupplyUid() {
        return supplyUid;
    }

    public void setSupplyUid(String supplyUid) {
        this.supplyUid = supplyUid;
    }

    public int getSupplyDay() {
        return supplyDay;
    }

    public void setSupplyDay(int supplyDay) {
        this.supplyDay = supplyDay;
    }

    public BigDecimal getSupplyJe() {
        return supplyJe;
    }

    public void setSupplyJe(BigDecimal supplyJe) {
        this.supplyJe = supplyJe;
    }

    public Boolean getIsPlatform() {
        return isPlatform;
    }

    public void setIsPlatform(Boolean isPlatform) {
        this.isPlatform = isPlatform;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getConcatPerson() {
        return concatPerson;
    }

    public void setConcatPerson(String concatPerson) {
        this.concatPerson = concatPerson;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkTelephone() {
        return workTelephone;
    }

    public void setWorkTelephone(String workTelephone) {
        this.workTelephone = workTelephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
