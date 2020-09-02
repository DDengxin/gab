package com.tengzhi.business.xt.getINOut.clwc.vo;

import com.tengzhi.business.xt.getINOut.clwc.model.EXtClwc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EXtClwcVo implements Serializable {

    EXtClwc eXtClwc;
    List<EXtClwc> eXtClwcList,
        addedList = new ArrayList<>(),
        modifyedList = new ArrayList<>(),
        deletedList = new ArrayList<>();

    public EXtClwc geteXtClwc() { return eXtClwc; }

    public void seteXtClwc(EXtClwc eXtClwc) { this.eXtClwc = eXtClwc; }

    public List<EXtClwc> geteXtClwcList() { return eXtClwcList; }

    public void seteXtClwcList(List<EXtClwc> eXtClwcList) { this.eXtClwcList = eXtClwcList; }

    public List<EXtClwc> getAddedList() { return addedList; }

    public void setAddedList(List<EXtClwc> addedList) { this.addedList = addedList; }

    public List<EXtClwc> getModifyedList() { return modifyedList; }

    public void setModifyedList(List<EXtClwc> modifyedList) { this.modifyedList = modifyedList; }

    public List<EXtClwc> getDeletedList() { return deletedList; }

    public void setDeletedList(List<EXtClwc> deletedList) { this.deletedList = deletedList; }

    public static EXtClwcVo initEXtClwcVo(EXtClwcVo eXtClwcVo){
        StreamSupport.stream(eXtClwcVo.geteXtClwcList().spliterator(),false).forEach(list->{
            switch(list.get_state()){
                case "added" :
                    list.setNote(eXtClwcVo.geteXtClwc().getNote());
                    eXtClwcVo.getAddedList().add(list);
                    break;
                case "modified" :
                    eXtClwcVo.getModifyedList().add(list);
                    break;
                case "removed" :
                    eXtClwcVo.getDeletedList().add(list);
            }
        });

        return eXtClwcVo;
    }
}
