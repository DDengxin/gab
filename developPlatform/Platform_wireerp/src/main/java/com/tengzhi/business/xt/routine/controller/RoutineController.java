package com.tengzhi.business.xt.routine.controller;

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
import com.tengzhi.business.xt.routine.service.RoutineService;
import com.tengzhi.business.xt.routine.vo.EXtSjxgVo;

@RestController
@RequestMapping("/xt/routine")
public class RoutineController {

	@Autowired
	private RoutineService routineService;
	
    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
    
    /**
     * 查询数据列表
     * @return
     */
    @PostMapping(value = "getSrchTopList")
    public Result getSrchTopList(BaseDto baseDto) throws IOException {
        return routineService.getSrchTopList(baseDto).getResult();
    }
    
    /**
     * 查询明细数据列表
     * @return
     */
    @PostMapping(value = "getSrchBottomList")
    public Result getSrchBottomList(BaseDto baseDto) throws IOException {
        return routineService.getSrchBottomList(baseDto).getResult();
    }
    
    /**
     * 保存数据
     * @return
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EXtSjxgVo vo) throws Exception {
        return routineService.save(vo);
    }
    
    /**
     * 修改数据
     * @return
     */
    @PutMapping(value = "add.html")
    public Result update(@RequestBody EXtSjxgVo vo) throws Exception {
        return routineService.update(vo);
    }
    
    /**
     *获取单条数据
     * @return
     */
    @GetMapping(value = "getByNote/{sqNote}")
    public Result getById(@PathVariable(value = "sqNote") String sqNote) {
        return Result.resultOk(routineService.getByNote(sqNote));
    }
    
    /**
     *删除数据
     * @return
     */
    @DeleteMapping(value = "delete/{sqNote}")
    public Result delete(@PathVariable(value = "sqNote") String sqNote) {
        return Result.resultOk(routineService.delete(sqNote));
    }
    
    /**
     * 	 申请类型
     * @return
     * @throws IOException
     */
    @GetMapping(value = "type/sqlx")
	public List<Map> getSqlxList() throws IOException {
		String mod="财务",type="数据修改",parent="SJLX";
		return routineService.getlList(mod,type,parent);
	}
    
    /**
     * 	 修改项目
     * @return
     * @throws IOException
     */
    @GetMapping(value = "type/sgxm")
	public List<Map> getSgxmList() throws IOException {
		String mod="财务",type="修改项目",parent="XGXM";
		return routineService.getlList(mod,type,parent);
	}
    
}
