package com.tengzhi.business.xt.logistics.purchase.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.logistics.purchase.model.Purchase;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseDao extends BasedaoRepository<Purchase, String> {
    @Modifying
    @Query(value =" delete from Purchase where note = :Note ")
    void deleteBysqNote(@Param(value = "Note") String Note);

    @Query(value =" select distinct flag from Purchase where note = :note ")
    String getFlag(@Param(value = "note") String note);

    @Modifying
    @Query(value =" update Purchase set flag=:flag  where note = :note ")
    int setFlag(@Param(value = "note") String note,@Param(value = "flag") String flag);


    @Query(value ="select * from e_xt_purchase where note = :note limit 1 ",nativeQuery = true)
    Purchase findBySqnote(@Param(value = "note") String note);


}
