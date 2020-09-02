package com.tengzhi.business.mesGz.gzck.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.mesGz.gzck.vo.EckOut.ECkOut_PK;



public interface LyrkDao extends BasedaoRepository<EckOut,ECkOut_PK> {

	 @Modifying
	 @Query(value =" delete from EckOut where outNote = :outNote ")
	 void deleteByInNote(@Param(value = "outNote") String outNote);
	
	 @Query(value =" select  out_Flag from E_Ck_Out where out_Note = :outNote  limit 1 ", nativeQuery = true)
	 String getFlag(@Param(value = "outNote") String outNote);
	 
	 @Modifying
	 @Query(value =" update EckOut set  Flag=:flag  where inNote = :inNote ")
	 int setFlag(@Param(value = "inNote") String inNote,@Param(value = "flag") String flag);

	 @Query(value =" select count(*) cn from E_Ck_In   where in_pack = :inPack and in_act not in ('0102') ", nativeQuery = true)
	 int getAddInBack(@Param(value = "inPack") String inPack);
	 
}
