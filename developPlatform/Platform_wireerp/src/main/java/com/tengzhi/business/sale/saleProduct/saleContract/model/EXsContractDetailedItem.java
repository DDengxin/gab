package com.tengzhi.business.sale.saleProduct.saleContract.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_xs_contract_detailed_item")
public class EXsContractDetailedItem extends BaseModel {

    @Id
    private String  itemSid;
    private String htNo,htMo,htCode,htItemCode,htItemValue,dataMan,dataCorp;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date dataDate;

    private String htItemValueReply,htItemReplyMan;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date htItemReplyDate;


    @Transient
    private String _state;

    public String getHtMo() {
        return htMo;
    }

    public void setHtMo(String htMo) {
        this.htMo = htMo;
    }

    public String getHtNo() {
        return htNo;
    }

    public void setHtNo(String htNo) {
        this.htNo = htNo;
    }

    public String getHtCode() {
        return htCode;
    }

    public void setHtCode(String htCode) {
        this.htCode = htCode;
    }

    public String getHtItemCode() {
        return htItemCode;
    }

    public void setHtItemCode(String htItemCode) {
        this.htItemCode = htItemCode;
    }

    public String getHtItemValue() {
        return htItemValue;
    }

    public void setHtItemValue(String htItemValue) {
        this.htItemValue = htItemValue;
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

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String getHtItemValueReply() {
        return htItemValueReply;
    }

    public void setHtItemValueReply(String htItemValueReply) {
        this.htItemValueReply = htItemValueReply;
    }

    public String getHtItemReplyMan() {
        return htItemReplyMan;
    }

    public void setHtItemReplyMan(String htItemReplyMan) {
        this.htItemReplyMan = htItemReplyMan;
    }

    public Date getHtItemReplyDate() {
        return htItemReplyDate;
    }

    public void setHtItemReplyDate(Date htItemReplyDate) {
        this.htItemReplyDate = htItemReplyDate;
    }
}
