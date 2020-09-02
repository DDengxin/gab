package com.tengzhi.business.sc.finishedPicking.notice.service;

import java.io.IOException;

import com.tengzhi.app.ck.model.DeliveryNotice;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.model.ECkReceivingNotice;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.vo.ECkReceivingNoticeVo;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;

public interface finishedPickingService extends BaseService  {

	/**
	 * 获取发货通知列表
	 * @param baseDto
	 * @return
	 * @throws Exception
	 */
	BasePage<DeliveryNotice> getSrchTopList(BaseDto baseDto) throws Exception;
	
	/**
	 * 明细
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<DeliveryNotice> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	
	Result save(ECkDeliveryNoticeVo eCkDeliveryNoticeVo) throws Exception;
	
	Result update(ECkDeliveryNoticeVo eCkDeliveryNoticeVo)throws Exception;
	
	DeliveryNotice findByInNote(String shNote);
	
	void deleteByNote(String shNote);

	Result getFlag(String shNote,String flag);
	
	Result setFlag(String shNote,String flag);
}
