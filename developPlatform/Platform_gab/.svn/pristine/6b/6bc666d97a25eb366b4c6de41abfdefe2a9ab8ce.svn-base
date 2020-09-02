package com.tengzhi.business.platform.manage.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.form.dao.DynamicFormSqlDao;
import com.tengzhi.business.platform.manage.dao.ServiceManageDao;
import com.tengzhi.business.platform.manage.dao.ServiceManageSqlDao;
import com.tengzhi.business.platform.manage.model.G_ServiceManage;
import com.tengzhi.business.platform.need.dao.UserCollectDao;
import com.tengzhi.business.system.user.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class ServeiceManageSqlImpl extends BasicsDaoImpl<G_ServiceManage, String> implements ServiceManageSqlDao {
	
	@Autowired
	private UserCollectDao userCollectDao;
	
	@Autowired
	private  ServiceManageDao  serviceManageDao;
	
	@Autowired
	private	DynamicFormSqlDao dynamicFormSqlDao;
	
	@Override
	public BasePage<Map<String, Object>> getSrch(BaseDto baseDto) {
		SessionUser sessionUser = SessionUser.SessionUser();
		SysUser  user = sessionUser.getSysUser();		
		Map<String, String> map = baseDto.getParamsMap();
		StringBuffer where = SqlJoint.where(e -> {
			  e.and(el->{el.contains("service_title", map.get("srch_title"));});
			  e.and(el->{el.contains("service_code",map.get("srch_code")  );});
			  e.and(el->{el.contains("service_result",map.get("srchResult"));});
		});		 
		String sql = "SELECT *,to_char(apply_date,'yyyy-MM-dd') \"applyChar\",f_getname('GETUSERNAME',apply_man,'',data_corp) \"applyName\",f_getparamname('GETBYPARENTID', service_result, '表单配置', '平台参数', 'FWXQ', '') \"typeName\" from  g_service_manage manage left join (" + dynamicFormSqlDao.getSql(map.get("srchResult"))+") dyn on dyn.associated_form_key = manage.service_note"
				+ " where 1=1 " +where.toString();
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return userCollectDao.QueryPageList(sql, countSql, baseDto);
	}
	
	
	
	@SuppressWarnings("static-access")
	public Result getSrchAllCollection() {
		SessionUser sessionUser = SessionUser.SessionUser();
		SysUser user = sessionUser.getSysUser();
		String sql = "SELECT collect_time, collect_note ,      (SELECT nick_name from sys_user  where  user_id = g_user_collection.user_id) nick_name, collect_type ,name,collect_mode ,contact_mode ,remarks,collect_url "
				+ "   from  g_user_collection  where user_id = '" + user.getUserId() + "'";
		return Result.resultOk(userCollectDao.SelectListMap(sql));

	}
	
	public Map<String,Object> findByNote(String Id,String result) {
		String sql = "select manage.*,dyn.* from g_service_manage manage " 
				+ " left join (" + dynamicFormSqlDao.getSql(result)+") dyn on dyn.associated_form_key = manage.service_note where service_note = '"+Id+"'";
		return serviceManageDao.QueryhumpMap(sql).get(0);
	}



	@Override
	public Map<String, Object> examination(String id) {
		String sql = "select * from g_service_manage where service_note = '"+id+"'";
		String result = (String) serviceManageDao.QueryhumpMap(sql).get(0).get("serviceResult");
		return findByNote(id,result);
	}

}
