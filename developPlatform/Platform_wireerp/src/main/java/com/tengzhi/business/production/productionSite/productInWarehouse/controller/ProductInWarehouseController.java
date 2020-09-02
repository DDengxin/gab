package com.tengzhi.business.production.productionSite.productInWarehouse.controller;

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
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.production.productionSite.productInWarehouse.service.ProductInWarehouseService;
import com.tengzhi.business.production.productionSite.productPacking.service.ProductPackingService;

@RestController
@RequestMapping("/production/productionSite/productInWarehouse/")
public class ProductInWarehouseController {

	@Autowired
	private ProductInWarehouseService productInWarehouseService;
	
	@Autowired
	private ProductPackingService productPackingService;

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	@GetMapping(value = { "{paramType}/list.html" })
	public ModelAndView toPage(@PathVariable String paramType, HttpServletRequest request, RedirectAttributes rt,
			ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/production/productionSite/productInWarehouse/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("paramType", paramType); // 是否定向
		mv.addObject("orient", true);
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */

	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return productInWarehouseService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return productInWarehouseService.getSrchBottomList(baseDto).getResult();
	}

	@PostMapping(value = "add.html")
	public Result add(@RequestBody ECkInVo vo) throws Exception {
		return productInWarehouseService.save(ECkInVo.initECgContractVo(vo));
	}

	@PutMapping(value = "add.html")
	public Result modify(@RequestBody ECkInVo vo) throws Exception {
		return productInWarehouseService.update(ECkInVo.initECgContractVo(vo));

	}

	@GetMapping(value = "getById/{inNote}")
    public Result getById(@PathVariable(value = "inNote") String inNote) {
        return Result.resultOk(productInWarehouseService.findByInNote(inNote));
    }
	
	 @DeleteMapping(value = "delete/{inNote}")
	public Result delete(@PathVariable(value = "inNote") String inNote) throws Exception {
		 productInWarehouseService.deleteById(inNote);
		 return Result.resultOk();
	}
	 
	@PutMapping(value = "/getFlag/{inNote}/{flag}")
	public Result getFlag(@PathVariable(value = "inNote") String inNote,@PathVariable(value = "flag") String flag) throws Exception {
		return productInWarehouseService.getFlag(inNote,flag);
	}
	@PutMapping(value = "/confirm/{inNote}")
	public Result ok(@PathVariable(value = "inNote") String inNote) throws Exception {
		return productInWarehouseService.setFlag(inNote,"结算");
	}
	@PutMapping(value = "/cancel/{inNote}")
	public Result no(@PathVariable(value = "inNote") String inNote) throws Exception {
		return productInWarehouseService.setFlag(inNote,"登记");
	}
	
	@PostMapping(value = "/getInList/{inValue}")
	public Result getInList(@PathVariable(value = "inValue") String inValue) throws Exception {
		return productInWarehouseService.getInList(inValue);
	}
	
	@PutMapping(value = "/printPack/{printType}/{packType}/{inputValue}")
	public Result printPack(@PathVariable(value = "printType") String printType,@PathVariable(value = "packType") String packType,@PathVariable(value = "inputValue") String inputValue) throws Exception {
    	return productPackingService.printPack(printType,packType,inputValue);
         
    }
}
