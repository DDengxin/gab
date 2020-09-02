package com.tengzhi.business.xt.getINOut.wlcl.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.getINOut.wlcl.model.EXtWlcl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WlclDao extends BasedaoRepository<EXtWlcl,String> {

    @Modifying
    @Query(value="delete from EXtWlcl where note =:note")
    void deleteByNote(@Param(value="note")String note);

    @Modifying
    @Query(value = "update EXtWlcl set rcFlag=:rcFlag where note =:note")
    void updateFlag(@Param(value="note")String note,@Param(value="rcFlag")String rcFlag);

    @Query(value="select rc_flag from e_xt_wlcl where note =:note",nativeQuery=true)
    String getFlag(@Param(value="note")String note);
}
