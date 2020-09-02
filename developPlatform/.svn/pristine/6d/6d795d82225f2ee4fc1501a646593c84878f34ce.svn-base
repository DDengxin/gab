package com.tengzhi.business.sc.pd.scpd.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.pd.scpd.service.ScpdService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwWlVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sc/pd/scpd")
public class ScpdController {
	
	@Autowired
	private ScpdService scpdService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}


	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopLeftList")
	public Result getSrchTopLeftList(BaseDto baseDto) throws IOException {
		return scpdService.getSrchTopLeftList(baseDto).getResult();
	}



}
