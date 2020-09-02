package com.tengzhi.business.mSbJt.by.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mSbJt.by.model.MSbSbwb;
import com.tengzhi.business.mSbJt.by.service.MSbSbwbService;
import com.tengzhi.business.mSbJt.by.service.impl.MSbSbwbServiceImpl;
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
@RequestMapping("/mSbJt/by/")
public class MSbSbwbControler {
    @Autowired
    private MSbSbwbService mSbSbwbService;
    @Autowired
    private MSbSbwbServiceImpl mSbSbwbServiceImpl;
    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

   /**
        * 查询数据
    * @param baseDto
    * @return
    * @throws IOException
    */
    @PostMapping(value = "/getSrchButonList")
	public Result getSrchBotonList(BaseDto baseDto) throws IOException {
		return mSbSbwbService.getSrchButonList(baseDto).getResult();
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
    public Result add(@RequestBody MSbSbwb mSbSbwb) throws Exception{
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
	 @PutMapping("add.html") public Result modify(@RequestBody MSbSbwb mSbSbwb) { 
	     
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
	 @GetMapping("/getSbwbType")
	 public List<SelectVo> getSbwbType(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getSbwbType(trueText, falseText);
	 }
	 @GetMapping("/getSbwbKtype")
	 public List<SelectVo> getSbwbKtype(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getSbwbKtype(trueText, falseText);
	 }
	 @GetMapping("/getDataMan")
	 public List<SelectVo> getDataMan(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getDataMan(trueText, falseText);
	 }
	 @GetMapping("/getSbwbDman")
	 public List<SelectVo> getSbwbDman(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getSbwbDman(trueText, falseText);
	 }
	 @GetMapping("/getJjcd")
	 public List<SelectVo> getJjcd(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
		 return mSbSbwbService.getJjcd(trueText, falseText);
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
		 * 取消确认
		 **/
		@GetMapping("cancel")
		public Result modiff(String sbwbNote) {
			if (mSbSbwbServiceImpl.cancel(sbwbNote)) {
				return Result.resultOk(sbwbNote);
			} else {
				return Result.error("该条记录不是确认状态");
			}
		}
		/**
		 * 接单
		 **/
		@GetMapping("okk")
		public Result mod(String sbwbNote) {
			if (mSbSbwbServiceImpl.okk(sbwbNote)) {
				return Result.resultOk(sbwbNote);
			} else {
				return Result.error("该条记录不是确认状态");
			}
		}
		/**
		 * 安排
		 **/
		@PutMapping("ook")
		public Result modi(@RequestBody MSbSbwb mSbSbwb) {
			if (mSbSbwbServiceImpl.ook(mSbSbwb)) {
				return Result.resultOk(mSbSbwb);
			} else {
				return Result.error("该条记录不是接单状态");
			}
		}
		
		/**
		 * 完成
		 **/
		@PutMapping("conf")
		public Result modif(@RequestBody MSbSbwb mSbSbwb) {
			if (mSbSbwbServiceImpl.conf(mSbSbwb)) {
				return Result.resultOk(mSbSbwb);
			} else {
				return Result.error("该条记录不是安排状态");
			}
		}
		 
		@PutMapping(value = "/getAPFlag")
		public Result getAPFlag(@RequestBody BaseDto baseDto) throws IOException {
			Map<String, String> map = baseDto.getParamsMap();
			return mSbSbwbService.getFlag(String.valueOf(map.get("sbwbNote")),"接单");
		}
		@PutMapping(value = "/getAAFlag")
		public Result getAAFlag(@RequestBody BaseDto baseDto) throws IOException {
			Map<String, String> map = baseDto.getParamsMap();
			return mSbSbwbService.getFlag(String.valueOf(map.get("sbwbNote")),"安排");
		}
		
		
				
		
		
		
   
		 
}
