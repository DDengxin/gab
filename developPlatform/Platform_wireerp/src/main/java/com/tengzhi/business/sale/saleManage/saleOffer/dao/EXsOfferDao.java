package com.tengzhi.business.sale.saleManage.saleOffer.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOffer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EXsOfferDao extends BasedaoRepository<EXsOffer,String> {

    @Query(value="select sale_flag from e_xs_offer where note =:note",nativeQuery=true)
    String getFlag(@Param(value="note")String note);

    @Modifying
    @Query(value="update EXsOffer set saleFlag=:saleFlag where note=:note")
    void updateFlag(@Param(value="note")String note,@Param(value="saleFlag")String saleFlag);

    @Modifying
    @Query(value="delete from EXsOffer where note=:note")
    void daleteByNote(@Param(value="note")String note);
}
