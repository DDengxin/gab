package com.tengzhi.business.system.workflow.controller;

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

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.workflow.model.LinkTable;
import com.tengzhi.business.system.workflow.service.LinkTableService;

@RestController
@RequestMapping("system/workflow/")
public class LinkTableController {
	@Autowired
	private LinkTableService linkTableService;

	@GetMapping("linkTable/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询配置数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "linkTable/list")
	@Log("查询业务表单")
	public Result getLinkTableList(BaseDto baseDto) {
		return linkTableService.findAll(baseDto).getResult();
	}

	/**
	 * 查询下拉框数据列表
	 * 
	 * @return
	 */
	@GetMapping("linkTable/findAll")
	public List<LinkTable> findAll() {
		return linkTableService.findAll();
	}

	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "linkTable/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(linkTableService.findById(id));
	}

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("linkTable")
	@Log("新增业务表单")
	public Result add(@RequestBody LinkTable linkTable) throws Exception {
		linkTable = linkTableService.save(linkTable);
		return Result.resultOk(linkTable.getWorkflowTableId());
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	@PutMapping("linkTable")
	@Log("修改业务表单")
	public Result modify(@RequestBody LinkTable linkTable) {
		linkTableService.update(linkTable);
		return Result.resultOk(linkTable.getWorkflowTableId());
	}

	/**
	 * 删除数据
	 *
	 *
	 * @return
	 */
	@DeleteMapping(value = "linkTable/{id}")
	@Log("删除业务表单")
	public Result delete(@PathVariable(value = "id") String id) {
		return linkTableService.deleteById(id);
	}
}
