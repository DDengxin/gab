package com.tengzhi.business.xt.logistics.express.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.logistics.express.model.EXtExpress;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpressDao extends BasedaoRepository<EXtExpress,String> {

    @Modifying
    @Query(value="delete from EXtExpress where note =:note")
    void deleteByNote(@Param(value="note")String note);
}
