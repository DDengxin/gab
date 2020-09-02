package com.tengzhi.business.codeGeneration.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.codeGeneration.dao.TemplateDao;
import com.tengzhi.business.codeGeneration.dao.impl.CodeGenerationdaoImpl;
import com.tengzhi.business.codeGeneration.service.CodeGenerationService;
import com.tengzhi.business.codeGeneration.vo.Documents;
import com.tengzhi.business.codeGeneration.vo.Structure;
import com.tengzhi.business.codeGeneration.vo.TableVo;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@Transactional
public class CodeGenerationServiceImpl implements CodeGenerationService {
	@Autowired
	private CodeGenerationdaoImpl dao;
	@Autowired
	private TemplateDao templateDao;

	@Override
	public BasePage<TableVo> findAllTable(BaseDto baseDto) throws IOException {
		return dao.findAllTable(baseDto);
	}

	@Override
	public void prit(String tablename, String tempId, HttpServletResponse response, String filename) throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		cfg.setCacheStorage(new freemarker.cache.NullCacheStorage());
		StringTemplateLoader stl = new StringTemplateLoader();
		com.tengzhi.business.codeGeneration.model.Template temp = templateDao.findById(tempId).orElse(null);
		stl.putTemplate("myTemplate", StringEscapeUtils.unescapeHtml3(temp.getContext()));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateLoader(stl);
		Template template = cfg.getTemplate("myTemplate", "UTF-8");
		Map<String, Object> root = new HashMap<String, Object>();
		List<TableVo> list = dao.findTable(tablename);
		List<Documents> documents = new ArrayList<Documents>();
		list.stream().forEach(x -> {
			Documents doc = new Documents();
			doc.setTabname(x.getTabname());
			doc.setComment(x.getComment());
			doc.setStructure(dao.findStructure(x.getTabname()));
			documents.add(doc);
		});
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msword");
		response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".doc");
		response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		root.put("List", documents);
		Writer out = response.getWriter();
		template.process(root, out);
		response.flushBuffer();
	}

	@Override
	public List<Structure> findStructure(String tablename) {
		return dao.findStructure(tablename);
	}

	@Override
	public List<TableVo> findAllTable() {
		// TODO Auto-generated method stub
		return dao.findTable("");
	}
}
