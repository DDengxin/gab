package com.tengzhi.business.production.productionSite.productInWarehouse.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;

public interface ProductInWarehouseService extends BaseService {

	BasePage<Map<String,Object>> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<Map<String,Object>> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	Result save(ECkInVo eCkInVo) throws Exception;
	
	Result update(ECkInVo eCkInVo)throws Exception;
	
	ECkIn findByInNote(String inNote);
	
	void deleteById(String inNote);

	Result getFlag(String inNote,String flag);
	
	Result setFlag(String inNote,String flag);
	
	Result getInList(String inValue);
}
