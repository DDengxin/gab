package com.tengzhi.business.sale.saleProduct.saleFinish.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.saleProduct.saleFinish.service.SaleFinishService;

@RestController
@RequestMapping("/sale/saleProduct/saleFinish")
public class SaleFinishController {
	
	@Autowired
	private SaleFinishService saleFinishService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	
	/**
	 * 	查询数据列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "list.html")
	public   Map<String, Object> getContractDetailed(BaseDto baseDto) throws IOException {
		return saleFinishService.getContractDetailed(baseDto);
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
		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleFinish/%s",servletPath)));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("paramType",paramType);
		mv.addObject("paramMod",paramMod);
		//是否定向
		mv.addObject("orient",true);
		return mv;
	}	
    
    @PutMapping(value = "/getFlag/{htMo}/{flag}/{type}")
	public Result getFlag(@PathVariable(value = "htMo") String htMo,@PathVariable(value = "flag") String flag,@PathVariable(value = "type") String type) throws Exception {
		return saleFinishService.getFlag(htMo,flag,type);
	}
    /**
     *  核销
     * @param htNo
     * @return
     * @throws Exception
     */
    @PutMapping(value = "hx/{htNo}/{htMo}/{type}")
    public Result hx(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "htMo") String htMo,@PathVariable(value = "type") String type) throws Exception {
        return saleFinishService.hx(htNo,htMo,type);
    }
    
    @PutMapping(value = "qxhx/{htNo}/{htMo}/{type}")
    public Result qxhx(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "htMo") String htMo,@PathVariable(value = "type") String type) throws Exception {
        return saleFinishService.qxhx(htNo,htMo,type);
    }
}
