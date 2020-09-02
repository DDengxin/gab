package com.tengzhi.business.platform.quotation.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.quotation.vo.QuotationVo;

import java.util.Map;

public interface QuotationService extends BaseService {
	
	Result saveOffer(QuotationVo quotationVo);
	
    BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws Exception;


    Map<String,Object> findById(String id);


//    Result save(Vo vo);
//
//
//    void update(Vo vo);


    void deleteById(String Id);


}
