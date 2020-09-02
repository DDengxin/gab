package com.tengzhi.business.sale.processProduct.processContract.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailed;

public interface ProcessContractDetailedDao extends BasedaoRepository<EXsContractDetailed, String> {

	@Modifying
	@Query(value =" delete from EXsContractDetailed where htNo = :htNo ")
	void deleteByHtNo(@Param(value = "htNo") String htNo);
	
	@Modifying
	@Query(value =" update EXsContractDetailed set htFlag=:htFlag  where htMo = :htMo ")
	void updateFlag(@Param(value = "htMo") String htNo,@Param(value = "htFlag") String htFlag);
	
	@Query(value=" select  ht_flag from e_xs_contract_detailed where ht_mo= :htMo  ",nativeQuery = true)
	String getFlag(@Param("htMo")String htMo);
}
