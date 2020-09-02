package com.tengzhi.business.finance.gathering.controller;

import java.io.IOException;
import java.text.ParseException;

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
import com.tengzhi.business.finance.gathering.service.gatheringService;
import com.tengzhi.business.finance.payment.model.payment;

@RestController
@RequestMapping("/finance/gathering/")
public class gatheringController {
	
	@Autowired
	private gatheringService gatheringService;
	
	
	@GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "gatheringList.html")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
            return gatheringService.findAll(baseDto).getResult();
    }
    
    
    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody payment payment) throws Exception{
    	payment = gatheringService.save(payment);
        return Result.resultOk(payment.getSfkId());
    }
    
    
    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping(value = "gatheringList.html/{sfkId}")
    public Result delete(@PathVariable(value = "sfkId") String sfkId) {
    	gatheringService.deleteByPaymentId(sfkId);
        return Result.resultOk();
    }
    
    /**
     *确认操作修改状态
     *
     *
     * @return
     */
    @PutMapping(value = "ok/{sfkId}")
    public Result ok(@PathVariable(value = "sfkId") String sfkId) {
    	gatheringService.ok(sfkId);
        return Result.resultOk();
    }
    
    
    
    /**
     *确认月结审核修改状态
     *
     *
     * @return
     */
    @PutMapping(value = "yok/{sfkId}")
    public Result yok(@PathVariable(value = "sfkId") String sfkId) {
    	gatheringService.yok(sfkId);
        return Result.resultOk();
    }
    
    /**
     *取消操作修改状态
     *
     *
     * @return
     */
    @PutMapping(value = "qx/{sfkId}")
    public Result qx(@PathVariable(value = "sfkId") String sfkId) {
    	gatheringService.qx(sfkId);
        return Result.resultOk();
    }
    
    
    
    /**
     *取消月结审核修改状态
     *
     *
     * @return
     */
    @PutMapping(value = "yqx/{sfkId}")
    public Result yqx(@PathVariable(value = "sfkId") String sfkId) {
    	gatheringService.yqx(sfkId);
        return Result.resultOk();
    }
    
    /**
     * 通过ID获取对象
     *
     * @param fpId
     * @return
     */
	
	 @GetMapping(value = "gatheringList.html/{sfkId}") 
	 public Result getById(@PathVariable String sfkId) {
		 return Result.resultOk(gatheringService.findByPaymentId(sfkId)); 
	 }
	 
    
    /**
     * 修改
     *
     * @return
     */
	 @PutMapping("add.html") public Result modify(@RequestBody payment payment) { 
	     
		 gatheringService.update(payment);
         return Result.resultOk(payment.getSfkId());
         }

	 @PutMapping("confirm.html")
	 public Result confirmGathering(@RequestBody payment payment) throws Exception{
	    	gatheringService.confirmGathering(payment);
	        return Result.resultOk("修改成功");
	}
}
