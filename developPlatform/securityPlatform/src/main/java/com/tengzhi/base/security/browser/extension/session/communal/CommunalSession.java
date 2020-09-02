package com.tengzhi.base.security.browser.extension.session.communal;

import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import com.tengzhi.base.tools.log.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommunalSession {
	private final String message = "由于您长时间未操作,登录已过期,请您重新登录";
	private final String Kickmessage = "您的账号已在其他地方登录,请注意密码是否被泄露！";
	private String destinationUrl;
	private boolean createNewSession = true;
	@Autowired
	private SecurityProperty securityProperty;

	public CommunalSession(String invalidSessionUrl) {
		this.destinationUrl = invalidSessionUrl;
	}

	protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (createNewSession) {
			request.getSession();
		}
		int code = 401;
		String msg = null;
		String redirect = null;

		msg = isConcurrency() ? Kickmessage:message;
		if (StringUtils.isNotBlank(securityProperty.getBrowser().getSessionFailureUrl())) {
			redirect = destinationUrl;
		} else {
			msg = "登录超时,请重新登陆";
			redirect = securityProperty.getBrowser().getLoginPage();
		}
		if(isXHR(request)){
			Result r = new Result();
			r.put("code", code);
			r.put("msg", msg);
			r.put("redirect", redirect);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(JSONUtil.toJsonStr(r));
		}else{
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("<script>alert('" + msg + "');</script>");
			response.getWriter().write("<script>top.location.href='" + redirect + "';</script>");
		}
		response.flushBuffer();
	}

	/**
	 * session是不是被挤的
	 * 
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}

	private boolean isXHR(HttpServletRequest request){
		String requestedWith = request.getHeader("x-requested-with");
		return requestedWith != null && "XMLHttpRequest".equalsIgnoreCase(requestedWith);
	}
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}
}
