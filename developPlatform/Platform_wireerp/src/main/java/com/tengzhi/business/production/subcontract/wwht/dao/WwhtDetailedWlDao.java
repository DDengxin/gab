package com.tengzhi.business.production.subcontract.wwht.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailedWl;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailedWlPK;

public interface WwhtDetailedWlDao extends BasedaoRepository<EXsContractDetailedWl, EXsContractDetailedWlPK> {

	@Modifying
	@Query(value =" delete from EXsContractDetailedWl where htNo = :htNo ")
	void deleteByHtNo(@Param(value = "htNo") String htNo);
	
	
	
}
