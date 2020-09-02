package com.tengzhi.business.finance.allowance.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.allowance.model.allowance;
import com.tengzhi.business.finance.allowance.service.allowanceService;

@RestController
@RequestMapping("/finance/allowance/")
public class allowanceController {
	
	@Autowired
	private allowanceService allowanceService;
	
	
	@GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
	
	
	   /**
     * 区分模块的参数入口路径
     * @param cwStype
     * @param request
     * @param rt
     * @param mv
     * @return
     */
    @GetMapping(value = {"{cwStype}/allowanceList.html"})
    public ModelAndView toPage(@PathVariable String cwStype, 
    		HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
        mv.setView(new RedirectView(String.format("/finance/allowance/%s",servletPath)));
        request.getParameterMap().forEach((key,value) ->{
            mv.addObject(key,value);
        });
        mv.addObject("cwStype",cwStype);
        //是否定向
        mv.addObject("orient",true);
        return mv;
    }
    
    
    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "allowanceList.html")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
            return allowanceService.findAll(baseDto).getResult();
    }
    
    
    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody allowance allowance) throws Exception{
    	allowance = allowanceService.save(allowance);
        return Result.resultOk(allowance.getCwNote());
    }
    
    
    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping(value = "allowanceList.html/{cwNote}")
    public Result delete(@PathVariable(value = "cwNote") String cwNote) {
    	allowanceService.deleteByCwNote(cwNote);
        return Result.resultOk();
    }
    
    /**
     *确认操作修改状态
     *
     *
     * @return
     * @throws IOException 
     */
    @PutMapping(value = "ok")
    public Result ok(@RequestBody BaseDto baseDto) throws IOException {
    	Map<String, String> map = baseDto.getParamsMap();
    	allowanceService.ok(map.get("cwNote"),map.get("cwCode"));
        return Result.resultOk();
    }
    
    
    
    /**
     *确认月结审核修改状态
     *
     *
     * @return
     * @throws IOException 
     */
    @PutMapping(value = "yok")
    public Result yok(@RequestBody BaseDto baseDto) throws IOException {
    	Map<String, String> map = baseDto.getParamsMap();
    	allowanceService.yok(map.get("cwNote"),map.get("cwCode"));
        return Result.resultOk();
    }
    
    /**
     *取消操作修改状态
     *
     *
     * @return
     * @throws IOException 
     */
    @PutMapping(value = "qx")
    public Result qx(@RequestBody BaseDto baseDto) throws IOException {
    	Map<String, String> map = baseDto.getParamsMap();
    	allowanceService.qx(map.get("cwNote"),map.get("cwCode"));
        return Result.resultOk();
    }
    
    
    
    /**
     *取消月结审核修改状态
     *
     *
     * @return
     * @throws IOException 
     */
    @PutMapping(value = "yqx")
    public Result yqx(@RequestBody BaseDto baseDto) throws IOException {
    	Map<String, String> map = baseDto.getParamsMap();
    	allowanceService.yqx(map.get("cwNote"),map.get("cwCode"));
        return Result.resultOk();
    }
  
    
    /**
     * 通过ID获取对象
     *
     * @param fpId
     * @return
     */
	
	 @GetMapping(value = "allowanceList.html/{cwNote}") 
	 public Result getById(@PathVariable String cwNote) {
		 return Result.resultOk(allowanceService.findByCwNote(cwNote)); 
	 }
	 
    
    /**
     * 修改
     *
     * @return
     */
	 @PutMapping("add.html") public Result modify(@RequestBody allowance allowance) { 
	     
		 allowanceService.update(allowance);
         return Result.resultOk(allowance.getCwNote());
         }
	 
	 



}
