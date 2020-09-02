package com.tengzhi.business.mSbJt.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.StreamSupport;


import com.tengzhi.business.mSbJt.model.MSbJt;
import com.tengzhi.business.mSbJt.model.MSbJtbj;

public class MSbJtVo implements Serializable {
	MSbJt mSbJt;
    List<MSbJtbj> mSbJtbj,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
	public MSbJt getmSbJt() {
		return mSbJt;
	}
	public void setmSbJt(MSbJt mSbJt) {
		this.mSbJt = mSbJt;
	}
	public List<MSbJtbj> getmSbJtbj() {
		return mSbJtbj;
	}
	public void setmSbJtbj(List<MSbJtbj> mSbJtbj) {
		this.mSbJtbj = mSbJtbj;
	}
	public List<MSbJtbj> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<MSbJtbj> addedList) {
		this.addedList = addedList;
	}
	public List<MSbJtbj> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<MSbJtbj> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<MSbJtbj> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<MSbJtbj> deletedList) {
		this.deletedList = deletedList;
	}
	public static MSbJtVo initMSbJtVo(MSbJtVo mSbJtVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(mSbJtVo.getmSbJtbj().spliterator(),false).forEach(mSbJtbj -> {
            switch (mSbJtbj.get_state()) {
                case "added":
                	mSbJtbj.setMachineId(mSbJtVo.getmSbJt().getMachineId());
                	mSbJtVo.getAddedList().add(mSbJtbj);
                    break;
                case "modified":
                	mSbJtVo.getModifyedList().add(mSbJtbj);
                    break;
                case "removed":
                	mSbJtVo.getDeletedList().add(mSbJtbj);
                    break;
            }
        });
        return mSbJtVo;
    }
	

	

}
