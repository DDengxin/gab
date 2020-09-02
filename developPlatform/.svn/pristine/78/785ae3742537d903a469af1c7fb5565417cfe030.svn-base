package com.tengzhi.app.menu.service.impl;

import com.tengzhi.app.menu.dao.MenuDao;
import com.tengzhi.app.menu.service.MenuService;
import com.tengzhi.business.system.right.model.SysRight;
import com.tengzhi.business.xt.notice.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;
    /**
     * 查询app底部菜单
     * @param parentId  父id
     * @return
     */
    @Override
    public List<SysRight> findAppBottomMenu(String rightModule,String parentId) {
        return menuDao.findAppBottomMenu(rightModule,parentId);
    }
}
