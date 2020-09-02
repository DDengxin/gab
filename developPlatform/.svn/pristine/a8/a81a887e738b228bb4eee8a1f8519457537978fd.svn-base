package com.tengzhi.workflow.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.workflow.service.BacklogService;

@RestController
@RequestMapping("/workflow/backlog")
public class BacklogController {
	@Autowired
	protected BacklogService backlogService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	protected HistoryService historyService;

	@RequestMapping(value = "/approval")
	public ModelAndView approval(ModelAndView mv) {
		mv.setViewName("workflow/backlog/approval");
		return mv;
	}
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(ModelAndView mv) {
		mv.setViewName("workflow/backlog/backlog");
		return mv;
	}

	/**
	 * 查询待办数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getBacklog")
	public Result getBacklog(BaseDto baseDto) throws IOException {
		return backlogService.getBacklog(baseDto).getResult();
	}

	/**
	 * 进入办理界面
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/toTransaction")
	public ModelAndView toReleaseProcess(String key, ModelAndView mv) throws IOException {

		mv.setViewName("workflow/backlog/transaction");
		// 流程图
		if (StringUtils.isNotEmpty(key)) {
			List<ProcessDefinition> processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(key).list();
			String deploymentId = processDefinition.get(0).getDeploymentId();
			InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.get(0).getResourceName());
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
	 * 查询流程实例数据
	 *
	 * @return
	 */
	@PostMapping(value = "/gethistoryTask")
	public Result gethistoryTask(BaseDto baseDto) throws IOException {
		return backlogService.gethistoryTask(baseDto).getResult();
	}

}
