package com.tengzhi.business.sc.task.sctc.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctc.service.SctcService;

@RestController
@RequestMapping("/sc/task/sctc")
public class SctcController {
	
	@Autowired
	private SctcService sctcService;
	
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
		return sctcService.getSrchGridList(baseDto).getResult();
	}
	
	
	
	
}
