package com.tengzhi.business.xt.routine.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.routine.model.EXtSjxg;
import com.tengzhi.business.xt.routine.model.EXtSjxgDetailed;
import com.tengzhi.business.xt.routine.vo.EXtSjxgVo;

public interface  RoutineService {
	
	BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws IOException;
	
	BasePage<EXtSjxgDetailed> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	Result save(EXtSjxgVo vo) throws Exception;
	
	EXtSjxg getByNote(String sqNote);

	Result update(EXtSjxgVo vo) throws Exception;
	
	int delete(String sqNote);

	List<Map> getlList(String mod, String type, String parent);
}
