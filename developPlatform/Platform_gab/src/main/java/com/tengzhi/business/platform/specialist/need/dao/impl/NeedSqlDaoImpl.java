package com.tengzhi.business.platform.specialist.need.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.platform.form.dao.DynamicFormSqlDao;
import com.tengzhi.business.platform.specialist.need.dao.NeedDao;
import com.tengzhi.business.platform.specialist.need.dao.NeedSqlDao;
import com.tengzhi.business.platform.specialist.need.model.G_Need;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NeedSqlDaoImpl extends BasicsDaoImpl<G_Need, String> implements NeedSqlDao{

	@Autowired
	private DynamicFormSqlDao dynamicFormSqlDao;
	
	@Autowired
	private NeedDao needDao;
	
	@Override
	public BasePage<Map<String, Object>> sreach(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String type = "f_getparamname('GETBYPARENTID', need.cpcode_xp, '夹具大类', '技术', 'CP', 'JD') type_name";
		 if("HW".equals(map.get("srchForm"))){
			type = "f_getparamname('GETBYPARENTID', need.cpcode_xp, '服务大类', '技术', 'HW', 'JD') type_name";
		}
		String sql = "select dyn.*,need.*,f_getname('GETUSERNAME', need.apply_man, '', need.apply_corp) \"applyName\","+type+",to_char(end_time,'yyyy-MM-dd') end_char,to_char(apply_date,'yyyy-MM-dd') apply_char from g_need need";
		sql += " left join (" + dynamicFormSqlDao.getSql(map.get("cpcodeXp"))+") dyn on dyn.associated_form_key = need.need_note ";
		sql += SqlJoint.where(e -> { 
			e.ge("apply_date", map.get("srchRq1"));
			e.le("apply_date", map.get("srchRq2"));
			e.contains("cpcode_name", map.get("srchName"));
			e.eq("cpcode_xp", map.get("cpcodeXp"));
			e.eq("need_flag", map.get("srchFlag"));
			e.eq("apply_man", securityUser.getUserId());
			e.eq("need_form", map.get("srchForm"));
		}, true);
		return needDao.QueryMapPageList(baseDto,sql,true);
	}
	
	
	
	
	

	@Override
	public Map<String, Object> findById(String needNote, String cpcodeXp) {
		String sql = "select dyn.*,need.* from g_need need";
		sql += " left join (" + dynamicFormSqlDao.getSql(cpcodeXp)+") dyn on dyn.associated_form_key = need.need_note ";
		sql += SqlJoint.where(e -> {
			e.eq("need_note", needNote);
		}, true);
		return needDao.QueryhumpMap(sql).get(0);
	}

	@Override
	public List<Map> getCompany() {
		return needDao.QueryListMap("select *from g_supply where supply_note=(select supply_id from sys_user  where user_id='RNGFJ')");
	}


}
