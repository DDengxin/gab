package com.tengzhi.business.xt.getINOut.wpwc.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.getINOut.wpwc.model.EXtWpwc;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WpwcDao extends BasedaoRepository<EXtWpwc,String> {

    @Query(value="select cc_flag from e_xt_wpwc where note= :note",nativeQuery=true)
    String getFlag(@Param(value="note")String note);

    @Modifying
    @Query(value = "update EXtWpwc set ccFlag= :ccFlag where note = :note")
    void updateFlag(@Param(value="note")String note,@Param(value="ccFlag")String ccFlag);

    @Modifying
    @Query(value="delete from EXtWpwc where note=:note")
    void deleteByNote(@Param(value="note")String note);
}
