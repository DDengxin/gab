package com.tengzhi.business.codeGeneration.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.codeGeneration.service.CodeGenerationService;
import com.tengzhi.business.codeGeneration.vo.Structure;
import com.tengzhi.business.codeGeneration.vo.TableVo;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/codeGeneration")
public class CodeGenerationControler {

	@Autowired
	private CodeGenerationService codeGenerationService;

	@GetMapping("/table/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询所有表结构
	 *
	 * @return
	 */
	@PostMapping(value = "/findAllTable")
	public Result findAllTable(BaseDto baseDto) throws IOException {
		return codeGenerationService.findAllTable(baseDto).getResult();
	}
	
	/**
	 * 查询所有表结构
	 *
	 * @return
	 */
	@PostMapping(value = "/findAllTables")
	public List<TableVo> findAllTables() throws IOException {
		return codeGenerationService.findAllTable();
	}
	
	
	
	/**
	 * 查询表结构
	 *
	 * @return
	 */
	@PostMapping(value = "/findTableDetail")
	public List<Structure> findTableDetail(String tableName) throws IOException {
		return codeGenerationService.findStructure(tableName);
	}

	/**
	 * 查询表结构
	 *
	 * @return
	 */
	@PostMapping(value = "/findTable")
	public Result findTable(String tableName) throws IOException {
		return Result.formPage(codeGenerationService.findStructure(tableName));
	}

	/**
	 * 打印
	 *
	 * @return
	 * @throws TemplateException
	 */
	@GetMapping(value = "/prit")
	public void prit(String tablename, HttpServletResponse response, String tempId, String filename) throws IOException, TemplateException {
		codeGenerationService.prit(tablename, tempId, response, filename);
	}

}
