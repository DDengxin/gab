package com.tengzhi.business.sc.task.zzlz.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.task.zzlz.service.ZzlzService;

@RestController
@RequestMapping("/sc/task/zzlz")
public class ZzlzController {
	
	@Autowired
	private ZzlzService zzlzService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchGridList")
	public Result getSrchGridList(BaseDto baseDto) throws IOException {
		return zzlzService.getSrchGridList(baseDto).getResult();
	}
	
	
	
	
}
