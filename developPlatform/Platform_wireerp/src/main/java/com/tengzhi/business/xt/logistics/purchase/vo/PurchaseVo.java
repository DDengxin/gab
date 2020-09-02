package com.tengzhi.business.xt.logistics.purchase.vo;

import com.tengzhi.business.xt.logistics.purchase.model.Purchase;

import java.io.Serializable;
import java.util.List;

public class PurchaseVo implements Serializable {
    Purchase headdata;
    List<Purchase> entitydata;

    public Purchase getHeaddata() {
        return headdata;
    }
    public void setHeaddata(Purchase headdata) {
        this.headdata = headdata;
    }
    public List<Purchase> getEntitydata() {
        return entitydata;
    }
    public void setEntitydata(List<Purchase> entitydata) {
        this.entitydata = entitydata;
    }

}
