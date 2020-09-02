package com.tengzhi.business.system.datachart.service;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;

public interface SysDatachartService{
	Result findAll(BaseDto dto);
}
