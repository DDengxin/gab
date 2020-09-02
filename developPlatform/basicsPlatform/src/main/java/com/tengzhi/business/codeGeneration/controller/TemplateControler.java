package com.tengzhi.business.codeGeneration.controller;

import java.io.IOException;
import java.util.List;

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

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.codeGeneration.model.Template;
import com.tengzhi.business.codeGeneration.service.TemplateService;

@RestController
@RequestMapping("/codeGeneration/template")
public class TemplateControler {
	@Autowired
	private TemplateService templateService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getList")
	public Result getList(BaseDto baseDto) throws IOException {
		return templateService.findAll(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@GetMapping(value = "/getList/{type}")
	public List<Template> getList(@PathVariable(value = "type") String type) throws IOException {
		return templateService.findAll(type);
	}

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("/saveOrUpate")
	public Result add(@RequestBody Template template) throws Exception {
		template = templateService.save(template);
		return Result.resultOk(template.getId());
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	@PutMapping("/saveOrUpate")
	public Result modify(@RequestBody Template template) {
		templateService.update(template);
		return Result.resultOk(template.getId());
	}

	/**
	 * 通过ID获取对象
	 *
	 * @param roleId
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(templateService.findById(id));
	}

	/**
	 * 删除数据
	 *
	 *
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public Result delete(@PathVariable(value = "id") String id) {
		templateService.deleteById(id);
		return Result.resultOk();
	}
}
