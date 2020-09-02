package com.tengzhi.business.js.cpgy.scgy.vo;

import com.tengzhi.business.js.cpgy.scgy.model.MScScgy;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgymx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MScScgyVo implements Serializable {
    MScScgy mScScgy;
    List<MScScgymx> mScScgymx,
            addedList = new ArrayList<>(),
            modifyedList = new ArrayList<>(),
            deletedList = new ArrayList<>();


    public MScScgy getmScScgy() {
        return mScScgy;
    }


    public void setmScScgy(MScScgy mScScgy) {
        this.mScScgy = mScScgy;
    }


    public List<MScScgymx> getmScScgymx() {
        return mScScgymx;
    }


    public void setmScScgymx(List<MScScgymx> mScScgymx) {
        this.mScScgymx = mScScgymx;
    }


    public List<MScScgymx> getAddedList() {
        return addedList;
    }


    public void setAddedList(List<MScScgymx> addedList) {
        this.addedList = addedList;
    }


    public List<MScScgymx> getModifyedList() {
        return modifyedList;
    }


    public void setModifyedList(List<MScScgymx> modifyedList) {
        this.modifyedList = modifyedList;
    }


    public List<MScScgymx> getDeletedList() {
        return deletedList;
    }


    public void setDeletedList(List<MScScgymx> deletedList) {
        this.deletedList = deletedList;
    }


    public static MScScgyVo initMScScgyVo(MScScgyVo mScScgyVo) {
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(mScScgyVo.getmScScgymx().spliterator(), false).forEach(model -> {
            switch (model.get_state()) {
                case "added":
                    model.setGyId(mScScgyVo.getmScScgy().getGyId());
                    mScScgyVo.getAddedList().add(model);
                    break;
                case "modified":
                    model.setGyId(mScScgyVo.getmScScgy().getGyId());
                    mScScgyVo.getModifyedList().add(model);
                    break;
                case "removed":
                    mScScgyVo.getDeletedList().add(model);
                    break;
                default:
                    break;
            }
        });
        return mScScgyVo;
    }

}
