package com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.service.SaleDeliveryNoticeService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;

@RestController
@RequestMapping("/sale/saleProduct/saleDeliveryNotice")
public class SaleDeliveryNoticeController {

	@Autowired
	private SaleDeliveryNoticeService saleDeliveryNoticeService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value = "rq1",required = false) String rq1,@RequestParam(value = "rq2",required = false) String rq2,@RequestParam(value = "fhtype",required = false) String fhtype) {
		mv = saleDeliveryNoticeService.table(mv, rq1,rq2,fhtype);
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
	@GetMapping(value = {"{paramType}/{paramMod}/list.html"})
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod, 
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleDeliveryNotice/%s",servletPath)));
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
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return saleDeliveryNoticeService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return saleDeliveryNoticeService.getSrchBottomList(baseDto).getResult();
	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
    	return  saleDeliveryNoticeService.save(ECkDeliveryNoticeVo.initECkDeliveryNoticeVo(vo));
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
    	return saleDeliveryNoticeService.update(ECkDeliveryNoticeVo.initECkDeliveryNoticeVo(vo));
         
    }
    
    
    @GetMapping(value = "getById/{fhNote}")
    public Result getById(@PathVariable(value = "fhNote") String fhNote) {
        return Result.resultOk(saleDeliveryNoticeService.findByInNote(fhNote));
    }
    
    @DeleteMapping(value = "delete/{fhNote}")
    public Result delete(@PathVariable(value = "fhNote") String fhNote) throws Exception {
    	saleDeliveryNoticeService.deleteByNote(fhNote);
        return Result.resultOk();
    }
	@PutMapping(value = "/confirm/{fhNote}")
	public Result ok(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return saleDeliveryNoticeService.setFlag(fhNote,"确认");
	}
	@PutMapping(value = "/cancel/{fhNote}")
	public Result no(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return saleDeliveryNoticeService.setFlag(fhNote,"登记");
	}
	
	@PutMapping(value = "/getFlag/{fhNote}/{flag}")
	public Result ok(@PathVariable(value = "fhNote") String fhNote,@PathVariable(value = "flag") String flag) throws Exception {
		return saleDeliveryNoticeService.getFlag(fhNote, flag);
	}
	
	@PutMapping(value = "/getPrintFlag/{rq1}/{rq2}/{fhtype}")
	public Result getPrintFlag(@PathVariable(value = "rq1") String rq1,@PathVariable(value = "rq2") String rq2,@PathVariable(value = "fhtype") String fhtype) throws Exception {
		return saleDeliveryNoticeService.getPrintFlag(rq1, rq2,fhtype);
	}
}
