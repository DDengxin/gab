package com.tengzhi.business.cg.tj.orderStatistics.controller;

import java.io.IOException;
import java.util.List;

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
import com.tengzhi.business.cg.tj.orderStatistics.service.DdtjService;
import com.tengzhi.business.resouces.vo.SelectVo;


@RestController
@RequestMapping("/cg/tj/orderStatistics")
public class DdTjController {

	@Autowired
	private DdtjService ddtjService;

	@GetMapping(value = "/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	@GetMapping(value = "cglist.html")
	public ModelAndView toPages(@RequestParam(value="stype") String stype, ModelAndView mv) {
		mv.addObject("stype", stype);
		mv.addObject("orient", true);
		return mv;
	}

	/**
	 * 
	 * @TODO:该方法参数传递错误
	 * @param paramType 类型(CP,YL,WL)
	 * @param paramMod              模块
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "{paramType}/{paramMod}/{paraList}" })
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,
			HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/cg/tj/orderStatistics/%s", servletPath)));
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
		return ddtjService.getSrchTopList(baseDto).getResult();
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
		return ddtjService.getSrchXstjList(baseDto).getResult();
	}
	
	/**
     * 采购申请统计
     *
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/statistics")
    public Result getStatistics(BaseDto baseDto) throws IOException {
        return ddtjService.getStatistics(baseDto).getResult();
    }
	
    /**
	 * 查询数据列表
	 * 
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/getCgList")
	public Result getCgList(BaseDto baseDto) throws IOException {
		return ddtjService.getCgList(baseDto).getResult();
	}

	@GetMapping(value = "/getCpcode/{cpcodeType}/{fl}")
	public List<SelectVo> getCpcode(@PathVariable(name = "cpcodeType") String cpcodeType,@PathVariable(name = "fl") String fl) {
		return ddtjService.getCpcode(cpcodeType,fl);
	}
	
	
	
	@PostMapping(value = "/getSrchTopListExportExcel")
	public void getSrchTopListExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		ddtjService.getSrchTopListExportExcel(response, request);
	}

	@PostMapping(value = "/getSrchXstjListExpertExcel")
	public void getSrchXstjListExpertExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		ddtjService.getSrchXstjListExpertExcel(response, request);
	}
	
}
