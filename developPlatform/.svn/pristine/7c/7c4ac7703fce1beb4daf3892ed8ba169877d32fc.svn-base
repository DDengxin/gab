package com.tengzhi.base.security.browser.extension.Logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengzhi.base.jpa.result.Result;


public class BrowserLogout implements LogoutSuccessHandler {
    private String logoutSuccessUrl;
    public BrowserLogout(String logoutSuccessUrl) {
        this.logoutSuccessUrl = logoutSuccessUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (StringUtils.isNotBlank(logoutSuccessUrl)) {
            response.sendRedirect(logoutSuccessUrl);
        } else {

			Result r = new Result();
			r.put("code", "200");
			r.put("msg", "退出成功");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(JSONUtil.toJsonStr(r));
			response.flushBuffer();
        }
    }
}