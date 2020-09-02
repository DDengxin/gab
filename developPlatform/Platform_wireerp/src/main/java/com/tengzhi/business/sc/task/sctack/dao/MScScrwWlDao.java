package com.tengzhi.business.sc.task.sctack.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl.MScScrwWl_PK;
public interface MScScrwWlDao extends BasedaoRepository<MScScrwWl, MScScrwWl_PK> {

	@Modifying
	@Query(value = " delete from MScScrwWl where scrwMo = :scrwMo  and wlGxnote = :wlGxnote")
	void deleteByScrwMo(@Param(value = "scrwMo") String scrwMo, @Param(value = "wlGxnote") String wlGxnote);

	@Modifying
	@Query(value = " delete from MScScrwWl where scrwMo = :scrwMo ")
	void deleteByScrwMoAll(@Param(value = "scrwMo") String scrwMo);
}
