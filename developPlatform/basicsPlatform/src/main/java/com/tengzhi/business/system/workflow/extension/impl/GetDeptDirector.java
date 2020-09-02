package com.tengzhi.business.system.workflow.extension.impl;

import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.business.system.user.dao.SysUserDao;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.workflow.extension.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetDeptDirector implements Workflow {
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> find(Map<String, Object> map) {
        List<SysUser> list = sysUserDao.findAll(Specifications.where((c) -> {
            c.eq("positionName", "经理");
        }));
        return list;
    }

}
