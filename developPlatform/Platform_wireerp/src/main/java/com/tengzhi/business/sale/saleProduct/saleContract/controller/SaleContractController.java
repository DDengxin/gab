package com.tengzhi.business.sale.saleProduct.saleContract.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.service.SaleContractService;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;

@RestController
@RequestMapping("/sale/saleProduct/saleContract")
public class SaleContractController {
	
	@Autowired
	private SaleContractService saleContractService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value="htNo") String htNo) {
		mv = saleContractService.table(mv,htNo);
		return mv;
	}
	
	/**
	 * 	查询数据列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "list.html")
	public Result getSrchTopList(BaseDto baseDto){
		return saleContractService.findAll(baseDto).getResult();
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
		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleContract/%s",servletPath)));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("paramType",paramType);
		mv.addObject("paramMod",paramMod);
		return mv;
	}	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return saleContractService.getSrchBottomList(baseDto).getResult();
	}
	@PostMapping(value = "add.html")
    public Result add(@RequestBody EXsContractVo vo) throws Exception {
    	EXsContract  eXsContract=saleContractService.save(EXsContractVo.initEXsContractVo(vo));
        return Result.resultOk(eXsContract.getHtNo());
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody EXsContractVo vo) throws Exception {
    	saleContractService.update(EXsContractVo.initEXsContractVo(vo));
        return Result.resultOkMsg("修改成功");
    }	
    
    @GetMapping(value = "getByNote/{htNO}")
    public Result getById(@PathVariable(value = "htNO") String htNO) {
        return Result.resultOk(saleContractService.findByNote(htNO));
    }
    @PutMapping(value = "confirm/{htNo}")
    public Result confirm(@PathVariable(value = "htNo") String htNo) throws Exception {
        return saleContractService.confirm(htNo);
    }
    
    @PutMapping(value = "cancel/{htNo}")
    public Result cancel(@PathVariable(value = "htNo") String htNo) throws Exception {
        return saleContractService.cancel(htNo);
    }
    
    @DeleteMapping(value = "delete/{htNo}")
    public Result delete(@PathVariable(value = "htNo") String htNo) throws Exception {
         saleContractService.deleteByNote(htNo);
         return Result.resultOkMsg("删除成功");
    }
    @PostMapping(value = "/getContractDetailed")
	public Result getContractDetailed(BaseDto baseDto) throws IOException {
		return saleContractService.getContractDetailed(baseDto).getResult();
	}
    
    @PutMapping(value = "/getFlag/{htNo}/{flag}")
	public Result getFlag(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws Exception {
		return saleContractService.getFlag(htNo,flag);
	}
    
    /**
     * 
     * @param htNo
     * @param flag
     * @return 多选是判断状态
     * @throws IOException
     * @throws Exception
     */
    @PutMapping(value = "/getFlags/{htNo}/{flag}")
   	public Result getFlags(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws IOException, Exception {
   		return saleContractService.getFlags(htNo,flag);
   	}
    
	/**
	 * 
	 * 
	 * @param paramType 类型 (CP,YL,WL)
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
		mv.setView(new RedirectView(String.format("/sale/saleProduct/saleContract/%s",servletPath)));
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
		return saleContractService.getScddList(baseDto).getResult();
	}
	
    @GetMapping(value = "/getBySplitId/{htMo}")
    public Result getBySplitId(@PathVariable(value = "htMo") String htMo) {
        return Result.resultOk(saleContractService.getBySplitId(htMo));
    }
    
    @GetMapping(value = "/getByHpId/{htMo}")
    public Result getByHpId(@PathVariable(value = "htMo") String htMo) {
        return Result.resultOk(saleContractService.getByHpId(htMo));
    }
    @PostMapping(value = "/exportExcelScdd")
	public Result exportExcelScdd(HttpServletResponse response, HttpServletRequest request ) throws IOException {
    	saleContractService.exportExcelScdd(response, request );
		 return Result.resultOk();
	}
    
    
    /**
     *  核销
     * @param htNo
     * @return
     * @throws Exception
     */
    @PutMapping(value = "hx/{htNo}")
    public Result hx(@PathVariable(value = "htNo") String htNo) throws Exception {
        return saleContractService.hx(htNo);
    }
    
    @PutMapping(value = "qxhx/{htNo}")
    public Result qxhx(@PathVariable(value = "htNo") String htNo) throws Exception {
        return saleContractService.qxhx(htNo);
    }
    
    @PostMapping(value = "/getUsableContractDetailed")
	public Result getUsableContractDetailed(BaseDto baseDto) throws IOException {
		return saleContractService.getUsableContractDetailed(baseDto).getResult();
	}
}
