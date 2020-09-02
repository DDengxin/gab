package com.tengzhi.business.production.productionSite.productPacking.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tengzhi.business.production.productionSite.productPacking.service.ProductPackingService;
import com.tengzhi.business.production.productionSite.productPacking.vo.ProductPackingVo;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.service.SaleDeliveryNoticeService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;

@RestController
@RequestMapping("/production/productionSite/productPacking/")
public class ProductPackingController {

	
	
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
		mv.setView(new RedirectView(String.format("/production/productionSite/productPacking/%s", servletPath)));
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
		return productPackingService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return productPackingService.getSrchBottomList(baseDto).getResult();
	}
	/**
	 * 判断扫描加载的数据是否一致
	 * @param inMode
	 * @param scanDatas
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getConsistent/{inMode}/{scanDatas}")
	public Result getConsistent(@PathVariable(value = "inMode") String inMode,@PathVariable(value = "scanDatas") String scanDatas)  throws Exception{
		 return productPackingService.getConsistent(inMode,scanDatas);
	}
	
	/**
	 * 查询预包装的数据
	 * @param inMode
	 * @param scanDatas
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getPrePackList")
	public Result getPrePackList(BaseDto baseDto) throws IOException {
		return productPackingService.getPrePackList(baseDto).getResult();
	}
	
	/**
	 * 查询包装数据
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/getPackList")
	public Result getPackList(BaseDto baseDto) throws IOException {
		return productPackingService.getPackList(baseDto).getResult();
	}
	/**
	 * 新增包装
	 * @param vo
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@PutMapping(value = "/addPacks")
    public Result addPacks(@RequestBody ProductPackingVo vo) throws IOException, Exception {
    	return productPackingService.addPacks(ProductPackingVo.initProductPackingVo(vo));
         
    }
	/**
	 * 删除包装
	 * @param vo
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@PutMapping(value = "/deletePacks")
	public Result deletePacks(@RequestBody ProductPackingVo vo) throws IOException, Exception {
    	return productPackingService.deletePacks(ProductPackingVo.initProductPackingVo(vo));
         
    }
	
	/**
	 * 删除包装
	 * @param note
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@PutMapping(value = "/deleteAllPacks")
	public Result deleteAllPacks(@RequestBody String note) throws IOException, Exception {
    	return productPackingService.deleteAllPacks(note);
         
    }
	
	@PutMapping(value = "/printPack")
	public Result printPack(@RequestBody String printType,@RequestBody String packType,@RequestBody String inputValue) throws Exception {
    	return productPackingService.printPack(printType,packType,inputValue);
         
    }
	
}
