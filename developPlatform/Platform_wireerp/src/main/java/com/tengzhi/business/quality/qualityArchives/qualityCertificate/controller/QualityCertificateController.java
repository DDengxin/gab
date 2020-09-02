package com.tengzhi.business.quality.qualityArchives.qualityCertificate.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.quality.qualityArchives.qualityCertificate.service.QualityCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/quality/qualityArchives/qualityCertificate")
public class QualityCertificateController {

	@Autowired
	private QualityCertificateService qualityCertificateService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForward(ModelAndView mv) {
		return mv;
	}
	/**
	 * 
	 *
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
		mv.setView(new RedirectView(String.format("/quality/qualityArchives/qualityCertificate/%s",servletPath)));
		request.getParameterMap().forEach((key,value) ->{
		    mv.addObject(key,value);
		});
		mv.addObject("paramType",paramType);
		mv.addObject("paramMod",paramMod);
		//是否定向
		mv.addObject("orient",true);
		return mv;
	}	
	
	@PostMapping(value = "list.html")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return qualityCertificateService.getSrchTopList(baseDto).getResult();
	}


	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return qualityCertificateService.getSrchBottomList(baseDto);
	}

	@PostMapping(value = "/getSrchBottomList000")
	public Result getSrchBottomList000(BaseDto baseDto) throws IOException {
		return qualityCertificateService.getSrchBottomList0000(baseDto);
	}

	@PostMapping(value = "/getSrchBottomList1111")
	public Result getSrchBottomList1111(BaseDto baseDto) throws IOException {
		return qualityCertificateService.getSrchBottomList1111(baseDto);
	}

	@PostMapping(value = "add.html")
    public Result add(@RequestBody Map<String,Object> map) throws Exception {
		qualityCertificateService.saveData(map);
        return Result.resultOk("新增成功");
    }

    @PutMapping(value = "add.html")
    public Result modify(@RequestBody Map<String,Object> map) throws Exception {
    	qualityCertificateService.updateData(map);
        return Result.resultOkMsg("修改成功");
    }	
    
    @GetMapping(value = "getByNote/{note}")
    public Result getById(@PathVariable(value = "note") String note) {
        return Result.resultOk(qualityCertificateService.getByNote(note));
    }
    @DeleteMapping(value = "delete/{note}")
    public Result deleteByid(@PathVariable(value = "note") String note) {
        return Result.resultOk(qualityCertificateService.deleteByNote(note));
    }
    
    //getzmNoteAlone
    @GetMapping(value = "getzmNoteAlone/{zmNote}")
    public Result getzmNoteAlone(@PathVariable(value = "zmNote") String zmNote) {
        return Result.resultOk(qualityCertificateService.getzmNoteAlone(zmNote));
    }
    @PostMapping("all/{note}")
	public Result paramsA(@PathVariable String note){
		return Result.resultOk(qualityCertificateService.paramsAll(note));
	}
    
    
    @PostMapping(value = "/getOutList")
	public Result getOutList(BaseDto baseDto) throws IOException {
		return qualityCertificateService.getOutList(baseDto).getResult();
	}
    
    @PutMapping(value = "getFlag/{zmNote}/{flag}")
    public Result getFlag(@PathVariable(value = "zmNote") String zmNote,@PathVariable(value = "flag") String flag)throws Exception {
        return qualityCertificateService.getFlag(zmNote,flag);
    }
    
    @PutMapping(value = "confirm/{zmNote}")
    public Result confirm(@PathVariable(value = "zmNote") String zmNote) throws Exception {
        return qualityCertificateService.confirm(zmNote);
    }
    
    @PutMapping(value = "cancel/{zmNote}")
    public Result cancel(@PathVariable(value = "zmNote") String zmNote) throws Exception {
        return qualityCertificateService.cancel(zmNote);
    }
    
    @GetMapping("table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value="note") String note) {
		mv = qualityCertificateService.table(mv,note);
		return mv;
	}
	
	@GetMapping("table2.html")
	public ModelAndView table2(ModelAndView mv,@RequestParam(value="note") String note) {
		mv = qualityCertificateService.table(mv,note);
		return mv;
	}
}
