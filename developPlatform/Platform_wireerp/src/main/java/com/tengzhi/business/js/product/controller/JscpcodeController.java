package com.tengzhi.business.js.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.js.product.model.Jscpcode;
import com.tengzhi.business.js.product.service.JscpcodeService;

@RestController
@RequestMapping("/js/")
public class JscpcodeController {
	@Autowired
	private JscpcodeService jscpcodeService;

	@GetMapping("cpcode/*.html")
	public ModelAndView pageForwart(ModelAndView mv,@RequestParam(value="cpcodeType") String cpcodeType) {
		mv.addObject("cpcodeType", cpcodeType);
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "cpcode/list")
	@Log("查询产品信息")
	public Result getInboxList(BaseDto baseDto) {
		return jscpcodeService.findAll(baseDto).getResult();
	}

	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "cpcode/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(jscpcodeService.findById(id));
	}

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("cpcode")
	@Log("新增产品信息")
	public Result add(@RequestBody Jscpcode jscpcode) throws Exception {
		jscpcode = jscpcodeService.save(jscpcode);
		return Result.resultOk(jscpcode.getCpcodeId());
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	@PutMapping("cpcode")
	@Log("修改产品信息")
	public Result modify(@RequestBody Jscpcode jscpcode) {
		jscpcodeService.update(jscpcode);
		return Result.resultOk(jscpcode.getCpcodeId());
	}

	/**
	 * 删除数据
	 *
	 *
	 * @return
	 */
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
