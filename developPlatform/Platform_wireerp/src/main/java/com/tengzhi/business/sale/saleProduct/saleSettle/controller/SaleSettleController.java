package com.tengzhi.business.sale.saleProduct.saleSettle.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;
import com.tengzhi.business.cg.yw.purchaseSettle.vo.ECwYsyfVo;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleProduct.saleDelivery.service.SaleDeliveryService;
import com.tengzhi.business.sale.saleProduct.saleSettle.service.SaleSettleService;

@RestController
@RequestMapping("/sale/saleProduct/saleSettle")
public class SaleSettleController {

	@Autowired
	private SaleSettleService saleSettleService;
	
	@Autowired
	private SaleDeliveryService saleDeliveryService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value = "outNote",required = false) String outNote,@RequestParam(value = "addressNote",required = false) String addressNote) {
		mv = saleSettleService.table(mv, outNote,addressNote);
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
		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleSettle/%s",servletPath)));
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
	public Result getDeliveryList(BaseDto baseDto) throws IOException {
		return saleSettleService.getSrchTopList(baseDto).getResult();
	}
	
	@GetMapping(value = "getByNote/{note}")
    public Result getByNote(@PathVariable(value = "note") String note) throws Exception {
		
        return saleSettleService.getByNote(note);
    }
	@PostMapping(value = "/getSrchBottomList")
	public  Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return saleSettleService.getSrchBottomList(baseDto).getResult();
	}
	@PostMapping(value = "/getSettleList")
	public  Result getSettleList(BaseDto baseDto) throws IOException {
		return saleSettleService.getSettleList(baseDto).getResult();
	}
	
	@PutMapping(value = "add.html")
	public Result add(@RequestBody ECwYsyfVo vo) throws Exception {
        return saleSettleService.settle(ECwYsyfVo.initECwYsyfVo(vo));
    }
    
	@PutMapping(value = "cancel/{note}")
    public Result cancel(@PathVariable(value = "note") String note) throws Exception {
    	
        return saleSettleService.cancel(note);
    }
	@PutMapping(value = "getFlag/{outNote}/{flag}")
    public Result getFlag(@PathVariable(value = "outNote") String outNote,@PathVariable(value = "flag") String flag)throws Exception {
        return saleDeliveryService.getFlag(outNote,flag);
    }
}
