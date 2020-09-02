package com.tengzhi.business.sc.task.blcl.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;
import com.tengzhi.business.sc.task.blcl.model.Blcl;

public interface BlclService extends BaseService<Blcl, String> {
	
	BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException;

    Result getGcstatus(BaseDto baseDto);

    Result getById(BaseDto baseDto);

    void saveDataBl(MScGclist mScgc);

    Object update(Blcl blclmd);
}
