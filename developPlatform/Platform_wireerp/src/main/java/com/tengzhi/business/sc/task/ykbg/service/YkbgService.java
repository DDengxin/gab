package com.tengzhi.business.sc.task.ykbg.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistVo;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;

public interface YkbgService extends BaseService<MScScrw, MScScrw_PK> {
	
	BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException;

	Result getByScmo(BaseDto baseDto)throws IOException;
	
	Result getByScgx(BaseDto baseDto)throws IOException;
	
	Result gxxl(MScGclistVo vo) throws Exception;

    Result getInList(String inValue);
}
