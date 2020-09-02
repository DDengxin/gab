package com.tengzhi.business.production.productionSite.siteTask.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ExamineVo;
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistSlVo;
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistVo;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclistSl;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl;



public interface SiteTaskService extends BaseService {
	
	BasePage<Map<String, Object>> getTopLeftGridList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getTopRightGridList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getTopRightGcGridList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getBottomRightGcGridList(BaseDto baseDto)throws IOException;
	
	MScScrwGx getByRwGx(String scMo, String gxId);

	Result getByPack(BaseDto baseDto)throws IOException;

	Result getCount(BaseDto baseDto)throws IOException;

	Result gxxl(MScGclistVo vo) throws Exception;
	
	Result tladd(MScGclistSlVo vo) throws Exception;
	
	Result getByTl(BaseDto baseDto)throws Exception;
	
	Result getTlflag(BaseDto baseDto)throws Exception;

	Set<SelectVo> getTph(String rwMo, String gxId);

	Set<SelectVo> getTts(String rwMo, String gxId);

	Set<SelectVo> getGxFlag();

	Result getDataByGch (String gch) throws Exception;
	
}
