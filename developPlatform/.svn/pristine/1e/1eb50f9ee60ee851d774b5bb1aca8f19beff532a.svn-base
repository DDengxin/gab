package com.tengzhi.business.sc.pd.gcrw.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.pd.gcrw.service.GcrwkService;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sc/pd/gcrw")
public class GcrwController {
	
	@Autowired
	private GcrwkService gcrwkService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}


	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchScpdList")//中间工序grid
	public Result getSrchScpdList(BaseDto baseDto) throws IOException {
		return gcrwkService.getSrchScpdList(baseDto);
	}
	


	@PostMapping(value = "/updateRwGx")
    public Result updateRwGx(@RequestBody MScScrwGxVo vo) throws Exception {
        return gcrwkService.updateRwGx(vo);
    }
	


}
