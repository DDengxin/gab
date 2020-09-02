package com.tengzhi.business.demo.service.impl;

import org.springframework.stereotype.Service;

import com.tengzhi.base.tools.excel.ExcelCheck;
import com.tengzhi.business.demo.model.Test;

@Service
public class ExcelChecks implements ExcelCheck<Test> {
	@Override
	public Test Check(Test t) {
		Test test = t;
		if ("男".equals(test.getSex())) {
			throw new RuntimeException("性别列，不能为男");
		}
		return test;
	}
}
