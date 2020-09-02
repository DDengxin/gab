package com.tengzhi.business.mSbJt.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.mSbJt.model.MSbJt;

public interface MSbJtDao extends BasedaoRepository<MSbJt,String> {

	
	 @Modifying
	 @Query(value =" delete from MSbJt where machineId = :machineId ")
	 void deleteByMachineId(@Param(value = "machineId") String machineId);

}
