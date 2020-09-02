package com.tengzhi.business.finance.receivables.receivablesCheck.service;

import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;

public interface ReceivablesCheckService extends BaseService {

	/**
	 * 获取应收应付列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<ECwYsyf> getGridList(BaseDto baseDto) throws IOException;
	
	
	/**
	 * 获取应收应付明细
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<ECwYsyf> getDetailGridList(BaseDto baseDto) throws IOException;
	
	
	ECwYsyf getDataByNote(String note);
	
	/**
	 * 审核
	 * @param cwNote
	 * @return
	 */
	Result confirm(String cwNote);
	
	/**
	 * 取消
	 * @param cwNote
	 * @return
	 */
	Result cancel(String cwNote);
	
	/**
	 * 获取状态
	 * @param 
	 * @return
	 */
	Result getFlag(String cwNote,String flag);
	
	
	ModelAndView table(ModelAndView mv, String cwNote)   ;
}
