package com.tengzhi.business.ck.yw.warehouseInfo.controller;

import java.io.IOException;
import java.util.List;
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
import com.tengzhi.business.ck.yw.warehouseInfo.service.impl.WarehouseInfoServiceImpl;
import com.tengzhi.business.resouces.vo.SelectVo;

@RestController
@RequestMapping("/ck/yw/warehouseInfo")
public class WarehouseInfoController {

	@Autowired
	private WarehouseInfoServiceImpl warehouseInfoServiceImpl;
	
	@GetMapping(value = "/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	
	/**
	 * 
	 * 
	 * @param paramType类型(CP,YL,WL)
	 * @param paramMod              模块
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "{paramType}/{paramMod}/inlist.html" })
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,
			HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/ck/yw/warehouseInfo/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("paramType", paramType);
		mv.addObject("paramMod", paramMod);
		// 是否定向
		mv.addObject("orient", true);
		return mv;
	}
	
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return warehouseInfoServiceImpl.getSrchTopList(baseDto).getResult();
	}
	
	/**
	 * 
	 * 
	 * @param paramType类型(CP,YL,WL)
	 * @param paramMod              模块
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "{paramType}/{paramMod}/outlist.html" })
	public ModelAndView toOutPage(@PathVariable String paramType, @PathVariable String paramMod,
			HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/ck/yw/warehouseInfo/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("paramType", paramType);
		mv.addObject("paramMod", paramMod);
		// 是否定向
		mv.addObject("orient", true);
		return mv;
	}
	
	@PostMapping(value = "/getSrchOutList")
	public Result getSrchOutList(BaseDto baseDto) throws IOException {
		return warehouseInfoServiceImpl.getSrchOutList(baseDto).getResult();
	}
	
	@GetMapping(value = { "{paramType}/{paramMod}/inoutlist.html" })
	public ModelAndView toInOutPage(@PathVariable String paramType, @PathVariable String paramMod,
			HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/ck/yw/warehouseInfo/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("paramType", paramType);
		mv.addObject("paramMod", paramMod);
		// 是否定向
		mv.addObject("orient", true);
		return mv;
	}
	@PostMapping(value = "inoutlist.html")
	public Result getInOutList(BaseDto baseDto) throws IOException {
		return warehouseInfoServiceImpl.getInOutList(baseDto).getResult();
	}
	
	@GetMapping(value = { "{paramType}/{paramMod}/inoutjelist.html" })
	public ModelAndView toInOutJePage(@PathVariable String paramType, @PathVariable String paramMod,
			HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/ck/yw/warehouseInfo/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("paramType", paramType);
		mv.addObject("paramMod", paramMod);
		// 是否定向
		mv.addObject("orient", true);
		return mv;
	}
	@PostMapping(value = "inoutjelist.html")
	public Result getInOutJeList(BaseDto baseDto) throws IOException {
		return warehouseInfoServiceImpl.getInOutJeList(baseDto).getResult();
	}
	
	
	@GetMapping("/getActSelectList/{paramType}/{paramValue2}/{paramValue3}")
    public List<SelectVo> getActSelectList(@PathVariable(name = "paramType") String paramType, @PathVariable(name = "paramValue2") String paramValue2, @PathVariable(name = "paramValue3") String paramValue3) {
        return warehouseInfoServiceImpl.getActSelected(paramType, paramValue2,paramValue3);
    } 
	
	@PostMapping(value = "/exportExcelIn")
	public Result exportExcelIn(HttpServletResponse response, HttpServletRequest request ) throws IOException {
		 warehouseInfoServiceImpl.exportExcelIn(response, request );
		 return Result.resultOk();
	}
	@PostMapping(value = "/exportExcelOut")
	public Result exportExcelOut(HttpServletResponse response, HttpServletRequest request ) throws IOException {
		 warehouseInfoServiceImpl.exportExcelOut(response, request );
		 return Result.resultOk();
	}
	@PostMapping(value = "/exportExcelInOut")
	public Result exportExcelInOut(HttpServletResponse response, HttpServletRequest request ) throws IOException {
		 warehouseInfoServiceImpl.exportExcelInOut(response, request );
		 return Result.resultOk();
	}
	@PostMapping(value = "/exportExcelInOutJe")
	public Result exportExcelInOutJe(HttpServletResponse response, HttpServletRequest request ) throws IOException {
		 warehouseInfoServiceImpl.exportExcelInOutJe(response, request );
		 return Result.resultOk();
	}
}
