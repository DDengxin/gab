package com.tengzhi.business.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;

public interface TestService {
	Result find(BaseDto dto);

	void exportExcel(HttpServletResponse response, HttpServletRequest request);


	Result upload(MultipartFile files) throws Exception;
}
