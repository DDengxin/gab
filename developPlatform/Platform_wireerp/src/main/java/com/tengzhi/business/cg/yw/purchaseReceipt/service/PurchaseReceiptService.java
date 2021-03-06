package com.tengzhi.business.cg.yw.purchaseReceipt.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;


public interface PurchaseReceiptService extends BaseService {
	BasePage<ECkIn> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<Map<String,Object>> getSrchBottomList(BaseDto baseDto) throws IOException;
	BasePage<ECkIn> getSrchBottomListAll(BaseDto baseDto) throws IOException;
	Result save(ECkInVo eCkInVo) throws Exception;
	
	Result update(ECkInVo eCkInVo)throws Exception;
	
	ECkIn findById(String inNote,String inPack);
	
	ECkIn findByInNote(String inNote);
	
	void deleteById(String inNote);

	Result getFlag(String inNote,String flag);
	
	Result setFlag(String inNote,String flag);

	/**
	 * 更新原包装号的数量
	 */
	Result updateSplitData(BigDecimal sl, String pack,String contract);
}
