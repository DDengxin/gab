package com.tengzhi.business.platform.specialist.need.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.specialist.need.model.G_Need;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NeedDao extends BasedaoRepository<G_Need,String>{

	@Modifying
	@Query(value =" delete from G_Need where needNote = :needNote")
	void deleteByNote(@Param("needNote") String needNote);

	@Query(value =" select cpcodeXp from G_Need where needNote = :needNote ")
	String getType(@Param("needNote") String needNote);




}

