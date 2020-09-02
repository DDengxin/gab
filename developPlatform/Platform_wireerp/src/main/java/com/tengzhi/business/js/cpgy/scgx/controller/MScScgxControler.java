package com.tengzhi.business.js.cpgy.scgx.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.js.cpgy.scgx.service.MScScgxService;
import com.tengzhi.business.js.cpgy.scgx.vo.MScScgxVo;
import com.tengzhi.business.resouces.vo.SelectVo;

@RestController
@RequestMapping("/js/cpgy/scgx")
public class MScScgxControler {

	@Autowired
	private MScScgxService mScScgxService;
	
	
	

	@GetMapping("/*")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}


    
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return mScScgxService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return mScScgxService.getSrchBottomList(baseDto).getResult();
	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody MScScgxVo vo) throws Exception {
    	return   mScScgxService.save(MScScgxVo.initMScScgxVo(vo));
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody MScScgxVo vo) throws Exception {
    	return mScScgxService.update(MScScgxVo.initMScScgxVo(vo));
    }
    
    
    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{gxId}")
    public Result getById(@PathVariable(value = "gxId") String gxId) {
        return Result.resultOk(mScScgxService.findById(gxId));
    }
    
    @DeleteMapping(value = "delete/{gxId}")
    public Result delete(@PathVariable(value = "gxId") String gxId) throws Exception {
		Result result = mScScgxService.deleteById(gxId);
		return result;
    }
    
    
    @GetMapping("/getGxAllSelectList")
	public List<SelectVo> getGxAllSelectList() {
		return mScScgxService.getGxAllSelectList();
	}

	@GetMapping("/getGxRootSelectList/{gxtype}")
	public List<SelectVo> getGxRootSelectList(@PathVariable(name = "gxtype") String gxtype) {
		return mScScgxService.getGxRootSelectList(gxtype);
	}

	@PostMapping("/getGxAllTreeList/{gxtype}")
	public List<SelectVo> getGxAllTreeList(@PathVariable(name = "gxtype") String gxtype) {
		return mScScgxService.getGxAllTreeList(gxtype);
	}
    /**
	 * 车间下拉框
	 * 
	 * @param gx
	 * @return
	 */
	@GetMapping("/getCjList/{gx}")
	public List<SelectVo> getCjList(@PathVariable(name = "gx") String gx) {
		return mScScgxService.getCjList(gx);
	}


	/**
	 * 根据生产任务获取工序下拉框
	 * 
	 * @param scmo
	 * @return
	 */
	@GetMapping("/getGxByScmoList/{scmo}")
	public List<SelectVo> getGxByScmoList(@PathVariable(name = "scmo") String scmo) {
		return mScScgxService.getGxByScmoList(scmo);
	}
	

}
