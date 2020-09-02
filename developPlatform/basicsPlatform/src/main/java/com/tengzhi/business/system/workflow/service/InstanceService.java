package com.tengzhi.business.system.workflow.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.workflow.model.Backlog;
import com.tengzhi.business.system.workflow.model.Link;
import com.tengzhi.business.system.workflow.model.Transact;
import com.tengzhi.business.system.workflow.vo.Examine;

public interface InstanceService {

	/**
	 * 启动第一步 环节为开始——》执行——>结束 环节在执行(默认跳过开始环节，办理表记录)
	 * 
	 * @param classifyId 流程分类id
	 * @param businessId 业务主键
	 * @param title      待办标题
	 * @param url        待办路径
	 * @param map        额外参数，自定义办理人设置以及线路走向
	 * @return
	 */
	Link Start(String classifyId, String businessId, String title, String url, Map<String, Object> map);

	Link Start(String classifyId, String businessId, String title, String url);

	/**
	 * 获取当前人员的待办
	 * 
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<Map<String,Object>> getBacklogs(BaseDto baseDto);

	/**
	 * 获取流程已办
	 * 
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<Transact> getTransact(BaseDto baseDto, String processId, String businessId);

	List<Transact> getTransact(String processId, String businessId);

	/**
	 * 办理
	 * 
	 * @param link
	 * @param opinion
	 * @param map
	 * @return
	 */
	Link handle(Link link, String opinion, Map<String, Object> map);

	Link handle(Link link, Map<String, Object> map);

	Link handle(Link link, String opinion);

	Link handle(Link link);

	/**
	 * 办理
	 * 
	 * @param backlogid
	 * @param opinion
	 * @param map
	 * @return
	 */
	Link handle(String backlogid, String opinion, Map<String, Object> map);

	Link handle(String backlogid, Map<String, Object> map);

	Link handle(String backlogid, String opinion, String signature);

	Link handle(String backlogid);

	/**
	 * 退回到最开始
	 * 
	 * @param backlogid
	 * @param signature 
	 * @return
	 */
	Link Back(String backlogid, String opinion, String signature);

	/**
	 * 流程描述
	 * 
	 * @param examine
	 * @return
	 */
	Result getProcessDescription(Examine examine);

	/**
	 * 获取环节
	 * 
	 * @param id
	 * @return
	 */
	Result getlink(String id);

	List<Map> getProcessClassify(String process_module, String process_system_type);
}
