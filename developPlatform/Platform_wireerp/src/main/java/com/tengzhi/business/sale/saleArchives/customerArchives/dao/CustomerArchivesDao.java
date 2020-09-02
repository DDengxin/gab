package com.tengzhi.business.sale.saleArchives.customerArchives.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;

public interface CustomerArchivesDao extends BasedaoRepository<SysCustomer,String>{
	
	SysCustomer findByCustomerId(String customerId);
	
	@Query(value=" select  customerDay from SysCustomer where customerId= :customerId  " )
	String getCustoemrDay(@Param("customerId")String customerId);
}
