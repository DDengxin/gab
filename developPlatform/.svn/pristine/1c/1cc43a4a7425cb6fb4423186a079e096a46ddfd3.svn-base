package com.tengzhi.business.quality.qualityDetection.productDetection.service.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.finance.constituent.model.EPzJyList;
import com.tengzhi.business.quality.qualityDetection.productDetection.model.EPzJylistJc;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;

public class EPzJyListJcVo implements Serializable {

	EPzJyList ePzJylist;
	List<EPzJylistJc> ePzJcs,
	addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
	
	
	public EPzJyList getePzJylist() {
		return ePzJylist;
	}
	public void setePzJylist(EPzJyList ePzJylist) {
		this.ePzJylist = ePzJylist;
	}
	public List<EPzJylistJc> getePzJcs() {
		return ePzJcs;
	}
	public void setePzJcs(List<EPzJylistJc> ePzJcs) {
		this.ePzJcs = ePzJcs;
	}
	
	public List<EPzJylistJc> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<EPzJylistJc> addedList) {
		this.addedList = addedList;
	}
	public List<EPzJylistJc> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<EPzJylistJc> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<EPzJylistJc> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<EPzJylistJc> deletedList) {
		this.deletedList = deletedList;
	}
	public static EPzJyListJcVo initEPzJyListJcVo(EPzJyListJcVo ePzJyListJcVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(ePzJyListJcVo.getePzJcs().spliterator(),false).forEach(model -> {
            switch (model.get_state()) {
                case "added":
                	model.setPch(ePzJyListJcVo.getePzJylist().getPch());
                	ePzJyListJcVo.getAddedList().add(model);
                    break;
                case "modified":
                	ePzJyListJcVo.getModifyedList().add(model);
                    break;
                case "removed":
                	ePzJyListJcVo.getDeletedList().add(model);
                    break;
            }
        });
        return ePzJyListJcVo;
    }
}
