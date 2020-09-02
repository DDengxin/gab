package com.tengzhi.business.ck.yw.warehouseAllot.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.ck.yw.warehouseAllot.model.ECkAllot;

public class ECkAllotVo implements Serializable {

	ECkOut eCkAllot;
	List<ECkOut> ckList,
	addedList = new ArrayList<>(),
	modifyedList = new ArrayList<>(),
	deletedList = new ArrayList<>();
	
	
	public ECkOut geteCkAllot() {
		return eCkAllot;
	}
	public void seteCkAllot(ECkOut eCkAllot) {
		this.eCkAllot = eCkAllot;
	}
	public List<ECkOut> getCkList() {
		return ckList;
	}
	public void setCkList(List<ECkOut> ckList) {
		this.ckList = ckList;
	}
	public List<ECkOut> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<ECkOut> addedList) {
		this.addedList = addedList;
	}
	public List<ECkOut> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<ECkOut> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<ECkOut> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<ECkOut> deletedList) {
		this.deletedList = deletedList;
	}
	public static ECkAllotVo initECkAllotVo(ECkAllotVo eCkAllotVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(eCkAllotVo.getCkList().spliterator(),false).forEach(ckList -> {
            switch (ckList.get_state()) {
                case "added":
                	ckList.setOutRq(eCkAllotVo.geteCkAllot().getOutRq());
                	ckList.setOutNote(eCkAllotVo.geteCkAllot().getOutNote());
                	ckList.setOutAct(eCkAllotVo.geteCkAllot().getOutAct());
                	ckList.setOutCustomer(eCkAllotVo.geteCkAllot().getOutCustomer());
                	ckList.setOutLib(eCkAllotVo.geteCkAllot().getOutLib());
                	ckList.setOutAllotLib(eCkAllotVo.geteCkAllot().getOutAllotLib());
                	ckList.setOutType(eCkAllotVo.geteCkAllot().getOutType());
                	eCkAllotVo.getAddedList().add(ckList);
                    break;
                    
                case "modified":
                	ckList.setOutRq(eCkAllotVo.geteCkAllot().getOutRq());            
                	ckList.setOutNote(eCkAllotVo.geteCkAllot().getOutNote());        
                	ckList.setOutAct(eCkAllotVo.geteCkAllot().getOutAct());          
                	ckList.setOutCustomer(eCkAllotVo.geteCkAllot().getOutCustomer());
                	ckList.setOutLib(eCkAllotVo.geteCkAllot().getOutLib());
                	ckList.setOutAllotLib(eCkAllotVo.geteCkAllot().getOutAllotLib());
                	ckList.setOutType(eCkAllotVo.geteCkAllot().getOutType());        
                	eCkAllotVo.getModifyedList().add(ckList);
                    break;
                    
                case "removed":
                	ckList.setOutRq(eCkAllotVo.geteCkAllot().getOutRq());            
                	ckList.setOutNote(eCkAllotVo.geteCkAllot().getOutNote());        
                	ckList.setOutAct(eCkAllotVo.geteCkAllot().getOutAct());          
                	ckList.setOutCustomer(eCkAllotVo.geteCkAllot().getOutCustomer());
                	ckList.setOutLib(eCkAllotVo.geteCkAllot().getOutLib());
                	ckList.setOutAllotLib(eCkAllotVo.geteCkAllot().getOutAllotLib()); 
                	ckList.setOutType(eCkAllotVo.geteCkAllot().getOutType());        
                	eCkAllotVo.getDeletedList().add(ckList);
                    break;
            }
        });
        return eCkAllotVo;
    }
}
