package com.tengzhi.business.platform.shopping.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.shopping.model.ShoppingCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShoppingCartDao extends BasedaoRepository<ShoppingCart, String> {
    //通过dataman获取供应商的supply_note
//    @Query(value = "select supply_note from g_supply where supply_note = (select supply_id from sys_user where user_id =:dataMan)",nativeQuery = true)
//    String  getSupplyNote(@Param("dataMan") String dataMan );

//    @Query(value = "select user_id from g_userinfo where supply_note = (select supply_id from sys_user where user_id =:dataMan)",nativeQuery = true)
//    String  getSupplyNote(@Param("dataMan") String dataMan );


    //通过用户(owner)和商品编号(product_num)判断商品中是否已存在
    @Query(value = "select * from g_shopping where product_num =:product_num and owner=:owner and status='登记'", nativeQuery = true)
    List<ShoppingCart> getproduct(@Param("product_num") String product_num, @Param("owner") String owner);

    //删除单个商品
    @Modifying
    @Query(value = " delete from g_shopping where product_id = :productId and owner=:owner ", nativeQuery = true)
    int delete(@Param("productId") String productId, @Param("owner") String owner);

    //修改商品数量
    @Modifying
    @Query(value = "update g_shopping set product_count=:count where product_id= :productId and owner=:owner",nativeQuery = true)
    int updateCount(@Param(value = "count") int count,@Param(value = "productId") String productId,@Param(value = "owner") String owner);

  //修改商品状态为确认
    @Modifying
    @Query(value = "update g_shopping set status = '确认' where product_id = :productId",nativeQuery = true)
    int comfirm(@Param(value = "productId") String productId);



}
