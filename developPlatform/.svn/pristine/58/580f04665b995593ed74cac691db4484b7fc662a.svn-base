package com.tengzhi.business.ck.yw.ckrk.service;

import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;



public interface WarehouseInService extends BaseService {
	BasePage<ECkIn> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<ECkIn> getSrchBottomList(BaseDto baseDto) throws IOException;

	Result getFlag(String inNote,String flag);
	
	Result setFlag(String inNote,String flag);
	
	ModelAndView table(ModelAndView mv,String inNote);
}
