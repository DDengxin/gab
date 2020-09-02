package com.tengzhi.business.sale.saleManage.saleOffer.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOfferDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EXsOfferDetailDao extends BasedaoRepository<EXsOfferDetail,String> {

    @Modifying
    @Query(value="delete from EXsOfferDetail where item=:item")
    void daleteByNote(@Param(value="item")String item);


}
