package com.tengzhi.business.sc.task.blcl.controller;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sc.task.blcl.model.Blcl;
import com.tengzhi.business.system.datachart.model.SysDatachart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.task.blcl.service.BlclService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctc.service.SctcService;

@RestController
@RequestMapping("/sc/task/blcl")
public class BlclController {
	
	@Autowired
	private BlclService blclService;
	
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
		return blclService.getSrchGridList(baseDto).getResult();
	}

	/**
	 *
	 * 根据工程号当前数据状态
	 */
	@PostMapping(value = "/getGcstatus")
	public Result getGcstatus(@RequestBody BaseDto baseDto) {
		return  blclService.getGcstatus(baseDto);
	}

	/**
	 * 获取对象
	 */
	@PutMapping("/getById")
	public  Result getById(@RequestBody BaseDto baseDto){
		return blclService.getById(baseDto);
	}

	/**
	 * 修改
	 */
	@PutMapping("add.html")
	public Result modify(@RequestBody Blcl blclmd) {
		return Result.resultOk(blclService.update(blclmd));
	}
}
