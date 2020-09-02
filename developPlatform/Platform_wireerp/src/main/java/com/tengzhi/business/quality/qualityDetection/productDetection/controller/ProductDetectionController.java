package com.tengzhi.business.quality.qualityDetection.productDetection.controller;

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
import com.tengzhi.business.quality.qualityDetection.productDetection.service.ProductDetectionService;
import com.tengzhi.business.quality.qualityDetection.productDetection.service.vo.EPzJyListJcVo;

@RestController
@RequestMapping("/quality/qualityDetection/productDetection")
public class ProductDetectionController {

	@Autowired
	private ProductDetectionService productDetectionService;
	
	@GetMapping(value="/*.html")
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
	@GetMapping(value = {"{paramType}/{paramMod}/list.html"})
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod, 
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/quality/qualityDetection/productDetection/%s",servletPath)));
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
	@PostMapping(value = "list.html")
	public Result getList(BaseDto baseDto) throws IOException {
		return productDetectionService.getSrchTopList(baseDto);
	}
	@GetMapping(value = "getByNote/{note}")
	public Result getById(@PathVariable(value = "note") String note) {
		return Result.resultOk(productDetectionService.getByNote(note));
	}
	
	@PostMapping(value = "getDetailedList")
	public Map<String, Object>  getAddDetailed(BaseDto baseDto) throws IOException {
		return productDetectionService.getAddDetailed(baseDto);
	} 
	
	@PostMapping(value = "add.html")
    public Result add(@RequestBody EPzJyListJcVo vo) throws Exception {
        return productDetectionService.save(EPzJyListJcVo.initEPzJyListJcVo(vo));
    }
	
	@PutMapping(value = "add.html")
    public Result modify(@RequestBody EPzJyListJcVo vo) throws Exception {
		productDetectionService.update(EPzJyListJcVo.initEPzJyListJcVo(vo));
        return Result.resultOkMsg("修改成功");
    }	
	
	@PostMapping("all/{parentId}")
	public Result paramsA(@PathVariable String parentId){
		return Result.resultOk(productDetectionService.paramsAll(parentId));
	}
	
	@GetMapping("getNoteByPch/{pch}/{pchType}")
	public Result getNoteByPch(@PathVariable String pch,@PathVariable String pchType){
		return Result.resultOk(productDetectionService.getNoteByPch(pch,pchType));
	}
}
