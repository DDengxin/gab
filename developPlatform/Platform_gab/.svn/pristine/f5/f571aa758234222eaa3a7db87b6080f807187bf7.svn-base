package com.tengzhi.business.platform.specialist.product.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.specialist.product.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ProductService  extends BaseService{
	
	BasePage<Map<String,Object>> sreach(BaseDto baseDto) throws IOException;
	
	Result saveNeed(Product product) throws Exception;

	Product getByNote(String productNote);

	Result updateNeed(Product product) throws Exception;

	int deleteByNote(String productNote);

	Map<String, Object> getProductPaging(String params, String pageIndex, String pageSize) throws Exception;

	Map<String, Object> getProduct(String productNote);

	List<Map<String, Object>> getSupplyProduct(String supply_id);

	String supplyId(String sql);
	
}
