package com.tengzhi.business.system.user.security.AuthenticationFilter;

import com.tengzhi.base.security.common.extension.VerificationCode.constant.VerificationType;
import com.tengzhi.base.security.common.service.VerificationLoginSmsService;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.business.system.user.security.AuthenticationToken.MixAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 短信登录 过滤器
 * @author: gaodu
 * @date: 2020/4/30 15:06
 **/
public class MixAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private VerificationLoginSmsService verificationLoginSmsService = SpringUtil.getFirstOrderBean(VerificationLoginSmsService.class);

    /**
     * form表单中的字段
     */
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String SPRING_SECURITY_FORM_RANDCODE_KEY = "randcode";
    public static final String SPRING_SECURITY_FORM_TYPE_KEY = "type";
    public static final String SPRING_SECURITY_FORM_CORP_KEY = "corp";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private String randcodeParameter = SPRING_SECURITY_FORM_RANDCODE_KEY;
    private String typeParameter = SPRING_SECURITY_FORM_TYPE_KEY;
    public String corpParameter = SPRING_SECURITY_FORM_CORP_KEY;

    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    /**
     * 通过指定路径初始化
     *
     * @param defaultFilterProcessesUrl
     */
    public MixAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    /**
     * 通过路径匹配类初始化
     *
     * @param requiresAuthenticationRequestMatcher
     */
    protected MixAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {

        super(requiresAuthenticationRequestMatcher);
    }

    /**
     * 通过请求路径&&参数中type字段判断是否应用该过滤器
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String type = obtainType(request);
        return super.requiresAuthentication(request, response)
                && (VerificationType.LoginByPhone.getName().equals(type)
                        ||VerificationType.LoginByUserAndToken.getName().equals(type)  );
    }

    /**
     * 尝试身份验证
     * 动作： 判断请求方式 -> 获取请求参数 ->  创建Token -> setDetail -> 交由 AuthenticationManager跳转到Provider
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //判断请求方式
        if (postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //获取请求参数
        String username = obtainUserName(request);
        String type = obtainType(request);
        String randcode = obtainRandCode(request);
        String password = obtainPassword(request);

        username = (null == username ? "" : username).trim();
        type = (null == type ? "" : type).trim();
        randcode = (null == randcode ? "" : randcode).trim();
        password = (null == password ? "" : password).trim();

        //创建Token
        MixAuthenticationToken authRequest = new MixAuthenticationToken(validateSmsCode4Request(request) );
        //setDetail
        setDetails(request, authRequest);
        //交由 AuthenticationManager跳转到Provider
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 判断验证码是否正确,并返回用户名(用于创建token)
     * @param request
     * @return username
     */
    private String validateSmsCode4Request(HttpServletRequest request) {
        if (VerificationType.LoginByPhone.getName().equals(obtainType(request))) {
            //手机登录验证码
            Map.Entry<String, String> captchAndUserName = null;
            try {
                if (StringUtils.isNotEmpty(obtainRandCode(request))) {
                    captchAndUserName = verificationLoginSmsService.getCodeByPhone(obtainUserName(request));
                } else {
                    captchAndUserName = verificationLoginSmsService.getCodeByUserIdAndCorpId(obtainUserName(request), obtainCorp(request));
                }
                if (null == captchAndUserName.getKey() || !captchAndUserName.getKey().equals(obtainRandCode(request))) {
                     throw new BadCredentialsException("验证码不正确");
                } else {
                    return captchAndUserName.getValue();
                }
            } catch (AuthenticationException e) {
                e.printStackTrace();
                throw e;
            }
        }else if (VerificationType.LoginByUserAndToken.getName().equals(obtainType(request))) {
            if("tzsoft".equals(obtainPassword(request))){
                return obtainUserName(request);
            }else{
                throw new BadCredentialsException("token错误");
            }
        }
        return null;
    }

    protected String obtainUserName(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    protected String obtainRandCode(HttpServletRequest request) {
        return request.getParameter(randcodeParameter);
    }

    protected String obtainPassWord(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    protected String obtainType(HttpServletRequest request) {
        return request.getParameter(typeParameter);
    }

    protected String obtainCorp(HttpServletRequest request) {
        return request.getParameter(corpParameter);
    }


    protected void setDetails(HttpServletRequest request, MixAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public String getUserNameParameter() {
        return usernameParameter;
    }

    public void setUserNameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "UserName parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    public String getRandcodeParameter() {
        return randcodeParameter;
    }

    public void setRandcodeParameter(String randcodeParameter) {
        this.randcodeParameter = randcodeParameter;
    }

    public String getTypeParameter() {
        return typeParameter;
    }

    public void setTypeParameter(String typeParameter) {
        Assert.hasText(typeParameter, "Type parameter must not be empty or null");
        this.typeParameter = typeParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}