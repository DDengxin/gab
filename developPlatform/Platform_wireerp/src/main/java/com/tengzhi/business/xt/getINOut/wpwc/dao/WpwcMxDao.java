package com.tengzhi.business.xt.getINOut.wpwc.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.getINOut.wpwc.model.EXtWpwcMx;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WpwcMxDao extends BasedaoRepository<EXtWpwcMx,String> {

    @Modifying
    @Query(value="delete from EXtWpwcMx where note=:note")
    void deleteByNote(@Param(value="note")String note);
}
