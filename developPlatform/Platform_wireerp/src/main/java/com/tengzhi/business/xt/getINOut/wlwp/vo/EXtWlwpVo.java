package com.tengzhi.business.xt.getINOut.wlwp.vo;

import com.tengzhi.business.xt.getINOut.wlwp.model.EXtWlwp;
import com.tengzhi.business.xt.getINOut.wlwp.model.EXtWlwpMx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EXtWlwpVo implements Serializable {

    EXtWlwp eXtWlwp;
    List<EXtWlwpMx> eXtWlwpMx,
        addedList = new ArrayList<>(),
        modifyedList = new ArrayList<>(),
        removedList = new ArrayList<>();

    public EXtWlwp geteXtWlwp() { return eXtWlwp; }

    public void seteXtWlwp(EXtWlwp eXtWlwp) { this.eXtWlwp = eXtWlwp; }

    public List<EXtWlwpMx> geteXtWlwpMx() { return eXtWlwpMx; }

    public void seteXtWlwpMx(List<EXtWlwpMx> eXtWlwpMx) { this.eXtWlwpMx = eXtWlwpMx; }

    public List<EXtWlwpMx> getAddedList() { return addedList; }

    public void setAddedList(List<EXtWlwpMx> addedList) { this.addedList = addedList; }

    public List<EXtWlwpMx> getModifyedList() { return modifyedList; }

    public void setModifyedList(List<EXtWlwpMx> modifyedList) { this.modifyedList = modifyedList; }

    public List<EXtWlwpMx> getRemovedList() { return removedList; }

    public void setRemovedList(List<EXtWlwpMx> removedList) { this.removedList = removedList; }

    public static EXtWlwpVo initEXtWlwpVo(EXtWlwpVo eXtWlwpVo){

        StreamSupport.stream(eXtWlwpVo.geteXtWlwpMx().spliterator(),false).forEach(mx->{
            switch(mx.get_state()){
                case "added" :
                    mx.setNote(eXtWlwpVo.geteXtWlwp().getNote());
                    eXtWlwpVo.getAddedList().add(mx);
                    break;
                case "modified" :
                    eXtWlwpVo.getModifyedList().add(mx);
                    break;
                case "removed" :
                    eXtWlwpVo.getRemovedList().add(mx);
            }
        });
        return eXtWlwpVo;
    }
}
