package com.tengzhi.business.system.keygenerator;

import cn.hutool.core.util.ArrayUtil;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.ehcache.util.EhcacheEnum;
import com.tengzhi.business.dynamicrow.model.dynamicrow;
import com.tengzhi.business.system.right.model.SysRight;
import com.tengzhi.business.system.right.vo.SysRightVo;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Cache 缓存 key策略生成器
 * @author 鱼游浅水
 * @create 2020-07-31
 * 后续可扩展写自己的策略生成器
 */
@Configuration
public class KeyCacheConfig {

    /**
     * @Param: []
     * @return: org.springframework.cache.interceptor.KeyGenerator
     * @Author: 鱼油浅水
     * @Date: 2020/8/4 16:00
     * @description: String策略生成器
     */
    @Bean("keyGeneratorSingle")
    public KeyGenerator getKeyGenerator1() {
        return (obj, method, objects) -> {
            SessionUser user = SessionUser.SessionUser();
            return EhcacheEnum.dynamicColumn.getGroup() + user.getUserId() + objects[0];
        };
    }


    /**
     * @Param: []
     * @return: org.springframework.cache.interceptor.KeyGenerator
     * @Author: 鱼油浅水
     * @Date: 2020/8/4 16:00
     * @description: 对象策略生成器
     */
    @Bean("keyGeneratorObject")
    public KeyGenerator getKeyGenerator2() {
        return (obj, method, objects) -> {
            dynamicrow dynamicrow = (dynamicrow) objects[0];
            return genUserMixToken(EhcacheEnum.menu, dynamicrow.getMenuId());
        };
    }

    @Bean("keyGeneratorMap")
    public KeyGenerator getKeyGeneratorMap() {
        return (obj, method, objects) -> {
            Map<String, Object> map = (Map<String, Object>) objects[0];
            return genUserMixToken(EhcacheEnum.menu, map.get("menuId"));
        };
    }


    @Bean("keyGeneratorMenuObjvo")
    public KeyGenerator getKeyGeneratorMenuObjVo() {
        return (obj, method, objects) -> {
            SysRightVo sysRights = (SysRightVo) objects[0];
            return genUserMixToken(EhcacheEnum.menu, sysRights.getMenu().getRightModule());
        };
    }


    @Bean("keyGeneratorButtonObjvo")
    public KeyGenerator getKeyGeneratorButtonObjVo() {
        return (obj, method, objects) -> {
            SysRightVo sysRights = (SysRightVo) objects[0];
            return genUserMixToken(EhcacheEnum.menu, sysRights.getMenu().getOldid());
        };
    }


    @Bean("keyGeneratorMenuObj")
    public KeyGenerator getKeyGeneratorMenuObj() {
        return (obj, method, objects) -> {
            SysRight sysRights = (SysRight) objects[0];
            return genUserMixToken(EhcacheEnum.menu, sysRights.getRightModule());
        };
    }


    @Bean("keyGeneratorButtonObj")
    public KeyGenerator getKeyGeneratorButtonObj() {
        return (obj, method, objects) -> {
            SysRight sysRights = (SysRight) objects[0];
            return genUserMixToken(EhcacheEnum.menu, sysRights.getRightId());
        };
    }


    @Bean("keyGeneratorMenuObjMap")
    public KeyGenerator getKeyGeneratorMenuObjMap() {
        return (obj, method, objects) -> {
            Map<String, Object> map = (Map<String, Object>) objects[0];
            return genUserMixToken(EhcacheEnum.menu, map.get("rightModule"));
        };
    }


    @Bean("keyGeneratorButtonObjMap")
    public KeyGenerator getKeyGeneratorButtonObjMap() {
        return (obj, method, objects) -> {
            Map<String, Object> map = (Map<String, Object>) objects[0];
            return genUserMixToken(EhcacheEnum.menu, map.get("rightId"));
        };
    }


    @Bean("keyGeneratorMenu")
    public KeyGenerator getKeyGeneratorMenu() {
        return (obj, method, objects) -> genUserMixToken(EhcacheEnum.menu, objects[0]);
    }

    @Bean("keyGeneratorButton")
    public KeyGenerator getKeyGeneratorButton() {
        return (obj, method, objects) -> genUserMixToken(EhcacheEnum.button, objects[0]);
    }


    /**
     * 通过用户信息和和前后缀组合成唯一的cache键名
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    private static String genUserMixToken(String prefix, String suffix) {
        SessionUser sessionUser = SessionUser.SessionUser();
        return String.format("%s%s%s%s", prefix, sessionUser.getCorpId(), sessionUser.getUserId(), suffix);
    }

    /**
     * 通过缓存枚举名称和和前后缀组合成唯一的cache键名
     *
     * @param ehcacheEnum 枚举
     * @param suffix      后缀
     * @return
     */
    private static String genUserMixToken(EhcacheEnum ehcacheEnum, Object... suffix) {
        return genUserMixToken(ehcacheEnum.getGroup(), ArrayUtil.join(suffix, ""));
    }

}
