package com.tengzhi.business.platform.enroll.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商表
 *
 * @author lsh
 */
@Entity
@Table(name = "g_supply")
public class G_Supply {
    private String district,content;
    private String customerExp;
    private String supplyNote; //adopt_rate
    private String supplyInfo, supplyImg, productsYears, outputValue, equipAbility,
            localSize, serviceScope, adoptRate, offsetSize, accuracy, outputMonth, strengths, productArt,
            singleOrMix, bill, lossRate, annexInfo, sideSaleInfo, brandType, brand, techAblitity, mixAblitity, moneyAbility, ContactsAblitity,
            teamAblitity, manNumber, avgDay, supplyType, supplyName, headImg, keyWord, status, submitter, role, supplyAddress, supplyLevel, supplyUid, businessLicenseCode;
    private Boolean isSale, isBeforeSale, isAfterSale, isTest, isFlag, isMatching, isExplainBook, isOuttest, isEntrust, isReform, isFullsale, isFullstorage, isFullaftersale, isPlatform;
    private int supplyDay;
    private BigDecimal supplyJe;

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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Boolean getIsLongTerm() {
        return isLongTerm;
    }

    public void setIsLongTerm(Boolean longTerm) {
        isLongTerm = longTerm;
    }

    public Boolean IsLongTerm() {
        return isLongTerm;
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

    //


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

    @Id
    public String getSupplyNote() {
        return supplyNote;
    }

    public void setSupplyNote(String supplyNote) {
        this.supplyNote = supplyNote;
    }

    public String getSupplyInfo() {
        return supplyInfo;
    }

    public void setSupplyInfo(String supplyInfo) {
        this.supplyInfo = supplyInfo;
    }

    public String getSupplyImg() {
        return supplyImg;
    }

    public void setSupplyImg(String supplyImg) {
        this.supplyImg = supplyImg;
    }

    public String getProductsYears() {
        return productsYears;
    }

    public void setProductsYears(String productsYears) {
        this.productsYears = productsYears;
    }

    public String getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(String outputValue) {
        this.outputValue = outputValue;
    }

    public String getEquipAbility() {
        return equipAbility;
    }

    public void setEquipAbility(String equipAbility) {
        this.equipAbility = equipAbility;
    }

    public String getLocalSize() {
        return localSize;
    }

    public void setLocalSize(String localSize) {
        this.localSize = localSize;
    }

    public String getServiceScope() {
        return serviceScope;
    }

    public void setServiceScope(String serviceScope) {
        this.serviceScope = serviceScope;
    }

    public String getAdoptRate() {
        return adoptRate;
    }

    public void setAdoptRate(String adoptRate) {
        this.adoptRate = adoptRate;
    }

    public String getOffsetSize() {
        return offsetSize;
    }

    public void setOffsetSize(String offsetSize) {
        this.offsetSize = offsetSize;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getOutputMonth() {
        return outputMonth;
    }

    public void setOutputMonth(String outputMonth) {
        this.outputMonth = outputMonth;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getProductArt() {
        return productArt;
    }

    public void setProductArt(String productArt) {
        this.productArt = productArt;
    }

    public String getSingleOrMix() {
        return singleOrMix;
    }

    public void setSingleOrMix(String singleOrMix) {
        this.singleOrMix = singleOrMix;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getLossRate() {
        return lossRate;
    }

    public void setLossRate(String lossRate) {
        this.lossRate = lossRate;
    }

    public String getAnnexInfo() {
        return annexInfo;
    }

    public void setAnnexInfo(String annexInfo) {
        this.annexInfo = annexInfo;
    }

    public String getSideSaleInfo() {
        return sideSaleInfo;
    }

    public void setSideSaleInfo(String sideSaleInfo) {
        this.sideSaleInfo = sideSaleInfo;
    }

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTechAblitity() {
        return techAblitity;
    }

    public void setTechAblitity(String techAblitity) {
        this.techAblitity = techAblitity;
    }

    public String getMixAblitity() {
        return mixAblitity;
    }

    public void setMixAblitity(String mixAblitity) {
        this.mixAblitity = mixAblitity;
    }

    public String getMoneyAbility() {
        return moneyAbility;
    }

    public void setMoneyAbility(String moneyAbility) {
        this.moneyAbility = moneyAbility;
    }

    public String getContactsAblitity() {
        return ContactsAblitity;
    }

    public void setContactsAblitity(String contactsAblitity) {
        ContactsAblitity = contactsAblitity;
    }

    public String getTeamAblitity() {
        return teamAblitity;
    }

    public void setTeamAblitity(String teamAblitity) {
        this.teamAblitity = teamAblitity;
    }

    public String getManNumber() {
        return manNumber;
    }

    public void setManNumber(String manNumber) {
        this.manNumber = manNumber;
    }

    public Boolean getIsSale() {
        return isSale;
    }

    public void setIsSale(Boolean isSale) {
        this.isSale = isSale;
    }

    public Boolean getIsBeforeSale() {
        return isBeforeSale;
    }

    public void setIsBeforeSale(Boolean isBeforeSale) {
        this.isBeforeSale = isBeforeSale;
    }

    public Boolean getIsAfterSale() {
        return isAfterSale;
    }

    public void setIsAfterSale(Boolean isAfterSale) {
        this.isAfterSale = isAfterSale;
    }

    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }

    public Boolean getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Boolean isFlag) {
        this.isFlag = isFlag;
    }

    public Boolean getIsExplainBook() {
        return isExplainBook;
    }

    public void setIsExplainBook(Boolean isExplainBook) {
        this.isExplainBook = isExplainBook;
    }

    public Boolean getIsOuttest() {
        return isOuttest;
    }

    public void setIsOuttest(Boolean isOuttest) {
        this.isOuttest = isOuttest;
    }

    public Boolean getIsEntrust() {
        return isEntrust;
    }

    public void setIsEntrust(Boolean isEntrust) {
        this.isEntrust = isEntrust;
    }

    public Boolean getIsReform() {
        return isReform;
    }

    public void setIsReform(Boolean isReform) {
        this.isReform = isReform;
    }

    public Boolean getIsFullsale() {
        return isFullsale;
    }

    public void setIsFullsale(Boolean isFullsale) {
        this.isFullsale = isFullsale;
    }

    public Boolean getIsFullstorage() {
        return isFullstorage;
    }

    public void setIsFullstorage(Boolean isFullstorage) {
        this.isFullstorage = isFullstorage;
    }


    public Boolean getIsFullaftersale() {
        return isFullaftersale;
    }

    public void setIsFullaftersale(Boolean isFullaftersale) {
        this.isFullaftersale = isFullaftersale;
    }

    public String getAvgDay() {
        return avgDay;
    }

    public void setAvgDay(String avgDay) {
        this.avgDay = avgDay;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public Boolean getIsMatching() {
        return isMatching;
    }

    public void setIsMatching(Boolean isMatching) {
        this.isMatching = isMatching;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


    public String getBusinessLicenseCode() {
        return businessLicenseCode;
    }

    public void setBusinessLicenseCode(String businessLicenseCode) {
        this.businessLicenseCode = businessLicenseCode;
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

    public String getSupplyAddress() {
        return supplyAddress;
    }

    public void setSupplyAddress(String supplyAddress) {
        this.supplyAddress = supplyAddress;
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


    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

   /**************联系人信息后面补充字段*******************/
    public Boolean getSale() {
        return isSale;
    }

    public void setSale(Boolean sale) {
        isSale = sale;
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