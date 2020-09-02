package com.tengzhi.business.sc.task.sctack.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;

public interface ScTackDao extends BasedaoRepository<MScScrw, MScScrw_PK> {

	

	 @Modifying
	 @Query(value =" update MScScrw set scGylx=:scGylx  where scMo = :scMo ")
	 void updateGyId(@Param(value = "scGylx") String scGylx,@Param(value = "scMo") String scMo);

	 @Modifying
	 @Query(value =" update MScScrw set scGylx=null,scFlag='登记'  where scMo = :scMo ")
	 void clearGylx(@Param(value = "scMo") String scMo);

	 @Modifying
	 @Query(value =" delete from MScScrw where scMo = :scMo ")
	 void deleteByScMo(@Param(value = "scMo") String scMo);
}
