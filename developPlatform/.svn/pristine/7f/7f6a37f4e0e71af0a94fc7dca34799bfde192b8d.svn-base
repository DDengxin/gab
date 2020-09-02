package com.tengzhi.business.sale.saleProduct.saleInvoice.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.invoice.service.invoiceService;
import com.tengzhi.business.finance.invoice.vo.invoiceVo;

@RestController
@RequestMapping("/sale/saleProduct/saleInvoice")
public class SaleInvoiceController {

	
	/*
	 * @Autowired private invoiceService invoiceService;
	 */
	 
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	/**
	* 区分模块的参数入口路径
	* @param paramMod
	* @param paramType
	* @param request
	* @param mv
	* @return
	*/
	@GetMapping(value = {"{fpCgtype}/{cwStype}/{fpDtype}/list.html"})
	public ModelAndView toPage(@PathVariable String fpCgtype, @PathVariable String cwStype, @PathVariable String fpDtype,
		HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleInvoice/%s",servletPath)));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("fpCgtype",fpCgtype);
		mv.addObject("cwStype",cwStype);
		mv.addObject("fpDtype",fpDtype);
		//是否定向
		mv.addObject("orient",true);
		return mv;
	}
	/*
	 * @GetMapping(value = "list.html") public Result getList(BaseDto baseDto)
	 * throws IOException, ParseException { return
	 * saleInvoiceService.findAll(baseDto).getResult(); }
	 */
	
	/*
	 * @PostMapping(value = "addInvoiceList") public Result getAddList(BaseDto
	 * baseDto) throws IOException, ParseException { return
	 * saleInvoiceService.findAllByAdd(baseDto).getResult(); }
	 * 
	 * @PostMapping(value = "add.html") public Result add(@RequestBody invoiceVo vo)
	 * throws IOException, Exception { return saleInvoiceService.save(vo); }
	 * 
	 * @PutMapping(value = "add.html") public Result modify(@RequestBody invoiceVo
	 * vo) throws IOException, Exception {
	 * 
	 * return saleInvoiceService.update(vo); }
	 * 
	 * @DeleteMapping(value = "delete/{fpNote}") public Result
	 * delete(@PathVariable(value = "fpNote") String fpNote) {
	 * saleInvoiceService.deleteByNote(fpNote); return Result.resultOk(); }
	 */
}
