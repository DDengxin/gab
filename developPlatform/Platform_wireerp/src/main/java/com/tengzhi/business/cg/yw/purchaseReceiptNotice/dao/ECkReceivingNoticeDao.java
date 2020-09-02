package com.tengzhi.business.cg.yw.purchaseReceiptNotice.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.model.ECkReceivingNotice;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.model.ECkReceivingNotice.ECkReceivingNotice_PK;

public interface ECkReceivingNoticeDao extends BasedaoRepository<ECkReceivingNotice, ECkReceivingNotice_PK> {

	
	@Modifying
	@Query(value =" delete from ECkReceivingNotice where shNote = :shNote ")
	void deleteByNote(@Param(value = "shNote") String shNote);

	@Modifying
	@Query(value =" update ECkReceivingNotice set shFlag=:flag  where shNote = :shNote ")
	void setFlag(@Param(value = "shNote") String shNote,@Param(value = "flag") String flag);

	@Query(value =" select  sh_flag from E_Ck_Receiving_Notice where sh_note = :shNote  limit 1 ", nativeQuery = true)
	String getFlag(@Param(value = "shNote") String shNote);

	
	
}
