package com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.app.ck.model.DeliveryNotice;

public class ECkDeliveryNoticeVo implements Serializable {

	DeliveryNotice deliveryNotice;
	List<DeliveryNotice> deliveryNoticeMx,
	addedList = new ArrayList<>(),
	modifyedList = new ArrayList<>(),
	deletedList = new ArrayList<>();
	public DeliveryNotice getDeliveryNotice() {
		return deliveryNotice;
	}
	public void setDeliveryNotice(DeliveryNotice deliveryNotice) {
		this.deliveryNotice = deliveryNotice;
	}
	public List<DeliveryNotice> getDeliveryNoticeMx() {
		return deliveryNoticeMx;
	}
	public void setDeliveryNoticeMx(List<DeliveryNotice> deliveryNoticeMx) {
		this.deliveryNoticeMx = deliveryNoticeMx;
	}
	public List<DeliveryNotice> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<DeliveryNotice> addedList) {
		this.addedList = addedList;
	}
	public List<DeliveryNotice> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<DeliveryNotice> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<DeliveryNotice> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<DeliveryNotice> deletedList) {
		this.deletedList = deletedList;
	}
	
	public static ECkDeliveryNoticeVo initECkDeliveryNoticeVo(ECkDeliveryNoticeVo eCkDeliveryNoticeVo){
		 //创建流低级方法(基于迭代器)
      StreamSupport.stream(eCkDeliveryNoticeVo.getDeliveryNoticeMx().spliterator(),false).forEach(model -> {
          switch (model.get_state()) {
              case "added":
            	  model.setFhNote(eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote());
            	  model.setFhType(eCkDeliveryNoticeVo.getDeliveryNotice().getFhType());
            	  model.setFhLib(eCkDeliveryNoticeVo.getDeliveryNotice().getFhLib());
            	  model.setFhRq(eCkDeliveryNoticeVo.getDeliveryNotice().getFhRq());
            	  model.setFhCustomer(eCkDeliveryNoticeVo.getDeliveryNotice().getFhCustomer());
            	  eCkDeliveryNoticeVo.getAddedList().add(model);
                  break;
              case "modified":
           	   model.setFhNote(eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote());
           	   model.setFhType(eCkDeliveryNoticeVo.getDeliveryNotice().getFhType());
           	   model.setFhLib(eCkDeliveryNoticeVo.getDeliveryNotice().getFhLib());
                  model.setFhRq(eCkDeliveryNoticeVo.getDeliveryNotice().getFhRq());
                  model.setFhCustomer(eCkDeliveryNoticeVo.getDeliveryNotice().getFhCustomer());
              		eCkDeliveryNoticeVo.getModifyedList().add(model);
                  break;
              case "removed":
           	    model.setFhNote(eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote());
           	    model.setFhType(eCkDeliveryNoticeVo.getDeliveryNotice().getFhType());
                 	model.setFhLib(eCkDeliveryNoticeVo.getDeliveryNotice().getFhLib());
                 	model.setFhRq(eCkDeliveryNoticeVo.getDeliveryNotice().getFhRq());
                 	model.setFhCustomer(eCkDeliveryNoticeVo.getDeliveryNotice().getFhCustomer());
                   eCkDeliveryNoticeVo.getDeletedList().add(model);
                  break;
          }
      });
      return eCkDeliveryNoticeVo;
  }
}
