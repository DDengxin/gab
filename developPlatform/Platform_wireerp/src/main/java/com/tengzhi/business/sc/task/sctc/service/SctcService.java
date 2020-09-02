package com.tengzhi.business.sc.task.sctc.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;

public interface SctcService extends BaseService<MScScrw, MScScrw_PK> {
	
	BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException;

}
