package com.tengzhi.business.sale.saleManage.saleOffer.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOfferHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EXsOfferHistoryDao extends BasedaoRepository<EXsOfferHistory,String> {

    @Modifying
    @Query(value="delete from EXsOfferHistory where item=:item")
    void daleteByNote(@Param(value="item")String item);


}
