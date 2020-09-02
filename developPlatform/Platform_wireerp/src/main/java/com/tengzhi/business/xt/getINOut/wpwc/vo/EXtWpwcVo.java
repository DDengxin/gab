package com.tengzhi.business.xt.getINOut.wpwc.vo;

import com.tengzhi.business.xt.getINOut.wpwc.model.EXtWpwc;
import com.tengzhi.business.xt.getINOut.wpwc.model.EXtWpwcMx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EXtWpwcVo implements Serializable {

    EXtWpwc eXtWpwc;
    List<EXtWpwcMx> eXtWpwcMx,
        addedList = new ArrayList<>(),
        modifyedList = new ArrayList<>(),
        deletedList = new ArrayList<>();

    public EXtWpwc geteXtWpwc() { return eXtWpwc; }

    public void seteXtWpwc(EXtWpwc eXtWpwc) { this.eXtWpwc = eXtWpwc; }

    public List<EXtWpwcMx> geteXtWpwcMx() { return eXtWpwcMx; }

    public void seteXtWpwcMx(List<EXtWpwcMx> eXtWpwcMx) { this.eXtWpwcMx = eXtWpwcMx; }

    public List<EXtWpwcMx> getAddedList() { return addedList; }

    public void setAddedList(List<EXtWpwcMx> addedList) { this.addedList = addedList; }

    public List<EXtWpwcMx> getModifyedList() { return modifyedList; }

    public void setModifyedList(List<EXtWpwcMx> modifyedList) { this.modifyedList = modifyedList; }

    public List<EXtWpwcMx> getDeletedList() { return deletedList; }

    public void setDeletedList(List<EXtWpwcMx> deletedList) { this.deletedList = deletedList; }


    public static EXtWpwcVo initEXtWpwcVo(EXtWpwcVo eXtWpwcVo){

        StreamSupport.stream(eXtWpwcVo.geteXtWpwcMx().spliterator(),false).forEach(eXtWpwcMx->{
            switch (eXtWpwcMx.get_state()){
                case "added" :
                    eXtWpwcMx.setNote(eXtWpwcVo.geteXtWpwc().getNote());
                    eXtWpwcVo.getAddedList().add(eXtWpwcMx);
                    break;
                case "modified" :
                    eXtWpwcMx.setNote(eXtWpwcVo.geteXtWpwc().getNote());
                    eXtWpwcVo.getModifyedList().add(eXtWpwcMx);
                    break;
                case "removed" :
                    eXtWpwcMx.setNote(eXtWpwcVo.geteXtWpwc().getNote());
                    eXtWpwcVo.getDeletedList().add(eXtWpwcMx);
                    break;
                default:
                    break;
            }
        });
        return eXtWpwcVo;
    }
}
