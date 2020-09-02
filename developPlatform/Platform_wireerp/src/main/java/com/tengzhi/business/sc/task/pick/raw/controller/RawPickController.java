package com.tengzhi.business.sc.task.pick.raw.controller;

import java.io.IOException;
import java.util.List;

import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.service.SaleDeliveryNoticeService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.sc.task.pick.raw.service.RawPickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.app.ck.model.DeliveryNotice;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sc/task/pick/raw")
public class RawPickController {
	
	@Autowired
	private RawPickService rawPickService;
	
	@Autowired
	private SaleDeliveryNoticeService saleDeliveryNoticeService;
	
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
	@GetMapping(value = { "/{codeType}/list.html" })
	public ModelAndView toPage(@PathVariable String codeType, HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/sc/task/pick/raw/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("codeType", codeType);
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping("/getTopList")
	public Result getTopList(BaseDto baseDto) throws IOException {
		return rawPickService.getTopList(baseDto).getResult();
	}
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping("/getBottomLeftList")
	public Result getBottomLeftList(BaseDto baseDto) throws IOException {
		return rawPickService.getBottomLeftList(baseDto).getResult();
	}
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping("/getBottomRightList")
	public Result getBottomRightList(BaseDto baseDto) throws IOException {
		return rawPickService.getBottomRightList(baseDto).getResult();
	}
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping("/demandNotice/getById")
	public Result getDemandNotifyListById(@RequestParam String fhNote,@RequestParam String fhCode) throws IOException {
		return rawPickService.getDemandNotifyListById(fhNote,fhCode).getResult();
	}
	
	@PostMapping("/demandNotice/getByNote")
	public Result getDemandNotifyListByNote(@RequestParam String fhNote) throws IOException {
		return rawPickService.getDemandNotifyListByNote(fhNote).getResult();
	}
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping("/manualNotice/getById")
	public Result getManualNoticeListById(@RequestParam String fhNote,@RequestParam String fhCode) throws IOException {
		return rawPickService.getManualNoticeListById(fhNote,fhCode).getResult();
	}
	
	@PostMapping("/manualNotice/getByNote")
	public Result getManualNoticeListByNote(@RequestParam String fhNote) throws IOException {
		return rawPickService.getManualNoticeListByNote(fhNote).getResult();
	}



	@PostMapping(value = "add.html")
	public Result add(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
		return  rawPickService.save(vo);
	}

	@PutMapping(value = "add.html")
	public Result modify(@RequestBody ECkDeliveryNoticeVo vo) throws Exception {
		return rawPickService.update(ECkDeliveryNoticeVo.initECkDeliveryNoticeVo(vo));

	}
	@PutMapping(value = "/confirm/{fhNote}")
	public Result ok(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return saleDeliveryNoticeService.setFlag(fhNote,"确认");
	}
	@PutMapping(value = "/cancel/{fhNote}")
	public Result no(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return saleDeliveryNoticeService.setFlag(fhNote,"登记");
	}
	
	@PutMapping(value = "/getFlag/{fhNote}/{flag}")
	public Result ok(@PathVariable(value = "fhNote") String fhNote,@PathVariable(value = "flag") String flag) throws Exception {
		return saleDeliveryNoticeService.getFlag(fhNote, flag);
	}
	
	@DeleteMapping(value = "/delete/{fhNote}")
	public Result delete(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return rawPickService.delete(fhNote);
	}
	
	@GetMapping("/table.html")
	public ModelAndView table(ModelAndView mv,@RequestParam(value = "outNotes",required = false) String outNotes) {
		mv = rawPickService.table(mv, outNotes);
		return mv;
	}
	@PostMapping("/getOutLsList")
	public Result getOutLsList(BaseDto baseDto) throws IOException {
		return rawPickService.getOutLsList(baseDto).getResult();
	}

	@PostMapping(value = "addOutLsData")
	public Result addOutLsData(@RequestBody DeliveryNotice vo) throws Exception {
		return  rawPickService.addOutLsData(vo);
	}
	
	@PostMapping(value = "/saveOutData/{fhNote}")
	public Result saveOutData(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return  rawPickService.saveOutData(fhNote);
	}

	@DeleteMapping(value = "/deleteOut/{fhNote}/{packType}/{outPack}")
	public Result deleteOutLsData(@PathVariable(value = "fhNote") String fhNote,@PathVariable(value = "packType") String packType,@PathVariable(value = "outPack") String outPack) throws Exception {
		return rawPickService.deleteOutLsData(fhNote,packType,outPack);
	}
	
	@GetMapping("/getFhCode/{fhNote}")
    public List<SelectVo> getFhCode(@PathVariable(name = "fhNote") String fhNote) {
        return rawPickService.getFhCode(fhNote);
    }
	
	@GetMapping("/getDataByNote/{fhNote}")
    public Result getDataByNote(@PathVariable(name = "fhNote") String fhNote) {
		 return Result.resultOk(rawPickService.getDataByNote(fhNote));
    }
	
	@PostMapping("/getStockList")
	public Result getStockList(BaseDto baseDto) throws IOException {
		return rawPickService.getStockList(baseDto).getResult();
	}
	
	@DeleteMapping(value = "/deleteOutAll/{fhNote}")
	public Result deleteOutLsData(@PathVariable(value = "fhNote") String fhNote) throws Exception {
		return rawPickService.deleteOutLsData(fhNote,"","");
	}
}
