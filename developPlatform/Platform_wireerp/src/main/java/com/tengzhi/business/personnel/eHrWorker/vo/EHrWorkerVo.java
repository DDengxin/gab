package com.tengzhi.business.personnel.eHrWorker.vo;

import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorkerFamily;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EHrWorkerVo {
    EHrWorker eHrWorker;
    List<EHrWorkerFamily> eHrWorkerFamilies,
            addedList = new ArrayList<>(),
            modifyedList = new ArrayList<>(),
            deletedList = new ArrayList<>();

    public EHrWorker geteHrWorker() {
        return eHrWorker;
    }

    public void seteHrWorker(EHrWorker eHrWorker) {
        this.eHrWorker = eHrWorker;
    }

    public List<EHrWorkerFamily> geteHrWorkerFamilies() {
        return eHrWorkerFamilies;
    }

    public void seteHrWorkerFamilies(List<EHrWorkerFamily> eHrWorkerFamilies) {
        this.eHrWorkerFamilies = eHrWorkerFamilies;
    }

    public List<EHrWorkerFamily> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<EHrWorkerFamily> addedList) {
        this.addedList = addedList;
    }

    public List<EHrWorkerFamily> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<EHrWorkerFamily> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<EHrWorkerFamily> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<EHrWorkerFamily> deletedList) {
        this.deletedList = deletedList;
    }

    public static EHrWorkerVo initEHrWorkerVo(EHrWorkerVo eHrWorkerVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(eHrWorkerVo.geteHrWorkerFamilies().spliterator(),false).forEach(eHrWorkerFamily -> {
            switch (eHrWorkerFamily.get_state()) {
                case "added":
                    eHrWorkerFamily.setWorkerId(eHrWorkerVo.geteHrWorker().getWorkerId());
                    eHrWorkerVo.getAddedList().add(eHrWorkerFamily);
                    break;
                case "modified":
                    eHrWorkerFamily.setWorkerId(eHrWorkerVo.geteHrWorker().getWorkerId());
                    eHrWorkerVo.getModifyedList().add(eHrWorkerFamily);
                    break;
                case "removed":
                    eHrWorkerVo.getDeletedList().add(eHrWorkerFamily);
                    break;
            }
        });
        return eHrWorkerVo;
    }
}
