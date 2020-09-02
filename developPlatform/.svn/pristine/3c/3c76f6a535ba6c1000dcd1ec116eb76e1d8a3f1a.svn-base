package com.tengzhi.business.sc.task.sctack.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.StreamSupport;


import cn.hutool.core.util.RandomUtil;
import com.tengzhi.business.mSbJt.model.MSbJt;
import com.tengzhi.business.mSbJt.model.MSbJtbj;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.system.right.model.SysRight;
import com.tengzhi.business.system.right.vo.SysRightVo;

public class MScScrwGxVo implements Serializable {
	
	MScScrw  mScScrw;
    List<MScScrwGx> mScScrwGxList;

    
	public MScScrw getmScScrw() {
		return mScScrw;
	}

	public void setmScScrw(MScScrw mScScrw) {
		this.mScScrw = mScScrw;
	}

	public List<MScScrwGx> getmScScrwGxList() {
		return mScScrwGxList;
	}

	public void setmScScrwGxList(List<MScScrwGx> mScScrwGxList) {
		this.mScScrwGxList = mScScrwGxList;
	}


	/**
	 * 同一个方法同时存在保存，删除，修改时调用
	 */
	List<MScScrwGx> addedList = new ArrayList<>(),
			modifyedList = new ArrayList<>(),
			deletedList = new ArrayList<>();
	public List<MScScrwGx> getAddedList() {
		return addedList;
	}
	public List<MScScrwGx> getModifyedList() {
		return modifyedList;
	}
	public List<MScScrwGx> getDeletedList() {
		return deletedList;
	}
	public static MScScrwGxVo initMScScrwGxVo(MScScrwGxVo mScScrwGxVo){
		//创建流低级方法(基于迭代器)
		mScScrwGxVo.getmScScrwGxList().forEach( gxVo -> {
			switch (gxVo.get_state()) {
				case "added"://新建
					mScScrwGxVo.getAddedList().add(gxVo);
					break;
				case "modified"://修改
					mScScrwGxVo.getModifyedList().add(gxVo);
					break;
				case "removed"://删除
					mScScrwGxVo.getDeletedList().add(gxVo);
					break;
				default:break;
			}
		});
		return mScScrwGxVo;
	}
}
