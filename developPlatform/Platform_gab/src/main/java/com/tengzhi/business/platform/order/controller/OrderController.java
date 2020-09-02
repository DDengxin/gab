package com.tengzhi.business.platform.order.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/platform/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
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
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return orderService.findAll(baseDto).getResult();
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
	@GetMapping(value = {"{paramType}/{paramMod}/{accessType}/list.html"})
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,@PathVariable String accessType,HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/platform/order/%s",servletPath)));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("paramType",paramType);
		mv.addObject("paramMod",paramMod);
		mv.addObject("accessType",accessType);
		return mv;
	}	
	
	/**
	 * 查询数据列表
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return orderService.getSrchBottomList(baseDto).getResult();
	}

    @GetMapping(value = "getByNote/{htNO}")
    public Result getById(@PathVariable(value = "htNO") String htNO) {
        return Result.resultOk(orderService.findByNote(htNO));
    }
 
//    @PostMapping(value = "/getContractDetailed")
//	public Result getContractDetailed(BaseDto baseDto) throws IOException {
//		return saleContractService.getContractDetailed(baseDto).getResult();
//	}
//    
//    @PutMapping(value = "/getFlag/{htNo}/{flag}")
//	public Result getFlag(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws Exception {
//		return saleContractService.getFlag(htNo,flag);
//	}
//    
//    /**
//     * 
//     * @param htNo
//     * @param flag
//     * @return 多选是判断状态
//     * @throws IOException
//     * @throws Exception
//     */
//    @PutMapping(value = "/getFlags/{htNo}/{flag}")
//   	public Result getFlags(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws IOException, Exception {
//   		return saleContractService.getFlags(htNo,flag);
//   	}
//    
//	/**
//	 * 
//	 * 
//	 * @param paramType类型(CP,YL,WL)
//	 * @param paramMod 模块
//	 * @param request
//	 * @param rt
//	 * @param mv
//	 * @return
//	 */
//	@GetMapping(value = {"{paramType}/{paramMod}/scddlist.html"})
//	public ModelAndView ddtj(@PathVariable String paramType, @PathVariable String paramMod, 
//			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
//		String servletPath = request.getServletPath();
//		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
//		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleContract/%s",servletPath)));
//		request.getParameterMap().forEach((key,value) ->{
//		    mv.addObject(key,value);
//		});
//		mv.addObject("paramType",paramType);
//		mv.addObject("paramMod",paramMod);
//		//是否定向
//		mv.addObject("orient",true);
//		return mv;
//	}
//	
//	/**
//	 * 	查询数据列表
//	 * @param baseDto
//	 * @return
//	 * @throws IOException
//	 */
//	@PostMapping(value = "/scddlist.html")
//	public Result getScddList(BaseDto baseDto) throws IOException {
//		return saleContractService.getScddList(baseDto).getResult();
//	}
//	
//    @GetMapping(value = "/getBySplitId/{htMo}")
//    public Result getBySplitId(@PathVariable(value = "htMo") String htMo) {
//        return Result.resultOk(saleContractService.getBySplitId(htMo));
//    }
//    
//    @GetMapping(value = "/getByHpId/{htMo}")
//    public Result getByHpId(@PathVariable(value = "htMo") String htMo) {
//        return Result.resultOk(saleContractService.getByHpId(htMo));
//    }
//    @PostMapping(value = "/exportExcelScdd")
//	public Result exportExcelScdd(HttpServletResponse response, HttpServletRequest request ) throws IOException {
//    	saleContractService.exportExcelScdd(response, request );
//		 return Result.resultOk();
//	}
}
