package com.tengzhi.business.finance.complaint.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.complaint.model.Complaint;

public interface ComplaintDao extends BasedaoRepository<Complaint,String> {

	@Query(value ="select customer_exp from sys_customer where customer_id = :ksDw", nativeQuery = true)
	String getDwName(@Param("ksDw") String ksDw);

	@Query(value =" from Complaint where ksNote = :ksNote")
	Complaint getComplaint(@Param("ksNote") String ksNote);

	@Query(value ="select worker_name from e_hr_worker where worker_id = :slMan", nativeQuery = true)
	String getManName(@Param("slMan") String slMan);
	
	@Modifying
    @Query(value ="delete from e_pz_ks where ks_note = :ksNote", nativeQuery = true)
    void deleteAll(@Param("ksNote") String ksNote);
	
}
