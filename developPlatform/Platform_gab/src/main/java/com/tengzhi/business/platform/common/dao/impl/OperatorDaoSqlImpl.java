package com.tengzhi.business.platform.common.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.common.dao.OperatorDao;
import com.tengzhi.business.platform.common.dao.OperatorDaoSql;
import com.tengzhi.business.platform.common.model.ParamVo;
import com.tengzhi.business.platform.enroll.model.G_Expert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperatorDaoSqlImpl extends BasicsDaoImpl<G_Expert, String> implements OperatorDaoSql{
	@Autowired
	OperatorDao operatorDao;

	@Override
	public Result getAllLikeSearch(String SearchKeyword) {
	String  sql = "SELECT * from (\r\n" + 
			"SELECT  cpcode_name ,   product_description,('产品市场>>' ||(select param_name from sys_param where param_mod='平台参数' and param_type='表单配置' and  param_id = cpcode_xp) )  cpcode_fl_name from   e_js_cpcode  where  status  = '核准'\r\n" + 
			"union  all \r\n" + 
			"SELECT  supply_name,supply_info,('夹治具商>>' ||supply_type)  from g_supply   where  status  = '核准'\r\n" + 
			"union all \r\n" + 
			"SELECT  expert_name ,introduce,('专家团队>>'||expert_type)  from  g_expert  where  status  = '核准'\r\n" + 
			"union \r\n" + 
			"SELECT title,brief_introduction,('资讯>>'||classify)  from  g_advisory      where  status  = '核准' \r\n" + 
			"union \r\n" + 
			"SELECT cpcode_name,introduce,'需求市场' from  g_need   where  need_flag = '投标中'\r\n" + 
			")t  ";
	if(StringUtils.isNotEmpty(SearchKeyword)) {
		String  where =" where  cpcode_name  like  '%"+SearchKeyword+"%'";
	}
		
		return null;
	}

	@Override
	public List<ParamVo> findChildren(String parentName) {
		String sql = "select  param_id as id,param_name as name,parent_name from sys_param  where  parent_name='" + parentName + "'   and 'JD' = any(string_to_array( org_id,',')) and param_status='true'";
		List<ParamVo> ParamVo = operatorDao.QueryToVo(ParamVo.class, sql);
		return ParamVo;
	}

}
