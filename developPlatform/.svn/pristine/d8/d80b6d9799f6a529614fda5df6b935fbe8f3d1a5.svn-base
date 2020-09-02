package com.tengzhi.business.sale.saleProduct.saleDelivery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.mesGz.gzck.vo.EckOut.ECkOut_PK;

public interface SaleDeliveryDao extends BasedaoRepository<EckOut,ECkOut_PK> {

	 @Modifying
	 @Query(value =" delete from EckOut where outNote = :outNote ")
	 void deleteByNote(@Param(value = "outNote") String outNote);
	 
	 
	 @Modifying
	 @Query(value =" update EckOut set outFlag=:flag  where outNote = :outNote ")
	 void updateFlag(@Param(value = "outNote") String outNote,@Param(value = "flag") String flag);
	
	 @Query(value=" select  distinct outFlag from EckOut where outNote= :outNote  " )
	 String getFlag(@Param("outNote")String outNote);
	 
	 @Query(value=" select  outCustomer from EckOut where outNote= :outNote  " )
	 String getCustoemr(@Param("outNote")String outNote);
	 
}

