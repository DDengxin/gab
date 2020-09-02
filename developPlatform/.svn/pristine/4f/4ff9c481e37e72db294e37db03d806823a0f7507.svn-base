package com.tengzhi.business.system.workflow.dao;

import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.system.workflow.model.Backlog;
import com.tengzhi.business.system.workflow.model.Matter;
import com.tengzhi.business.system.workflow.vo.Describe;
import com.tengzhi.business.system.workflow.vo.Edges;
import com.tengzhi.business.system.workflow.vo.Nodes;

public interface DescribDao extends BasicsDao<Describe, String> {

	List<Describe> queryDescribe(String processId);

	List<Edges> queryEdges(String processId);

	List<Nodes> queryNodes(String processId);

	void update(String table, String note, String noteValue, String flag, String flagValue);

	Map<String, Object> findall(String table, String note, String noteValue);

	Boolean judge(String sql);

	String getStr(String sql);

	String getversion(String id);
	
	String getProcessId(String businessid);


	void delete(String id);

	void delallBacklogs(String BusinessId);

	BasePage<Map<String,Object>> getBacklogs(BaseDto baseDto);

	BasePage<Map<String,Object>> getMatter(BaseDto baseDto);
	
	List<Map> getProcessClassify(String process_module, String process_system_type);
	 BasePage<com.tengzhi.business.system.workflow.model.Describe> getProcess(BaseDto baseDto);

	List<Map> getClassify(String process_module, String process_system_type);
}
