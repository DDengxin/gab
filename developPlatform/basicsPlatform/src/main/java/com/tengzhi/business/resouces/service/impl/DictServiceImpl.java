package com.tengzhi.business.resouces.service.impl;

import com.tengzhi.base.dict.RequestMethod;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.resouces.service.DictService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.main.dao.SysUserTodoDao;
import com.tengzhi.business.system.main.model.SysUserTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private SysUserTodoDao sysUserTodoDao;

    @Override
    public List<SelectVo> getRequestMethod() {
        List<SelectVo> list = new ArrayList<SelectVo>();
        for (RequestMethod row : RequestMethod.values()) {
            list.add(new SelectVo(row.getName(), row.getName()));
        }
        return list;
    }

    @Override
    public List<SelectVo> getBoolean(String trueText, String falseText) {
        List<SelectVo> list = new ArrayList<SelectVo>();
        list.add(new SelectVo(true, StringUtils.isEmpty(trueText) ? "是" : trueText));
        list.add(new SelectVo(false, StringUtils.isEmpty(falseText) ? "否" : falseText));
        return list;
    }

    @Override
    public Set<SelectVo> getlabel() {
        SessionUser securityUser=SessionUser.SessionUser();
        List<SysUserTodo> to=sysUserTodoDao.findAll(Specifications.where((c) -> {
            c.startingWith("userId",securityUser.getUserId());
            c.startingWith("dataCorp",securityUser.getCorpId());
        }));
        Set<String> list = new HashSet<>();
        to.forEach(item->{list.add(item.getLabel());});
        Set<SelectVo> select=new HashSet<>();
        list.forEach(i->{
            if(i!="" || i!=null){
                select.add(new SelectVo(i,i));
            }
        });
        return select;
    }



}
