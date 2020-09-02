package com.tengzhi.business.js.cpgy.scgy.controller;

import java.io.IOException;
import java.util.List;
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
import com.tengzhi.business.js.cpgy.scgy.service.MScScgyService;
import com.tengzhi.business.js.cpgy.scgy.vo.MScScgyVo;
import com.tengzhi.business.resouces.vo.SelectVo;

@RestController
@RequestMapping("/js/cpgy/scgy")
public class MScScgyControler {

	@Autowired
	private MScScgyService mScScgyService;
	
	
	

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
		return mScScgyService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
/*	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		Result result = mScScgyService.getSrchBottomList(baseDto).getResult();
//		System.out.println(result.get("data"));
//		System.out.println("参数:"+baseDto.getParams());
		return mScScgyService.getSrchBottomList(baseDto).getResult();
	}*/

	@PostMapping(value = "/getSrchBottomList")
	public List<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
		return mScScgyService.getSrchBottomJoin(baseDto);
	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody MScScgyVo vo) throws Exception {
    	return   mScScgyService.save(MScScgyVo.initMScScgyVo(vo));
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody MScScgyVo vo) throws Exception {
    	return mScScgyService.update(MScScgyVo.initMScScgyVo(vo));
    }
    
    
    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{gyId}")
    public Result getById(@PathVariable(value = "gyId") String gyId) {
        return Result.resultOk(mScScgyService.findById(gyId));
    }
    
    @DeleteMapping(value = "delete/{htNO}")
    public Result delete(@PathVariable(value = "htNO") String htNO) throws Exception {
    	mScScgyService.deleteById(htNO);
        return Result.resultOk();
    }
    

    @GetMapping("/getGyAllSelectList")
	public List<SelectVo> getGyAllSelectList() {
		return mScScgyService.getGyAllSelectList();
	}
	


}
