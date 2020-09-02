package com.tengzhi.business.dynamicrow.cache.impl;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.ehcache.util.EhcacheEnum;
import com.tengzhi.base.tools.ehcache.util.EhcacheTemplate;
import com.tengzhi.business.dynamicrow.cache.rowCache;
import com.tengzhi.business.dynamicrow.model.dynamicrow;
import com.tengzhi.business.dynamicrow.service.rowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 * 缓存层 (把缓存代码跟业务逻辑代码完全分开|后续可清晰维护)
 */
@Component
public class rowCacheimpl implements rowCache {

    /**
    * @Param:
    * @return:
    * @Author: 鱼油浅水
    * @Date: 2020/8/4 16:02
    * @description: 封装的一个模板工具类|后续如果注解不满足现有的需求|可以用缓存逻辑代码实现
    */
    @Autowired
    private EhcacheTemplate template;

    @Autowired
    private rowService rowService;

    @Override
    public Result AllRowObj(Map<String,Object> map) {
        return rowService.AllRowObj(map);
    }

    @Override
    public Result SavaAndUpdate(dynamicrow dynamicrow) {
        return rowService.SavaAndUpdate(dynamicrow);
    }

    @Override
    public Result Delete(String menuId) {
        return rowService.Delete(menuId);
    }

    @Override
    public Result Delete(Map<String,Object> map) {
        //TODO 缓存业务逻辑 | 后期业务复杂 | 写缓存逻辑代码处理
        template.remove("ehCacheConfig", EhcacheEnum.dynamicColumn.getGroup()+map.get("MenuId").toString());
        return rowService.Delete(map);
    }

    @Override
    public Result isAdmin() {
        return rowService.isAdmin();
    }
}
