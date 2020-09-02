package com.tengzhi.business.xt.getINOut.wlwp.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.getINOut.wlwp.model.EXtWlwpMx;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WlwpMxDao extends BasedaoRepository<EXtWlwpMx,String> {

    @Modifying
    @Query(value="delete from EXtWpwcMx where note = :note")
    void deleteByNote(@Param(value="note") String note);
}
