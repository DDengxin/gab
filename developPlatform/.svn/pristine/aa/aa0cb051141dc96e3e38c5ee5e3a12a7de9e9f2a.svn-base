package com.tengzhi.business.mSbJt.by.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mSbJt.by.model.MSbSbwb;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;

public interface MSbSbwbDao extends BasedaoRepository<MSbSbwb,String>{
	
	MSbSbwb findBySbwbNote(MSbSbwb mSbSbwb);

	
	 @Modifying
	 @Query(value =" delete from MSbSbwb where sbwbNote = :sbwbNote ")
	 void deleteBySbwbNote(@Param(value = "sbwbNote") String sbwbNote);


	 
	 @Query(value =" select  sbwb_Flag from m_sb_sbwb where sbwb_Note = :sbwbNote  limit 1 ", nativeQuery = true)
	 String getFlag(@Param(value = "sbwbNote") String sbwbNote);
	 
	 @Modifying
	 @Query(value =" update MSbSbwb set sbwbFlag=:flag  where sbwbNote = :sbwbNote ")
	 int setFlag(@Param(value = "sbwbNote") String sbwbNote,@Param(value = "flag") String flag);
     
	 
	 
	 @Query(value="select f_getname('GETDEPTNAME', :sbwb_dept, '', :data_corp)",nativeQuery = true)
	 String getDepatName (@Param("sbwb_dept") String sbwb_dept,@Param("data_corp") String data_corp);

	MSbSbwb findBySbwbNote(String sbwbNote);

}
