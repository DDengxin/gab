package com.tengzhi.business.xt.receive.jdap.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.receive.jdap.model.QyJdJdsq;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JdapDao extends BasedaoRepository<QyJdJdsq,String> {

    @Modifying
    @Query("update QyJdJdsq set flag= :state where note = :note")
    void updateFlage(@Param("state") String state,@Param("note") String note);

    @Query("select flag from QyJdJdsq  where note = :note")
    String getFlage(@Param("note")String note);
}
