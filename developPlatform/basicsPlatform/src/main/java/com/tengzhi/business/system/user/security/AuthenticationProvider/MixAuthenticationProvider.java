package com.tengzhi.business.system.user.security.AuthenticationProvider;

import com.tengzhi.business.system.user.security.AuthenticationToken.MixAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 短信登陆鉴权 Provider(供应商)，要求实现 AuthenticationProvider 接口
 *
 * @author jitwxs
 * @since 2019/1/9 13:59
 */
public class MixAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     *
     * @param authentication 鉴权对象(token)
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MixAuthenticationToken authenticationToken = (MixAuthenticationToken) authentication;
        String username = (String) authenticationToken.getPrincipal();
        //checkSmsCode(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        MixAuthenticationToken authenticationResult = new MixAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * 判断AuthenticationManager抛送的Token是否能被Provider接收处理(该处是判断是否符合SmsAuthenticationToken)
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsAuthenticationToken 的子类或子接口
        return MixAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}