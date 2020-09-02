package com.tengzhi.base.security.common.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.enums.LoginTypeEnum;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.base.tools.log.Log;



/**
 * @author lqy
 *自定义登陆失败接口,默认json方式考虑后期，保留跳转页面配置
 */
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private SecurityProperty securityProperty=SpringUtil.getBean(SecurityProperty.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Log.info("验证失败！");
        if (Objects.equals(securityProperty.getAuthentication().getLoginType(), LoginTypeEnum.JSON)) {
        	response.setStatus(200);
    		response.setContentType("application/json;charset=UTF-8");
    		PrintWriter writer = null;
    		try {
    			writer = response.getWriter();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		ObjectMapper objectMapper = new ObjectMapper();
    		writer.write(objectMapper.writeValueAsString(Result.error("用户名或密码错误!")));
    		writer.flush();
    		writer.close();
        } else {
            response.setContentType("text/html;charset=UTF-8");
            if (StringUtils.isNotBlank(securityProperty.getAuthentication().getLoginErrorPage())) {
                // 将异常信息存到session对象
                request.getSession().setAttribute("exception", exception);
                redirectStrategy.sendRedirect(request, response, securityProperty.getAuthentication().getLoginErrorPage());
            } else {
                super.onAuthenticationFailure(request, response, exception);
            }
        }
    }
}

