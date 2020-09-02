package com.tengzhi.business.cg.yw.purchaseContract.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ECgContractDetailedDao extends BasedaoRepository<ECgContractDetailed,String> {

	 @Modifying
	 @Query(value =" delete from ECgContractDetailed where htNo = :htNo ")
	 void deleteByHtNo(@Param(value = "htNo") String htNo);


	 @Query(value=" select ht_flag from e_xs_contract_detailed where ht_no= :htNo  ",nativeQuery = true)
	 String getFlag(@Param("htNo")String htNo);

	 @Modifying
	 @Query(value=" update e_cg_contract set ht_flag=?1  where ht_no = ?2 ",nativeQuery = true)
	 void getNo(String flag,String htNo);

	@Modifying
	@Query(value =" update e_cg_contract_detailed set ht_flag=?1  where ht_no = ?2 ",nativeQuery = true)
	void updateFlag(String flag,String htNo);

	@Modifying
	@Query(value =" update e_cg_requisition set flag=:flag where sq_note = :sqNote and code=:code ",nativeQuery = true)
	void requisitionhx(@Param("flag")String flag,@Param("sqNote")String sqNote,@Param("code")String code);

	@Modifying
	@Query(value =" update e_cg_contract_detailed set ht_flag=:flag  where ht_no = :htNo and ht_code= :htCode",nativeQuery = true)
	void updateFlag1(@Param("flag")String flag,@Param("htNo")String htNo,@Param("htCode")String htCode );

	@Query(value =" select htFlag from ECgContractDetailed where htNo = :htNo and htCode = :code ")
	String getFlagH(@Param(value = "htNo") String htNo,@Param(value = "code") String code);
	@Modifying
	@Query(value = " update ECgContractDetailed set htFlag= :flag where htNo = :htNo and htCode = :code")
	void  updateHx(@Param(value ="htNo" ) String htNo,@Param(value = "code") String code,@Param(value = "flag")String flag);
}
