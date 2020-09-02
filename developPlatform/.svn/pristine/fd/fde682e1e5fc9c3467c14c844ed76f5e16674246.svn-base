package com.tengzhi.business.xt.dailyRoutine.wcsq.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsq;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WcsqDao extends BasedaoRepository<EXtWcsq,String> {

    @Modifying
    @Query(value =" update EXtWcsq set wcFlag=:wcFlag  where note = :note ")
    void updateFlag(@Param(value = "note") String note, @Param(value = "wcFlag") String wcFlag);

    @Query(value=" select  wc_flag from e_xt_wcsq where note= :note  ",nativeQuery = true)
    String getFlag(@Param("note")String note);

    @Modifying
    @Query(value =" delete from EXtWcsq where note = :note ")
    void deleteByNote(@Param(value = "note") String note);

}
