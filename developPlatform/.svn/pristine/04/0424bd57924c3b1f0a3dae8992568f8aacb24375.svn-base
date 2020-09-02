package com.tengzhi.workflow.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengzhi.workflow.service.factory.activitiFactory;
import com.tengzhi.workflow.utils.ActivitiUtils;

/**
 * 流程图片获取 红色边框和流程图对应不上未解决
 *
 */
@Controller
@RequestMapping("/workflow")
public class WorkflowImageController extends activitiFactory {
	protected static Logger logger = LoggerFactory.getLogger(WorkflowImageController.class);
	/** 流程图生成器 */
	@Autowired
	private ProcessDiagramGenerator processDiagramGenerator;

	@ResponseBody
	@RequestMapping(value = "/getImg")
	public void showImg(String instanceId, HttpServletResponse response) throws IOException {
		if (StringUtils.isBlank(instanceId)) {
            return;
        }
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).singleResult();
		if (processInstance == null) {
			return;
		}
		// 根据流程对象获取流程对象模型
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		// 构造历史流程查询
		HistoricActivityInstanceQuery historyInstanceQuery = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceId);
		// 查询历史节点
		List<HistoricActivityInstance> historicActivityInstanceList = historyInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();
		if (historicActivityInstanceList == null || historicActivityInstanceList.size() == 0) {
			outputImg(response, bpmnModel, null, null);
			return;
		}
		// 已执行的节点ID集合
		List<String> executedActivityIdList = historicActivityInstanceList.stream().map(item -> item.getActivityId()).collect(Collectors.toList());
		// 获取流程走过的线
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		List<String> flowIds = ActivitiUtils.getHighLightedFlows(bpmnModel, processDefinition, historicActivityInstanceList);
		outputImg(response, bpmnModel, flowIds, executedActivityIdList);
	}

	private void outputImg(HttpServletResponse response, BpmnModel bpmnModel, List<String> flowIds, List<String> executedActivityIdList) throws IOException {
		InputStream imageStream = null;
		response.setHeader("Content-Type", "text/xml");//指定格式，不指定格式图形无法加载
		try {
			imageStream = processDiagramGenerator.generateDiagram(bpmnModel, executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", true, "png");
			byte[] b = new byte[1024];
			int len;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
			response.getOutputStream().flush();
		} catch (Exception e) {
			logger.error("下载流程图失败");
		} finally { // 流关闭
			if (imageStream != null) {
                imageStream.close();
            }
		}
	}

}
