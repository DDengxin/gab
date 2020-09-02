package com.tengzhi.base.jpa.sql;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author 王翔
 * @create 2020-04-28
 */
@Deprecated
public class nativeSql {


    /**
     * @Param: Sql拼接条件方法
     * @return:
     * @Author: 王翔
     * @Date: 2020/4/28 23:12
     * @description:
     */
    @Deprecated
    public static String getwhere(String name, String equal, String type) {
        if (StringUtils.isEmpty(name)) {
            return name;
        } else {
            return StrUtil.addPrefixIfNot(StrUtil.addSuffixIfNot(name, "%"), "%");
        }
    }


    /**
     * @Param: [name, value]
     * @return: java.lang.String
     * @Author: 王翔
     * @Date: 2020/4/28 21:47
     * @description: 全加%
     */
    @Deprecated
    public static String like(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        } else {
            return StrUtil.addPrefixIfNot(StrUtil.addSuffixIfNot(name, "%"), "%");
        }
    }


    /**
     * @Param: [name, value]
     * @return: java.lang.String
     * @Author: 王翔
     * @Date: 2020/4/28 21:47
     * @description: 尾追加%
     */
    @Deprecated
    public static String endingWith(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        } else {
            return StrUtil.addSuffixIfNot(name, "%");
        }
    }


    /**
     * @Param: [name, value]
     * @return: java.lang.String
     * @Author: 王翔
     * @Date: 2020/4/28 21:47
     * @description: 前加%
     */
    @Deprecated
    public static String startingWith(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        } else {
            return StrUtil.addPrefixIfNot(name, "%");
        }
    }

    /**
     * @Param: []
     * @return: java.lang.String
     * @Author: 王翔
     * @Date: 2020/4/30 1:36
     * @description: 如果等于空赋值默认
     */
    @Deprecated
    public static String isDefault(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return "";
        } else {
            return obj.toString();
        }
    }


}
