package com.tengzhi.business.ck.yw.warehouseAllot.controller;

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
import com.tengzhi.business.ck.yw.warehouseAllot.service.WarehouseAllotService;
import com.tengzhi.business.ck.yw.warehouseAllot.vo.ECkAllotVo;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;

@RestController
@RequestMapping("/ck/yw/warehouseAllot")
public class WarehouseAllotController {

	@Autowired
	private WarehouseAllotService warehouseAllotService;
	
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
	@GetMapping(value = {"{paramType}/{paramMod}/list.html"})
	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
	  String servletPath = request.getServletPath();
	  servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
	  mv.setView(new RedirectView(String.format("/ck/yw/warehouseAllot/%s",servletPath)));
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
	 * @throws Exception 
	 */
	@PostMapping(value = "list.html")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return warehouseAllotService.getSrchTopList(baseDto).getResult();
	}
	
	
	@PostMapping(value = "/getOutDetailedList")
	public Result getOutList(BaseDto baseDto) throws Exception {
		return warehouseAllotService.getOutDetailedList(baseDto).getResult();
	}
	
	@PostMapping(value = "/getKcDetailedList")
	public Map<String, Object> getKcDetailedList(BaseDto baseDto) throws Exception {
		return warehouseAllotService.getKcDetailedList(baseDto);
	}
	@PostMapping(value = "add.html")
    public Result add(@RequestBody ECkAllotVo vo) throws Exception {
        return warehouseAllotService.save(ECkAllotVo.initECkAllotVo(vo));
    }
	@PutMapping(value = "add.html")
    public Result modify(@RequestBody ECkAllotVo vo) throws Exception {
		warehouseAllotService.update(ECkAllotVo.initECkAllotVo(vo));
        return Result.resultOkMsg("修改成功");
    }
	
	@GetMapping(value = "getByNote/{note}")
    public Result getById(@PathVariable(value = "note") String note) {
        return Result.resultOk(warehouseAllotService.getByNote(note));
    }
	
	@PutMapping(value = "confirm/{outNote}/{outType}")
    public Result confirm(@PathVariable(value = "outNote") String outNote,@PathVariable(value = "outType") String outType) throws Exception {
        return warehouseAllotService.confirm(outNote,outType);
    }
    @PutMapping(value = "cancel/{outNote}")
    public Result cancel(@PathVariable(value = "outNote") String outNote) throws Exception {
    	
        return warehouseAllotService.cancel(outNote);
    }
    @PutMapping(value = "getFlag/{outNote}/{flag}")
    public Result getFlag(@PathVariable(value = "outNote") String outNote,@PathVariable(value = "flag") String flag)throws Exception {
        return warehouseAllotService.getFlag(outNote,flag);
    }
    
    @DeleteMapping(value = "delete/{outNote}")
    public Result delete(@PathVariable(value = "outNote") String outNote) throws Exception {
        return warehouseAllotService.deleteByNote(outNote);
    }
}
