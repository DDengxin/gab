package com.tengzhi.business.mSbJt.wx.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mSbJt.by.model.MSbSbwb;
import com.tengzhi.business.mSbJt.by.service.MSbSbwbService;
import com.tengzhi.business.mSbJt.by.service.impl.MSbSbwbServiceImpl;
import com.tengzhi.business.mSbJt.wx.model.MSbSbwbb;
import com.tengzhi.business.mSbJt.wx.service.MSbSbwbServicee;
import com.tengzhi.business.mSbJt.wx.service.impl.MSbSbwbServiceImpll;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.personnel.eHrWorker.service.eHrWorkerService;
import com.tengzhi.business.resouces.vo.SelectVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mSbJt/wx/")
public class MSbSbwbControlerr {
    @Autowired
    private MSbSbwbServicee mSbSbwbService;
    @Autowired
    private MSbSbwbServiceImpll mSbSbwbServiceImpl;
    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "mSbSbwbList.html")
    public Result getList(BaseDto baseDto) throws IOException {
            return mSbSbwbService.findAll(baseDto).getResult();
    }
    /**
      * 通过ID获取对象
   * @param roleId
   * @return
   */

    @GetMapping(value = "mSbSbwbList.html/{sbwbNote}") 
    public Result getById(@PathVariable String sbwbNote) {
	 
	 return Result.resultOk(mSbSbwbService.findBySbwbNote(sbwbNote)); 
}
    
    /**
          * 新增
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody MSbSbwbb mSbSbwb) throws Exception{
    	mSbSbwb = mSbSbwbService.save(mSbSbwb);
        return Result.resultOk(mSbSbwb.getSbwbNote());
    }
    
    
    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping(value = "mSbSbwbList.html/{sbwbNote}")
    public Result delete(@PathVariable(value = "sbwbNote") String sbwbNote) {
    	mSbSbwbService.deleteBySbwbNote(sbwbNote);
        return Result.resultOk();
    }
    
    /**
          * 修改
     *
     * @return
     */
	 @PutMapping("add.html") public Result modify(@RequestBody MSbSbwbb mSbSbwb) { 
		 mSbSbwbService.update(mSbSbwb);
         return Result.resultOk(mSbSbwb.getSbwbNote());
	 }
	 
	 @GetMapping("/getSbwbDtype")
	    public List<SelectVo> getSbwbDtype(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
	    	return mSbSbwbService.getSbwbDtype(trueText, falseText);
	    }
	 @GetMapping("/getSbwbXtype")
	 public List<SelectVo> getSbwbXtype(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getSbwbXtype(trueText, falseText);
	 }
	 @GetMapping("/getSbwbDept")
	 public List<SelectVo> getSbwbDept(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getSbwbDept(trueText, falseText);
	 }
	 @GetMapping("/getSbwbSman")
	 public List<SelectVo> getSbwbSman(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getSbwbSman(trueText, falseText);
	 }
	 @GetMapping("/getSbwbSbid")
	 public List<SelectVo> getSbwbSbid(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getSbwbSbid(trueText, falseText);
	 }

	 /**
		 * 确认
		 **/
		@GetMapping("sure")
		public Result modify(String sbwbNote) {
			if (mSbSbwbServiceImpl.sure(sbwbNote)) {
				return Result.resultOk(sbwbNote);
			} else {
				return Result.error("该条记录不是登记状态");
			}
		}
		/**
		 * 接单
		 **/
		@GetMapping("okk")
		public Result modify1(String sbwbNote) {
			if (mSbSbwbServiceImpl.okk(sbwbNote)) {
				return Result.resultOk(sbwbNote);
			} else {
				return Result.error("该条记录不是确认状态");
			}
		}
		/**
		 * 完成
		 **/
		@GetMapping("conf")
		public Result modify2(String sbwbNote) {
			if (mSbSbwbServiceImpl.conf(sbwbNote)) {
				return Result.resultOk(sbwbNote);
			} else {
				return Result.error("该条记录不是接单状态");
			}
		}
  
}
