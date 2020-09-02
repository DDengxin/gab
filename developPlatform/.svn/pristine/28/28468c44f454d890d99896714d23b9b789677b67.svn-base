package com.tengzhi.business.production.subcontract.wwfl.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.app.ck.model.DeliveryNotice;
import com.tengzhi.app.ck.model.DeliveryNotice.DeliveryNotice_PK;
import com.tengzhi.base.jpa.dao.BasedaoRepository;

public interface WwflDao extends BasedaoRepository<DeliveryNotice,DeliveryNotice_PK>{

	 @Modifying
	 @Query(value =" delete from DeliveryNotice where fhNote = :fhNote ")
	 void deleteByNote(@Param(value = "fhNote") String outNote);
	 
	 @Modifying
	 @Query(value =" update DeliveryNotice set fhFlag=:fhFlag  where fhNote = :fhNote ")
	 void updateFlag(@Param(value = "fhNote") String fhNote,@Param(value = "fhFlag") String fhFlag);
	
	 @Query(value=" select  max(fhFlag)  from DeliveryNotice where fhNote= :fhNote  " )
	 String getFlag(@Param("fhNote")String fhNote);
	 
	 @Query(value=" select  fhCustomer from DeliveryNotice where fhNote= :fhNote  " )
	 String getCustoemr(@Param("fhNote")String fhNote);
	 
}

