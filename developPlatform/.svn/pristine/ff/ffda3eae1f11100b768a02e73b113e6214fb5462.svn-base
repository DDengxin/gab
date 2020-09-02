package com.tengzhi.business.sc.da.rydg.vo;

import com.tengzhi.business.sc.da.rydg.model.MRyYgdg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MRyYgdgVo implements Serializable {
    MRyYgdg mRyYgdg;
    List<MRyYgdg> mRyYgdgList,
            addedList = new ArrayList<>(),
            modifyedList = new ArrayList<>(),
            deletedList = new ArrayList<>();


    public MRyYgdg getmRyYgdg() {
        return mRyYgdg;
    }


    public void setmRyYgdg(MRyYgdg mRyYgdg) {
        this.mRyYgdg = mRyYgdg;
    }


    public List<MRyYgdg> getmRyYgdgList() {
        return mRyYgdgList;
    }


    public void setmRyYgdgList(List<MRyYgdg> mRyYgdgList) {
        this.mRyYgdgList = mRyYgdgList;
    }


    public List<MRyYgdg> getAddedList() {
        return addedList;
    }


    public void setAddedList(List<MRyYgdg> addedList) {
        this.addedList = addedList;
    }


    public List<MRyYgdg> getModifyedList() {
        return modifyedList;
    }


    public void setModifyedList(List<MRyYgdg> modifyedList) {
        this.modifyedList = modifyedList;
    }


    public List<MRyYgdg> getDeletedList() {
        return deletedList;
    }


    public void setDeletedList(List<MRyYgdg> deletedList) {
        this.deletedList = deletedList;
    }


    public static MRyYgdgVo initMRyYgdgVo(MRyYgdgVo mRyYgdgVo) {
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(mRyYgdgVo.getmRyYgdgList().spliterator(), false).forEach(mRyYgdg -> {
            switch (mRyYgdg.get_state()) {
                case "added":
                    mRyYgdg.setWorkRq(mRyYgdgVo.getmRyYgdg().getWorkRq());
                    mRyYgdg.setWorkDept(mRyYgdgVo.getmRyYgdg().getWorkDept());
                    mRyYgdgVo.getAddedList().add(mRyYgdg);
                    break;
                case "modified":
                    mRyYgdg.setWorkRq(mRyYgdgVo.getmRyYgdg().getWorkRq());
                    mRyYgdg.setWorkDept(mRyYgdgVo.getmRyYgdg().getWorkDept());
                    mRyYgdgVo.getModifyedList().add(mRyYgdg);
                    break;
                case "removed":
                    mRyYgdgVo.getDeletedList().add(mRyYgdg);
                    break;
                default:
                    break;
            }
        });
        return mRyYgdgVo;
    }

}
