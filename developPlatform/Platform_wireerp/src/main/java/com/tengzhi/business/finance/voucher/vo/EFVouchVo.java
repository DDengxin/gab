package com.tengzhi.business.finance.voucher.vo;

import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.EFVoucherentry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EFVouchVo implements Serializable {
    EFVoucher efVoucher;
    List<EFVoucherentry> efVoucherentries,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();

    public EFVoucher getEfVoucher() {
        return efVoucher;
    }

    public void setEfVoucher(EFVoucher efVoucher) {
        this.efVoucher = efVoucher;
    }

    public List<EFVoucherentry> getEfVoucherentries() {
        return efVoucherentries;
    }

    public void setEfVoucherentries(List<EFVoucherentry> efVoucherentries) {
        this.efVoucherentries = efVoucherentries;
    }

    public List<EFVoucherentry> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<EFVoucherentry> addedList) {
        this.addedList = addedList;
    }

    public List<EFVoucherentry> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<EFVoucherentry> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<EFVoucherentry> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<EFVoucherentry> deletedList) {
        this.deletedList = deletedList;
    }

    public  static EFVouchVo initEFVouchVo(EFVouchVo efVouchVo ){
        //创建流低级方法(基于迭代器)
        if(null==efVouchVo.getEfVoucherentries()){}else{
            StreamSupport.stream(efVouchVo.getEfVoucherentries().spliterator(),false).forEach(voucherentry -> {
                switch (voucherentry.get_state()) {
                    case "added":
                        voucherentry.setFvoucherid(efVouchVo.getEfVoucher().getFvoucherid());
                        efVouchVo.getAddedList().add(voucherentry);
                        break;
                    case "modified":
                        efVouchVo.getModifyedList().add(voucherentry);
                        break;
                    case "removed":
                        efVouchVo.getDeletedList().add(voucherentry);
                        break;
                }
            });
        }

        return efVouchVo;
    }

}
