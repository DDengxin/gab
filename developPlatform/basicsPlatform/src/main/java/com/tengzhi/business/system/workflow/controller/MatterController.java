package com.tengzhi.business.system.workflow.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.workflow.service.MatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("system/workflow/matter")
public class MatterController {
	@Autowired
	private MatterService matterService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	@PostMapping("getMatter")
	public Result getBacklog(BaseDto baseDto) {
		return matterService.getMatters(baseDto).getResult();
	}


}
