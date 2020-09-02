package com.tengzhi.business.platform.specialist.product.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.specialist.product.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductsDao  extends BasedaoRepository<Product,String>{
	
	@Query(value =" from Product where productNote = :productNote")
	Product getByNote(@Param("productNote") String productNote);
	
	@Modifying
	@Query(value ="delete from Product where productNote = :productNote")
	int deleteByNote(@Param("productNote") String productNote);

	@Query(value ="select supply_id from sys_user where user_id = :userId",nativeQuery = true)
	String getSupply(@Param("userId") String userId);
	
}
