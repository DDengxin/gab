package com.tengzhi.business.sale.saleProduct.saleContract.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;

public interface EXsContractDao extends BasedaoRepository<EXsContract, String> {

	@Modifying
	@Query(value =" delete from EXsContract where htNo = :htNo ")
	void deleteByHtNo(@Param(value = "htNo") String htNo);
	
	@Modifying
	@Query(value =" update EXsContract set htFlag=:htFlag  where htNo = :htNo ")
	void updateFlag(@Param(value = "htNo") String htNo,@Param(value = "htFlag") String htFlag);
	
	@Query(value=" select  ht_flag from e_xs_contract where ht_no= :htNo  ",nativeQuery = true)
	String getFlag(@Param("htNo")String htNo);
	
	@Query(value=" select  * from EXsContract where htNo= :htNo  ",nativeQuery = true)
	EXsContract getEXsContract(@Param("htNo")String htNo);

}
