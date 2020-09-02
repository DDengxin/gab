package com.tengzhi.business.platform.manage.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.manage.model.G_PriceConfigure;
import com.tengzhi.business.platform.manage.service.PriceConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/***
 * 报价设置
 * 
 * @author lsh
 *
 */
@RestController
@RequestMapping("platform/priceconfigure")
public class PriceConfigureControler {
	
	@Autowired
	private PriceConfigureService priceConfigureService;
	
	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	/**
	 * 分页查询
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrch")
	public Result getSrch(BaseDto baseDto) throws IOException {
		return priceConfigureService.getSrch(baseDto).getResult();
	}
	
	/**
	 * 新增
	 * 
	 */
	@PostMapping(value = "add")
	public Result add(@RequestBody G_PriceConfigure model) throws Exception {
		Result rs = priceConfigureService.save(model);
		return rs;
	}

	@GetMapping(value = "uniqueQuery/{id}")
    public Result uniqueQuery(@PathVariable String id) {
        return Result.resultOk(priceConfigureService.findByPcNote(id));
    }

	/**
	 * 修改
	 * @param advisory
	 * @return
	 */
    @PutMapping("add")
    public Result modify(@RequestBody G_PriceConfigure advisory) {
    	priceConfigureService.update(advisory);
        return Result.resultOk();
    }
	    
    @DeleteMapping(value = "delete/{pc_note}")
    public Result delete(@PathVariable String pc_note) {
    	priceConfigureService.deleteByPcNote(pc_note);
        return Result.resultOk();
    }
	 
    /**
     * 通过类型查找配置数据
     * @param type
     * @return
     */
    @PostMapping(value = "findByType")
    public List<Map<String,Object>> findByType(@RequestParam String type) {
        return priceConfigureService.findByType(type);
    }
}
