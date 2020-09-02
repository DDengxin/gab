package com.tengzhi.business.ck.yw.ckck.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;



public interface WarehouseOutService extends BaseService {
	BasePage<ECkOut> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

	Result getFlag(String outNote,String flag);
	
	Result setFlag(String outNote,String flag);
	
	ModelAndView table(ModelAndView mv,String outNote);
}
