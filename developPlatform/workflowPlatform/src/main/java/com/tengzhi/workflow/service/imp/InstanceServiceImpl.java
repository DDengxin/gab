package com.tengzhi.workflow.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManagerImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.workflow.dao.BacklogDao;
import com.tengzhi.workflow.dao.OpinionDao;
import com.tengzhi.workflow.model.Backlog;
import com.tengzhi.workflow.model.Opinion;
import com.tengzhi.workflow.service.InstanceService;
import com.tengzhi.workflow.service.factory.activitiFactory;

/**
 * @author lqy 默認不添加事务，由其他调用类传入
 */
@Service
public class InstanceServiceImpl extends activitiFactory implements InstanceService {
	@Autowired
	private BacklogDao backlogDao;
	@Autowired
	private OpinionDao opinionDao;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ProcessInstance startByKey(String key) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
		return processInstance;
	}

	@Override
	public ProcessInstance startById(String id) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(id);
		return processInstance;
	}

	@Override
	public ProcessInstance startByKey(String key, Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, variables);
		return processInstance;
	}

	@Override
	public ProcessInstance startById(String id, Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(id, variables);
		return processInstance;
	}

	@Override
	public ProcessInstance startByKey(String key, String businesskey, Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, businesskey, variables);
		return processInstance;
	}

	@Override
	public ProcessInstance startById(String id, String businesskey, Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(id, businesskey, variables);
		return processInstance;
	}

	@Override
	public void suspendedById(String id) {
		runtimeService.suspendProcessInstanceById(id);
	}

	@Override
	public void activationById(String id) {
		runtimeService.activateProcessInstanceById(id);
	}

	@Override
	public String startById(String id, String businesskey, Map<String, Object> variables, Backlog backlog) {
		ProcessInstance processInstance = null;
		String taskName="";
		try {
			List<Backlog> list = backlogDao.findAll(Specifications.where((c) -> {
				c.eq("businessId", businesskey);
			}));
			list.forEach(e -> {
				backlogDao.delete(e);
			});
			Authentication.setAuthenticatedUserId(backlog.getTransactorId());
			processInstance = runtimeService.startProcessInstanceById(id, businesskey, variables);
			entityManager.createNativeQuery("update   ACT_HI_ACTINST q set assignee_='"+backlog.getTransactorId()+"'  where q.proc_inst_id_='" + processInstance.getId() + "'")
			.executeUpdate();
			backlog.setId(UUIDUtil.uuid());
			backlog.setBusinessId(businesskey);
			backlog.setTime(new Date());
			backlog.setProcessKey(id);
			backlog.setProcessInstanceId(processInstance.getId());
			backlogDao.save(backlog);
			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();// 拿到任务
			taskService.setAssignee(task.getId(), backlog.getTransactorId());
			taskName =getTaskName(processInstance.getId(),  task.getId());
			taskService.complete(task.getId());// 结束第一个任务
		} catch (Exception e) {
			e.printStackTrace();// 异常抛出
		}
		return taskName;
	}

	@Override
	public String agree(String taskid, String opinion, String businesskey, String instanceId) {
		Task task = null;
		String taskName = null;
		try {
			taskName = getTaskName(instanceId, taskid);
			SessionUser securityUser=SessionUser.SessionUser();
			taskService.setAssignee(taskid, securityUser.getJobNumber());
			taskService.complete(taskid);// 结束任务
			Opinion opini = new Opinion();
			opini.setId(UUIDUtil.uuid());
			opini.setTaskId(taskid);
			opini.setBusinessId(businesskey);
			opini.setOpinion(opinion);
			opini.setTransactor(securityUser.getNickName());
			opini.setTransactorId(securityUser.getJobNumber());
			opini.setHandleTime(new Date());
			opinionDao.save(opini);
			Backlog backlog = backlogDao.findByBusinessId(businesskey);
			backlog.setTransactor(securityUser.getNickName());
			backlog.setTransactorId(securityUser.getJobNumber());
			backlog.setProcessInstanceId(instanceId);
			backlog.setTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();// 异常抛出
		}
		return taskName;
	}
	


	@Override
	public String disagree(String taskid, String opinion, String businesskey, String instanceId) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		Task task = taskService.createTaskQuery() // 创建任务查询
				.processInstanceId(instanceId) // 根据流程实例id查询
				.singleResult();
		taskService.setAssignee(taskid, securityUser.getJobNumber());
		entityManager.createNativeQuery("update   ACT_HI_ACTINST q set end_time_=now()  where q.proc_inst_id_='" + instanceId + "' and  q.task_id_='" + task.getId() + "'")
				.executeUpdate();
		// 获取流程定义
		org.activiti.bpmn.model.Process process = repositoryService.getBpmnModel(task.getProcessDefinitionId()).getMainProcess();
		int i = 0,index=0;
		List<FlowElement> list = (List<FlowElement>) process.getFlowElements();
		List<FlowElement> Usertasklist=new ArrayList<FlowElement>();
		for (FlowElement e : list) {
			if (e instanceof org.activiti.bpmn.model.UserTask) {
				i++;
				Usertasklist.add(e);
				org.activiti.bpmn.model.UserTask usertask = (UserTask) e;
				if (usertask.getId().equals(task.getTaskDefinitionKey())) {
					index=i;
				}
			}
		}
		FlowNode targetNode = (FlowNode) Usertasklist.get(index-2);
		// 删除当前运行任务
		String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(task.getId()));
		// 流程执行到来源节点
		Authentication.setAuthenticatedUserId(securityUser.getJobNumber());
		managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
		Opinion opini = new Opinion();
		opini.setId(UUIDUtil.uuid());
		opini.setTaskId(taskid);
		opini.setBusinessId(businesskey);
		opini.setOpinion(opinion);
		opini.setTransactor(securityUser.getNickName());
		opini.setTransactorId(securityUser.getJobNumber());
		opini.setHandleTime(new Date());
		opinionDao.save(opini);
		FlowNode Node =  (FlowNode) Usertasklist.get(index-2);
		return Node.getName();
	}

	// 这里继承了NeedsActiveTaskCmd，主要时很多跳转业务场景下，要求不能时挂起任务。可以直接继承Command即可
	public class DeleteTaskCmd extends NeedsActiveTaskCmd<String> {
		public DeleteTaskCmd(String taskId) {
			super(taskId);
		}

		@Override
		public String execute(CommandContext commandContext, TaskEntity currentTask) {
			// 获取所需服务
			TaskEntityManagerImpl taskEntityManager = (TaskEntityManagerImpl) commandContext.getTaskEntityManager();
			// 获取当前任务的来源任务及来源节点信息
			ExecutionEntity executionEntity = currentTask.getExecution();
			// 删除当前任务,来源任务
			taskEntityManager.deleteTask(currentTask, "jumpReason", false, false);
			return executionEntity.getId();
		}

		@Override
		public String getSuspendedTaskException() {
			return "挂起的任务不能跳转";
		}

	}

	// 根据提供节点和执行对象id，进行跳转命令
	public class SetFLowNodeAndGoCmd implements Command<Void> {
		private FlowNode flowElement;
		private String executionId;

		public SetFLowNodeAndGoCmd(FlowNode flowElement, String executionId) {
			this.flowElement = flowElement;
			this.executionId = executionId;
		}

		@Override
        public Void execute(CommandContext commandContext) {
			// 获取目标节点的来源连线
			List<SequenceFlow> flows = flowElement.getIncomingFlows();
			if (flows == null || flows.size() < 1) {
				throw new ActivitiException("回退错误，目标节点没有来源连线");
			}
			// 随便选一条连线来执行，时当前执行计划为，从连线流转到目标节点，实现跳转
			ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findById(executionId);
			executionEntity.setCurrentFlowElement(flows.get(0));
			commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(executionEntity, true);
			return null;
		}
	}

	@Override
	public String agree(String taskid, String businesskey, String instanceId) {
		String taskName = null;
		try {
			taskName = getTaskName(instanceId, taskid);
			Task task = taskService.createTaskQuery().taskId(taskid).singleResult();// 拿到任务
			SessionUser securityUser=SessionUser.SessionUser();
			taskService.setAssignee(taskid, securityUser.getJobNumber());
			taskService.complete(taskid);// 结束任务
			Backlog backlog = backlogDao.findByBusinessId(businesskey);
			backlog.setTransactor(securityUser.getNickName());
			backlog.setTransactorId(securityUser.getJobNumber());
			backlog.setProcessInstanceId(instanceId);
			backlog.setTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();// 异常抛出
		}
		return taskName;
	}

	public String getTaskName(String instanceId, String taskid) {
		Task task = taskService.createTaskQuery() // 创建任务查询
				.processInstanceId(instanceId) // 根据流程实例id查询
				.singleResult();
		// 获取流程定义
		org.activiti.bpmn.model.Process process = repositoryService.getBpmnModel(task.getProcessDefinitionId()).getMainProcess();
		// 获取目标节点定义flowElementId
		List<FlowElement> list = (List<FlowElement>) process.getFlowElements();
		List<FlowElement> Usertasklist=new ArrayList<FlowElement>();
		int i = 0,index=0;
		String EndEventName="";
		for (FlowElement e : list) {
			if (e instanceof org.activiti.bpmn.model.UserTask) {
				i++;
				Usertasklist.add(e);
				org.activiti.bpmn.model.UserTask usertask = (UserTask) e;
				if (usertask.getId().equals(task.getTaskDefinitionKey())) {
					index=i;
				}
			}
			if(e instanceof org.activiti.bpmn.model.EndEvent) {
				org.activiti.bpmn.model.EndEvent EndEvent = (org.activiti.bpmn.model.EndEvent) e;
				EndEventName=EndEvent.getName();
			}
		}
		FlowNode targetNode = null;
		if(index==Usertasklist.size()) {
			return EndEventName;
		}else {
			 targetNode = (FlowNode) Usertasklist.get(index);
		}
		return targetNode.getName();
	}

	@Override
	public String agree(String taskid, String businesskey, String instanceId, Map<String, Object> variables) {
		String taskName = null;
		try {
			taskName = getTaskName(instanceId, taskid);
			Task task = taskService.createTaskQuery().taskId(taskid).singleResult();// 拿到任务
			SessionUser securityUser=SessionUser.SessionUser();
			taskService.setAssignee(taskid, securityUser.getJobNumber());
			taskService.complete(taskid,variables,true);// 结束任务
			Backlog backlog = backlogDao.findByBusinessId(businesskey);
			backlog.setTransactor(securityUser.getNickName());
			backlog.setTransactorId(securityUser.getJobNumber());
			backlog.setProcessInstanceId(instanceId);
			backlog.setTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();// 异常抛出
		}
		return taskName;
	
	}

	@Override
	public String agree(String taskid, String opinion, String businesskey, String instanceId, Map<String, Object> variables) {
		Task task = null;
		String taskName = null;
		try {
			taskName = getTaskName(instanceId, taskid);
			SessionUser securityUser=SessionUser.SessionUser();
			taskService.setAssignee(taskid, securityUser.getJobNumber());
			taskService.complete(taskid,variables,true);// 结束任务
			Opinion opini = new Opinion();
			opini.setId(UUIDUtil.uuid());
			opini.setTaskId(taskid);
			opini.setBusinessId(businesskey);
			opini.setOpinion(opinion);
			opini.setTransactor(securityUser.getNickName());
			opini.setTransactorId(securityUser.getJobNumber());
			opini.setHandleTime(new Date());
			opinionDao.save(opini);
			Backlog backlog = backlogDao.findByBusinessId(businesskey);
			backlog.setTransactor(securityUser.getNickName());
			backlog.setTransactorId(securityUser.getJobNumber());
			backlog.setProcessInstanceId(instanceId);
			backlog.setTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();// 异常抛出
		}
		return taskName;
	}	

}
