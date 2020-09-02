package com.tengzhi.business.cg.yw.purchaseReceiptNotice.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.model.ECkReceivingNotice;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.vo.ECkReceivingNoticeVo;

public interface PurchaseReceiptNoticeService extends BaseService {

	/**
	 * 获取收货通知列表
	 * @param baseDto
	 * @return
	 * @throws Exception
	 */
	BasePage<ECkReceivingNotice> getSrchTopList(BaseDto baseDto) throws Exception;
	
	/**
	 * 明细
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	
	Result save(ECkReceivingNoticeVo eCkReceivingNoticeVo) throws Exception;
	
	Result update(ECkReceivingNoticeVo eCkReceivingNoticeVo)throws Exception;
	
	ECkReceivingNotice findByInNote(String shNote);
	
	void deleteByNote(String shNote);

	Result getFlag(String shNote,String flag);
	
	Result setFlag(String shNote,String flag);
}
