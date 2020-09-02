package com.tengzhi.business.xt.getINOut.rylf.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.getINOut.rylf.model.EXtRylf;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RylfDao extends BasedaoRepository<EXtRylf,String> {

    @Query(value="select lf_flag from e_xt_rylf where note=:note",nativeQuery=true)
    String getFlag(@Param(value="note")String note);

    @Modifying
    @Query(value="update EXtRylf set lfFlag=:lfFlag where note =:note")
    void updateFlag(@Param(value="note")String note,@Param(value="lfFlag")String lfFlag);

    @Modifying
    @Query(value="delete from EXtRylf where note =:note")
    void deleteByNote(@Param(value="note")String note);
}
