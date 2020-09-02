package com.tengzhi.business.project.projectStage.projectLine.vo;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.project.projectStage.projectLine.model.EXmXmlc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public class EXmXmlcVo {
    EXmXmlc headData;
    List<EXmXmlc> entryData,
            addedList = new ArrayList<>(),
            modifyedList = new ArrayList<>(),
            deletedList = new ArrayList<>();

    public List<EXmXmlc> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<EXmXmlc> deletedList) {
        this.deletedList = deletedList;
    }

    public List<EXmXmlc> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<EXmXmlc> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<EXmXmlc> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<EXmXmlc> addedList) {
        this.addedList = addedList;
    }

    public List<EXmXmlc> getEntryData() {
        return entryData;
    }

    public void setEntryData(List<EXmXmlc> entryData) {
        this.entryData = entryData;
    }

    public EXmXmlc getHeadData() {
        return headData;
    }

    public void setHeadData(EXmXmlc headData) {
        this.headData = headData;
    }

    public static EXmXmlcVo initVo(EXmXmlcVo vo) {
        SessionUser su = SessionUser.SessionUser();

        //创建流低级方法(基于迭代器)
        StreamSupport.stream(vo.getEntryData().spliterator(), false).forEach(model -> {
            model.setLcNote(vo.getHeadData().getLcNote());
            model.setLcRq(vo.getHeadData().getLcRq());
            model.setLcXmId(vo.getHeadData().getLcXmId());
            model.setLcTitle(vo.getHeadData().getLcTitle());
            model.setCreatetime(new Date());
            model.setMan(su.getUserId());
            model.setDataCorp(su.getCorpId());
            if( model.get_state() == null || "added".equals(model.get_state()) ){
                vo.getAddedList().add(model);
            }else if("modified".equals(model.get_state())){
                vo.getModifyedList().add(model);
            }
        });
        return vo;
    }

}
