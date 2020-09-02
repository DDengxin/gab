package com.tengzhi.business.platform.shopping.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.business.platform.shopping.dao.ShoppingCartDao;
import com.tengzhi.business.platform.shopping.dao.ShoppingCartSqlDao;
import com.tengzhi.business.platform.shopping.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ShoppingCartDaoSqlImpl extends BasicsDaoImpl<ShoppingCart, String> implements ShoppingCartSqlDao {
    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Override
    public List<Map<String, Object>> getMyShoppingCart(String owner) {
        String sql = "select g_shopping.*,f_gab_getname('COVERPICTURE', product_num, '', operator_company) AS coverPicture,f_getname('CPCODENAME', product_num, '', operator_company) as cpcodeName,f_gab_getname('GETSUPPLYNAME', product_company, '', '') as productCompany,cpcode_price,cover_picture from g_shopping left join e_js_cpcode on product_num = cpcode_id where owner='" + owner + "' and g_shopping.status = '登记' order by product_company";
        return shoppingCartDao.QueryhumpMap(sql);
    }

    @Override
    public List<Map<String, Object>> getMyShoppingCart(String owner, List<String> productIds) {
        String where = " where owner='"+owner+"'";
        if(productIds.size()>0) {
        	where += " and ("; 
        	for (int i=0;i<productIds.size();i++){
            	if(i>0) {
            		where += " or ";
            	}
                where += "product_id='"+productIds.get(i)+"'";
            }
        	where += ")";
        }
        String sql = "select g_shopping.*,f_gab_getname('COVERPICTURE', product_num, '', operator_company) AS coverPicture,f_getname('CPCODENAME', product_num, '', operator_company) as cpcodeName,f_gab_getname('GETSUPPLYNAME', product_company, '', '') as productCompany,cpcode_price,cover_picture from g_shopping left join e_js_cpcode on product_num = cpcode_id " + where + "  order by product_company";
        return shoppingCartDao.QueryhumpMap(sql);
    }

	@Override
	public List<Map<String, Object>> getCpcodeById(String productIds) {
		String sql = "SELECT cp.*,shop.product_id,shop.product_company,shop.product_num,shop.product_count FROM g_shopping shop left join e_js_cpcode cp on cpcode_id = product_num where product_id in('"+productIds+"')";
        return shoppingCartDao.QueryhumpMap(sql);
	}
}