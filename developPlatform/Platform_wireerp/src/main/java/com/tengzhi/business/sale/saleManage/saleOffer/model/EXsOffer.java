package com.tengzhi.business.sale.saleManage.saleOffer.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="e_xs_offer")
public class EXsOffer {

    @Id
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rq;
    private String saleMaster;
    private String saleTaxRate;
    private String saleCurrency;
    private String saleTechnicalStandard;
    private String saleTechnicalRemarks;
    private String salePackRemarks;
    private String saleShippingMethod;
    private String saleDeliveryAddress;
    private String saleOtherRequest;
    private String saleRequestRemarks;
    private String saleClearForm;
    private String saleClearRequest;
    private String salePriceWay;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date salePriceValidity;
    private String saleOtherRemarks;
    private String saleStype;
    private String masterProvince;
    private String masterCity;
    private String masterCountry;
    private String saleMan;
    private String saleIsTodoor;
    private String saleTransitAddress;
    private String saleCommission;
    private String saleExchangeRate;
    private String lastNote;
    private String saleDeparturePort;
    private String masterLinkMan;
    private String masterLinkTel;
    private String masterFax;
    private String dataMan;
    private String dataCorp;
    private String saleFlag;
    private String thisNote;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getSaleMaster() {
        return saleMaster;
    }

    public void setSaleMaster(String saleMaster) {
        this.saleMaster = saleMaster;
    }

    public String getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(String saleTaxRate) { this.saleTaxRate = saleTaxRate; }

    public String getSaleCurrency() {
        return saleCurrency;
    }

    public void setSaleCurrency(String saleCurrency) {
        this.saleCurrency = saleCurrency;
    }

    public String getSaleTechnicalStandard() {
        return saleTechnicalStandard;
    }

    public void setSaleTechnicalStandard(String saleTechnicalStandard) { this.saleTechnicalStandard = saleTechnicalStandard; }

    public String getSaleTechnicalRemarks() {
        return saleTechnicalRemarks;
    }

    public void setSaleTechnicalRemarks(String saleTechnicalRemarks) { this.saleTechnicalRemarks = saleTechnicalRemarks; }

    public String getSalePackRemarks() {
        return salePackRemarks;
    }

    public void setSalePackRemarks(String salePackRemarks) {
        this.salePackRemarks = salePackRemarks;
    }

    public String getSaleShippingMethod() {
        return saleShippingMethod;
    }

    public void setSaleShippingMethod(String saleShippingMethod) {
        this.saleShippingMethod = saleShippingMethod;
    }

    public String getSaleDeliveryAddress() {
        return saleDeliveryAddress;
    }

    public void setSaleDeliveryAddress(String saleDeliveryAddress) {
        this.saleDeliveryAddress = saleDeliveryAddress;
    }

    public String getSaleOtherRequest() { return saleOtherRequest; }

    public void setSaleOtherRequest(String saleOtherRequest) {
        this.saleOtherRequest = saleOtherRequest;
    }

    public String getSaleRequestRemarks() {
        return saleRequestRemarks;
    }

    public void setSaleRequestRemarks(String saleRequestRemarks) {
        this.saleRequestRemarks = saleRequestRemarks;
    }

    public String getSaleClearForm() {
        return saleClearForm;
    }

    public void setSaleClearForm(String saleClearForm) {
        this.saleClearForm = saleClearForm;
    }

    public String getSaleClearRequest() { return saleClearRequest; }

    public void setSaleClearRequest(String saleClearRequest) { this.saleClearRequest = saleClearRequest; }

    public String getSalePriceWay() {
        return salePriceWay;
    }

    public void setSalePriceWay(String salePriceWay) {
        this.salePriceWay = salePriceWay;
    }

    public Date getSalePriceValidity() {
        return salePriceValidity;
    }

    public void setSalePriceValidity(Date salePriceValidity) {
        this.salePriceValidity = salePriceValidity;
    }

    public String getSaleOtherRemarks() { return saleOtherRemarks; }

    public void setSaleOtherRemarks(String saleOtherRemarks) {
        this.saleOtherRemarks = saleOtherRemarks;
    }

    public String getSaleStype() {
        return saleStype;
    }

    public void setSaleStype(String saleStype) {
        this.saleStype = saleStype;
    }

    public String getMasterProvince() {
        return masterProvince;
    }

    public void setMasterProvince(String masterProvince) {
        this.masterProvince = masterProvince;
    }

    public String getMasterCity() {
        return masterCity;
    }

    public void setMasterCity(String masterCity) {
        this.masterCity = masterCity;
    }

    public String getMasterCountry() {
        return masterCountry;
    }

    public void setMasterCountry(String masterCountry) {
        this.masterCountry = masterCountry;
    }

    public String getSaleMan() {
        return saleMan;
    }

    public void setSaleMan(String saleMan) { this.saleMan = saleMan; }

    public String getSaleIsTodoor() {
        return saleIsTodoor;
    }

    public void setSaleIsTodoor(String saleIsTodoor) {
        this.saleIsTodoor = saleIsTodoor;
    }

    public String getSaleTransitAddress() {
        return saleTransitAddress;
    }

    public void setSaleTransitAddress(String saleTransitAddress) {
        this.saleTransitAddress = saleTransitAddress;
    }

    public String getSaleCommission() {
        return saleCommission;
    }

    public void setSaleCommission(String saleCommission) {
        this.saleCommission = saleCommission;
    }

    public String getSaleExchangeRate() { return saleExchangeRate; }

    public void setSaleExchangeRate(String saleExchangeRate) {
        this.saleExchangeRate = saleExchangeRate;
    }

    public String getLastNote() {
        return lastNote;
    }

    public void setLastNote(String lastNote) {
        this.lastNote = lastNote;
    }

    public String getSaleDeparturePort() {
        return saleDeparturePort;
    }

    public void setSaleDeparturePort(String saleDeparturePort) {
        this.saleDeparturePort = saleDeparturePort;
    }

    public String getMasterLinkMan() {
        return masterLinkMan;
    }

    public void setMasterLinkMan(String masterLinkMan) {
        this.masterLinkMan = masterLinkMan;
    }

    public String getMasterLinkTel() {
        return masterLinkTel;
    }

    public void setMasterLinkTel(String masterLinkTel) {
        this.masterLinkTel = masterLinkTel;
    }

    public String getMasterFax() {
        return masterFax;
    }

    public void setMasterFax(String masterFax) {
        this.masterFax = masterFax;
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

    public String getSaleFlag() { return saleFlag; }

    public void setSaleFlag(String saleFlag) {
        this.saleFlag = saleFlag;
    }

    public String getThisNote() { return thisNote; }

    public void setThisNote(String thisNote) { this.thisNote = thisNote; }
}
