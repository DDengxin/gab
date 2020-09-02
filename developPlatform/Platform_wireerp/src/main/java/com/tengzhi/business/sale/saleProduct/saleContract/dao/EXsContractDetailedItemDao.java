package com.tengzhi.business.sale.saleProduct.saleContract.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailedItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EXsContractDetailedItemDao extends BasedaoRepository<EXsContractDetailedItem, String> {

    @Modifying
    @Query(value =" delete from EXsContractDetailedItem where htMo = :htMo ")
    void deleteByHtMo(@Param(value = "htMo") String htMo);
}
