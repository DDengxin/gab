package com.tengzhi.business.sale.saleStatistics.orderStatistics.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.saleStatistics.orderStatistics.service.OrderStatisticsService;

@RestController
@RequestMapping("/sale/saleStatistics/orderStatistics")
public class OrderStatisticsController {

	@Autowired
	private OrderStatisticsService orderStatisticsService;

	@GetMapping(value = "/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	@GetMapping(value = "xslist.html")
	public ModelAndView toPages(@RequestParam(value="stype") String stype, ModelAndView mv) {
		mv.addObject("stype", stype);
		mv.addObject("orient", true);
		return mv;
	}

	/**
	 * 
	 * 
	 * @param paramType 类型(CP,YL,WL)
	 * @param paramMod 模块
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "{paramType}/{paramMod}/list.html" })
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,
			HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/sale/saleStatistics/orderStatistics/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("paramType", paramType);
		mv.addObject("paramMod", paramMod);
		// 是否定向
		mv.addObject("orient", true);
		return mv;
	}

	/**
	 * 查询数据列表
	 * 
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return orderStatisticsService.getSrchTopList(baseDto).getResult();
	}
	
	
	/**
	 * 查询数据列表
	 * 
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/getSrchXstjList")
	public Result getSrchXstjList(BaseDto baseDto) throws IOException {
		return orderStatisticsService.getSrchXstjList(baseDto).getResult();
	}
	
	/**
	 * 查询数据列表
	 * 
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/getXsList")
	public Result getXsList(BaseDto baseDto) throws IOException {
		return orderStatisticsService.getXsList(baseDto).getResult();
	}
	
    @PostMapping(value = "/getCpcode")
    public Result search_option(@RequestParam String keyText,@RequestParam String cpcodeType,@RequestParam String fl,BaseDto baseDto) throws IOException {
        return orderStatisticsService.search_option(keyText,cpcodeType,fl,baseDto).getResult();
    }
	
    /**
	 * 导出订单excel
	 *
	 * @return
	 */
	@PostMapping(value = "/DD/exportExcel")
	public void ddExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		orderStatisticsService.DdExportExcel(response, request);
	}
    
	/**
	 * 导出销售excel
	 *
	 * @return
	 */
	@PostMapping(value = "/XS/exportExcel")
	public void xsExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		orderStatisticsService.XsExportExcel(response, request);
	}
	
}
