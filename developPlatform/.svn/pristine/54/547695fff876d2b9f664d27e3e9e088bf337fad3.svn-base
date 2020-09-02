package com.tengzhi.business.cg.yw.purchaseSettle.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn.ECkIn_PK;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf.ECwYsyf_PK;



public interface ECwYsyfDao extends BasedaoRepository<ECwYsyf,ECwYsyf_PK> {

	
	 @Modifying
	 @Query(value =" delete from ECwYsyf where cwNote = :cwNote ")
	 void deleteByInNote(@Param(value = "cwNote") String cwNote);
	 
	 @Query(value =" select  cw_Flag from e_cw_ysyf where cw_Note = :cwNote  limit 1 ", nativeQuery = true)
	 String getFlag(@Param(value = "cwNote") String cwNote);
	 
	 @Modifying
	 @Query(value =" update ECwYsyf set cwFlag=:flag  where cwNote = :cwNote ")
	 int setFlag(@Param(value = "cwNote") String cwNote,@Param(value = "flag") String flag);
	 
}
