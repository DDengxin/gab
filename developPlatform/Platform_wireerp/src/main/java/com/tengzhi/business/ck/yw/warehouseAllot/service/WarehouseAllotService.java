package com.tengzhi.business.ck.yw.warehouseAllot.service;

import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.ck.yw.warehouseAllot.model.ECkAllot;
import com.tengzhi.business.ck.yw.warehouseAllot.vo.ECkAllotVo;

public interface WarehouseAllotService extends BaseService {

	BasePage<ECkOut> getSrchTopList(BaseDto baseDto) throws Exception;
	
	BasePage<Map<String,Object>> getOutDetailedList(BaseDto baseDto) throws Exception;
	
	Map<String, Object> getKcDetailedList(BaseDto baseDto) throws Exception;
	
	ECkOut getByNote(String dbNote);
	
	Result save (ECkAllotVo vo) throws Exception;
	
	Result update(ECkAllotVo vo) throws Exception;
	
	Result getFlag(String outNote,String flag) ;
	
	Result confirm(String outNote,String outType) throws Exception;
	
	Result cancel(String dbNote);
	
	Result deleteByNote(String outNote);
}
