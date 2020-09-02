package com.tengzhi.business.sale.saleProduct.saleReturn.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleProduct.saleDelivery.service.SaleDeliveryService;
import com.tengzhi.business.sale.saleProduct.saleReturn.service.SaleReturnService;

@RestController
@RequestMapping("/sale/saleProduct/saleReturn")
public class SaleReturnController {

	@Autowired
	private SaleDeliveryService saleDeliveryService;
	
	@Autowired
	private SaleReturnService saleReturnService;
	
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
		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleReturn/%s",servletPath)));
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
		return saleReturnService.getList(baseDto).getResult();
	}
	@PostMapping(value = "/getDetailList")
	public Result getOutList(BaseDto baseDto) throws IOException {
		return saleReturnService.getDetailList(baseDto).getResult();
	}
	@PostMapping(value = "add.html")
    public Result add(@RequestBody ECKOutVo vo) throws Exception {
        return saleReturnService.save(ECKOutVo.initECgContractVo(vo));
    }
	@PutMapping(value = "add.html")
    public Result modify(@RequestBody ECKOutVo vo) throws Exception {
		saleReturnService.update(ECKOutVo.initECgContractVo(vo));
        return Result.resultOkMsg("修改成功");
    }	
	/**
	 * 获取订单号
	 * @param customer 客户
	 * @param complainNote 客诉号
	 * @return
	 */
	@GetMapping("/getComplainContractList/{customer}/{complainNote}")
	public List<SelectVo> getComplainContractList(@PathVariable(name = "customer") String customer,@PathVariable(name = "complainNote") String complainNote) {
		return saleReturnService.getComplainContractList(customer,complainNote);
	}
	
    @GetMapping(value = "getByNote/{outNote}")
    public Result getById(@PathVariable(value = "outNote") String outNote) {
        return Result.resultOk(saleReturnService.findHeadData(outNote));
    }
    
    @DeleteMapping(value = "delete/{htNO}")
    public Result delete(@PathVariable(value = "htNO") String htNO) throws Exception {
        return saleDeliveryService.deleteByNote(htNO);
    }
    
    @PutMapping(value = "confirm/{outNote}")
    public Result confirm(@PathVariable(value = "outNote") String outNote) throws Exception {
        return saleDeliveryService.confirm(outNote);
    }
    @PutMapping(value = "cancel/{outNote}")
    public Result cancel(@PathVariable(value = "outNote") String outNote) throws Exception {
    	
        return saleDeliveryService.cancel(outNote);
    }
    @PutMapping(value = "getFlag/{outNote}/{flag}")
    public Result getFlag(@PathVariable(value = "outNote") String outNote,@PathVariable(value = "flag") String flag)throws Exception {
        return saleDeliveryService.getFlag(outNote,flag);
    }
    @GetMapping("/getItemSelectList/{customer}/{outType}")
	public List<Map> getItemSelectList(@PathVariable(name = "customer") String customer,@PathVariable(name = "outType") String outType) {
		return saleReturnService.getItemSelectList(customer,outType);
	}
}
