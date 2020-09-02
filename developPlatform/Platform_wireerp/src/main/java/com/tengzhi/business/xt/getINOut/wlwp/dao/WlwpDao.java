package com.tengzhi.business.xt.getINOut.wlwp.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.getINOut.wlwp.model.EXtWlwp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WlwpDao extends BasedaoRepository<EXtWlwp,String> {

    @Query(value="select rc_flag from e_xt_wlwp where note =:note",nativeQuery=true)
    String getFlag(@Param(value="note")String note);

    @Modifying
    @Query(value="update EXtWlwp set rcFlag= :rcFlag where note =:note")
    void updateFlag(@Param(value="note")String note,@Param(value="rcFlag")String rcFlag);

    @Modifying
    @Query(value="delete from EXtWlwp where note =:note")
    void deleteByNote(@Param(value="note")String note);
}
