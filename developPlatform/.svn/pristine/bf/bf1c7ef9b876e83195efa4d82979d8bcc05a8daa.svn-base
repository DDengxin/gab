package com.tengzhi.business.production.subcontract.wwrk.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.mesGz.gzck.vo.ECkInVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl;
import org.springframework.web.servlet.ModelAndView;

public interface WwrkService extends BaseService<MScScrwWl, MScScrwWl.MScScrwWl_PK> {
BasePage<ECkIn> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<ECkIn> getSrchBottomList(BaseDto baseDto) throws IOException;
	
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
	
	BasePage<Map<String, Object>> getWwhtSelectList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getCgWwhtSelectList(BaseDto baseDto) throws IOException;

	ModelAndView table(ModelAndView mv, String inNote);
}
