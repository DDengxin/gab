package com.tengzhi.business.platform.form.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.form.model.DynamicForm;
import com.tengzhi.business.platform.form.service.DynamicFormService;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("platform/form")
public class DynamicFormControler {

	@Autowired
	private DynamicFormService dynamicFormService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询待办数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/findAll")
	public Result findAll(BaseDto baseDto) throws IOException {
		return dynamicFormService.findAll(baseDto).getResult();
	}

	/**
	 * 新增
	 * 
	 * @param dynamicForm
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	public Result save(@RequestBody DynamicForm dynamicForm) throws Exception {
		return dynamicFormService.save(dynamicForm);
	}

	/**
	 * 获取单条
	 * 
	 * @param Id
	 * @return
	 */
	@GetMapping(value = "/getById/{Id}")
	public Result getById(@PathVariable String Id) {
		return Result.resultOk(dynamicFormService.getById(Id));
	}

	/**
	 * 修改
	 * 
	 * @param dynamicForm
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/save")
	public Result update(@RequestBody DynamicForm dynamicForm) throws Exception {
		return dynamicFormService.update(dynamicForm);
	}

	/**
	 * 删除
	 * 
	 * @param dynamicForm
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/delete/{Id}")
	public Result delete(@PathVariable String Id) throws Exception {
		return dynamicFormService.delete(Id);
	}

	/**
	 * 获取Field下拉框
	 */
	@GetMapping(value = "/getField")
	public List<SelectVo> getField() throws Exception {
		return dynamicFormService.getField();
	}

	/**
	 * 根据参数获取表单配置
	 */
	@RequestMapping(value = "/getParams")
	public Result getParams(String type) throws Exception {
		return Result.resultOk(dynamicFormService.findAll(type));
	}

	/**
	 * 获取行业分类配置的条数
	 */
	@GetMapping(value = "getFieldByClassify")
	public int getFieldByClassify(String classify) throws Exception {
		return dynamicFormService.getFieldByClassify(classify) + 1;
	}

	/**
	 * 获取行业分类对应的配置
	 */

	@PostMapping(value = "getDynamicFormByClassify")
	public List<Map<String, Object>> getDynamicFormByClassify(String classify) throws Exception {
		return dynamicFormService.getDynamicFormByClassify(classify);
	}

	/**
	 * 根据行业拼接sql返回
	 */
	@GetMapping(value = "getFieldsssByClassify")
	public String getFieldsssByClassify(String classify) throws Exception {
		return dynamicFormService.getShowSql(classify);
	}

	/**
	 * 根据行业拼接sql返回值
	 */
	@GetMapping(value = "getdynamicFormValue/{classify}/{id}/{table}")
	public Result getFieldsssByClassify(@PathVariable String classify, @PathVariable String id,
			@PathVariable String table) throws Exception {
		return dynamicFormService.getShowSqlValue(classify, id, table);
	}

}
