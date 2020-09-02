package com.tengzhi.business.finance.currency.controller;

import java.io.IOException;
import java.text.ParseException;
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
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.currency.service.currencyService;
import com.tengzhi.business.system.params.model.SysParams;

@RestController
@RequestMapping("/finance/financeRecordRoom/currency/")
public class currencyController {
	
	@Autowired
	private currencyService currencyService;
	
	
	
	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	 
	@GetMapping("table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value="note") String note) {
		mv = currencyService.table(mv,note);
		return mv;
	}
	
	@GetMapping("table2.html")
	public ModelAndView table2(ModelAndView mv,@RequestParam(value="note") String note) {
		mv = currencyService.table(mv,note);
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
	    @GetMapping(value = {"{paramMod}/{paramType}/{parentId}/{parentName}/list.html"})
	    public ModelAndView toPage(@PathVariable String paramMod, @PathVariable String paramType,
	    		@PathVariable String parentId,@PathVariable String parentName,
	    		HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
	        String servletPath = request.getServletPath();
	        servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
	        mv.setView(new RedirectView(String.format("/finance/financeRecordRoom/currency/%s",servletPath)));
	        request.getParameterMap().forEach((key,value) ->{
	            mv.addObject(key,value);
	        });
	        mv.addObject("paramMod",paramMod);
	        mv.addObject("paramType",paramType);
	        mv.addObject("parentId",parentId);
	        mv.addObject("parentName",parentName);
	        //是否定向
	        mv.addObject("orient",true);
	        return mv;
	    }

	    /**
	     * 查询数据列表
	     *
	     * @return
	     * @throws ParseException 
	     */
	    //@GetMapping("/staffRecordList.html")
	    @PostMapping(value = "list.html")
	    public Result getList(BaseDto baseDto) throws IOException{
	            return currencyService.findAll(baseDto).getResult();
	    }
	    
	    
	    
	    /**
	     * 新增
	     *
	     * @return
	     */
	    @PostMapping("add.html")
	    public Result add(@RequestBody SysParams sysParams) throws Exception{
	    	sysParams = currencyService.save(sysParams);
	        return Result.resultOk(sysParams.getParamId());
	    }
	    
	    
	    /**
	     * 删除数据
	     *
	     * @return
	     */
	    @DeleteMapping(value = "currencyList.html")
	    public Result delete(@RequestBody Map<String,Object> map) throws Exception {
	        return  currencyService.deleteByParameterId(map);
	    }

	    
	    
	    
	    
	    /**
	     * 通过ID获取对象
	     *
	     * @param roleId
	     * @return
	     */
		
		 @GetMapping(value = "list.html/{paramId}") 
		 public Result getById(@PathVariable String paramId) {
			 
			 return Result.resultOk(currencyService.findByParamId(paramId)); 
		 }
		 
	    
	    /**
	     * 修改
	     *
	     * @return
	     */
		 @PutMapping("add.html") public Result modify(@RequestBody SysParams sysParams) { 
		     
			 currencyService.update(sysParams);
	         return Result.resultOk(sysParams.getParamId());
	         }
		 
		 
		 
		 

}
