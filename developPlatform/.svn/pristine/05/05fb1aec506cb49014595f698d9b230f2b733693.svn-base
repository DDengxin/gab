package com.tengzhi.business.codeGeneration.service;

import java.io.IOException;
import java.util.List;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.codeGeneration.model.Template;

public interface TemplateService extends BaseService<Template, String> {
	BasePage<Template> findAll(BaseDto baseDto) throws IOException;

	Template save(Template template) throws Exception;

	void update(Template template);

	void deleteById(String id);
	
	Template findById(String id);

	List<Template> findAll(String type);


}
