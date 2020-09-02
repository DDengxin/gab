package com.tengzhi.business.platform.quotation.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.quotation.model.QuotationCostItems;

import java.util.List;

public interface QuotationCostService extends BaseService {
    BasePage<QuotationCostItems> getQuotationCost(BaseDto baseDto) throws Exception;


    List<QuotationCostItems> findById(String id);


    Result save(QuotationCostItems quotationCostItems);


    void update(QuotationCostItems quotationCostItems);


    void deleteById(String Id);
}
