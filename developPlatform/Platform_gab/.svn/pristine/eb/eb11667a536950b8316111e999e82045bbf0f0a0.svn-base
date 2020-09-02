package com.tengzhi.business.platform.quotation.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.quotation.dao.QuotationCostItemsDao;
import com.tengzhi.business.platform.quotation.model.QuotationCostItems;
import com.tengzhi.business.platform.quotation.service.QuotationCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Transactional

public class QuotationCostServiceImpl implements QuotationCostService {

    @Autowired
    QuotationCostItemsDao quotationCostItemsDao;

    @Override
    public BasePage<QuotationCostItems> getQuotationCost(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        String quotationId = map.get("quotationId");
        String sql = "select * from g_quatation_cost_items where direct_cost_items='"+quotationId+"'";
        return quotationCostItemsDao.QueryPageLists(baseDto,sql);
    }

    @Override
    public List<QuotationCostItems> findById(String id) {

        return quotationCostItemsDao.findByCostItems(id);
    }

    @Override
    public Result save(QuotationCostItems quotationCostItems) {
        return null;
    }

    @Override
    public void update(QuotationCostItems quotationCostItems) {

    }

    @Override
    public void deleteById(String Id) {

    }
}
