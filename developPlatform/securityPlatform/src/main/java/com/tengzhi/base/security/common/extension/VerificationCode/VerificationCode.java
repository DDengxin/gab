package com.tengzhi.base.security.common.extension.VerificationCode;

import cn.hutool.json.JSONUtil;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.extension.VerificationCode.constant.VerificationType;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.base.tools.captcha.utils.CaptchaUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 图形验证码 过滤器
 */
public class VerificationCode extends OncePerRequestFilter {
    private SecurityProperty securityProperty = SpringUtil.getBean(SecurityProperty.class);

    public VerificationCode() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Objects.equals(request.getRequestURI(), securityProperty.getBrowser().getLoginProcessingUrl())) {
            String verifyCode = request.getParameter("randcode");
            String type = request.getParameter("type");
            if (VerificationType.LoginByUser.getName().equals(request.getParameter("type"))) {
                //图形验证码
                if (!CaptchaUtil.verIgnoreCase(verifyCode, request)) {
                    wirteResponse(response, 200, "验证码错误,请刷新验证码并重新填写");
                } else {
                    filterChain.doFilter(request, response);
                }
            } else if (VerificationType.LoginByUserWithOutCode.getName().equals(type)
                    || VerificationType.LoginByTokenWithOutCode.getName().equals(type)) {
                //非验证码方式登录
                filterChain.doFilter(request, response);
            } else {
                wirteResponse(response, 200, "认证方式不支持");
            }
        } else {
            filterChain.doFilter(request, response);
        }
        return;
        //filterChain.doFilter(request, response);
    }

    /**
     * 返回数据
     *
     * @param response HttpServletResponse
     * @param code     响应码
     * @param message  信息
     */
    private static void wirteResponse(HttpServletResponse response, Integer code, String message) {
        response.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSONUtil.toJsonStr(Result.error(message)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.flush();
                writer.close();
            }
        }
    }

}
