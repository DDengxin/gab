package com.tengzhi.business.mSbJt.wx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mSbJt.by.model.MSbSbwb;
import com.tengzhi.business.mSbJt.wx.model.MSbSbwbb;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;

public interface MSbSbwbDaoo extends BasedaoRepository<MSbSbwbb,String>{
	
	MSbSbwbb findBySbwbNote(String sbwbNote);

	
	 @Modifying
	 @Query(value =" delete from MSbSbwbb where sbwbNote = :sbwbNote ")
	 void deleteBySbwbNote(@Param(value = "sbwbNote") String sbwbNote);
	

}
