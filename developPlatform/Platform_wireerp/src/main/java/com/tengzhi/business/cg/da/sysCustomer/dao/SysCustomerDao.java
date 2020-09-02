package com.tengzhi.business.cg.da.sysCustomer.dao;

import org.springframework.data.jpa.repository.Query;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;

import org.springframework.data.repository.query.Param;
public interface SysCustomerDao extends BasedaoRepository<SysCustomer, String>{
	
	SysCustomer findByCustomerId(String customerId);
	
	@Query(value = "select f_getname('GETDWEXP', cast(:customeruid as varchar), '',cast(:datacorp as varchar)) ",nativeQuery = true)
	String lastCustomername(@Param("customeruid") String customeruid,@Param("datacorp") String datacorp);
	
	
	
}
