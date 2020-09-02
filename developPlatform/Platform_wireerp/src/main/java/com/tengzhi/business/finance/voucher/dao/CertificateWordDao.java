package com.tengzhi.business.finance.voucher.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.finance.voucher.model.CertificateWord;
import com.tengzhi.business.js.cpbom.model.EJsCpcode;

public interface CertificateWordDao extends BasedaoRepository<CertificateWord,Long>{

	
	 CertificateWord findByFgenerateid(Long fgenerateid);
	 @Modifying
	 @Query(value =" delete from e_f_voucher_certificateword where fgenerateid = :fgenerateid  ")
	 void deleteById(@Param(value = "fgenerateid") Long fgenerateid);
	 
	 
	 
	 
}
