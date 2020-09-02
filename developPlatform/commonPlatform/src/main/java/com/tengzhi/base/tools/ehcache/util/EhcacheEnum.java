package com.tengzhi.base.tools.ehcache.util;

/**
 * Ehcache缓存枚举
 * @author 鱼游浅水
 * @create 2020-08-03
 */
public enum EhcacheEnum {

    dynamicColumn("@dynamicColumn@"),
    button("@button@"),
    menu("@menu@");

    private String group;

    EhcacheEnum(String group) {
        this.group = group;
    }

    /**
     * 获取缓存枚举的组名
     * @return
     */
    public String getGroup() {
        return group;
    }

    private  void setGroup(String group) {
        this.group = group;
    }

}
