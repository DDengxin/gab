package com.tengzhi.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.model.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;

public interface Logservice extends BaseService<Log, String> {

	BasePage<Log> findAll(BaseDto baseDto) throws IOException;

	Log findByRightId(String rightId);

	void save(Log log);

	void update(Log log);

	void deleteByRightId(String rightId);

	BasePage<Map<String, Object>> getArchiveLogList(BaseDto baseDto);

}
