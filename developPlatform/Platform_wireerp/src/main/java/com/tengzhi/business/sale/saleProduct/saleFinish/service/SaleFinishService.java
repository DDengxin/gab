package com.tengzhi.business.sale.saleProduct.saleFinish.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;

public interface SaleFinishService extends BaseService  {

	/**
	 * 
	 * 查询订单明细
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	Map<String, Object> getContractDetailed(BaseDto baseDto) throws IOException;
	
	/**
	 * 获取状态并比较
	 * @param htMo
	 * @param flag
	 * @return
	 */
	Result getFlag(String htMo,String flag,String type) throws Exception;
	
	Result hx(String htNo,String htMo,String type);
	
	Result qxhx(String htNo,String htMo,String type);
	
}
