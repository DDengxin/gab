package com.tengzhi.business.personnel.eHrWorker.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.personnel.eHrWorker.service.eHrWorkerService;
import com.tengzhi.business.personnel.eHrWorker.vo.EHrWorkerVo;
import com.tengzhi.business.resouces.vo.SelectVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personnel/eHrWorker/")
public class eHrWorkerControler {
    @Autowired
    private eHrWorkerService eHrWorkerService;
    

    
    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    //@GetMapping("/staffRecordList.html")
    @PostMapping(value = "eHrWorkerList.html")
    public Result getList(BaseDto baseDto) throws IOException {
            return eHrWorkerService.findAll(baseDto).getResult();
    }
    
    
    
    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EHrWorker eHrWorker) throws Exception{
    	eHrWorker = eHrWorkerService.save(eHrWorker);
        return Result.resultOk(eHrWorker.getWorkerId());
    }
    
    
    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping(value = "eHrWorkerList.html/{workerId}")
    public Result delete(@PathVariable(value = "workerId") String workerId) {
        try{
			eHrWorkerService.deleteByWorkerId(workerId);
			return Result.resultOk();
		}catch (Exception e){
        	e.printStackTrace();
        	return Result.resultError(e);
		}
    }
    
    
    
    
    /**
     * 通过ID获取对象
     *
     * @param workerId
     * @return
     */
	
	 @GetMapping(value = "eHrWorkerList.html/{workerId}") 
	 public Result getById(@PathVariable String workerId) {
		 
		 return Result.resultOk(eHrWorkerService.findByWorkerId(workerId)); 
	 }
	 
    
    /**
     * 修改
     *
     * @return
     */
	 @PutMapping("add.html") public Result modify(@RequestBody EHrWorker eHrWorker) { 
	     
		 eHrWorkerService.update(eHrWorker);
         return Result.resultOk(eHrWorker.getWorkerId());
         }
	 
	 
	 
	 
	/* 部门下拉框 */
	 
	 @GetMapping("selectDept")
		public List<Map<Object,String>>  getRequestMethod(){
			return eHrWorkerService.selectDept();
		}
	 
	/* 学历下拉框 */
	 @GetMapping("selectXl")
	    public List<SelectVo> selectXl(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
		    return eHrWorkerService.getXl(trueText, falseText);
	 }
	 
	 /* 员工类型下拉框 */
	 @GetMapping("selectYglx")
	    public List<SelectVo> selectYglx(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
		    return eHrWorkerService.getYglx(trueText, falseText);
	 }
	 
	 /* 工作岗位下拉框 */
	 @GetMapping("selectGzgw/{corpId}")
	    public List<SelectVo> selectGzgw(@PathVariable(value = "corpId") String corpId){
		    return eHrWorkerService.getGzgw(corpId);
	 }
	 
	 
	 /* 工作职务下拉框 */
	 @GetMapping("selectGzzw/{corpId}")
	    public List<SelectVo> selectGzzw(@PathVariable(value = "corpId") String corpId){
		    return eHrWorkerService.getGzzw(corpId);
	 }
	 
	 /* 工作保险下拉框 */
	 @GetMapping("selectGzbx")
	    public List<SelectVo> selectGzbx(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
		    return eHrWorkerService.getGzbx(trueText, falseText);
	 }



	 /* 根据部门获取人员下拉框 */
    @GetMapping("/getDeptWorker/{deptId}")
    public List<SelectVo> getDeptWorker(@PathVariable String deptId ) {
        return eHrWorkerService.getDeptWorker(deptId);
    }




	/* 公司下拉框 */
	@GetMapping("selectCorp")
	public List<SelectVo> selectCorp(){
		return eHrWorkerService.getCorp();
	}

	@PutMapping("savedata")
	public Result saveData(@RequestBody EHrWorkerVo vo) throws Exception {
		return eHrWorkerService.saveData(EHrWorkerVo.initEHrWorkerVo(vo));
	}
	@PostMapping("savedata")
	public Result updateData(@RequestBody EHrWorkerVo vo) throws Exception {
		return eHrWorkerService.updateData(EHrWorkerVo.initEHrWorkerVo(vo));
	}
	@PostMapping("/getFamilyList")
	public Result getFamilyList(BaseDto baseDto) throws  Exception{
		return eHrWorkerService.getFamilyList(baseDto).getResult();
	}
  
}
