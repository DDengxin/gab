package com.tengzhi.business.cg.yw.purchaseRequisition.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition.EcgRequisition_PK;

public interface ECgRequisitionDao extends BasedaoRepository<EcgRequisition,EcgRequisition_PK> {

	
	 @Modifying
	 @Query(value =" delete from EcgRequisition where sqNote = :sqNote ")
	 void deleteBysqNote(@Param(value = "sqNote") String sqNote);
	 
	 @Query(value =" select distinct flag from EcgRequisition where sqNote = :sqNote ")
	 String getFlag(@Param(value = "sqNote") String sqNote);

	 @Modifying
	 @Query(value =" update EcgRequisition set flag=:flag  where sqNote = :sqNote ")
	 int setFlag(@Param(value = "sqNote") String sqNote,@Param(value = "flag") String flag);

	 
	 @Query(value ="select * from E_cg_Requisition where sq_Note = :sqNote limit 1 ",nativeQuery = true)
	 EcgRequisition findBySqnote(@Param(value = "sqNote") String sqNote);

	 @Modifying
	@Query(value =" update e_cg_requisition set flag=?1 where sq_note = ?2 and code=?3 ",nativeQuery = true)
	void requisitionhx(String flag,String htNo,String code);

}
