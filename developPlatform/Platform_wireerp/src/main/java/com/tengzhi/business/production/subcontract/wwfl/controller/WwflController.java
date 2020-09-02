package com.tengzhi.business.production.subcontract.wwfl.controller;

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
import com.tengzhi.business.production.subcontract.wwfl.service.WwflService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.system.params.service.SysParamService;

@RestController
@RequestMapping("/production/subcontract/wwfl")
public class WwflController {
	
	@Autowired
	private WwflService wwflService;
	
	@Autowired
	private SysParamService sysParamService;
	
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}


	/**
	 *
	 * @param codeType 产品类型(YL,WL,CP)
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "/{fhType}/list.html" })
	public ModelAndView toPage(@PathVariable String fhType, HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/production/subcontract/wwfl/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("fhType", fhType);
		return mv;
	}

	/**
	 * 查询列表
	 *
	 * @return
	 */
	@PostMapping("/getTopList.html")
	public Result getTopList(BaseDto baseDto) throws IOException {
		return wwflService.getTopList(baseDto).getResult();
	}
	
	/**
	 * 查询明细列表
	 *
	 * @return
	 */
	@PostMapping("/getBottomLeftList.html")
	public Result getBottomLeftList(BaseDto baseDto) throws IOException {
		return wwflService.getBottomLeftList(baseDto).getResult();
	}
	
	/**
	 * 新增保存
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "add.html")
    public Result add(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
        return wwflService.save(ECkDeliveryNoticeVo.initECkDeliveryNoticeVo(vo));
    }
	
	/**
	 * 修改保存
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "add.html")
    public Result modify(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
        return wwflService.update(ECkDeliveryNoticeVo.initECkDeliveryNoticeVo(vo));
    }	
	
	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping("/demandNotice/getById")
	public Result getDemandNotifyListById(@RequestParam String fhNote) throws IOException {
		return wwflService.getDemandNotifyListById(fhNote).getResult();
	}
	
	/**
	 * 删除整单
	 * @param fhNote
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/delete/{fhNote}")
	public Result delete(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return wwflService.delete(fhNote);
	}
	
	/**
	 * 确认记录
	 * @param htNo
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/confirm/{fhNote}")
    public Result confirm(@PathVariable(value = "fhNote") String fhNote) throws Exception {
        return wwflService.confirm(fhNote);
    }
	
	/**
	 * 取消记录
	 * @param htNo
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/cancel/{fhNote}")
    public Result cancel(@PathVariable(value = "fhNote") String fhNote) throws Exception {
        return wwflService.cancel(fhNote);
    }
	
	/**
	 * 打印列表
	 * @param mv
	 * @param htNo
	 * @param ywType
	 * @return
	 */
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value="fhNote") String fhNote,@RequestParam(value="fhType") String fhType) {
		mv = wwflService.table(mv,fhNote,fhType);
		return mv;
	}
}
