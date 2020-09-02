package com.tengzhi.business.sale.processProduct.processContract.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailedWl;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailedWlPK;

public class ProcessContractWlVo implements Serializable {

	EXsContract eXsContract;
	List<EXsContractDetailedWl> eXsContractDetailedWl,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
	private String taskid;
	private String opinion;
	private String businessId;
	private String instanceId;
	public EXsContract geteXsContract() {
		return eXsContract;
	}
	public void seteXsContract(EXsContract eXsContract) {
		this.eXsContract = eXsContract;
	}
	public List<EXsContractDetailedWl> geteXsContractDetailedWl() {
		return eXsContractDetailedWl;
	}
	public void seteXsContractDetailed(List<EXsContractDetailedWl> eXsContractDetailed) {
		this.eXsContractDetailedWl = eXsContractDetailed;
	}
	public List<EXsContractDetailedWl> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<EXsContractDetailedWl> addedList) {
		this.addedList = addedList;
	}
	public List<EXsContractDetailedWl> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<EXsContractDetailedWl> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<EXsContractDetailedWl> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<EXsContractDetailedWl> deletedList) {
		this.deletedList = deletedList;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public static ProcessContractWlVo initProcessContractWlVo(ProcessContractWlVo eXsContractWlVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(eXsContractWlVo.geteXsContractDetailedWl().spliterator(),false).forEach(eXsContractDetailedWl -> {
            switch (eXsContractDetailedWl.get_state()) {
                case "added":
//                	eXsContractDetailed.setHtNo(eXsContractVo.geteXsContract().getHtNo());
               	    eXsContractDetailedWl.setId(new EXsContractDetailedWlPK(eXsContractWlVo.geteXsContract().getHtNo(),eXsContractDetailedWl.getWlCode()));
                	eXsContractWlVo.getAddedList().add(eXsContractDetailedWl);
                    break;
                case "modified":
               	    eXsContractDetailedWl.setId(new EXsContractDetailedWlPK(eXsContractWlVo.geteXsContract().getHtNo(),eXsContractDetailedWl.getWlCode()));
                	eXsContractWlVo.getModifyedList().add(eXsContractDetailedWl);
                    break;
                case "removed":
                	eXsContractWlVo.getDeletedList().add(eXsContractDetailedWl);
                    break;
            }
        });
        return eXsContractWlVo;
    }
}
