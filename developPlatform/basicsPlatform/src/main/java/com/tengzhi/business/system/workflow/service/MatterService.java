package com.tengzhi.business.system.workflow.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.system.workflow.model.Matter;

import java.io.IOException;
import java.util.Map;

public interface MatterService {

	BasePage<Map<String,Object>> getMatters(BaseDto baseDto);

	Matter findByMatterId(String Id);

	Matter save(Matter matter);

	void update(Matter matter);

	void deleteById(String Id);

}
