package com.tengzhi.business.quality.qualityArchives.qualityCertificate.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.quality.qualityArchives.qualityCertificate.model.QualityCertificate;
import com.tengzhi.business.quality.qualityArchives.qualityCertificate.model.QualityCertificate.QualityCertificate_PK;

public interface QualityCertificateDao extends BasedaoRepository<QualityCertificate, QualityCertificate_PK> {


	 @Modifying
	 @Query(value =" delete from QualityCertificate where zmNote = :zmNote ")
	 int deleteByNote(@Param(value = "zmNote") String zmNote);
	 
	 @Modifying
	 @Query(value =" update QualityCertificate set zmFlag= :zmFlag  where zmNote = :zmNote ")
	 void updateFlag(@Param(value = "zmNote") String zmNote,@Param(value = "zmFlag") String zmFlag);
	
	 @Query(value=" select  distinct zmFlag from QualityCertificate where zmNote= :zmNote  " )
	 String getFlag(@Param("zmNote")String zmNote);
	 

	 
	 @Query(value=" SELECT  zmRq,zmNote,zmDeliveryNo FROM QualityCertificate  where zmNote = :zmNote GROUP BY  zmRq,zmNote,zmItem,zmCustomer,zmDeliveryNo,dataMan,zmFlag  " )
	 Map<String, Object>   getzmNoteAlone(@Param("zmNote") String zmNote);
	 
	 
}
