package com.tengzhi.business.ck.yw.ckck.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut.ECkOut_PK;



public interface ECkOutDao extends BasedaoRepository<ECkOut,ECkOut_PK> {

	 @Modifying
	 @Query(value =" delete from ECkOut where outNote = :outNote ")
	 void deleteByInNote(@Param(value = "outNote") String outNote);
	
	 @Query(value =" select  out_Flag from E_Ck_Out where out_Note = :outNote  limit 1  ", nativeQuery = true)
	 String getFlag(@Param(value = "outNote") String outNote);
	 
	 @Modifying
	 @Query(value =" update ECkOut set outFlag=:outFlag  where outNote = :outNote ")
	 int setFlag(@Param(value = "outNote") String outNote,@Param(value = "outFlag") String outFlag);

	 @Query(value =" select count(*) cn from e_ck_out   where out_pack = :outPack  ", nativeQuery = true)
	 int getAddOutPack(@Param(value = "outPack") String outPack);
	 
	 
	 @Modifying
	 @Query(value =" update ECkOut set outSl=:outSl  where outPack = :outPack  and outSl<0")
	 int updateSplit(@Param(value = "outSl") String outSl,@Param(value = "outPack") String outPack);
	 
	 
	 @Modifying
	 @Query(value =" update ECkOut set outFlag='库审' ,outMan=:outMan,outDate=now()  where outNote = :outNote ")
	 int out(@Param(value = "outNote") String outNote,@Param(value = "outMan") String outMan);
}
