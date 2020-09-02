package com.tengzhi.business.platform.shopping.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.business.platform.shopping.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShoppingCartSqlDao extends BasicsDao<ShoppingCart, String> {
    List<Map<String,Object>> getMyShoppingCart(String owner);
    List<Map<String,Object>> getMyShoppingCart(String owner,List<String> productIds);
	List<Map<String,Object>> getCpcodeById(String productIds);

}
