package com.tengzhi.business.cg.yw.purchaseReceiptNotice.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.cg.yw.purchaseReceiptNotice.model.ECkReceivingNotice;

public class ECkReceivingNoticeVo implements Serializable {

	ECkReceivingNotice eCkRe;
	List<ECkReceivingNotice> eCkReMx,
	addedList = new ArrayList<>(),
	modifyedList = new ArrayList<>(),
	deletedList = new ArrayList<>();
	
	
	public ECkReceivingNotice geteCkRe() {
		return eCkRe;
	}
	public void seteCkRe(ECkReceivingNotice eCkRe) {
		this.eCkRe = eCkRe;
	}
	public List<ECkReceivingNotice> geteCkReMx() {
		return eCkReMx;
	}
	public void seteCkReMx(List<ECkReceivingNotice> eCkReMx) {
		this.eCkReMx = eCkReMx;
	}
	public List<ECkReceivingNotice> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<ECkReceivingNotice> addedList) {
		this.addedList = addedList;
	}
	public List<ECkReceivingNotice> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<ECkReceivingNotice> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<ECkReceivingNotice> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<ECkReceivingNotice> deletedList) {
		this.deletedList = deletedList;
	}
	
	
	public static ECkReceivingNoticeVo initECkReceivingNoticeVo(ECkReceivingNoticeVo eCkReceivingNoticeVo){
		 //创建流低级方法(基于迭代器)
       StreamSupport.stream(eCkReceivingNoticeVo.geteCkReMx().spliterator(),false).forEach(model -> {
           switch (model.get_state()) {
               case "added":
            	model.setShNote(eCkReceivingNoticeVo.geteCkRe().getShNote());
            	model.setShType(eCkReceivingNoticeVo.geteCkRe().getShType());
            	model.setShLib(eCkReceivingNoticeVo.geteCkRe().getShLib());
               	model.setShRq(eCkReceivingNoticeVo.geteCkRe().getShRq());
               	model.setShCustomer(eCkReceivingNoticeVo.geteCkRe().getShCustomer());
               	model.setShBz(eCkReceivingNoticeVo.geteCkRe().getShBz());
               	model.setShTax(eCkReceivingNoticeVo.geteCkRe().getShTax());
               	eCkReceivingNoticeVo.getAddedList().add(model);
                   break;
               case "modified":
            	   model.setShNote(eCkReceivingNoticeVo.geteCkRe().getShNote());
            	   model.setShType(eCkReceivingNoticeVo.geteCkRe().getShType());
            	   model.setShLib(eCkReceivingNoticeVo.geteCkRe().getShLib());
                   model.setShRq(eCkReceivingNoticeVo.geteCkRe().getShRq());
                   model.setShCustomer(eCkReceivingNoticeVo.geteCkRe().getShCustomer());
                   model.setShBz(eCkReceivingNoticeVo.geteCkRe().getShBz());
                   model.setShTax(eCkReceivingNoticeVo.geteCkRe().getShTax());
                   eCkReceivingNoticeVo.getModifyedList().add(model);
                   break;
               case "removed":
            	    model.setShNote(eCkReceivingNoticeVo.geteCkRe().getShNote());
            	    model.setShType(eCkReceivingNoticeVo.geteCkRe().getShType());
                  	model.setShLib(eCkReceivingNoticeVo.geteCkRe().getShLib());
                  	model.setShRq(eCkReceivingNoticeVo.geteCkRe().getShRq());
                  	model.setShCustomer(eCkReceivingNoticeVo.geteCkRe().getShCustomer());
                  	model.setShBz(eCkReceivingNoticeVo.geteCkRe().getShBz());
                    model.setShTax(eCkReceivingNoticeVo.geteCkRe().getShTax());
                    eCkReceivingNoticeVo.getDeletedList().add(model);
                   break;
           }
       });
       return eCkReceivingNoticeVo;
   }
}
