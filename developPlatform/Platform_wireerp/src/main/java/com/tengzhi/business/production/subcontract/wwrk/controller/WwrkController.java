package com.tengzhi.business.production.subcontract.wwrk.controller;

import java.io.IOException;
import java.util.List;
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
import com.tengzhi.business.mesGz.gzck.vo.ECkInVo;
import com.tengzhi.business.production.subcontract.wwrk.service.WwrkService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.service.SaleDeliveryNoticeService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.system.params.service.SysParamService;

@RestController
@RequestMapping("/production/subcontract/wwrk")
public class WwrkController {
	
	@Autowired
	private WwrkService wwrkService;

	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}


	/**
	 *
	 * @param inType 产品类型(YL,WL,CP)
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "/{inType}/list.html" })
	public ModelAndView toPage(@PathVariable String inType, HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/production/subcontract/wwrk/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("inType", inType);
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return wwrkService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return wwrkService.getSrchBottomList(baseDto).getResult();
	}


	@PostMapping(value = "add.html")
	public Result add(@RequestBody ECkInVo vo) throws Exception {
		return  wwrkService.save(vo);
	}

	@PutMapping(value = "add.html")
	public Result update(@RequestBody ECkInVo vo) throws Exception {
		return  wwrkService.update(ECkInVo.initECgContractVo(vo));
	}

	@PutMapping(value = "/confirm/{inNote}")
	public Result ok(@PathVariable(value = "inNote") String inNote) throws Exception {
		return wwrkService.setFlag(inNote,"确认");
	}
	@PutMapping(value = "/cancel/{inNote}")
	public Result no(@PathVariable(value = "inNote") String inNote) throws Exception {
		return wwrkService.setFlag(inNote,"登记");
	}
	
	@PutMapping(value = "/getFlag/{inNote}/{flag}")
	public Result ok(@PathVariable(value = "inNote") String inNote,@PathVariable(value = "flag") String flag) throws Exception {
		return wwrkService.getFlag(inNote, flag);
	}
	
	@PutMapping(value = "/getDjFlag")
	public Result getDjFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return wwrkService.getFlag(String.valueOf(map.get("inNote")),"登记");
	}
	
	/**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{inNote}")
    public Result getById(@PathVariable(value = "inNote") String inNote) {
        return Result.resultOk(wwrkService.findByInNote(inNote));
    }
	
	
    @DeleteMapping(value = "delete/{inNote}")
    public Result delete(@PathVariable(value = "inNote") String inNote) throws Exception {
    	wwrkService.deleteById(inNote);
        return Result.resultOk();
    }
    
	/**
	 * 委外加工合同选择列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/getWwhtSelectList")
	public  Result getWwhtSelectList(BaseDto baseDto) throws IOException {
		return wwrkService.getWwhtSelectList(baseDto).getResult();
	}
	

	/**
	 * 委外加工合同选择列表(采购)
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/getCgWwhtSelectList")
	public  Result getCgWwhtSelectList(BaseDto baseDto) throws IOException {
		return wwrkService.getCgWwhtSelectList(baseDto).getResult();
	}


	/**
	 * 打印列表
	 * @param mv
	 * @return
	 */
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value="inNote") String inNote) {
		mv = wwrkService.table(mv,inNote);
		return mv;
	}
}
