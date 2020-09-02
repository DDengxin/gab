package com.tengzhi.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.service.Logservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/system")
public class logController {
	@Autowired
	private Logservice logservice;

	/**
	 * 操作日志
	 *
	 * @return
	 */
	@GetMapping("/operationLog")
	public ModelAndView operationLog(ModelAndView mv) {
		mv.setViewName("system/log/operationLog/logList");
		return mv;
	}
	
	/**
	 *异常日志
	 *
	 * @return
	 */
	@GetMapping("/abnormalLog")
	public ModelAndView abnormalLog(ModelAndView mv) {
		mv.setViewName("system/log/abnormalLog/logList");
		return mv;
	}
	
	/**
	 *登陆日志
	 *
	 * @return
	 */
	@GetMapping("/loginLog")
	public ModelAndView loginLog(ModelAndView mv) {
		mv.setViewName("system/log/loginLog/logList");
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/log/list")
	@ResponseBody
	public Result getList(BaseDto baseDto) throws IOException {
		return logservice.findAll(baseDto).getResult();
	}
}
