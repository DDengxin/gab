package com.tengzhi.business.sc.pd.gcrw.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;

import java.io.IOException;

public interface GcrwkService extends BaseService<MScScrw, MScScrw_PK> {

	Result getSrchScpdList(BaseDto baseDto) throws IOException;

	
	Result updateRwGx(MScScrwGxVo vo)throws IOException;

}
