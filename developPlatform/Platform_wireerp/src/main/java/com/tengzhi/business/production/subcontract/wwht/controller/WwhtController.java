package com.tengzhi.business.production.subcontract.wwht.controller;

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
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.production.subcontract.wwht.service.WwhtService;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractVo;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractWlVo;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;


@RestController
@RequestMapping("/production/subcontract/wwht")
public class WwhtController {
	
	@Autowired
	private WwhtService service;
	
	@Autowired
	private SysParamService sysParamService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForward(ModelAndView mv) {
		return mv;
	}
	
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value="htNo") String htNo,@RequestParam(value="ywType") String ywType) {
		mv = service.table(mv,htNo,ywType);
		return mv;
	}
	
	/**
	 * 	查询数据列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/xs/list.html")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return service.findAll(baseDto).getResult();
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
	@GetMapping(value = {"{htStype}/{ywType}/list.html"})
	public ModelAndView toPage(@PathVariable String htStype, @PathVariable String ywType, 
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		String url = String.format("/production/subcontract/wwht/%s",servletPath);
		mv.setView(new RedirectView(url));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("htStype",htStype);
		mv.addObject("ywType",ywType);
		SysParams sysParams=sysParamService.findByParameterId(htStype, "产品大类");
        mv.addObject("cgName",sysParams.getParamName());
		//是否定向
		mv.addObject("orient",true);
		return mv;
	}
	
	/**
	 * 查询明细列表
	 *
	 * @return
	 */
	@PostMapping(value = "/xs/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return service.getSrchBottomList(baseDto).getResult();
	}
	
	
	/**
	 * 新增保存
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/xs/add.html")
    public Result add(@RequestBody ProcessContractVo vo) throws Exception {
    	EXsContract  eXsContract=service.save(ProcessContractVo.initProcessContractVo(vo));
        return Result.resultOk(eXsContract.getHtNo());
    }
	
    /**
     * 修改
     * @param vo
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/xs/add.html")
    public Result modify(@RequestBody ProcessContractVo vo) throws Exception {
    	service.update(ProcessContractVo.initProcessContractVo(vo));
        return Result.resultOkMsg("修改成功");
    }	
    
    /**
     * 根据合同单号获取合同信息
     * @param htNO
     * @return
     */
    @GetMapping(value = "/xs/getById/{htNO}")
    public Result getById(@PathVariable(value = "htNO") String htNO) {
        return Result.resultOk(service.findByNote(htNO));
    }
    
    
    
    @PutMapping(value = "/xs/confirm/{htNo}")
    public Result confirm(@PathVariable(value = "htNo") String htNo) throws Exception {
        return service.confirm(htNo);
    }
    
    @PutMapping(value = "/xs/cancel/{htNo}")
    public Result cancel(@PathVariable(value = "htNo") String htNo) throws Exception {
        return service.cancel(htNo);
    }
    
    @DeleteMapping(value = "/xs/delete/{htNo}")
    public Result delete(@PathVariable(value = "htNo") String htNo) throws Exception {
    	service.deleteByNote(htNo);
         return Result.resultOkMsg("删除成功");
    }
    
    
    @PostMapping(value = "/xs/getContractDetailed")
	public Result getContractDetailed(BaseDto baseDto) throws IOException {
		return service.getContractDetailed(baseDto).getResult();
	}
    
    @PutMapping(value = "/xs/getFlag/{htNo}/{flag}")
	public Result getFlag(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws Exception {
		return service.getFlag(htNo,flag);
	}
    
    /**
     * 
     * @param htNo
     * @param flag
     * @return 多选是判断状态
     * @throws IOException
     * @throws Exception
     */
    @PutMapping(value = "/xs/getFlags/{htNo}/{flag}")
   	public Result getFlags(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws IOException, Exception {
   		return service.getFlags(htNo,flag);
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
	@GetMapping(value = {"{paramType}/{paramMod}/scddlist.html"})
	public ModelAndView ddtj(@PathVariable String paramType, @PathVariable String paramMod, 
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/production/subcontract/wwht/%s",servletPath)));
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
	@PostMapping(value = "/scddlist.html")
	public Result getScddList(BaseDto baseDto) throws IOException {
		return service.getScddList(baseDto).getResult();
	}
	
    @GetMapping(value = "/getBySplitId/{htMo}")
    public Result getBySplitId(@PathVariable(value = "htMo") String htMo) {
        return Result.resultOk(service.getBySplitId(htMo));
    }
    
    @GetMapping(value = "/getByHpId/{htMo}")
    public Result getByHpId(@PathVariable(value = "htMo") String htMo) {
        return Result.resultOk(service.getByHpId(htMo));
    }
    
    
    /**
     *  核销
     * @param htNo
     * @return
     * @throws Exception
     */
    @PutMapping(value = "hx/{htNo}")
    public Result hx(@PathVariable(value = "htNo") String htNo) throws Exception {
        return service.hx(htNo);
    }
    
    @PutMapping(value = "qxhx/{htNo}")
    public Result qxhx(@PathVariable(value = "htNo") String htNo) throws Exception {
        return service.qxhx(htNo);
    }
    
    @PostMapping(value = "/getUsableContractDetailed")
	public Result getUsableContractDetailed(BaseDto baseDto) throws IOException {
		return service.getUsableContractDetailed(baseDto).getResult();
	}
    
    
    
}
