package com.tengzhi.business.platform.shopping.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.shopping.dao.AddrDao;
import com.tengzhi.business.platform.shopping.dao.AddrSqlDao;
import com.tengzhi.business.platform.shopping.model.GShopAddr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AddrSqlDaoImpl extends BasicsDaoImpl<GShopAddr, String> implements AddrSqlDao {

	@Autowired
	private AddrDao addrDao;
	
	@Override
	public List<Map<String, Object>> findAllByUserId() {
		String sql = "select * from e_cw_customer_address " + SqlJoint.where(e -> {
			e.eq("customer_id", SessionUser.getGabUserId());
		}, true);
		return addrDao.QueryToMap(sql + " order by is_default desc,sort_ord");
	}

	@Override
	public GShopAddr findByAddrId(String addressNote) {
		return addrDao.findById(addressNote).orElse(null);
	}

	@Override
	public List<Map<String, Object>> findDefaultAddr() {
		String sql = "select * from e_cw_customer_address " + SqlJoint.where(e -> {
			e.eq("customer_id", SessionUser.getGabUserId());
			e.eq("is_default","true");
		}, true);
		return addrDao.QueryToMap(sql);

	}

}