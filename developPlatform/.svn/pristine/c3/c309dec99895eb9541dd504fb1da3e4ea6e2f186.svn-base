package com.tengzhi.business.sc.pd.scpc.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.pd.scpc.service.ScpcService;
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
@RequestMapping("/sc/pd/scpc")
public class ScpcController {
	
	@Autowired
	private ScpcService scpcService;
	
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
		return scpcService.getSrchTopLeftList(baseDto).getResult();
	}
	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return scpcService.getSrchBottomList(baseDto).getResult();
	}
	
	 /**
     * @throws IOException 
     * @Param: [vo]
     * @description:半成品list
     */
    @PostMapping("/getSrchGridList")
	public  Result getSrchGridList(BaseDto baseDto) throws IOException {
		return scpcService.getSrchGridList(baseDto).getResult();
	}
    
	@PostMapping(value = "/xsddSplit")
    public Result xsddSplit(@RequestBody MScScrw model) throws Exception {
        return scpcService.xsddSplit(model);
    }
	
	@PostMapping(value = "/xsddHp")
    public Result xsddHp(@RequestBody MScScrw model) throws Exception {
        return scpcService.xsddHp(model);
    }


	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopRightList")//中间工序grid
	public Result getSrchTopRightList(BaseDto baseDto) throws IOException {
		return scpcService.getSrchTopRightList(baseDto);
	}
	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchScpdList")//中间工序grid
	public Result getSrchScpdList(BaseDto baseDto) throws IOException {
		return scpcService.getSrchScpdList(baseDto);
	}
	
	
	/**
	 * 查询数据列表   srch_bottomleftgrid
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomLeftList")//左下方物料grid
	public Result getSrchBottomLeftList(BaseDto baseDto) throws IOException {
		return scpcService.getSrchBottomLeftList(baseDto).getResult();
	}

	/**
	 * 查询生产任务数据(用于选择列表)
	 * @return
	 */
	@PostMapping(value = "/getProductionTaskSelectList")
	public Result getProductionTaskSelectList(BaseDto baseDto) throws IOException {
		return scpcService.getProductionTaskSelectList(baseDto).getResult();
	}
	
	@PostMapping(value = "/saveRwGx")
    public Result saveRwGx(@RequestBody MScScrwGxVo vo) throws Exception {
		List<MScScrwGx> scgxlist = vo.getmScScrwGxList();
		boolean flag=false;
		for(int i=0;i<scgxlist.size();i++){
			Map<String,Object> rst=scpcService.veriFication(scgxlist.get(i).getScMo(),scgxlist.get(i).getGxNote());
			if("Y".equals(rst.get("status"))){
				flag=true;
				break;
			}
		}
		if(flag){
			return Result.error("包含已使用的数据，不能操作");
		}else {
			//return scTackService.saveRwGx(vo);
			return scpcService.saveGx(MScScrwGxVo.initMScScrwGxVo(vo));
		}
    }
	
	@PostMapping(value = "/updateRwGx")
    public Result updateRwGx(@RequestBody MScScrwGxVo vo) throws Exception {
        return scpcService.updateRwGx(vo);
    }
	
	
	@DeleteMapping(value = "deleteRwgx/{scMo}")
    public Result deleteRwgx(@PathVariable(value = "scMo") String scMo) throws Exception {
		Map<String,Object> rst=scpcService.veriFication(scMo,"");
		if("Y".equals(rst.get("status"))){
			return Result.error(rst.get("message").toString());
		}else {
			scpcService.deleteRwgx(scMo);
			return Result.resultOk();
		}
    }

	@PostMapping(value = "/saveRwWl")
    public Result saveRwWl(@RequestBody MScScrwWlVo vo) throws Exception {
		List<MScScrwWl> scwlist = vo.getmScScrwWlList();
		boolean flag=false;
		for(int i=0;i<scwlist.size();i++){
			Map<String,Object> rst=scpcService.veriFication(scwlist.get(i).getScrwMo(),scwlist.get(i).getWlGxnote());
			if("Y".equals(rst.get("status"))){
				flag=true;
				break;
			}
		}
		if(flag){
		    return Result.error("包含已使用的数据，不能操作");
		}else {
			//return scTackService.saveRwWl(vo);
			return scpcService.saveWl(MScScrwWlVo.initMScScrwWlVo(vo));
		}
    }
	
	@DeleteMapping(value = "deleteRwWl/{scrwMo}/{scrwGxnote}")
    public Result deleteRwWl(@PathVariable(value = "scrwMo") String scrwMo,@PathVariable(value = "scrwGxnote") String scrwGxnote) throws Exception {
		Map<String,Object> rst=scpcService.veriFication(scrwMo,scrwGxnote);
		if("Y".equals(rst.get("status"))){
			return Result.error(rst.get("message").toString());
		}else {
			scpcService.deleteRwWl(scrwMo, scrwGxnote);
			return Result.resultOk("删除成功！");
		}
    }
	
	  @DeleteMapping(value = "delete/{scMo}")
    public Result delete(@PathVariable(value = "scMo") String scMo) throws Exception {
		  return scpcService.deleteByScMo(scMo);
    }


	//@GetMapping("/gykview.html")
	@PostMapping(value = "gykview/{scMo}")
	public Result gykview(@PathVariable(value="scMo") String scMo) {
		return scpcService.gykview(scMo);
	}

	/**
	 * 查询数据列表   srch_bottomleftgrid
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomprintList")//打印时用料grid
	public Result getSrchBottomprintList(BaseDto baseDto){
		return scpcService.getSrchBottomprintList(baseDto).getResult();
	}
	/**
	 * 查询数据列表   srch_bottomleftgrid
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomRightprintList")//打印检验grid
	public Result getSrchBottomRightprintList(BaseDto baseDto){
		return scpcService.getSrchBottomRightprintList(baseDto).getResult();
	}

}
