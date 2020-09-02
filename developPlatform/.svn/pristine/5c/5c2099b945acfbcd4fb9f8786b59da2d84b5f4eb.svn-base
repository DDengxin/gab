package com.tengzhi.business.cg.yw.purchaseReceiptNotice.controller;

import java.io.IOException;
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
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.service.PurchaseReceiptNoticeService;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.vo.ECkReceivingNoticeVo;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;


@RestController
@RequestMapping("/cg/yw/purchaseFile/purchaseReceiptNotice")
public class PurchaseReceiptNoticeControler {

	@Autowired
	private PurchaseReceiptNoticeService purchaseReceiptNoticeService;
	
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
 @GetMapping(value = {"{paramType}/list.html"})
 public ModelAndView toPage(@PathVariable String paramType, 
 		HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
     String servletPath = request.getServletPath();
     servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
     mv.setView(new RedirectView(String.format("/cg/yw/purchaseFile/purchaseReceiptNotice/%s",servletPath)));
     request.getParameterMap().forEach((key,value) ->{
         mv.addObject(key,value);
     });
     mv.addObject("paramType",paramType);
     SysParams sysParams=sysParamService.findByParameterId(paramType, "产品大类");
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
		return purchaseReceiptNoticeService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return purchaseReceiptNoticeService.getSrchBottomList(baseDto).getResult();
	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody ECkReceivingNoticeVo vo) throws Exception {
    	return  purchaseReceiptNoticeService.save(ECkReceivingNoticeVo.initECkReceivingNoticeVo(vo));
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody ECkReceivingNoticeVo vo) throws Exception {
    	return purchaseReceiptNoticeService.update(ECkReceivingNoticeVo.initECkReceivingNoticeVo(vo));
         
    }
    
    
    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{shNote}")
    public Result getById(@PathVariable(value = "shNote") String shNote) {
        return Result.resultOk(purchaseReceiptNoticeService.findByInNote(shNote));
    }
    
    @DeleteMapping(value = "delete/{shNote}")
    public Result delete(@PathVariable(value = "shNote") String shNote) throws Exception {
    	purchaseReceiptNoticeService.deleteByNote(shNote);
        return Result.resultOk();
    }
	@PutMapping(value = "/ok/{shNote}")
	public Result ok(@PathVariable(value = "shNote") String shNote) throws Exception {
		return purchaseReceiptNoticeService.setFlag(shNote,"确认");
	}
	@PutMapping(value = "/no/{shNote}")
	public Result no(@PathVariable(value = "shNote") String shNote) throws Exception {
		return purchaseReceiptNoticeService.setFlag(shNote,"登记");
	}
	
	@PutMapping(value = "/getFlag/{shNote}/{flag}")
	public Result ok(@PathVariable(value = "shNote") String shNote,@PathVariable(value = "flag") String flag) throws Exception {
		return purchaseReceiptNoticeService.getFlag(shNote, flag);
	}
	
}
