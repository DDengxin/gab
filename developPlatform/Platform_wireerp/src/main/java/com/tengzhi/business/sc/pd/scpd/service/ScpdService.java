package com.tengzhi.business.sc.pd.scpd.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwWlVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ScpdService extends BaseService<MScScrw, MScScrw_PK> {

	BasePage<Map<String, Object>> getSrchTopLeftList(BaseDto baseDto) throws IOException;

}
