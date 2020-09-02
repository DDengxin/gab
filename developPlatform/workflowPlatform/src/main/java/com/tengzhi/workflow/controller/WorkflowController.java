package com.tengzhi.workflow.controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.property.Property;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;

@Controller
@RequestMapping("/workflow")
public class WorkflowController {
	protected static Logger logger = LoggerFactory.getLogger(WorkflowController.class);
	@Autowired
	private Property property;
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	protected HistoryService historyService;

	@RequestMapping(value = "/toIndex")
	public String toIndex() {
		return "workflow/list";
	}

	/**
	 * 进入发布流程界面
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/toReleaseProcess")
	public ModelAndView toReleaseProcess(String id, String key, ModelAndView mv) throws IOException {
		mv.setViewName("workflow/releaseProcess");
		mv.addObject("id", id);
		if (StringUtils.isNotEmpty(id)) {// 修改
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(id).processDefinitionKey(key).singleResult();
			String deploymentId = processDefinition.getDeploymentId();
			InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());
			// 将file文件内容转成字符串
			InputStreamReader isr = new InputStreamReader(bpmnIs, StandardCharsets.UTF_8);
			BufferedReader bf = new BufferedReader(isr);
			String content = "";
			StringBuilder sb = new StringBuilder();
			while (content != null) {
				content = bf.readLine();
				if (content == null) {
					break;
				}
				sb.append(content.trim());
			}
			bf.close();
			String fileStr = sb.toString();
			sb.toString();
			mv.addObject("xml", fileStr);
		}
		return mv;
	}

	/**
	 * 部署并保存流程
	 * 
	 * @return
	 * @throws DocumentException
	 */
	@PostMapping(value = "/deploymentProcess")
	@ResponseBody
	@Log("部署流程")
	public com.tengzhi.base.jpa.result.Result deploymentProcess(String xml, String name) throws DocumentException {
		Document document = DocumentHelper.parseText(xml);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = null;
//		try {
//			String address = "";
//			if (property.getSystem() == null || property.getSystem().getXmladdress() == null) {
//				logger.info("请配置流程保存地址！已将流程文件保存在c盘workflow文件夹中");
//				address = "C://workflow/" + name + ".bpmn20.xml";
//			} else {
//				address = property.getSystem().getXmladdress() + name + ".bpmn20.xml";
//			}
//			writer = new XMLWriter(new FileWriter(address), format);
//			writer.write(document);
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		Deployment deployment = processEngine.getRepositoryService()// 获取流程定义和部署对象相关的Service
				.createDeployment()// 创建部署对象
				.addString(name + ".bpmn", xml).deploy();// 完成部署
		logger.info("部署ID：" + deployment.getId());// 1
		logger.info("部署时间：" + deployment.getDeploymentTime());
		return com.tengzhi.base.jpa.result.Result.resultOk();
	}

	/**
	 * 查询已部署流程
	 * 
	 * @return
	 */
	@RequestMapping("/getWorkflowList")
	@Log("查询部署流程")
	public @ResponseBody Result getWorkflowList(BaseDto baseDto) {
		String like = "%%";
		if (baseDto.getParams() != null) {
			like = "%" + baseDto.getParams() + "%";
		}
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> list = processDefinitionQuery.orderByProcessDefinitionVersion().desc().processDefinitionNameLike(like).listPage(baseDto.getStart(),
				baseDto.getEnd());// 分页查询;
		long cout = processDefinitionQuery.orderByProcessDefinitionVersion().desc().count();// 分页查询;
		List<Map<String, Object>> pd = new ArrayList<Map<String, Object>>();
		for (ProcessDefinition processDefinition : list) {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("id", processDefinition.getId());
			values.put("name", processDefinition.getName());
			values.put("key", processDefinition.getKey());
			values.put("version", processDefinition.getVersion());
			values.put("description", processDefinition.getDescription());
			values.put("deploymentId", processDefinition.getDeploymentId());
			pd.add(values);
		}
		return Result.formPage(pd, cout);
	}

	/**
	 * 删除流程
	 * 
	 * @return
	 */
	@RequestMapping("/deleteProcess")
	@Log("删除流程")
	public @ResponseBody Result deleteProcess(String id) {
		try {
			repositoryService.deleteDeployment(id);// ID
			// 强制删除,可以使用repositoryService.deleteDeployment("ID",true);
		} catch (Exception e) {
			return Result.error("流程没有审批结束！请稍后重试。");
		}
		return Result.resultOk();
	}

	/**
	 * 下载流程
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/downloadProcess")
	@Log("下载流程")
	public void downloadProcess(String id, HttpServletResponse response) throws IOException {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult();
		String deploymentId = processDefinition.getDeploymentId();
		InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(processDefinition.getResourceName(), "UTF-8"));
		OutputStream bpmnOs = response.getOutputStream();
		IOUtils.copy(bpmnIs, bpmnOs);
		bpmnOs.close();
		bpmnIs.close();
	}

	@RequestMapping("/getApplyStatus")
	@ResponseBody
	public Map<String, Object> getApplyStatus(String instanceId) {
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).singleResult();
		// 获取bpmnModel对象
		BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
		// 因为我们这里只定义了一个Process 所以获取集合中的第一个即可
		Process process = bpmnModel.getProcesses().get(0);
		// 获取所有的FlowElement信息
		Collection<FlowElement> flowElements = process.getFlowElements();
		Map<String, String> map = new HashMap<>();
		for (FlowElement flowElement : flowElements) {
			// 判断是否是连线
			if (flowElement instanceof SequenceFlow) {
				SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
				String ref = sequenceFlow.getSourceRef();
				String targetRef = sequenceFlow.getTargetRef();
				map.put(ref + targetRef, sequenceFlow.getId());
			}
		}
		// 获取流程实例 历史节点(全部)
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceId).list();
		// 各个历史节点 两两组合 key
		Set<String> keyList = new HashSet<>();
		for (HistoricActivityInstance i : list) {
			for (HistoricActivityInstance j : list) {
				if (i != j) {
					keyList.add(i.getActivityId() + j.getActivityId());
				}
			}
		}
		// 高亮连线ID
		Set<String> highLine = new HashSet<>();
		keyList.forEach(s -> highLine.add(map.get(s)));
		// 获取流程实例 历史节点（已完成）
		List<HistoricActivityInstance> listFinished = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceId).finished().list();
		// 高亮节点ID
		Set<String> highPoint = new HashSet<>();
		listFinished.forEach(s -> highPoint.add(s.getActivityId()));
		// 获取流程实例 历史节点（待办节点）
		List<HistoricActivityInstance> listUnFinished = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceId).unfinished().list();
		// 需要移除的高亮连线
		Set<String> set = new HashSet<>();
		// 待办高亮节点
		Set<String> waitingToDo = new HashSet<>();
		listUnFinished.forEach(s -> {
			waitingToDo.add(s.getActivityId());
			for (FlowElement flowElement : flowElements) {
				// 判断是否是 用户节点
				if (flowElement instanceof UserTask) {
					UserTask userTask = (UserTask) flowElement;
					if (userTask.getId().equals(s.getActivityId())) {
						List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();
						// 因为 高亮连线查询的是所有节点 两两组合 把待办 之后 往外发出的连线 也包含进去了 所以要把高亮待办节点 之后 即出的连线去掉
						if (outgoingFlows != null && outgoingFlows.size() > 0) {
							outgoingFlows.forEach(a -> {
								if (a.getSourceRef().equals(s.getActivityId())) {
									set.add(a.getId());
								}
							});
						}
					}
				}
			}
		});
		highLine.removeAll(set);
		// 获取当前用户
		SessionUser securityUser=SessionUser.SessionUser();
		Set<String> iDo = new HashSet<>(); // 存放 高亮 我的办理节点
		// 当前用户已完成的任务
		List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery().taskAssignee(securityUser.getJobNumber()).finished()
				.processInstanceId(instanceId).list();
		taskInstanceList.forEach(a -> iDo.add(a.getTaskDefinitionKey()));
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("highPoint", highPoint);
		reMap.put("highLine", highLine);
		reMap.put("waitingToDo", waitingToDo);
		reMap.put("iDo", iDo);
		return reMap;
	}
}
