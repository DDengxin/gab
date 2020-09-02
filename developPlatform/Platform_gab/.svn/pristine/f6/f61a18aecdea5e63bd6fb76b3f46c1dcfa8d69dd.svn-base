package com.tengzhi.business.platform.shopping.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.business.platform.shopping.model.GShopAddr;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AddrSqlDao extends BasicsDao<GShopAddr, String> {

	List<Map<String, Object>> findAllByUserId();

	GShopAddr findByAddrId(String address_note);

	List<Map<String, Object>> findDefaultAddr();


}
