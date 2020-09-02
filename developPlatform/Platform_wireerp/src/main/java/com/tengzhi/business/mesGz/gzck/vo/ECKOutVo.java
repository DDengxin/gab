package com.tengzhi.business.mesGz.gzck.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class ECKOutVo implements Serializable {
	EckOut eCkOut;
    List<EckOut> eckOutMx,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
	public EckOut geteCkOut() {
		return eCkOut;
	}
	public void seteCkOut(EckOut eCkOut) {
		this.eCkOut = eCkOut;
	}

	public List<EckOut> getEckOutMx() {
		return eckOutMx;
	}
	public void setEckOutMx(List<EckOut> eckOutMx) {
		this.eckOutMx = eckOutMx;
	}
	public List<EckOut> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<EckOut> addedList) {
		this.addedList = addedList;
	}
	public List<EckOut> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<EckOut> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<EckOut> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<EckOut> deletedList) {
		this.deletedList = deletedList;
	}
    
	public static ECKOutVo initECgContractVo(ECKOutVo eCkOutVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(eCkOutVo.getEckOutMx().spliterator(),false).forEach(eCkOutMx -> {
            switch (eCkOutMx.get_state()) {
                case "added":
                	eCkOutMx.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                	eCkOutMx.setOutNote(eCkOutVo.geteCkOut().getOutNote());
                	eCkOutMx.setOutAct(eCkOutVo.geteCkOut().getOutAct());
                	eCkOutMx.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                	eCkOutMx.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                	eCkOutMx.setOutType(eCkOutVo.geteCkOut().getOutType());
                	eCkOutVo.getAddedList().add(eCkOutMx);
                    break;
                    
                case "modified":
                	eCkOutMx.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                	eCkOutMx.setOutNote(eCkOutVo.geteCkOut().getOutNote());
                	eCkOutMx.setOutAct(eCkOutVo.geteCkOut().getOutAct());
                	eCkOutMx.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                	eCkOutMx.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                	eCkOutMx.setOutType(eCkOutVo.geteCkOut().getOutType());
                	eCkOutVo.getModifyedList().add(eCkOutMx);
                    break;
                    
                case "removed":
                	eCkOutMx.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                	eCkOutMx.setOutNote(eCkOutVo.geteCkOut().getOutNote());
                	eCkOutMx.setOutAct(eCkOutVo.geteCkOut().getOutAct());
                	eCkOutMx.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                	eCkOutMx.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                	eCkOutMx.setOutType(eCkOutVo.geteCkOut().getOutType());
                	eCkOutVo.getDeletedList().add(eCkOutMx);
                    break;
            }
        });
        return eCkOutVo;
    }
}
