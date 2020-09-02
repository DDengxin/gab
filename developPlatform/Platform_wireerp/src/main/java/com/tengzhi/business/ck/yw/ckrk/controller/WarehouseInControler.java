package com.tengzhi.business.ck.yw.ckrk.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.ck.yw.ckrk.service.WarehouseInService;


@RestController
@RequestMapping("/ck/yw/warehouseIn")
public class WarehouseInControler {

	@Autowired
	private WarehouseInService warehouseInService;
	
	

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value = "inNote",required = false) String inNote) {
		mv = warehouseInService.table(mv,inNote);
		return mv;
	}

	/**
	 * 区分模块的参数入口路径
	 * @param cgStype
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = {"{cgStype}/list.html"})
	public ModelAndView toPage(@PathVariable String cgStype, 
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
	  String servletPath = request.getServletPath();
	  servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
	  mv.setView(new RedirectView(String.format("/ck/yw/warehouseIn/%s",servletPath)));
	  request.getParameterMap().forEach((key,value) ->{
	      mv.addObject(key,value);
	  });
	  mv.addObject("cgStype",cgStype);
	  //是否定向
	  mv.addObject("orient",true);
	  return mv;
	}
	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return warehouseInService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return warehouseInService.getSrchBottomList(baseDto).getResult();
	}
	
	@PutMapping(value = "/getRkFlag")
	public Result getRkFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return warehouseInService.getFlag(String.valueOf(map.get("inNote")),"结算");
	}
	
	@PutMapping(value = "/getQrFlag")
	public Result getQrFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return warehouseInService.getFlag(String.valueOf(map.get("inNote")),"库审");
	}

	@PutMapping(value = "/ok/{inNote}")
	public Result ok(@PathVariable(value = "inNote") String inNote) throws Exception {
		return warehouseInService.setFlag(inNote,"库审");
	}
	@PutMapping(value = "/no/{inNote}")
	public Result no(@PathVariable(value = "inNote") String inNote) throws Exception {
		return warehouseInService.setFlag(inNote,"结算");
	}
	
    
    
    
}
