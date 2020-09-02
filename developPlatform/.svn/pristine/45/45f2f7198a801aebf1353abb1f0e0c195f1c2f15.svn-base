package com.tengzhi.business.sc.task.gccl.controller;

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
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistSlVo;
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistVo;
import com.tengzhi.business.sc.task.gccl.service.GcclService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctc.service.SctcService;

@RestController
@RequestMapping("/sc/task/gccl")
public class GcclController {
	
	@Autowired
	private GcclService gcclService;
	
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
		return gcclService.getSrchGridList(baseDto).getResult();
	}
	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return gcclService.getSrchTopList(baseDto).getResult();
	}
	

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return gcclService.getSrchBottomList(baseDto).getResult();
	}
	
	
	 /**
     * 
     * @description:
     */
    @PostMapping(value = "/split")
    public Result split(@RequestBody MScGclistVo vo) throws Exception {
    	return  gcclService.split(vo);
    }
	
    
    /**
     * 
     * @description:
     */
    @PostMapping(value = "/gchp")
    public Result gchp(@RequestBody BaseDto baseDto) throws Exception {
    	return  gcclService.gchp(baseDto);
    }
    
    
	
}
