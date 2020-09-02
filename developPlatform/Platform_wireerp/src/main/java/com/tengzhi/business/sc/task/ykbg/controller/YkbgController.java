package com.tengzhi.business.sc.task.ykbg.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistVo;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.ykbg.service.YkbgService;

@RestController
@RequestMapping("/sc/task/ykbg")
public class YkbgController {
	
	@Autowired
	private YkbgService ykbgService;
	
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
		return ykbgService.getSrchGridList(baseDto).getResult();
	}
	
	 @PutMapping("/getByScmo")
   	public  Result getByScmo(@RequestBody BaseDto baseDto) throws IOException {
   		return ykbgService.getByScmo(baseDto);
   	}
	
	 @PutMapping("/getByScgx")
   	public  Result getByScgx(@RequestBody BaseDto baseDto) throws Exception {
   		return ykbgService.getByScgx(baseDto);
   	} 
	 
	 /**
     * 
     * @description:工序下料
     */
    @PostMapping(value = "/gxxl")
    public Result gxxl(@RequestBody MScGclistVo vo) throws Exception {
        return ykbgService.gxxl(vo);
    }

	@PostMapping(value = "/getInList/{inValue}")
	public Result getInList(@PathVariable(value = "inValue") String inValue) throws Exception {
		return ykbgService.getInList(inValue);
	}
	    
}
