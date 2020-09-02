package com.tengzhi.base.security.common.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 安全框架-权限枚举类
 *
 * @author: gaodu
 * @date: 2020/8/21 8:58
 **/
public enum AuthorityRoleEnum {
    /**
     * 用于开发平台 - 普通用户
     */
    PLATFORM_USER("PLATFORM_USER"),
    /**
     * 用于开发平台 - 超级管理员
     */
    PLATFORM_ADMIN("PLATFORM_ADMIN"),
    /**
     * 用于哥爱帮 - 普通用户
     */
    GAB_USER("GAB_USER"),
    /**
     * 用于Activici7 普通用户
     */
    ACTIVITI_USER("ACTIVITI_USER"),
    /**
     * 用于Activici7 超级管理员
     */
    ACTIVITI_ADMIN("ACTIVITI_ADMIN");

    private String name;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    AuthorityRoleEnum(String name) {
        setName(name);
    }

    public String getRolePrefixName() {
        return String.format("ROLE_%s", getName());
    }

    public SimpleGrantedAuthority toSimpleGrantedAuthority() {
        return new SimpleGrantedAuthority(getRolePrefixName());
    }
}
