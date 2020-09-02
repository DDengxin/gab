package com.tengzhi.business.sale.saleManage.saleOffer.vo;

import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOffer;
import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOfferDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EXsOfferVo implements Serializable {

    EXsOffer eXsOffer;
    List<EXsOfferDetail> eXsOfferDetail,
            addedList = new ArrayList<>(),
            modifyedList = new ArrayList<>(),
            deletedList = new ArrayList<>();

    public EXsOffer geteXsOffer() { return eXsOffer; }

    public void seteXsOffer(EXsOffer eXsOffer) { this.eXsOffer = eXsOffer; }

    public List<EXsOfferDetail> geteXsOfferDetail() { return eXsOfferDetail; }

    public void seteXsOfferDetail(List<EXsOfferDetail> eXsOfferDetail) { this.eXsOfferDetail = eXsOfferDetail; }

    public List<EXsOfferDetail> getAddedList() { return addedList; }

    public void setAddedList(List<EXsOfferDetail> addedList) { this.addedList = addedList; }

    public List<EXsOfferDetail> getModifyedList() { return modifyedList; }

    public void setModifyedList(List<EXsOfferDetail> modifyedList) { this.modifyedList = modifyedList; }

    public List<EXsOfferDetail> getDeletedList() { return deletedList; }

    public void setDeletedList(List<EXsOfferDetail> deletedList) { this.deletedList = deletedList; }

    public static EXsOfferVo initEXsOfferVo(EXsOfferVo eXsOfferVo){
        StreamSupport.stream(eXsOfferVo.geteXsOfferDetail().spliterator(),false).forEach(list->{
            switch (list.get_state()){
                case "added" :
                    list.setItem(eXsOfferVo.geteXsOffer().getNote());
                    eXsOfferVo.getAddedList().add(list);
                case "modified" :
                    eXsOfferVo.getModifyedList().add(list);
                case "removed" :
                    eXsOfferVo.getDeletedList().add(list);
            }
        });

        return eXsOfferVo;
    }
}
