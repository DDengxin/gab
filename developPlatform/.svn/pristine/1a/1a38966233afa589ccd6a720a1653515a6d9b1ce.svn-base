package com.tengzhi.business.dynamicrow.service;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.dynamicrow.model.dynamicrow;

import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 */
public interface rowService {

    /**
    * @Param: [menu]
    * @return: java.util.List<com.tengzhi.business.dynamicrow.model.dynamicrow>
    * @Author: 鱼油浅水
    * @Date: 2020/8/1 14:44  (适用于第一次加载页面JS动态加载表格列)
    * @description:  传入菜单ID查出当前菜单下所有的Grid|列
    */
    Result AllRowObj(Map<String,Object> map);


    /**
    * @Param: [dynamicrow]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/8/1 14:54
    * @description: 常规数据修改与保存
    */
    Result SavaAndUpdate(dynamicrow dynamicrow);
    
    
    /** 
    * @Param: [rowId]
    * @return: com.tengzhi.base.jpa.result.Result 
    * @Author: 鱼油浅水
    * @Date: 2020/8/1 14:56 
    * @description: 删除菜单模块后清空对应的数据
     */
    Result Delete(String menuId);

    /**
    * @Param: []
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/8/1 15:01
    * @description: 第一次打开页面进行数据校验(看数据是否匹配不匹配删除)
    */
    Result Delete(Map<String,Object> map);

    Result isAdmin();

}
