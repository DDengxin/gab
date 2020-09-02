package com.tengzhi.business.js.cpgy.scgy.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgy;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgymx;
import com.tengzhi.business.js.cpgy.scgy.vo.MScScgyVo;
import com.tengzhi.business.resouces.vo.SelectVo;



public interface MScScgyService extends BaseService {
	BasePage<MScScgy> getSrchTopList(BaseDto baseDto) throws IOException;
	
//	BasePage<MScScgymx> getSrchBottomList(BaseDto baseDto) throws IOException;

	List<Map<String, Object>> getSrchBottomJoin(BaseDto baseDto) throws IOException;
	
	Result save(MScScgyVo MScScgyVo) throws Exception;
	
	Result update(MScScgyVo MScScgyVo)throws Exception;
	
	MScScgymx findById(String gyId, Integer gxOrd,String gxId);

	List<MScScgymx> findByMscScgxId(String gxId);
	
	MScScgy findById(String gyId);
	
	void deleteById(String gyId);
	
	List<SelectVo> getGyAllSelectList();
	
}
