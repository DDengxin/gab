package com.tengzhi.base.security.common.authorize;

import org.springframework.security.config.annotation.web.builders.WebSecurity;

public interface AuthorizeWebconfig {
    void config(WebSecurity web) throws Exception;
}
