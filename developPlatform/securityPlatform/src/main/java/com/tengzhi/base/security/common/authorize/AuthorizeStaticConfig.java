package com.tengzhi.base.security.common.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**权限自定义配置管理
 * @author lqy
 *方便后期不同自定义加入
 *拆分配置
 */
public interface AuthorizeStaticConfig {
    void config(HttpSecurity httpSecurity) throws Exception;
}
