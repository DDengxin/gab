package com.tengzhi.business.platform.specialist.product.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.specialist.product.model.Product;
import com.tengzhi.business.platform.specialist.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/platform/specialist/product")
public class ProductControler {

	@Autowired
	private ProductService productService;
	
    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

	@PostMapping(value = "sreach")
	public Result sreach(BaseDto baseDto) throws IOException {
		return productService.sreach(baseDto).getResult();
	}
	
	@PostMapping(value = "saveNeed")
	public Result saveNeed(@RequestBody Product product) throws Exception {
		return productService.saveNeed(product);
	}
	
	@PutMapping(value = "saveNeed")
	public Result updataNeed(@RequestBody Product product) throws Exception {
		return productService.updateNeed(product);
	}
	
	@GetMapping(value = "add.html/{productNote}")
	public Result saveNeed(@PathVariable String productNote) throws Exception {
		return Result.resultOk(productService.getByNote(productNote));
	}
	
	@DeleteMapping(value = "deleteByNote/{productNote}")
	public Result deleteByNote(@PathVariable String productNote) throws Exception {
		return Result.resultOk(productService.deleteByNote(productNote));
	}
	
}

