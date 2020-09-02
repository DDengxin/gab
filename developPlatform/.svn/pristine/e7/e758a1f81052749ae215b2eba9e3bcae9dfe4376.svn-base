package com.tengzhi.business.codeGeneration.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.codeGeneration.vo.Structure;
import com.tengzhi.business.codeGeneration.vo.TableVo;

import freemarker.template.TemplateException;

public interface CodeGenerationService {

	BasePage<TableVo> findAllTable(BaseDto baseDto) throws IOException;

	void prit(String tablename, String tempId, HttpServletResponse response, String filename) throws IOException, TemplateException;

	List<Structure> findStructure(String tablename);
	
	
	List<TableVo> findAllTable();

}
