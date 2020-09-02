package com.tengzhi.business.finance.generalLedger.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.generalLedger.service.GeneralLedgerService;


@RestController
@RequestMapping("/finance/generalLedger/")
public class GeneralLedgerController {
	
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
	
	
	/**
	 * 
	 * 
	 * @param paramType类型(CP,YL,WL)
	 * @param paramMod 模块
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = {"{paramType}/{paramMod}/generalLedgerList.html"})
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod, 
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/finance/generalLedger/%s",servletPath)));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("paramType",paramType);
		mv.addObject("paramMod",paramMod);
		//是否定向
		mv.addObject("orient",true);
		return mv;
	}
	
	/**
	 * 	查询数据列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "generalLedgerList.html")
	public  Map<String, Object> getDeliveryList(BaseDto baseDto) throws IOException {
		return generalLedgerService.getDataList(baseDto);
	}
	 
	/**
	 * 导出excel
	 */
	@PostMapping(value = "exportExcel")
	public void xsExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		generalLedgerService.exportExcel(response, request);
	}

}
