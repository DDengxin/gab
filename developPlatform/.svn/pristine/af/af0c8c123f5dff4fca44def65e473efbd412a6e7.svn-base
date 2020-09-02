package com.tengzhi.service.impl;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.security.common.service.LoginLogsService;
import com.tengzhi.service.Logservice;
import com.tengzhi.tool.RequestUtils;

@Service
public class LoginLogsServiceImpl implements LoginLogsService {
	@Autowired
	private Logservice logservice;

	@Override
	public void insertLogs(String loginType) {
		com.tengzhi.model.Log log = new com.tengzhi.model.Log();
		HttpServletRequest request = RequestUtils.getHttpServletRequest();
		log.setBrowser(RequestUtils.getBrowser(request));// 浏览器
		log.setCreateTime(new Timestamp(System.currentTimeMillis()));
		log.setRequestIp(RequestUtils.getIp(request));// ip
		log.setUsername(RequestUtils.getUsername());// 用户名
		log.setAddress(RequestUtils.getCityInfo(RequestUtils.getIp(request)));// ip地址
		log.setLogType("0");
		log.setLoginType(loginType);
		log.setName(RequestUtils.getName());
		logservice.save(log);
	}
}