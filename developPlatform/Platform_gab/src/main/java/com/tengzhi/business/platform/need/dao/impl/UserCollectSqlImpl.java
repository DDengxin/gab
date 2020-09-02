package com.tengzhi.business.platform.need.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import com.tengzhi.business.platform.need.dao.UserCollectDao;
import com.tengzhi.business.platform.need.dao.UserCollectSqlDao;
import com.tengzhi.business.platform.need.model.G_UserCollection;
import com.tengzhi.business.system.user.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class UserCollectSqlImpl extends BasicsDaoImpl<G_UserCollection, String> implements UserCollectSqlDao {
	@Autowired
	private UserCollectDao userCollectDao;

	@Override
	public BasePage<Map<String, Object>> getSrchCollection(BaseDto baseDto) {
		SessionUser sessionUser = SessionUser.SessionUser();
		SysUser  user = 	sessionUser.getSysUser();		
		Map<String, String> map = baseDto.getParamsMap();
		StringBuffer where = SqlJoint.where(e -> {
			e.and(el->{el.gt("collect_time", map.get("srchRq1"));});
			e.and(el->{el.lt("collect_time", map.get("srchRq3"));});
		//	e.and(el->{el.between("collect_time",map.get("srchRq1"),map.get("srchRq2"));});
			e.and(el->{el.contains("collect_mode",map.get("srch_collect_mode"));});
			e.and(el->{el.contains("collect_type", map.get("srch_collect_type"));});
			e.and(el->{el.contains("user_id",user.getUserId());});
		});
		String sql = "SELECT collect_time, collect_note ,      collect_type ,name,collect_mode ,contact_mode ,remarks,collect_url "
				+ "   from  g_user_collection  where 1=1 " +where.toString()+"order by collect_time desc";
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return userCollectDao.QueryPageList(sql, countSql, baseDto);
	}
	
	
	
	@Override
	@SuppressWarnings("static-access")
	public Result getSrchAllCollection() {
		SessionUser sessionUser = SessionUser.SessionUser();
		if(SessionUser.isGabUser()){
		GUserInfo user = sessionUser.getgUserInfo();
		String sql = "SELECT collect_time, collect_note , (SELECT nick_name from sys_user  where  user_id = g_user_collection.user_id) nick_name, collect_type ,name,collect_mode ,contact_mode ,remarks,collect_url "
				+ "   from  g_user_collection  where user_id = '" + user.getUserId() + "'";
			return Result.resultOk(userCollectDao.SelectListMap(sql));
		}else{
			return Result.error();
		}


	}
	
	
	

}
