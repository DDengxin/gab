package com.tengzhi.business.sale.saleArchives.customerAddr.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleArchives.customerAddr.model.ECwCustomerAddress;


public interface CustomerAddrDao extends BasedaoRepository<ECwCustomerAddress, String> {

	@Query(value=" from ECwCustomerAddress where customerId = :customerId and dataCorp=:corp and sortOrd = :sortOrd")
	ECwCustomerAddress getOne(@Param("customerId")String customerId,@Param("sortOrd") int sortOrd,@Param("corp") String corp);
	
	@Query(value="select customer_exp from sys_customer where customer_id = :customerId and data_corp=:corp",nativeQuery = true)
	String getcustomerName(@Param("customerId")String customerId,@Param("corp")String corp);
	
	@Modifying
	@Query(value="delete from ECwCustomerAddress where customerId = :customerId and dataCorp=:corp and sortOrd = :sortOrd")
	int deleteByNote(@Param("customerId")String customerId,@Param("sortOrd")int sortOrd,@Param("corp") String corp);
	
	@Modifying
	@Query(value="delete from ECwCustomerAddress where addressNote = :addressNote ")
	int deleteByNote(@Param("addressNote")String addressNote);

	@Query(value=" from ECwCustomerAddress where addressNote = :addressNote")
	ECwCustomerAddress getByNote(@Param("addressNote")String addressNote);
}
