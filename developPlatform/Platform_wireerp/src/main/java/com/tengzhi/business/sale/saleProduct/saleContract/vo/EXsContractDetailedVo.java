package com.tengzhi.business.sale.saleProduct.saleContract.vo;

import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailedItem;

import java.util.List;

public class EXsContractDetailedVo {
    EXsContractDetailed eXsContractDetailed;
    List<EXsContractDetailedItem> htItemData;

    public EXsContractDetailed geteXsContractDetailed() {
        return eXsContractDetailed;
    }

    public void seteXsContractDetailed(EXsContractDetailed eXsContractDetailed) {
        this.eXsContractDetailed = eXsContractDetailed;
    }

    public List<EXsContractDetailedItem> getHtItemData() {
        return htItemData;
    }

    public void setHtItemData(List<EXsContractDetailedItem> htItemData) {
        this.htItemData = htItemData;
    }
}
