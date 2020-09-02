package com.tengzhi.workflow.test.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.workflow.test.model.Employee;

public interface EmployeeDao extends BasedaoRepository<Employee,String> {


}
