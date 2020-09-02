package com.tengzhi.business.xt.dailyRoutine.wcsq.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsqZc;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WcsqZcDao extends BasedaoRepository<EXtWcsqZc,String> {

    @Modifying
    @Query(value =" delete from EXtWcsqZc where note = :note ")
    void deleteByNote(@Param(value = "note") String note);
}
