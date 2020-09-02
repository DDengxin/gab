package com.tengzhi.business.sc.task.sctack.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwWlVo;

public interface ScTackService extends BaseService<MScScrw, MScScrw_PK> {

	List<MScScrwGx> findByGxId(String gxId);
	
	BasePage<MScScrw> getSrchTopLeftList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException;
	
	Result xsddSplit(MScScrw model)throws Exception;
	
	Result xsddHp(MScScrw model)throws Exception;

	Result getSrchTopRightList(BaseDto baseDto) throws IOException;
	
	Result getSrchScpdList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getSrchBottomLeftList(BaseDto baseDto) throws IOException;
	
	Result saveRwGx(MScScrwGxVo vo)throws IOException;
	
	Result updateRwGx(MScScrwGxVo vo)throws IOException;

	void deleteRwgx(String scMo);
	
	Result saveRwWl(MScScrwWlVo vo)throws IOException;

	void deleteRwWl(String scMo, String gyId);

	BasePage<Map<String,Object>> getProductionTaskSelectList(BaseDto baseDto) throws IOException;

	Result deleteByScMo(String scMo);

	Map<String, Object> veriFication(String scMo, String gx);

    Result saveGx(MScScrwGxVo initMScScrwGxVo);

	Result saveWl(MScScrwWlVo wlVo) throws Exception;

	Result gykview(String htNo);

	BasePage<Map<String, Object>> getSrchBottomprintList(BaseDto baseDto);

	BasePage<Map<String, Object>> getSrchBottomRightprintList(BaseDto baseDto);
}
