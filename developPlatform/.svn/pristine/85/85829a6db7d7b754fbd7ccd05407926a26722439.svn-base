package com.tengzhi.business.tooling.materiel.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;
import com.tengzhi.business.tooling.materiel.service.CgMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tooling/materiel")
public class CgMaterielController {

	@Autowired
	private CgMaterielService materielService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	
	/**
	 * 
	 * 
	 * @param outType 产品类型(CP,YL,WL)
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = {"{outType}//list.html"})
	public ModelAndView toPage(@PathVariable String outType,
			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
		mv.setView(new RedirectView(String.format("/sc/requisition/materiel/%s",servletPath)));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("paramType",outType);
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
		return materielService.findAll(baseDto).getResult();
	}
	/**
	 * 	查询数据列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/getKcList")
	public Map<String, Object> getKcList(BaseDto baseDto) throws IOException {
		return materielService.getKcList(baseDto);
	}
	@PostMapping(value = "/getOutList")
	public Result getOutList(BaseDto baseDto) throws IOException {
		return materielService.getOutList(baseDto).getResult();
	}
	@PostMapping(value = "add.html")
    public Result add(@RequestBody ECKOutVo vo) throws Exception {
		System.out.println(vo);
        return materielService.save(ECKOutVo.initECgContractVo(vo));
    }
	@PutMapping(value = "add.html")
    public Result modify(@RequestBody ECKOutVo vo) throws Exception {
		System.out.println("修改");
		materielService.update(ECKOutVo.initECgContractVo(vo));
        return Result.resultOkMsg("修改成功");
    }	
    
    @GetMapping(value = "getByNote/{htNO}")
    public Result getById(@PathVariable(value = "htNO") String htNO) {
        return Result.resultOk(materielService.findByNote(htNO));
    }
    
    @DeleteMapping(value = "delete/{htNO}")
    public Result delete(@PathVariable(value = "htNO") String htNO) throws Exception {
        return materielService.deleteByNote(htNO);
    }
    
    @GetMapping("/getItemSelectList/{customer}/{outType}")
	public List<Map> getItemSelectList(@PathVariable(name = "customer") String customer,@PathVariable(name = "outType") String outType) {
		return materielService.getItemSelectList(customer,outType);
	}
    
    @GetMapping("/getCodeSelectList/{contract}")
	public List<Map> getCodeSelectList( @PathVariable(name = "contract") String contract) {
		return materielService.getCodeSelectList(contract);
	}
    @PutMapping(value = "confirm/{outNote}")
    public Result confirm(@PathVariable(value = "outNote") String outNote) throws Exception {
        return materielService.confirm(outNote);
    }
    @PutMapping(value = "cancel/{outNote}")
    public Result cancel(@PathVariable(value = "outNote") String outNote) throws Exception {
    	
        return materielService.cancel(outNote);
    }
    @PutMapping(value = "getFlag/{outNote}/{flag}")
    public Result getFlag(@PathVariable(value = "outNote") String outNote,@PathVariable(value = "flag") String flag) {
        return materielService.getFlag(outNote,flag);
    }

    @RequestMapping("facksele")
	public Result facksele(String park){
		return materielService.facksele(park);
	}

}
