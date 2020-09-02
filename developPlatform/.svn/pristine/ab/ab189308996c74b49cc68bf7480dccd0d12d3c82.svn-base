package com.tengzhi.business.project.projectArchives.xmda.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.project.projectArchives.xmda.model.EXmXmda;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XmdaDao extends BasedaoRepository<EXmXmda,String> {

    @Query(value =" select distinct xmFlag from EXmXmda where xmId = :xmId ")
    String getxmFlag(@Param(value = "xmId") String xmId);

    @Modifying
    @Query(value =" update EXmXmda set xmFlag=:xmFlag  where xmId = :xmId ")
    void setxmFlag(@Param(value = "xmId") String xmId,@Param(value = "xmFlag") String xmFlag);
}
