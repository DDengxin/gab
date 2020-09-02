package com.tengzhi.business.js.cpgy.scgy.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgymx;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgymx.MScScgymx_PK;

import java.util.List;

public interface MScScgymxDao extends BasedaoRepository<MScScgymx,MScScgymx_PK> {

	 @Modifying
	 @Query(value =" delete from MScScgymx where gyId = :gyId ")
	 void deleteByGxId(@Param(value = "gyId") String gyId);

	 @Query(value="select * from m_sc_scgymx where gx_id=?1",nativeQuery=true)
	 List<MScScgymx> findByGxId(String gxId);
	 
}
