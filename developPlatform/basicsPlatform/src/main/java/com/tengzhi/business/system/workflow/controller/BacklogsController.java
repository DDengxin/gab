package com.tengzhi.business.system.workflow.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.workflow.service.InstanceService;
import com.tengzhi.business.system.workflow.vo.Examine;

@RestController
@RequestMapping("system/workflow/backlog")
public class BacklogsController {
	@Autowired
	private InstanceService instanceService;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(ModelAndView mv) {
		mv.setViewName("system/workflow/backlog/backlog");
		return mv;
	}

	@RequestMapping(value = "/approval")
	public ModelAndView approval(ModelAndView mv) {
		mv.setViewName("system/workflow/backlog/approval");
		return mv;
	}
	@RequestMapping(value = "/applyAndTodo")
	public ModelAndView toApplyAndTodo(ModelAndView mv) {
		mv.setViewName("system/workflow/backlog/applyAndTodo");
		return mv;
	}

	/**
	 * 查询待办数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getBacklog")
	public Result getBacklog(BaseDto baseDto) {
		return instanceService.getBacklogs(baseDto).getResult();
	}

	/**
	 * 查询系统分类
	 *
	 * @return
	 */
	@GetMapping(value = "/getProcessClassify/{process_module}/{process_system_type}")
	public List<Map> getProcessClassify(@PathVariable String process_module, @PathVariable String process_system_type)	 {
		return instanceService.getProcessClassify(process_module, process_system_type);
	}

	/**
	 * 查询环节
	 *
	 * @return
	 */
	@GetMapping(value = "/getlink/{id}")
	public Result getlink(@PathVariable String id)  {
		return instanceService.getlink(id);
	}

	/**
	 * 进入办理界面
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/toTransaction")
	public ModelAndView toReleaseProcess(String key, ModelAndView mv) {
		mv.setViewName("system/workflow/backlog/approval");
		return mv;
	}

	/**
	 * 查询待办数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/gethistoryTask")
	public Result gethistoryTask(BaseDto baseDto, String processId, String businessId) {
		return instanceService.getTransact(baseDto, processId, businessId).getResult();
	}

	/**
	 * 查询待办数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/gethistory")
	public Result gethistoryTask(@RequestBody Examine examine) throws IOException {
		return Result.resultOk(instanceService.getTransact(examine.getProcessId(), examine.getBusinessId()));
	}

}
