package com.tengzhi.business.sc.task.sctack.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl;

public class MScScrwWlVo implements Serializable {
	
	MScScrwGx  mScScrwGx;
    List<MScScrwWl> mScScrwWlList;

    
	public MScScrwGx getmScScrwGx() {
		return mScScrwGx;
	}

	public void setmScScrwGx(MScScrwGx mScScrwGx) {
		this.mScScrwGx = mScScrwGx;
	}

	public List<MScScrwWl> getmScScrwWlList() {
		return mScScrwWlList;
	}

	public void setmScScrwWlList(List<MScScrwWl> mScScrwWlList) {
		this.mScScrwWlList = mScScrwWlList;
	}


	/**
	 * 同一个方法同时存在保存，删除，修改时调用
	 */
	List<MScScrwWl> addedList = new ArrayList<>(),
			modifyedList = new ArrayList<>(),
			deletedList = new ArrayList<>();
	public List<MScScrwWl> getAddedList() {
		return addedList;
	}
	public List<MScScrwWl> getModifyedList() {
		return modifyedList;
	}
	public List<MScScrwWl> getDeletedList() {
		return deletedList;
	}
	public static MScScrwWlVo initMScScrwWlVo(MScScrwWlVo wlvo){
		//创建流低级方法(基于迭代器)
		wlvo.getmScScrwWlList().forEach( wlitem -> {
			switch (wlitem.get_state()) {
				case "added"://新建
					wlvo.getAddedList().add(wlitem);
					break;
				case "modified"://修改
					wlvo.getModifyedList().add(wlitem);
					break;
				case "removed"://删除
					wlvo.getDeletedList().add(wlitem);
					break;
				default:break;
			}
		});
		return wlvo;
	}


}
