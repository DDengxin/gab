package com.tengzhi.business.system.workflow.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.workflow.dao.DescribeDao;
import com.tengzhi.business.system.workflow.dao.LinkTableDao;
import com.tengzhi.business.system.workflow.model.LinkTable;
import com.tengzhi.business.system.workflow.service.LinkTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LinkTableServiceImpl implements LinkTableService {
	@Autowired
	private LinkTableDao linkTableDao;

	@Autowired
	private DescribeDao describeDao;

	@Override
	public BasePage<LinkTable> findAll(BaseDto baseDto){
		Map<String, String> map = baseDto.getParamsMap();
		return linkTableDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.startingWith("workflowTableDescribe", map.get("workflowTableDescribe"));
			c.startingWith("workflowTableName", map.get("workflowTableName"));
		}));
	}

	@Override
	public LinkTable findById(String Id) {
		return linkTableDao.findById(Id).orElse(null);
	}

	@Override
	public LinkTable save(LinkTable linkTable)  {
		return linkTableDao.save(linkTable);
	}

	@Override
	public void update(LinkTable linkTable) {
		linkTableDao.dynamicSave(linkTable, linkTableDao.findById(linkTable.getWorkflowTableId()).orElse(null));
	}

	@Override
	public Result deleteById(String Id) {
		if(describeDao.findAll(Specifications.where((c)->{
			c.eq("workflowTableId",Id);
		})).size()==0){
			linkTableDao.deleteById(Id);
			return Result.resultOkMsg("删除成功");
		}else{
			return Result.error("该数据与工作流待流程关联");
		}
	}

	@Override
	public List<LinkTable> findAll() {
		return linkTableDao.findAll();
	}
}
