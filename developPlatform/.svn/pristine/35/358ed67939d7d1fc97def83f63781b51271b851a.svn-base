package com.tengzhi.business.sc.finishedPicking.notice.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.service.SaleDeliveryNoticeService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.sc.finishedPicking.notice.service.finishedPickingNoticeService;
import com.tengzhi.business.sc.finishedPicking.notice.service.finishedPickingService;

@RestController
@RequestMapping("/sc/finishedPicking/notice")
public class finishedPickingNoticeController {

	@Autowired
	private finishedPickingService saleDeliveryNoticeService;

	@Autowired
	private finishedPickingNoticeService noticeService;

	@GetMapping(value = "/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 
	 * 
	 * @param paramType 类型(YL,CP)
	 * @param request
	 * @param rt
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "{paramType}/list.html" })
	public ModelAndView toPage(@PathVariable String paramType, HttpServletRequest request, RedirectAttributes rt,
			ModelAndView mv) {
		mv.setViewName("sc/finishedPicking/notice/list.html");
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("paramType", paramType);
		// 是否定向
		mv.addObject("orient", true);
		return mv;
	}

	@GetMapping(value = "/findOrderNo/{type}")
	public Result findOrderNo(@PathVariable(value = "type") String type) {
		return Result.resultOk(noticeService.findOrderNo(type));
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/findSerialNumber")
	public Result findSerialNumber(BaseDto baseDto) throws Exception {
		return noticeService.findSerialNumber(baseDto);
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return saleDeliveryNoticeService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return saleDeliveryNoticeService.getSrchBottomList(baseDto).getResult();
	}

	/**
	 * 
	 * @description: Post新增Restful
	 */
	@PostMapping(value = "add.html")
	public Result add(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
		return saleDeliveryNoticeService.save(ECkDeliveryNoticeVo.initECkDeliveryNoticeVo(vo));
	}

	@PutMapping(value = "add.html")
	public Result modify(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
		return saleDeliveryNoticeService.update(ECkDeliveryNoticeVo.initECkDeliveryNoticeVo(vo));

	}

	@GetMapping(value = "getById/{fhNote}")
	public Result getById(@PathVariable(value = "fhNote") String fhNote) {
		return Result.resultOk(saleDeliveryNoticeService.findByInNote(fhNote));
	}

	@DeleteMapping(value = "delete/{fhNote}")
	public Result delete(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		saleDeliveryNoticeService.deleteByNote(fhNote);
		return Result.resultOk();
	}

	@PutMapping(value = "/ok/{fhNote}")
	public Result ok(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return saleDeliveryNoticeService.setFlag(fhNote, "确认");
	}

	@PutMapping(value = "/no/{fhNote}")
	public Result no(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return saleDeliveryNoticeService.setFlag(fhNote, "登记");
	}

	@PutMapping(value = "/getFlag/{fhNote}/{flag}")
	public Result ok(@PathVariable(value = "fhNote") String fhNote, @PathVariable(value = "flag") String flag)
			throws Exception {
		return saleDeliveryNoticeService.getFlag(fhNote, flag);
	}
}
