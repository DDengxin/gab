package com.tengzhi.business.demo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.demo.dao.TestDao;
import com.tengzhi.business.demo.model.Test;
import com.tengzhi.business.demo.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;

	@Override
	public Result find(BaseDto dto) {
		return testDao.find(dto);
	}

	@Override
	public void exportExcel(HttpServletResponse response, HttpServletRequest request) {
		//获取ExcelUitl实例
		ExcelUtil util = ExcelUtil.getInstance();
		//初始化dto对象
		BaseDto dto = BaseDto.ValueOfRequest(request);
		//将导出的页面定义为0
		dto.setPageIndex(0);
		//查询数据并且生成Excel
		util.ExcelToWeb(request, "测试导出", response, testDao.findList(dto));
	}

	@Override
	public Result upload(MultipartFile files) throws Exception {
		ExcelUtil util = ExcelUtil.getInstance();
		Result map = util.readExcel(files, new Test(), 1,"excelChecks");
		//数据处理
		if (!Result.judge(map)) {
			return Result.error(map.get("msg").toString());
		}
		List<Test> dataList = (List<Test>) map.get(Result.KEY_DATA);
		for (int i = 0; i < dataList.size(); i++) {
			Test test = dataList.get(i);
			test.setUserId(UUIDUtil.uuid());
			testDao.save(test);
		}
		return Result.resultOk();
	}
}
