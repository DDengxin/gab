package com.tengzhi.workflow.service.factory;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lqy activiti常用service，
 */
@Component
public class activitiFactory {
	@Autowired
	protected RepositoryService repositoryService;// 流程仓库Service，用于管理流程仓库，例如：部署，删除，读取流程资源

	@Autowired
	protected RuntimeService runtimeService;// 运行时Service，可以处理所有正在运行状态的流程实例，任务等

	@Autowired
	protected TaskService taskService;// 任务Service，用于管理，查询任务，例如：签收，办理，指派等

	@Autowired
	protected HistoryService historyService;// 历史Service，可以查询所有历史数据，例如：流程实例，任务，活动，变量，附件等

	@Autowired
	protected ManagementService managementService;// 引擎管理Service，和具体业务无关，主要是可以查询引擎配置，数据库，作业等

	@Autowired
	protected ProcessEngine processEngine;// 总对象
}
