package com.tengzhi.workflow.service;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.workflow.model.Backlog;
import com.tengzhi.workflow.model.Opinion;

public interface InstanceService {

	/**
	 * 启动流程通过key
	 * 
	 * @param key 流程key
	 * @return ProcessInstance
	 */
	ProcessInstance startByKey(String key);

	/**
	 * 启动流程通过key
	 * 
	 * @param key       流程key
	 * @param variables 变量
	 * @return ProcessInstance
	 */
	ProcessInstance startByKey(String key, Map<String, Object> variables);

	/**
	 * 启动流程通过key
	 * 
	 * @param key         流程key
	 * @param businesskey 业务主键
	 * @param variables   变量
	 * @return ProcessInstance
	 */
	ProcessInstance startByKey(String key, String businesskey, Map<String, Object> variables);

	/**
	 * 启动流程通过id
	 * 
	 * @param id 流程id
	 * @return
	 */
	ProcessInstance startById(String id);

	/**
	 * 启动流程通过id 设置属性
	 * 
	 * @param id        流程id
	 * @param variables 变量
	 * @return
	 */
	ProcessInstance startById(String id, Map<String, Object> variables);

	/**
	 * 启动流程通过id 设置属性
	 * 
	 * @param id          流程id
	 * @param businesskey 业务主键
	 * @param variables   变量
	 * @return
	 */
	ProcessInstance startById(String id, String businesskey, Map<String, Object> variables);

	/**
	 * @param id 挂起该流程
	 */
	void suspendedById(String id);

	/**
	 * @param id 重新激活流程
	 */
	void activationById(String id);
	
	
	/**
	 * @param id  流程id
	 * @param businesskey 业务id
	 * @param variables 流程变量
	 * @param opinion  扩展表
	 * @return
	 */
	String startById(String id, String businesskey, Map<String, Object> variables,Backlog backlog);
	
	String agree(String taskid, String opinion, String businesskey,String instanceId);
	
	String agree(String taskid, String opinion, String businesskey,String instanceId, Map<String, Object> variables);

	String disagree(String taskid, String opinion, String businesskey,String instanceId) throws Exception ;
	
	String agree(String taskid,  String businesskey,String instanceId);
	
	String agree(String taskid,  String businesskey,String instanceId, Map<String, Object> variables);
	
}
