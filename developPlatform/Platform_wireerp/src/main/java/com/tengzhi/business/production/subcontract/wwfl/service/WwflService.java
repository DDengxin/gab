package com.tengzhi.business.production.subcontract.wwfl.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl;

public interface WwflService extends BaseService<MScScrwWl, MScScrwWl.MScScrwWl_PK> {
	/**
	 * 获取上grid数据
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<Map<String,Object>> getTopList(BaseDto baseDto) throws IOException;

	/**
	 * 获取左下grid数据
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<Map<String,Object>> getBottomLeftList(BaseDto baseDto) throws IOException;


	/**
	 * 保存数据
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Result save(ECkDeliveryNoticeVo vo) throws Exception;

	BasePage<Map<String,Object>> getDemandNotifyListById(String fhNote);

	Result update(ECkDeliveryNoticeVo vo)  throws Exception;
	
	Result delete(String fhNote)  throws Exception;
	
	Result confirm(String fhNote);
	
	Result cancel(String fhNote);
	
	ModelAndView table(ModelAndView mv, String fhNote,String fhType);
}
