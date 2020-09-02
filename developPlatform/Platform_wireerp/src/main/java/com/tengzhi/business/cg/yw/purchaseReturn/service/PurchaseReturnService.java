package com.tengzhi.business.cg.yw.purchaseReturn.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;



public interface PurchaseReturnService extends BaseService {
	BasePage<ECkIn> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	Result save(ECkInVo eCkInVo) throws Exception;
	
	Result update(ECkInVo eCkInVo)throws Exception;
	
	ECkIn findById(String inNote,String inPack);
	
	ECkIn findByInNote(String inNote);
	
	void deleteById(String inNote);
	
	BasePage<Map<String, Object>> getCgthSelectList(BaseDto baseDto) throws IOException;
	
	Result getFlag(String inNote,String flag);
	
	Result setFlag(String inNote,String flag);
}
