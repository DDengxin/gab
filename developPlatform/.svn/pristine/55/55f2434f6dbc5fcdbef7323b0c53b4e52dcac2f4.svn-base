package com.tengzhi.business.codeGeneration.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.codeGeneration.dao.TemplateDao;
import com.tengzhi.business.codeGeneration.model.Template;
import com.tengzhi.business.codeGeneration.service.TemplateService;

@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateDao templateDao;

	@Override
	public BasePage<Template> findAll(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return templateDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.startingWith("name", map.get("name"));
		}));
	}

	@Override
	public Template save(Template Template) throws Exception {
		Template.setId(UUIDUtil.uuid());
		Template.setCreationTime(new Date());
		Template.setModifyTime(new Date());
		return templateDao.save(Template);
	}

	@Override
	public void update(Template Template) {
		Template.setModifyTime(new Date());
		templateDao.update(Template);
	}

	@Override
	public void deleteById(String roleId) {
		templateDao.deleteById(roleId);
	}

	@Override
	public Template findById(String id) {
		return templateDao.findById(id).orElse(null);
	}

	@Override
	public List<Template> findAll(String type) {
		return templateDao.findAll(Specifications.where((c) -> {
			c.startingWith("type", type);
		}));
	}
}
