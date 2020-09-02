package com.tengzhi.business.xt.dailyRoutine.wcsq.vo;

import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsq;
import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsqZc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EXtWcsqVo implements Serializable {

    EXtWcsq eXtWcsq;
    List<EXtWcsqZc> eXtWcsqZc,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();

    public EXtWcsq geteXtWcsq() {
        return eXtWcsq;
    }

    public void seteXtWcsq(EXtWcsq eXtWcsq) {
        this.eXtWcsq = eXtWcsq;
    }

    public List<EXtWcsqZc> geteXtWcsqZc() {
        return eXtWcsqZc;
    }

    public void seteXtWcsqZc(List<EXtWcsqZc> eXtWcsqZc) {
        this.eXtWcsqZc = eXtWcsqZc;
    }

    public List<EXtWcsqZc> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<EXtWcsqZc> addedList) {
        this.addedList = addedList;
    }

    public List<EXtWcsqZc> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<EXtWcsqZc> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<EXtWcsqZc> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<EXtWcsqZc> deletedList) {
        this.deletedList = deletedList;
    }


    public static EXtWcsqVo initEXtWcsqVo(EXtWcsqVo eXtWcsqVo){
        StreamSupport.stream(eXtWcsqVo.geteXtWcsqZc().spliterator(),false).forEach(eXtWcsqZc ->{
            switch (eXtWcsqZc.get_state()){
                case "added" :
                    eXtWcsqZc.setNote(eXtWcsqVo.geteXtWcsq().getNote());
                    eXtWcsqVo.getAddedList().add(eXtWcsqZc);
                    break;
                case "modified" :
                    eXtWcsqVo.getModifyedList().add(eXtWcsqZc);
                    break;
                case "removed" :
                    eXtWcsqVo.getDeletedList().add(eXtWcsqZc);
            }
        } );
        return eXtWcsqVo;
    }
}
