package com.tengzhi.business.platform.shopping.service;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.shopping.model.ShoppingCart;
import com.tengzhi.business.platform.shopping.model.ShoppingVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ShoppingService  extends BaseService {

    //添加购物车
    Result addShoppingCart(String dataMan,String productNum) throws Exception;

    //保存方法
    Result save(ShoppingCart shoppingCart) throws Exception;

    //通过用户查询所有的商品
    List<Map<String, Object>> findAll(String owner);

    //通过商品id查询用户结算时的商品
    List<Map<String, Object>> findByPid(String owner,List<String> productIds);

    //删除单行数据
    Result deleteByPId(String productId);

    //删除批量数据
    Result deleteByAll(List<String> productIds);

    //点击商品结算后修改商品数量
    Result updateByPId(List<ShoppingVo> products);

    //修改商品
    Result update(ShoppingCart shoppingCart);

    //保存订单信息
	Result sureShop(String goods) throws IOException;

}
