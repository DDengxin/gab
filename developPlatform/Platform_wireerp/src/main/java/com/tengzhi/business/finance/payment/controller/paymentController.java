package com.tengzhi.business.finance.payment.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.payment.model.payment;
import com.tengzhi.business.finance.payment.service.paymentService;

@RestController
@RequestMapping("/finance/payment/")
public class paymentController {
	
	@Autowired
	private paymentService paymentService;
	
	
	@GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

	@GetMapping("table.html")
    public ModelAndView table(ModelAndView mv,@RequestParam(value="sfkId") String sfkId) {
		mv = paymentService.table(mv,sfkId);
        return mv;
    }
	
    /**
     * 查询数据列表
     *
     * @return
     */
    //@GetMapping("/staffRecordList. html")
    @PostMapping(value = "paymentList.html")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
    	return paymentService.findAll(baseDto).getResult();
    }
    
    
    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody payment payment) throws Exception{
    	payment = paymentService.save(payment);
        return Result.resultOk(payment.getSfkId());
    }
    
    
    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping(value = "paymentList.html/{sfkId}")
    public Result delete(@PathVariable(value = "sfkId") String sfkId) {
    	paymentService.deleteByPaymentId(sfkId);
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
    	paymentService.ok(sfkId);
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
    	paymentService.yok(sfkId);
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
    	paymentService.qx(sfkId);
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
    	paymentService.yqx(sfkId);
        return Result.resultOk();
    }
  
    
    /**
     * 通过ID获取对象
     *
     * @param fpId
     * @return
     */
	
	 @GetMapping(value = "paymentList.html/{sfkId}") 
	 public Result getById(@PathVariable String sfkId) {
		 return Result.resultOk(paymentService.findByPaymentId(sfkId)); 
	 }
	 
    
    /**
     * 修改
     *
     * @return
     */
	@PutMapping("add.html")
	public Result modify(@RequestBody payment payment) { 
		paymentService.update(payment);
		return Result.resultOk(payment.getSfkId());
	}
	 
	/**
	 * 导出excel
	 */
	@PostMapping(value = "exportExcel")
	public void xsExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		paymentService.exportExcel(response, request);
	}

	@GetMapping(value = "getByNote/{note}") 
	public Result getByNote(@PathVariable String note) {
		 return Result.resultOk(paymentService.getByNote(note)); 
	}
}
