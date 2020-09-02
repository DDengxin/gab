package com.tengzhi.business.platform.manage.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.manage.dao.PriceConfigureDao;
import com.tengzhi.business.platform.manage.dao.PriceConfigureSqlDao;
import com.tengzhi.business.platform.manage.model.G_PriceConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class PriceConfigureSqlDaoImpl  extends BasicsDaoImpl<G_PriceConfigure, String> implements PriceConfigureSqlDao {

	@Autowired
	private PriceConfigureDao priceConfigureDao;
	
	@Override
	public BasePage<Map<String, Object>> getSrch(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		StringBuffer where = SqlJoint.where(e -> {
			  e.and(el->{el.contains("pc_type", map.get("srch_type"));});
			  e.and(el->{el.contains("pc_name",map.get("srch_name"));});
		});		 
		String sql = "SELECT *,to_char(start_time,'yyyy-MM-dd') \"startChar\",f_getparamname('GETBYPARENTID', pc_type, '表单配置', '平台参数', 'BJGL', '') \"typeName\",f_getname('GETCORPEXP','','',pc_corp) \"corpName\" from g_price_configure where 1=1 " +where.toString();
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return priceConfigureDao.QueryPageList(sql, countSql, baseDto);
	}

	@Override
	public Result getSrchAllCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findByType(String type) {
		String sql = "select * from g_price_configure";
		sql += SqlJoint.where(e -> {
			e.eq("pc_type", type);
		}, true);
		return priceConfigureDao.QueryhumpMap(sql+" order by pc_sort asc");
	}

}
