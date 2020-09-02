package com.tengzhi.business.cg.yw.purchaseContract.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;

public class ECgContractVo implements Serializable {
	ECgContract eCgContract;
    List<ECgContractDetailed> eCgContractDetailed,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
	private String taskid;
	private String opinion;
	private String businessId;
	private String instanceId;

  
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

	public ECgContract geteCgContract() {
		return eCgContract;
	}

	public void seteCgContract(ECgContract eCgContract) {
		this.eCgContract = eCgContract;
	}

    public List<ECgContractDetailed> geteCgContractDetailed() {
		return eCgContractDetailed;
	}

	public void seteCgContractDetailed(List<ECgContractDetailed> eCgContractDetailed) {
		this.eCgContractDetailed = eCgContractDetailed;
	}

	public List<ECgContractDetailed> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<ECgContractDetailed> addedList) {
        this.addedList = addedList;
    }

    public List<ECgContractDetailed> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<ECgContractDetailed> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<ECgContractDetailed> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<ECgContractDetailed> deletedList) {
        this.deletedList = deletedList;
    }

    public static ECgContractVo initECgContractVo(ECgContractVo eCgContractVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(eCgContractVo.geteCgContractDetailed().spliterator(),false).forEach(eCgContractDetailed -> {
            switch (eCgContractDetailed.get_state()) {
                case "added":
                	eCgContractDetailed.setHtNo(eCgContractVo.geteCgContract().getHtNo());
                	eCgContractVo.getAddedList().add(eCgContractDetailed);
                    break;
                case "modified":
                	eCgContractVo.getModifyedList().add(eCgContractDetailed);
                    break;
                case "removed":
                	eCgContractVo.getDeletedList().add(eCgContractDetailed);
                    break;
            }
        });
        return eCgContractVo;
    }

}
