package com.tengzhi.business.cg.yw.purchaseRequisition.vo;

import java.io.Serializable;
import java.util.List;

import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;

public class EcgRequisitiontVo implements Serializable {
	EcgRequisition headdata;
    List<EcgRequisition> entitydata;
    
	public EcgRequisition getHeaddata() {
		return headdata;
	}
	public void setHeaddata(EcgRequisition headdata) {
		this.headdata = headdata;
	}
	public List<EcgRequisition> getEntitydata() {
		return entitydata;
	}
	public void setEntitydata(List<EcgRequisition> entitydata) {
		this.entitydata = entitydata;
	}
   

}
