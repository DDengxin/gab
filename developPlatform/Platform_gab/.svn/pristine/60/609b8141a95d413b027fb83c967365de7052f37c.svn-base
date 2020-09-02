package com.tengzhi.business.platform.quotation.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.quotation.model.Quotation;
import org.springframework.data.jpa.repository.Query;


public interface QuotationDao extends BasedaoRepository<Quotation, String> {
    @Query(value=" SELECT * from g_quotation  where quotation_id=? ",nativeQuery = true)
    Quotation findQuotation(String id);
}
