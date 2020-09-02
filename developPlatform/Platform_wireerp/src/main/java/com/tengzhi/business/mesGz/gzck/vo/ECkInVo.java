package com.tengzhi.business.mesGz.gzck.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;



public class ECkInVo implements Serializable {
	ECkIn eCkIn;
    List<ECkIn> eCkInMx,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();

    public ECkIn geteCkIn() {
		return eCkIn;
	}


	public void seteCkIn(ECkIn eCkIn) {
		this.eCkIn = eCkIn;
	}


	public List<ECkIn> geteCkInMx() {
		return eCkInMx;
	}


	public void seteCkInMx(List<ECkIn> eCkInMx) {
		this.eCkInMx = eCkInMx;
	}


	public List<ECkIn> getAddedList() {
		return addedList;
	}


	public void setAddedList(List<ECkIn> addedList) {
		this.addedList = addedList;
	}


	public List<ECkIn> getModifyedList() {
		return modifyedList;
	}


	public void setModifyedList(List<ECkIn> modifyedList) {
		this.modifyedList = modifyedList;
	}


	public List<ECkIn> getDeletedList() {
		return deletedList;
	}


	public void setDeletedList(List<ECkIn> deletedList) {
		this.deletedList = deletedList;
	}


	public static ECkInVo initECgContractVo(ECkInVo eCkInVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(eCkInVo.geteCkInMx().spliterator(),false).forEach(eCkInMx -> {
            switch (eCkInMx.get_state()) {
                case "added":
                	eCkInMx.setInRq(eCkInVo.geteCkIn().getInRq());
                	eCkInMx.setInNote(eCkInVo.geteCkIn().getInNote());
                	eCkInMx.setInAct(eCkInVo.geteCkIn().getInAct());
                	eCkInMx.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
                	eCkInMx.setInLib(eCkInVo.geteCkIn().getInLib());
                	eCkInVo.getAddedList().add(eCkInMx);
                    break;
                case "modified":
                	eCkInMx.setInRq(eCkInVo.geteCkIn().getInRq());
                	eCkInMx.setInNote(eCkInVo.geteCkIn().getInNote());
                	eCkInMx.setInAct(eCkInVo.geteCkIn().getInAct());
                	eCkInMx.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
                	eCkInMx.setInLib(eCkInVo.geteCkIn().getInLib());
                	eCkInVo.getModifyedList().add(eCkInMx);
                    break;
                case "removed":
                	eCkInMx.setInRq(eCkInVo.geteCkIn().getInRq());
                	eCkInMx.setInNote(eCkInVo.geteCkIn().getInNote());
                	eCkInMx.setInAct(eCkInVo.geteCkIn().getInAct());
                	eCkInMx.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
                	eCkInMx.setInLib(eCkInVo.geteCkIn().getInLib());
                	eCkInVo.getDeletedList().add(eCkInMx);
                    break;
            }
        });
        return eCkInVo;
    }

}
