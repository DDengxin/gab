package com.tengzhi.business.sc.task.sctack.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;

import java.util.List;
import java.util.Optional;

public interface MScScrwGxDao extends BasedaoRepository<MScScrwGx, String> {

	 @Modifying
	 @Query(value =" delete from MScScrwGx where scMo = :scMo ")
	 void deleteRwgx(@Param(value = "scMo") String scMo);

	 List<MScScrwGx> findByGxId(String gxId);

}
