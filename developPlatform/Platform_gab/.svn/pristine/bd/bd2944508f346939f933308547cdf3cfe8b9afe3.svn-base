package com.tengzhi.business.platform.enroll.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.enroll.dao.ExpertDao;
import com.tengzhi.business.platform.enroll.dao.ExpertSqlDao;
import com.tengzhi.business.platform.enroll.model.G_Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExpertSqlDaoImpl  extends BasicsDaoImpl<G_Expert, String>  implements  ExpertSqlDao{
@Autowired
private  ExpertDao expertDao;
	@Override
	public List<Map<String, Object>> getExpertTech(BaseDto baseDto) {
		SecurityUser securityUser= SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		String sqlwhere = "where  transactor_id='" + securityUser.getUserId() + "'";
	
		String sql = "select back.*,descri.process_classify,descri.process_classify_id,descri.process_module,descri.process_system_type from sys_workflow_backlog  back left join sys_workflow_describe descri on back.process_id=descri.\"id\" "
				+ sqlwhere;
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return expertDao.SelectListMap(sql, countSql, baseDto);
	}

}
