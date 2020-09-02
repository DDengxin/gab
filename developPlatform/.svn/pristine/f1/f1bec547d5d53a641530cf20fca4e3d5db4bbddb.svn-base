package com.tengzhi.business.xt.dailyRoutine.sealApplication.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.dailyRoutine.sealApplication.model.EXtSealApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: czf
 * @Date:2020-08-20 19:05
 */
public interface SealApplicationDao extends BasedaoRepository<EXtSealApplication,String> {


    @Query(value="select flag from e_xt_seal_application where note=:note",nativeQuery=true)
    String getFlag(@Param(value="note")String note);

    @Modifying
    @Query(value="delete from EXtSealApplication where note=:note")
    void deleteByNote(@Param(value="note")String note);

    @Modifying
    @Query(value="update EXtSealApplication  set flag=:flag where note=:note")
    void updateFlag(@Param(value="note")String note,@Param(value="flag")String flag);
}
