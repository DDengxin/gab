package com.tengzhi.base.security.common.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.enums.LoginTypeEnum;
import com.tengzhi.base.security.common.extension.VerificationCode.constant.VerificationType;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import com.tengzhi.base.security.common.service.LoginLogsService;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.base.tools.log.Log;

import cn.hutool.json.JSONUtil;

/**
 * @author lqy 自定义登陆成功接口,默认json方式考虑后期，保留跳转页面配置，不配置默认返回上次请求路径
 */
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private SecurityProperty securityProperty = SpringUtil.getBean(SecurityProperty.class);

	private LoginLogsService loginLogsService = SpringUtil.getBean(LoginLogsService.class);

    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		loginLogsService.insertLogs(VerificationType.getValueByName(request.getParameter("type")));
		Log.info("验证成功！");
		// 存到session里，方便取值。
		request.getSession().setAttribute("authentication", authentication.getPrincipal());
		if (Objects.equals(securityProperty.getAuthentication().getLoginType(), LoginTypeEnum.JSON)) {
			response.setStatus(200);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			writer.write(JSONUtil.toJsonStr(Result.resultOk()));
			writer.flush();
			writer.close();
		} else {
			// 支持跳转到自定义页面
			if (StringUtils.isNotBlank(securityProperty.getAuthentication().getLoginSuccessPage())) {
				redirectStrategy.sendRedirect(request, response,
						securityProperty.getAuthentication().getLoginSuccessPage());
			} else {
				// 跳转到上一次请求的页面上
				super.onAuthenticationSuccess(request, response, authentication);
			}
		}
	}
}
