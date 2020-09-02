package com.tengzhi.business.system.workflow.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.CommunalService;
import com.tengzhi.business.system.workflow.model.Describe;
import com.tengzhi.business.system.workflow.vo.Examine;
import com.tengzhi.business.system.workflow.vo.SaveVo;
import com.tengzhi.business.system.workflow.vo.Source;

public interface ProcessService extends CommunalService<Describe, String> {
	BasePage<Describe> getProcess(BaseDto baseDto);

	Source getProcessInstance(String processId);

	Result agree(Examine examine);

	Result disagree(Examine examine);

	Result startFinishFirstStep(Examine examine);

	Result handle(Examine examine);

	Result release(String id);

	Result saveProcess(SaveVo soure);

	Result delete(String id);

	Result repeal(Examine examine);

	List<Map> getProcessClassify(String process_module, String process_system_type);

	Result saveSignature(Examine examine);

}
