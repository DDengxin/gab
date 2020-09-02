package com.tengzhi.business.system.workflow.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.workflow.service.InstanceService;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import com.tengzhi.business.system.workflow.vo.SaveVo;

import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping("system/workflow/process")
public class ProcessController {
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private InstanceService instanceService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	@GetMapping("/toSignature")
	public ModelAndView toSignature(ModelAndView mv) {
		mv.setViewName("model/signature");
		return mv;
	}
	
	@PostMapping("/saveSignature")
	public Result saveSignature(@RequestBody Examine examine) {
		return processService.saveSignature(examine);
	}

	/**
	 * 查询流程列表
	 *
	 * @return
	 */
	@PostMapping(value = "/saveProcess")
	public Result saveProcess(@RequestBody SaveVo soure) {
		return processService.saveProcess(soure);
	}

	/**
	 * 查询流程列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getProcess")
	public Result getProcess(BaseDto baseDto) throws IOException {
		return processService.getProcess(baseDto).getResult();
	}

	/**
	 * 查询流程列表
	 *
	 * @return
	 */
	@GetMapping(value = "/getProcessInstance")
	public Result getProcessInstance(@RequestParam String processId) {
		return Result
				.resultOk(JSONUtil.parseObj(processService.getProcessInstance(processId), false, false).toString());
	}
	
	/**
	 * 查询系统分类
	 *
	 * @return
	 */
	@GetMapping(value = "/getProcessClassify/{process_module}/{process_system_type}")
	public List<Map> getProcessClassify(@PathVariable String process_module, @PathVariable String process_system_type)
			throws IOException {
		return processService.getProcessClassify(process_module, process_system_type);
	}

	/**
	 * 审批同意
	 *
	 * @return
	 */
	@PutMapping(value = "/agree")
	public Result agree(@RequestBody Examine examine) {
		return processService.agree(examine);
	}

	/**
	 * 审批2步，办理人必须为同一人
	 *
	 * @return
	 */
	@PutMapping(value = "/handle")
	public Result handle(@RequestBody Examine examine) {
		return processService.handle(examine);
	}

	/**
	 * 发布流程
	 *
	 * @return
	 */
	@GetMapping(value = "/release")
	public Result release(String id) {
		return processService.release(id);
	}

	/**
	 * 审批不同意
	 *
	 * @return
	 */
	@PutMapping(value = "/disagree")
	public Result disagree(@RequestBody Examine examine) {
		return processService.disagree(examine);
	}

//	/**
//	 * 提交流程
//	 *
//	 * @return
//	 */
//	@PutMapping(value = "/start")
//	public Result start(@RequestBody Examine examine) throws IOException {
//		return processService.start(examine);
//	}

	/**
	 * 提交流程
	 *
	 * @return
	 */
	@PutMapping(value = "/startFinishFirstStep")
	public Result startFinishFirstStep(@RequestBody Examine examine)  {
		return processService.startFinishFirstStep(examine);
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	@DeleteMapping(value = "del/{id}")
	public Result delete(@PathVariable(value = "id") String id) {
		return processService.delete(id);
	}

	/**
	 * 取消
	 * 
	 * @param examine
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/repeal")
	public @ResponseBody Result repeal(@RequestBody Examine examine) {
		return processService.repeal(examine);
	}
	
	/**
	 * 获取流程（只能获取纯sql,固定人员，角色）
	 * 
	 * @param examine
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getProcessDescription")
	public @ResponseBody Result getProcessDescription(@RequestBody Examine examine) {
		return instanceService.getProcessDescription(examine);
	}
}
