package com.tengzhi.business.ck.yw.warehouseAllot.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.ck.yw.warehouseAllot.model.ECkAllot;

public interface WarehouseAllotDao extends BasedaoRepository<ECkOut,String> {

	
	@Query(value=" select  distinct outFlag from ECkOut where outNote= :outNote  " )
	String getFlag(@Param("outNote")String outNote);

	@Modifying
	@Query(value =" delete from ECkOut where outNote in( select distinct outAllotNote from ECkOut where outNote= :outNote)  ")
	int deleteByAllotNote(@Param(value = "outNote") String outNote);
	
	@Modifying
	@Query(value =" delete from ECkOut where outNote = :outNote and outFlag='登记' ")
	int deleteByNote(@Param(value = "outNote") String outNote);
	
	@Modifying
	@Query(value=" insert into e_ck_out (out_rq,out_note,out_customer,out_act,out_code,out_js,out_sl,out_zl,out_price,out_pack,out_bpack,out_contract,out_lib,out_type,out_flag,data_man,data_date,data_corp,out_bz,out_tax,out_hs,out_kfcode,out_man,out_date,out_ph,out_kw,out_remarks,out_allot_note,out_allot_lib,out_allot_kw)  select out_rq,:outAllotNote,out_customer,:allotAct,out_code,out_js,-1*out_sl,-1*out_zl,-1*out_price,out_pack,out_bpack,out_contract,out_allot_lib,out_type,out_flag,:userId,now(),data_corp,out_bz,out_tax,out_hs,out_kfcode,:userId,now(),out_ph,out_allot_kw,out_remarks,out_note,out_lib,out_kw from e_ck_out where out_note =:outNote",nativeQuery = true) 
	int insertAllot(@Param(value = "outAllotNote") String outAllotNote,@Param(value = "allotAct") String allotAct,@Param(value = "userId") String userId,@Param(value = "outNote") String outNote);

	@Modifying
	@Query(value =" update ECkOut set outFlag='库审' ,outAllotNote = :outAllotNote,outMan = :userId,outDate=now()  where outNote = :outNote ")
	int confirm(@Param(value = "outAllotNote") String outAllotNote,@Param(value = "userId") String userId,@Param(value = "outNote") String outNote);

	@Modifying
	@Query(value =" update ECkOut set outFlag='登记' ,outAllotNote=null,outMan=null,outDate = null where outNote = :outNote ")
	int cancel(@Param(value = "outNote") String outNote);
}
