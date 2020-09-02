package com.tengzhi.business.cg.yw.purchaseContract.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.service.PurchaseContractService;
import com.tengzhi.business.cg.yw.purchaseContract.service.impl.PurchaseContractServiceImpl;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ExamineVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;
import com.tengzhi.business.system.right.vo.SysRightVo;

@RestController
@RequestMapping("/cg/yw/purchaseFile/purchaseContract")
public class PurchaseContractControler {

	@Autowired
	private PurchaseContractService purchaseContractService;
	
	@Autowired
	private SysParamService sysParamService;
	

	@GetMapping("/*")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value="ht_no") String ht_no,@RequestParam(value="stype",required = false) String stype) {
		mv.addObject("stype", stype);
		mv = purchaseContractService.table(mv,ht_no);
		return mv;
	}
	
	   /**
	    * 区分模块的参数入口路径
     * @param request
     * @param mv
     * @return
     */
    @GetMapping(value = {"{cgStype}/contractList.html"})
    public ModelAndView toPage(@PathVariable String cgStype, 
    		HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
        mv.setView(new RedirectView(String.format("/cg/yw/purchaseFile/purchaseContract/%s",servletPath)));
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
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return purchaseContractService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return purchaseContractService.getSrchBottomList(baseDto).getResult();
	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody ECgContractVo vo) throws Exception {
    	ECgContract  eCgContract=purchaseContractService.save(ECgContractVo.initECgContractVo(vo));
        return Result.resultOk(eCgContract.getHtNo());
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody ECgContractVo vo) throws Exception {
    	purchaseContractService.update(ECgContractVo.initECgContractVo(vo));
        return Result.resultOkMsg("修改成功");
    }
    
    
    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{htNO}")
    public Result getById(@PathVariable(value = "htNO") String htNO) {
        return Result.resultOk(purchaseContractService.findById(htNO));
    }
    
    @DeleteMapping(value = "delete/{htNO}")
    public Result delete(@PathVariable(value = "htNO") String htNO) throws Exception {
    	purchaseContractService.deleteById(htNO);
        return Result.resultOk();
    }
    
    
    /**
     * @Param: [vo]
     * @description:紧急程度
     */
    @GetMapping("/getUrgent")
    public List<SelectVo> getRequestMethod(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
        return purchaseContractService.getUrgent(trueText, falseText);
    }
    
    
//    /**
//	 * 提交审核
//	 *
//	 * @return
//	 */
//	@PutMapping(value = "/commit")
//	public Result commit(@RequestBody BaseDto baseDto) throws IOException {
//		return purchaseContractService.submit(baseDto.getParams());
//	}
//	
	
	 /**
     * @Param: [vo]
     * @description:合同类型
     */
    @GetMapping("/getHtType")
    public List<SelectVo> getHtType(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
        return purchaseContractService.getHtType(trueText, falseText);
    }
    
	 /**
     * @Param: [vo]
     * @description:币种
     */
    @GetMapping("/getHtBz")
    public List<SelectVo> getHtBz(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
        return purchaseContractService.getHtBz(trueText, falseText);
    }
    
    /**
     * @Param: [vo]
     * @description:发票类型
     */
    @GetMapping("/getHtInvoice")
    public List<SelectVo> getHtInvoice(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
        return purchaseContractService.getHtInvoice(trueText, falseText);
    }
    
    /**
     * @Param: [vo]
     * @description:税率
     */
    @GetMapping("/getHtTax")
    public List<SelectVo> getHtTax(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
        return purchaseContractService.getHtTax(trueText, falseText);
    }
    
    
    /**
     * @throws IOException 
     * @Param: [vo]
     * @description:采购订单选择list
     */
    @PostMapping("/getCgddSelectList")
	public  Result getCgddSelectList(BaseDto baseDto) throws IOException {
		return purchaseContractService.getCgddSelectList(baseDto).getResult();
	}
    
    
    /**
     * @throws IOException 
     * @Param: [vo]
     * @description:库房下拉框
     */
    @GetMapping("/getLib/{libType}")
	public  List<Map> getLib(@PathVariable String libType) {
		return purchaseContractService.getLib(libType);
	}
    
    
//    @PutMapping(value = "saveDataAndcommit")
//	public Result saveDataAndcommit(@RequestBody ECgContractVo vo) throws Exception {
//		purchaseContractService.saveDataAndcommit(ECgContractVo.initECgContractVo(vo));
//		return Result.resultOkMsg("提交成功");
//	}
//    /**
//	 * 审批同意
//	 *
//	 * @return
//	 */
////	@PutMapping(value = "/agree")
////	public Result agree(@RequestBody ExamineVo examineVo) throws IOException {
////		return purchaseContractService.agree(examineVo);
////	}
	
//	/**
//	 * 审批不同意
//	 *
//	 * @return
//	 */
//	@PutMapping(value = "/disagree")
//	public Result disagree(@RequestBody ExamineVo examineVo) throws IOException {
//		return purchaseContractService.disagree(examineVo);
//	}

	 /**
     * @throws IOException 
     * @Param: [vo]
     * @description:参数表下拉框
     */
	@GetMapping(value = "/comboParam/{mod}/{pareatId}")
	public List<SelectVo> comboParam(@PathVariable String mod,@PathVariable String pareatId){
		return purchaseContractService.comboParam(mod, pareatId);
	}
	
	
	@PutMapping(value = "/getFlag")
	public Result getFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return purchaseContractService.getFlag(String.valueOf(map.get("htNo")),String.valueOf(map.get("flag")));
	}




    @RequestMapping(value = "listwriteoff")
    public Result getContractDetailed(BaseDto baseDto) throws IOException {
        return purchaseContractService.getContractDetailed(baseDto);
    }

    @RequestMapping(value = "/getFlag/{htMo}/{flag}/{code}")
    public Result getFlag(@PathVariable(value = "htMo") String htMo,@PathVariable(value = "flag") String flag,@PathVariable(value = "code") String code) throws Exception {
        return purchaseContractService.getFlagH(htMo,flag,code);
    }
    @RequestMapping(value = "/getCancelFlag/{htMo}/{flag}/{code}")
    public Result getCancelFlag(@PathVariable(value = "htMo") String htMo,@PathVariable(value = "flag") String flag,@PathVariable(value = "code") String code) throws Exception {
        return purchaseContractService.getCancelFlagH(htMo,flag,code);
    }


	  
    /**
     * @throws IOException 
     * @Param: [vo]
     * @description:采购订单选择list
     */
    @PostMapping("/getWlbmSelectList")
	public  Result getWlbmSelectList(BaseDto baseDto) throws IOException {
		return purchaseContractService.getWlbmSelectList(baseDto).getResult();
	}
    

	 /**
    * @Param: [vo]
    * @description:申请类别
    */
   @GetMapping("/getSqType")
   public List<SelectVo> getSqType(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
       return purchaseContractService.getSqType(trueText, falseText);
   }


    /**
     *  核销
     * @param htNo
     * @return
     * @throws Exception
     */
    @PutMapping(value = "hx/{htNo}/{htMo}")
    public Result hx(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "htMo") String htMo) throws Exception {
        return purchaseContractService.hx(htNo,htMo);
    }

    @PutMapping(value = "qxhx/{htNo}/{htMo}")
    public Result qxhx(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "htMo") String htMo) throws Exception {
        return purchaseContractService.qxhx(htNo,htMo);
    }
    
    
    
	
/**
 * 导出excel
 *
 * @return
 */
@PostMapping(value = "/exportExcel")
public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
	purchaseContractService.exportExcel(response, request);
}
	


   
}
