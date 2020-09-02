package com.tengzhi.business.xt.dailyRoutine.workPlan.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.dailyRoutine.workPlan.model.EXtWorkplan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: czf
 * @Date:2020-08-20 14:20
 */
public interface WorkplanDao extends BasedaoRepository<EXtWorkplan,String> {

    @Modifying
    @Query(value="delete from EXtWorkplan where note =:note")
    void deleteByNote(@Param(value="note")String note);

}
