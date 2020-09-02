package com.tengzhi.business.platform.specialist.product.controller;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.specialist.product.service.MyJscpcodeService;
import com.tengzhi.business.platform.specialist.product.vo.productVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product/")
public class MyJscpcodeController {
	@Autowired
	private MyJscpcodeService jscpcodeService;

	@GetMapping("cpcode/*.html")
	public ModelAndView pageForwart(ModelAndView mv,@RequestParam(value="cpcodeType") String cpcodeType) {
		mv.addObject("cpcodeType", cpcodeType);
		return mv;
	}


	@PostMapping(value = "cpcode/list")
	@Log("查询产品信息")
	public Result getInboxList(BaseDto baseDto) throws IOException {
		return jscpcodeService.findAll(baseDto);
	}

	@GetMapping(value = "cpcode/{cpcodeId}/{cpcodeXp}")
	public Result getById(@PathVariable String cpcodeId,@PathVariable String cpcodeXp) {
		return jscpcodeService.findById(cpcodeId,cpcodeXp);
	}

	@GetMapping(value = "getBycpcodeId/{cpcodeId}")
	public Result getById(@PathVariable String cpcodeId) {
		return jscpcodeService.findByCpcodeId(cpcodeId);
	}


	@PostMapping("cpcode")
	@Log("新增产品信息")
	public Result add(@RequestBody productVo productVo) throws Exception {
		jscpcodeService.save(productVo.getJscpcode(),productVo.getConfigForm());
		return Result.resultOk();
	}


	@PutMapping("cpcode")
	@Log("修改产品信息")
	public Result modify(@RequestBody productVo productVo) {
		jscpcodeService.update(productVo.getJscpcode(),productVo.getConfigForm());
		return Result.resultOk();
	}


	@DeleteMapping(value = "cpcode/{id}")
	@Log("删除产品信息")
	public Result delete(@PathVariable(value = "id") String id) {
		jscpcodeService.deleteById(id);
		return Result.resultOk();
	}

	@GetMapping("addType")
	public List<Map<Object, String>> parameType() {
		return jscpcodeService.parameType();
	}
	
}
