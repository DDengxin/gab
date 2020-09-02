package com.tengzhi.business.mSbJt.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mSbJt.model.MSbJtbj;
import com.tengzhi.business.mSbJt.model.MSbJtbj.MSbJtbj_PK;

public interface MSbJtbjDao extends BasedaoRepository<MSbJtbj, MSbJtbj_PK>{
	 @Modifying
	 @Query(value =" delete from MSbJtbj where machineId = :machineId ")
	 void deleteByMachineId(@Param(value = "machineId") String machineId);
}
