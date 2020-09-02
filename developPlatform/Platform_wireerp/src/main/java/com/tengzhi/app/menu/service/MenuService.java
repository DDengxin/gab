package com.tengzhi.app.menu.service;

import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.right.model.SysRight;

import java.util.List;

public interface MenuService extends BaseService {
    /**
     * 查询app底部菜单
      * @param parentId  父id
     * @return
     */
    List<SysRight> findAppBottomMenu(String rightModule,String parentId);
}
