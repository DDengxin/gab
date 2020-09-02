package com.tengzhi.business.cg.yw.purchaseContract.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;

public interface ECgContractDao extends BasedaoRepository<ECgContract,String> {

	
	 @Modifying
	 @Query(value =" delete from ECgContract where htNo = :htNo ")
	 void deleteByHtNo(@Param(value = "htNo") String htNo);
	 
	 @Query(value =" select htFlag from ECgContract where htNo = :htNo ")
	 String getFlag(@Param(value = "htNo") String htNO);

	 @Query(value =" select htFlag from ECgContract where htNo = :htNo ",nativeQuery = true)
	 Optional <ECgContractDetailed> findByHtNo(@Param(value = "htNo") String htNo);
	 
	 @Modifying
	 @Query(value=" update ECgContract set htFag= :htFlag  where htNo = :htNo ",nativeQuery = true)
	 int updateFlag(@Param(value = "htFlag") String htFlag,@Param(value = "htNo") String htNo);

}
