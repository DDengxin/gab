package com.tengzhi.business.production.subcontract.wwjs.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseSettle.service.PurchaseSettleService;
import com.tengzhi.business.cg.yw.purchaseSettle.vo.ECwYsyfVo;
import com.tengzhi.business.production.subcontract.wwjs.service.WwjsService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;


@RestController
@RequestMapping("/production/subcontract/wwjs")
public class WwjsControler {

	@Autowired
	private WwjsService wwjsService;
	
	@Autowired
	private SysParamService sysParamService;
	

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

//	/**
//	    * 区分模块的参数入口路径
//	* @param paramMod
//	* @param paramType
//	* @param request
//	* @param mv
//	* @return
//	*/
//	@GetMapping(value = {"/list.html"})
//	public ModelAndView toPage(
//			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
//	  String servletPath = request.getServletPath();
//	  servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
//	  mv.setView(new RedirectView(String.format("/production/subcontract/wwjs/%s",servletPath)));
//	  request.getParameterMap().forEach((key,value) ->{
//	      mv.addObject(key,value);
//	  });
////	  mv.addObject("cgStype",cgStype);
////	  SysParams sysParams=sysParamService.findByParameterId(cgStype, "产品大类");
////      mv.addObject("cgName",sysParams.getParamName());
//	  //是否定向
//	  mv.addObject("orient",true);
//	  return mv;
//	}
	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return wwjsService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return wwjsService.getSrchBottomList(baseDto).getResult();
	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result js(@RequestBody ECwYsyfVo vo) throws IOException {
        return wwjsService.Js(ECwYsyfVo.initECwYsyfVo(vo));
    }
    
    
    @PutMapping(value = "/qxJs")
    public Result qxJs(@RequestBody BaseDto baseDto) throws IOException {
    	Map<String, String> map = baseDto.getParamsMap();
        return wwjsService.qxJs(String.valueOf(map.get("inNote")));
    }
    
    @PutMapping(value = "/getJsFlag")
	public Result getJsFlag(@RequestBody BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return wwjsService.getFlag(String.valueOf(map.get("inNote")),"确认");
	}
	
	@PutMapping(value = "/getQxJsFlag")
	public Result getQxJsFlag(@RequestBody BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return wwjsService.getFlag(String.valueOf(map.get("inNote")),"结算");
	}
	
	@PutMapping(value = "/setFlag")
	public Result setFlag(@RequestBody BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return wwjsService.setFlag(String.valueOf(map.get("inNote")),String.valueOf(map.get("flag")));
	}
    
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getBottomList")
	public Result getBottomList(BaseDto baseDto) throws IOException {
		return wwjsService.getBottomList(baseDto).getResult();
	}
    
}
