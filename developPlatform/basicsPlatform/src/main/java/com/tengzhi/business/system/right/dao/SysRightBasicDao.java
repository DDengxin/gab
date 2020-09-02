package com.tengzhi.business.system.right.dao;

import java.util.List;

import com.tengzhi.business.system.right.model.SysRight;

public interface SysRightBasicDao  {

    /**
     * 通过用户ID和公司账套获取有权限的菜单
     * @param userid 用户ID
     * @param rightModule 菜单模块
     * @return
     */
    List<SysRight> findByUserId(String userid, String rightModule);

	List<SysRight> findParentIdAndRightType(String userid, String rightId, String typeButton);




}
