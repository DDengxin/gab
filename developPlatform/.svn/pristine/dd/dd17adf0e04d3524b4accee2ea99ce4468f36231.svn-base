package com.tengzhi.business.system.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.business.system.user.dao.SysUsersDao;
import com.tengzhi.business.system.user.model.SysUser;
@Repository
public class SysUsersDaoImpl extends BasicsDaoImpl<SysUser, String> implements SysUsersDao {

	@Override
	public List<SysUser> findUsers(String roleIds) {
		return super.queryBean("select * from sys_user where user_id in (select user_id from sys_user_r_role where role_id  in (:roleIds))", roleIds);
	}
}
