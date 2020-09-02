package com.tengzhi.business.finance.receivables.receivablesCheck.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.receivables.receivablesCheck.service.ReceivablesCheckService;

@RestController
@RequestMapping("/finance/receivables/receivablesCheck")
public class ReceivablesCheckController {

	@Autowired
	private  ReceivablesCheckService receivablesCheckService;
	
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value = "cwNote",required = false) String cwNote) {
		mv = receivablesCheckService.table(mv, cwNote);
		return mv;
	}
	@GetMapping(value = {"{paramType}/{paramMod}/list.html"})
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod, 
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/finance/receivables/receivablesCheck/%s",servletPath)));
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
	public Result getGridList(BaseDto baseDto) throws IOException {
		return receivablesCheckService.getGridList(baseDto).getResult();
	}
	
	@PostMapping(value = "/getDetailGridList")
	public Result getDetailGridList(BaseDto baseDto) throws IOException {
		return receivablesCheckService.getDetailGridList(baseDto).getResult();
	}
	
	@GetMapping(value = "getByNote/{note}")
	public Result getById(@PathVariable(value = "note") String note) {
		return Result.resultOk(receivablesCheckService.getDataByNote(note));
	}
	
	@PutMapping(value = "confirm/{note}")
    public Result confirm(@PathVariable(value = "note") String note) throws Exception {
        return receivablesCheckService.confirm(note);
    }
    @PutMapping(value = "cancel/{note}")
    public Result cancel(@PathVariable(value = "note") String note) throws Exception {
    	
        return receivablesCheckService.cancel(note);
    }
    @PutMapping(value = "getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable(value = "note") String note,@PathVariable(value = "flag") String flag)throws Exception {
        return receivablesCheckService.getFlag(note,flag);
    }
}
