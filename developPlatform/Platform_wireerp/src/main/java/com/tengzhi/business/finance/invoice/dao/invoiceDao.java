package com.tengzhi.business.finance.invoice.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.invoice.model.invoice;

import java.math.BigDecimal;


public interface invoiceDao extends BasedaoRepository<invoice, String>{
	
	invoice findByFpId(String fpId); 
	invoice findByFpNote(String note);
	@Modifying
	@Query(value="update invoice set fpFlag='确认',fpMonth='已结'  where fpFlag='登记'  and fpMonth='未结' and fpId=:fpId")
	int ok(@Param("fpId")String fpId);
	
	@Modifying
	@Query(value="update invoice set fpMonth='已结'  where fpId=:fpId")
	void yok(@Param("fpId")String fpId);
	
	@Modifying
	@Query(value="update invoice set fpFlag='登记',fpMonth='未结'  where fpFlag='确认'  and fpMonth='已结'  and fpId=:fpId")
	int qx(@Param("fpId")String fpId);
	
	@Modifying
	@Query(value="update invoice set fpMonth='未结'  where fpId=:fpId")
	void yqx(@Param("fpId")String fpId);
	
	@Query(value="select f_getname('GETDWEXP', :fpdw, '', :datacorp) ",nativeQuery = true)
	String getCorpName(@Param("fpdw") String fpDw,@Param("datacorp") String dataCorp);
	
	@Modifying
	@Query(value = "update e_cw_ysyf set cw_fph='N',cw_flag='库审'   where cw_fph=(select fp_note from e_cw_fp where fp_id=:fpId)",nativeQuery = true)
	void updateYsyf(@Param("fpId") String fpId);

	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "select count(*) from :tablename where :flagname = :flagnames and :monthname = :monthnames and :id = :ids"
	 * ) int getFlagInt(@Param("tablename") String
	 * tablename,@Param("flagname")String flagname,
	 * 
	 * @Param("flagnames") String flagnames,@Param("monthname") String monthname,
	 * 
	 * @Param("monthnames") String monthnames,@Param("id") String id,@Param("ids")
	 * String ids);
	 */
	 //供货单位 //CG应收应付发票判断 //发票类型 //发票税率 //产品类型(线材什么的)
	 @Query("from invoice where fpDw=?1 and fpDtype=?2 and fpType=?3 and fpSl=?4 and fpBz=?5 and fpCgtype=?6 ")
	 invoice selectOne(String fpDw, String fpDtype, String fpType,BigDecimal fpSl,String fpBz,String fpCgtype);




	 //联表修改e_ck_in表
	 @Modifying
	 @Query(value = "update e_ck_in set in_code=?1,in_price=?2,in_kfcode=?1 where in_contract=?3 and in_code=?4",nativeQuery = true)
	 void updateE_Ck_In(String in_code,BigDecimal in_price,String in_contract,String oldHtCode);


	//联表修改e_ck_in_ls表 临时
	@Modifying
	@Query(value = "update e_ck_in_ls set in_code=?1,in_price=?2,in_kfcode=?1 where in_contract=?3 and in_code=?4",nativeQuery = true)
	void updateE_Ck_In_Ls(String in_code,BigDecimal in_price,String in_contract,String oldHtCode);


	//联表修改e_ck_receiving_notice表
	@Modifying
	@Query(value = "update e_ck_receiving_notice set sh_code=?1,sh_price=?2 where sh_contract=?3 and sh_code=?4",nativeQuery = true)
	void updateE_Ck_Receiving_Notice(String sh_code,BigDecimal sh_price,String sh_contract,String oldHtCode);




	//联表修改e_ck_out表
	@Modifying
	@Query(value = "update e_ck_out set out_code=?1,out_price=?2,out_kfcode=?1 where out_contract=?3 and out_code=?4",nativeQuery = true)
	void updateE_Ck_Out(String out_code,BigDecimal out_price,String out_contract,String oldHtCode);


	//联表修改e_ck_out_ls表 临时
	@Modifying
	@Query(value = "update e_ck_out_ls set out_code=?1,out_price=?2,out_kfcode=?1 where out_contract=?3 and out_code=?4",nativeQuery = true)
	void updateE_Ck_Out_Ls(String out_code,BigDecimal out_price,String out_contract,String oldHtCode);



	//联表修改e_ck_delivery_notice表
	@Modifying
	@Query(value = "update e_ck_delivery_notice set fh_code=?1,fh_price=?2 where fh_contract=?3 and fh_code=?4",nativeQuery = true)
	void updateE_Ck_Delivery_Notice(String fh_code,BigDecimal fp_price,String fh_contract,String oldHtCode);




	//联表修改e_cw_ysyf表 公用 应收跟应付
	@Modifying
	@Query(value = "update e_cw_ysyf set cw_code=?1,cw_price=?2,cw_sje=(?2*cw_sl),cw_se=(?2*cw_sl/(1+cw_shl)*cw_shl) where cw_item=?3 and cw_code=?5 and cw_stype=?4",nativeQuery = true)
	void updateE_Cw_Ysyf(String cw_code,BigDecimal cw_price,String cw_item,String cw_stype,String oldHtCode);




	@Modifying
	@Query(value = "update e_cw_fp set fp_sje=?1,fp_je=?2,fp_se=?3 where fp_note=?4 ",nativeQuery = true)
	void updateE_Cw_FP(BigDecimal fp_sje,BigDecimal fp_je,BigDecimal fp_se,String fp_note);


}
