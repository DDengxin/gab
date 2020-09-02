package com.tengzhi.business.platform.paysdk.buyproduct.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-07-17
 */
@Entity
@Table(name = "g_product_notes")
public class GProductNotes {
    @Id
    private String note;
    private String productId;
    private String linkMan;
    private String district;
    private String city;
    private String phone;
    private String mail;
    private String providerId;
    private Integer orderSize;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;
    private String orderMan;
    private String orderManName;
    private Boolean invoice;
    private Boolean contract;
    private String remark;

    @Basic
    @Column(name = "note", nullable = true, length = 32)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "product_id", nullable = true, length = 32)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "link_man", nullable = true, length = 32)
    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Basic
    @Column(name = "district", nullable = true, length = 20)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 20)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "mail", nullable = true, length = 30)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "provider_id", nullable = true, length = 32)
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "order_size", nullable = true)
    public Integer getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(Integer orderSize) {
        this.orderSize = orderSize;
    }

    @Basic
    @Column(name = "order_time ", nullable = true)
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "order_man", nullable = true, length = 32)
    public String getOrderMan() {
        return orderMan;
    }

    public void setOrderMan(String orderMan) {
        this.orderMan = orderMan;
    }

    @Basic
    @Column(name = "order_man_name", nullable = true, length = 32)
    public String getOrderManName() {
        return orderManName;
    }

    public void setOrderManName(String orderManName) {
        this.orderManName = orderManName;
    }

    @Basic
    @Column(name = "invoice ", nullable = true)
    public Boolean getInvoice() {
        return invoice;
    }

    public void setInvoice(Boolean invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "contract", nullable = true)
    public Boolean getContract() {
        return contract;
    }

    public void setContract(Boolean contract) {
        this.contract = contract;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GProductNotes that = (GProductNotes) o;
        return Objects.equals(note, that.note) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(linkMan, that.linkMan) &&
                Objects.equals(district, that.district) &&
                Objects.equals(city, that.city) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(providerId, that.providerId) &&
                Objects.equals(orderSize, that.orderSize) &&
                Objects.equals(orderTime, that.orderTime) &&
                Objects.equals(orderMan, that.orderMan) &&
                Objects.equals(orderManName, that.orderManName) &&
                Objects.equals(invoice, that.invoice) &&
                Objects.equals(contract, that.contract) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note, productId, linkMan, district, city, phone, mail, providerId, orderSize, orderTime, orderMan, orderManName, invoice, contract, remark);
    }
}
