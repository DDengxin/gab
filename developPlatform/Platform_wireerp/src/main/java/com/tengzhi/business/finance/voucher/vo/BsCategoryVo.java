package com.tengzhi.business.finance.voucher.vo;

import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.EFVoucherBscategory;
import com.tengzhi.business.finance.voucher.model.EFVoucherBscategoryentry;
import com.tengzhi.business.finance.voucher.model.EFVoucherentry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class BsCategoryVo implements Serializable {
    EFVoucherBscategory bscategory;
    List<EFVoucherBscategoryentry> entries,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();

    public EFVoucherBscategory getBscategory() {
        return bscategory;
    }

    public void setBscategory(EFVoucherBscategory bscategory) {
        this.bscategory = bscategory;
    }


    public List<EFVoucherBscategoryentry> getEntries() {
        return entries;
    }

    public void setEntries(List<EFVoucherBscategoryentry> entries) {
        this.entries = entries;
    }

    public List<EFVoucherBscategoryentry> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<EFVoucherBscategoryentry> addedList) {
        this.addedList = addedList;
    }

    public List<EFVoucherBscategoryentry> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<EFVoucherBscategoryentry> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<EFVoucherBscategoryentry> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<EFVoucherBscategoryentry> deletedList) {
        this.deletedList = deletedList;
    }

    public  static BsCategoryVo initVo(BsCategoryVo efVouchVo ){
        //创建流低级方法(基于迭代器)
        if(null==efVouchVo.getEntries()){}else{
            StreamSupport.stream(efVouchVo.getEntries().spliterator(),false).forEach(entry -> {
                switch (entry.get_state()) {
                    case "added":
                        entry.setFevbusid(efVouchVo.getBscategory().getFevbusid());
                        efVouchVo.getAddedList().add(entry);
                        break;
                    case "modified":
                        entry.setFevbusid(efVouchVo.getBscategory().getFevbusid());
                        efVouchVo.getModifyedList().add(entry);
                        break;
                    case "removed":
                        efVouchVo.getDeletedList().add(entry);
                        break;
                }
            });
        }

        return efVouchVo;
    }

}
