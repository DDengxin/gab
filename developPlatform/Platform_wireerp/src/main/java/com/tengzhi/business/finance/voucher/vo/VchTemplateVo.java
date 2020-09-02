package com.tengzhi.business.finance.voucher.vo;

import com.tengzhi.business.finance.voucher.model.VchTemplate;
import com.tengzhi.business.finance.voucher.model.Vchtemplateentry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class VchTemplateVo implements Serializable {
    VchTemplate maindata;
    List<Vchtemplateentry> entries,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();

    public VchTemplate getMaindata() {
        return maindata;
    }

    public void setMaindata(VchTemplate maindata) {
        this.maindata = maindata;
    }

    public List<Vchtemplateentry> getEntries() {
        return entries;
    }

    public void setEntries(List<Vchtemplateentry> entries) {
        this.entries = entries;
    }

    public List<Vchtemplateentry> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<Vchtemplateentry> addedList) {
        this.addedList = addedList;
    }

    public List<Vchtemplateentry> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<Vchtemplateentry> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<Vchtemplateentry> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<Vchtemplateentry> deletedList) {
        this.deletedList = deletedList;
    }

    public  static VchTemplateVo initVo(VchTemplateVo vo ){
        //创建流低级方法(基于迭代器)
        if(null==vo.getEntries()){}else{
            StreamSupport.stream(vo.getEntries().spliterator(),false).forEach(entryx -> {
                switch (entryx.get_state()) {
                    case "added":
                        vo.getAddedList().add(entryx);
                        break;
                    case "modified":
                        entryx.setFvctemplateid(vo.getMaindata().getFvctemplateid());
                        vo.getModifyedList().add(entryx);
                        break;
                    case "removed":
                        vo.getDeletedList().add(entryx);
                        break;
                }
            });
        }

        return vo;
    }

}
