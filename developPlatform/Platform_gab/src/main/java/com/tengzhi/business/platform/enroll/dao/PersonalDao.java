package com.tengzhi.business.platform.enroll.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.enroll.model.G_personal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonalDao  extends BasedaoRepository<G_personal,String> {
	
	
	@Query(value = "SELECT  status  from  g_personal   where  person_note =:person_note  ", nativeQuery = true)
	String getPersonalStatus(@Param("person_note") String person_note);


	@Modifying
	@Query(value = "update  G_personal  set status = :status  where personNote= :personNote ")
	int updateStatus(@Param("personNote") String personNote, @Param("status") String status);

}
