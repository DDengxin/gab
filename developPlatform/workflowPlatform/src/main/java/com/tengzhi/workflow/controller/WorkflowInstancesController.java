package com.tengzhi.workflow.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.workflow.service.InstanceService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lqy 流程实例操作
 */
@Controller
@RequestMapping("/workflowInstances")
public class WorkflowInstancesController {
	private Logger logger = LoggerFactory.getLogger(WorkflowInstancesController.class);

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected InstanceService InstanceService;

	@Autowired
	protected HistoryService historyService;

	@RequestMapping(value = "/toIndex")
	public String toIndex() {
		return "workflowInstances/list";
	}

	@RequestMapping(value = "/toView")
	public ModelAndView toView(String id, ModelAndView mv) {
		mv.setViewName("workflowInstances/view");
		mv.addObject("id", id);
		return mv;
	}

	/**
	 * 查询所有流程实例
	 * 
	 * @param BusinessKey
	 * @param baseDto
	 * @return
	 */
	@PostMapping("/processInstances")
	@ResponseBody
	public Result getProcessInstances(String BusinessKey, BaseDto baseDto) {
		String where = "";
		if (BusinessKey != null && StringUtils.isNotEmpty(BusinessKey)) {
			where += " and RES.business_key_ like '%" + BusinessKey + "%' ";
		}
		if (baseDto.getParams() != null && StringUtils.isNotEmpty(baseDto.getParams())) {
			where += " and P.name_ like '%" + baseDto.getParams() + "%' ";
		}
		List<org.activiti.engine.runtime.ProcessInstance> list = runtimeService.createNativeProcessInstanceQuery().sql(
				"select distinct RES.* , P.KEY_ as ProcessDefinitionKey, P.ID_ as ProcessDefinitionId, P.NAME_ as ProcessDefinitionName, P.VERSION_ as ProcessDefinitionVersion, P.DEPLOYMENT_ID_ as DeploymentId, S.PROC_INST_ID_ AS PARENT_PROC_INST_ID_ \r\n"
						+ ", P.name_ AS \"name\" \r\n"
						+ "from ACT_RU_EXECUTION RES inner join ACT_RE_PROCDEF P on RES.PROC_DEF_ID_ = P.ID_ left outer join ACT_RU_EXECUTION S on RES.SUPER_EXEC_ = S.ID_ WHERE RES.PARENT_ID_ is null "
						+ where + " order by RES.ID_ asc ")
				.listPage(baseDto.getStart(), baseDto.getEnd());
		long cout = runtimeService.createProcessInstanceQuery().count();
		List<Map<String, Object>> pd = new ArrayList<Map<String, Object>>();
		for (org.activiti.engine.runtime.ProcessInstance instance : list) {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ProcessInstanceId", instance.getProcessInstanceId());
			values.put("ProcessDefinitionId", instance.getProcessDefinitionId());
			values.put("isEnded", instance.isEnded());
			values.put("isSuspended", instance.isSuspended());
			values.put("ActivityId", instance.getActivityId());
			values.put("BusinessKey", instance.getBusinessKey());
			values.put("name", instance.getName());
			pd.add(values);
		}
		return Result.formPage(pd, cout);
	}

	/**
	 * 刪除实例
	 * 
	 * @param processInstanceId
	 */
	@PostMapping("/deleteProcessInstance")
	@ResponseBody
	public Result deleteProcessInstance(String id) {
		runtimeService.deleteProcessInstance(id, "");
		return Result.resultOk();
	}

	/**
	 * @param id 修改流程
	 */
	@PostMapping("/editById")
	@ResponseBody
	public Result editById(String id) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
		if (processInstance.isSuspended()) {
			InstanceService.activationById(id);
		} else {
			InstanceService.suspendedById(id);
		}
		return Result.resultOk();
	}

    /**
	 * @param id 查询历史节点
	 */
	@PostMapping("/queryHistoricActivitiInstance")
	@ResponseBody
	public List<Map<String, Object>> queryHistoricActivitiInstance(String processInstanceId) {
		List<Map<String, Object>> pd = new ArrayList<Map<String, Object>>();
		if(processInstanceId==null) {
			return pd;
		}
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId).list();
		if (list != null && list.size() > 0) {
			for (HistoricActivityInstance hai : list) {
				Map<String, Object> values = new HashMap<String, Object>();
				values.put("ActivityId", hai.getActivityId());// 步骤ID
				values.put("ActivityName", hai.getActivityName());// 步骤名称
				values.put("Assignee", hai.getAssignee());// 执行人
				pd.add(values);
			}
		}
		return pd;
	}

}
