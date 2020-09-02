package com.tengzhi.business.production.subcontract.wwjs.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;
import com.tengzhi.business.cg.yw.purchaseSettle.vo.ECwYsyfVo;



public interface WwjsService extends BaseService {
	BasePage<ECkIn> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<ECkIn> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	
	ECwYsyf findById(String cwNote,String cwItem,String cwAct,String cwCode);
	
	void deleteById(String cwNote);

	Result getFlag(String inNote,String flag);
	
	Result setFlag(String inNote,String flag);

	Result Js(ECwYsyfVo eCwYsyfVo) throws IOException;
	
	Result qxJs(String cwNote) throws IOException;

	BasePage<ECwYsyf> getBottomList(BaseDto baseDto)throws IOException;

}
