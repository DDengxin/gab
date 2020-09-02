package com.tengzhi.workflow.test.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.workflow.service.InstanceService;
import com.tengzhi.workflow.service.factory.activitiFactory;
import com.tengzhi.workflow.test.dao.EmployeeDao;
import com.tengzhi.workflow.test.model.Employee;
import com.tengzhi.workflow.test.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl extends activitiFactory implements EmployeeService {
	@Autowired
	private EmployeeDao EmployeeDao;
	@Autowired
	private InstanceService work;

	@Override
	public BasePage<Employee> findAll(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return EmployeeDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.startingWith("employeeName", map.get("employeeName"));
		}));
	}

	@Override
	public Employee findByid(String id) {
		return EmployeeDao.findById(id).orElse(null);
	}

	@Override
	public Employee save(Employee employee) throws Exception {
		employee.setId(UUIDUtil.uuid());
		employee.setType("1");
		return EmployeeDao.save(employee);
	}

	@Override
	public void update(Employee Employee) {
		EmployeeDao.dynamicSave(Employee, EmployeeDao.findById(Employee.getId()).orElse(null));
	}

	@Override
	public void deleteByid(String id) {
		EmployeeDao.deleteById(id);
	}

	@Override
	public Result getBacklog(BaseDto baseDto) {
		SessionUser securityUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 只查询办理人 ，候选人和候选组未查询 复杂查询taskService .createNativeTaskQuery().sql("")
		List<Task> task = taskService.createTaskQuery().taskAssignee(securityUser.getJobNumber()).listPage(baseDto.getStart(), baseDto.getEnd());
		List<Map<String, Object>> pd = new ArrayList<Map<String, Object>>();
		task.stream().forEach(x -> {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("id", x.getId());
			values.put("name", x.getName());
			values.put("assignee", x.getAssignee());// 办理人
			pd.add(values);
		});
		long count = taskService.createTaskQuery().taskAssignee(securityUser.getJobNumber()).count();
		return Result.formPage(pd, count);
	}

	@Override
	public Employee saveAndSubmit(Employee employee) {
		String businesskey = "";
		if (!StringUtils.isNotBlank(employee.getId())) {
            businesskey = UUIDUtil.uuid();
        } else {
            businesskey = employee.getId();
        }
		employee.setId(businesskey);
		employee.setType("2");
		Map<String, Object> variables = new HashMap<String, Object>();
		SessionUser securityUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		variables.put("employeeName", securityUser.getJobNumber());
		variables.put("day", employee.getDay());
		// 发起流程设置人
		ProcessInstance processInstance = work.startById("Process_1:2:179d04ad-7e49-11ea-9743-04d4c4e724f5", businesskey, variables);
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();// 拿到任务
		taskService.complete(task.getId());// 结束第一个任务
		Employee bean = findByid(employee.getId());
		if (bean == null) {
			return EmployeeDao.save(employee);
		} else {
			EmployeeDao.update(employee);
			return employee;
		}
	}

	@Override
	public Map<String, Object> audit(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Task task = taskService.createTaskQuery().taskId(id).singleResult();
		// 流程实例
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		String businessKey = pi.getBusinessKey();
		map.put("data", findByid(businessKey));// 业务信息
		map.put("taskId", id);// 业务信息
//		List<Comment> historyCommnets =taskService.getProcessInstanceComments(task.getProcessInstanceId());
//		map.put("comments", historyCommnets);// 批注信息
		return map;
	}

	@Override
	public void agree(Employee employee) {
		Task task = taskService.createTaskQuery().taskId(employee.getTaskid()).singleResult();
		Map<String, Object> map = new HashMap<>();
		if("初审".equals(task.getName())) {
			map.put("state", "1");
			map.put("day",employee.getDay());
		}else if("审核".equals(task.getName())) {
			map.put("result", "1");
			employee.setType("3");
			update(employee);
		}
		taskService.complete(employee.getTaskid(), map,true);
	}

	@Override
	public void disagree(Employee employee) {
		Task task = taskService.createTaskQuery().taskId(employee.getTaskid()).singleResult();
		Map<String, Object> map = new HashMap<>();
		if("初审".equals(task.getName())) {
			map.put("state", "2");
		}else {
			map.put("result", "2");
		}
		employee.setType("1");
		update(employee);
		taskService.complete(employee.getTaskid(), map,true);
	}

	public void Submit(Employee employee) {
		employee.setType("2");
		update(employee);
		Map<String, Object> variables = new HashMap<String, Object>();
		SessionUser securityUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		variables.put("employeeName", securityUser.getJobNumber());
		taskService.complete(employee.getTaskid(),variables,true);
	}

}
