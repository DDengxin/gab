package com.tengzhi.business.js.cpgy.scgx.vo;

import com.tengzhi.business.js.cpgy.scgx.model.MScScgx;
import com.tengzhi.business.js.cpgy.scgx.model.MScScgxmx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MScScgxVo implements Serializable {
    MScScgx mScScgx;
    List<MScScgxmx> mScScgxmx,
            addedList = new ArrayList<>(),
            modifyedList = new ArrayList<>(),
            deletedList = new ArrayList<>();


    public MScScgx getmScScgx() {
        return mScScgx;
    }


    public void setmScScgx(MScScgx mScScgx) {
        this.mScScgx = mScScgx;
    }


    public List<MScScgxmx> getmScScgxmx() {
        return mScScgxmx;
    }


    public void setmScScgxmx(List<MScScgxmx> mScScgxmx) {
        this.mScScgxmx = mScScgxmx;
    }


    public List<MScScgxmx> getAddedList() {
        return addedList;
    }


    public void setAddedList(List<MScScgxmx> addedList) {
        this.addedList = addedList;
    }


    public List<MScScgxmx> getModifyedList() {
        return modifyedList;
    }


    public void setModifyedList(List<MScScgxmx> modifyedList) {
        this.modifyedList = modifyedList;
    }


    public List<MScScgxmx> getDeletedList() {
        return deletedList;
    }


    public void setDeletedList(List<MScScgxmx> deletedList) {
        this.deletedList = deletedList;
    }


    public static MScScgxVo initMScScgxVo(MScScgxVo mScScgxVo) {
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(mScScgxVo.getmScScgxmx().spliterator(), false).forEach(model -> {
            switch (model.get_state()) {
                case "added":
                    model.setGxId(mScScgxVo.getmScScgx().getGxId());
                    mScScgxVo.getAddedList().add(model);
                    break;
                case "modified":
                    model.setGxId(mScScgxVo.getmScScgx().getGxId());
                    mScScgxVo.getModifyedList().add(model);
                    break;
                case "removed":
                    mScScgxVo.getDeletedList().add(model);
                    break;
                default:
                    break;
            }
        });
        return mScScgxVo;
    }

}
