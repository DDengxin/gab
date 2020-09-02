package com.tengzhi.business.finance.complaint.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.complaint.model.ComplaintMx;

public interface ComplaintMxDao extends BasedaoRepository<ComplaintMx,String> {
	
	@Modifying
    @Query(value ="delete from e_pz_ksmx where ks_note = :ksNote", nativeQuery = true)
    void deleteMx(@Param("ksNote") String ksNote);
	
}
