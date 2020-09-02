package com.tengzhi.business.platform.specialist.need.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.specialist.need.service.NeedService;
import com.tengzhi.business.platform.specialist.need.vo.needVo;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/platform/specialist/need/")
public class NeedControler {

	@Autowired
	private NeedService needService;
	
	@Autowired
	private ProcessService processService;
	
	@GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
	
	@PostMapping(value = "sreach")
	public Result sreach(BaseDto baseDto) throws IOException {
		return needService.sreach(baseDto).getResult();
	}
	
	@PostMapping(value = "saveNeed")
	public Result saveNeed(@RequestBody needVo needVo) throws Exception {
		return needService.saveNeed(needVo);
	}

	@GetMapping(value="findById/{needNote}/{cpcodeXp}")
	public Result findById(@PathVariable String needNote,@PathVariable String cpcodeXp) {
		return Result.resultOk(needService.findById(needNote,cpcodeXp));
	}
	
	@PutMapping(value = "saveNeed")
	public Result updateNeed(@RequestBody needVo needVo) throws Exception {
		needService.updateNeed(needVo);
		return Result.resultOk();
	}
	
	@DeleteMapping(value = "deleteByNote/{needNote}")
	public Result deleteByNote(@PathVariable String needNote) {
		needService.deleteByNote(needNote);
		return Result.resultOk();
	}
	
	@GetMapping(value="examination/{needNote}")
	public Result examination(@PathVariable String needNote) {
		return Result.resultOk(needService.examination(needNote));
	}
	
	@PostMapping(value = "startFinishFirstStep")
	public Result startFinishFirstStep(@RequestBody Examine examine) throws IOException {
		return processService.startFinishFirstStep(examine);
	}
	
}
