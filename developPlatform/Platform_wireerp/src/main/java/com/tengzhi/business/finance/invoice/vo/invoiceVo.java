package com.tengzhi.business.finance.invoice.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.finance.allowance.model.allowance;
import com.tengzhi.business.finance.invoice.model.invoice;

public class invoiceVo implements Serializable{
	invoice invoice;
    List<allowance> eCgContractDetailed,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
	public invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(invoice invoice) {
		this.invoice = invoice;
	}
	public List<allowance> geteCgContractDetailed() {
		return eCgContractDetailed;
	}
	public void seteCgContractDetailed(List<allowance> eCgContractDetailed) {
		this.eCgContractDetailed = eCgContractDetailed;
	}
	public List<allowance> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<allowance> addedList) {
		this.addedList = addedList;
	}
	public List<allowance> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<allowance> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<allowance> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<allowance> deletedList) {
		this.deletedList = deletedList;
	}
    
	
    
    

}
