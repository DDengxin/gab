package com.tengzhi.business.cg.yw.purchaseReceipt.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseReceipt.service.PurchaseReceiptService;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/cg/yw/purchaseFile/purchaseReceipt")
public class PurchaseReceiptControler {

	@Autowired
	private PurchaseReceiptService purchaseReceiptService;
	
	@Autowired
	private SysParamService sysParamService;

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	    * 区分模块的参数入口路径
  * @param paramMod
  * @param paramType
  * @param request
  * @param mv
  * @return
  */
 @GetMapping(value = {"{cgStype}/list.html"})
 public ModelAndView toPage(@PathVariable String cgStype, 
 		HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
     String servletPath = request.getServletPath();
     servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
     mv.setView(new RedirectView(String.format("/cg/yw/purchaseFile/purchaseReceipt/%s",servletPath)));
     request.getParameterMap().forEach((key,value) ->{
         mv.addObject(key,value);
     });
     mv.addObject("cgStype",cgStype);
     SysParams sysParams=sysParamService.findByParameterId(cgStype, "产品大类");
     mv.addObject("cgName",sysParams.getParamName());
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
		return purchaseReceiptService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return purchaseReceiptService.getSrchBottomList(baseDto).getResult();
	}

	@PostMapping(value = "/getSrchBottomListAll")
	public Result getSrchBottomListAll(BaseDto baseDto) throws IOException {
		return purchaseReceiptService.getSrchBottomListAll(baseDto).getResult();
	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody ECkInVo vo) throws Exception {
    	return  purchaseReceiptService.save(ECkInVo.initECgContractVo(vo));
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody ECkInVo vo) throws Exception {
    	return purchaseReceiptService.update(ECkInVo.initECgContractVo(vo));
         
    }
    
    
    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{inNote}")
    public Result getById(@PathVariable(value = "inNote") String inNote) {
        return Result.resultOk(purchaseReceiptService.findByInNote(inNote));
    }
    
    @DeleteMapping(value = "delete/{inNote}")
    public Result delete(@PathVariable(value = "inNote") String inNote) throws Exception {
    	purchaseReceiptService.deleteById(inNote);
        return Result.resultOk();
    }
    
	@PutMapping(value = "/getDjFlag")
	public Result getDjFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return purchaseReceiptService.getFlag(String.valueOf(map.get("inNote")),"登记");
	}
	
	@PutMapping(value = "/getQrFlag")
	public Result getQrFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return purchaseReceiptService.getFlag(String.valueOf(map.get("inNote")),"确认");
	}
	
	@PutMapping(value = "/setFlag")
	public Result setFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return purchaseReceiptService.setFlag(String.valueOf(map.get("inNote")),String.valueOf(map.get("flag")));
	}
	
    
	@PutMapping(value = "/ok/{inNote}")
	public Result ok(@PathVariable(value = "inNote") String inNote) throws Exception {
		return purchaseReceiptService.setFlag(inNote,"确认");
	}
	@PutMapping(value = "/no/{inNote}")
	public Result no(@PathVariable(value = "inNote") String inNote) throws Exception {
		return purchaseReceiptService.setFlag(inNote,"登记");
	}
	
}
