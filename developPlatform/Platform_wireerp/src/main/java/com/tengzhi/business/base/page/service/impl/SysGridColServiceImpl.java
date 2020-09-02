package com.tengzhi.business.base.page.service.impl;

import com.tengzhi.business.base.page.dao.SysGridColDao;
import com.tengzhi.business.base.page.model.SysGrid;
import com.tengzhi.business.base.page.model.SysGridCol;
import com.tengzhi.business.base.page.service.SysGridColService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
class SysGridColServiceImpl implements SysGridColService {
    @Autowired
    private SysGridColDao sysGridColDao;

    @Override
    public List<SysGridCol> findByGridIdOrderByGridOrdAsc(String gridId) {
		return sysGridColDao.findByGridIdOrderByGridOrdAsc(gridId);
    }


}
