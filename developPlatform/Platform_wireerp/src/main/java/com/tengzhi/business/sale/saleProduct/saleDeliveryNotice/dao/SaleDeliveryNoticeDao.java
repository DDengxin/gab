package com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.app.ck.model.DeliveryNotice;
import com.tengzhi.app.ck.model.DeliveryNotice.DeliveryNotice_PK;
import com.tengzhi.base.jpa.dao.BasedaoRepository;

public interface SaleDeliveryNoticeDao extends BasedaoRepository<DeliveryNotice, DeliveryNotice_PK > {

	@Modifying
	@Query(value =" delete from DeliveryNotice where fhNote = :fhNote ")
	void deleteByNote(@Param(value = "fhNote") String fhNote);

	@Modifying
	@Query(value =" update DeliveryNotice set fhFlag=:flag  where fhNote = :fhNote ")
	void setFlag(@Param(value = "fhNote") String fhNote,@Param(value = "flag") String flag);

	@Query(value =" select  fh_flag from E_Ck_Delivery_Notice where fh_note = :fhNote  limit 1 ", nativeQuery = true)
	String getFlag(@Param(value = "fhNote") String fhNote);
}
