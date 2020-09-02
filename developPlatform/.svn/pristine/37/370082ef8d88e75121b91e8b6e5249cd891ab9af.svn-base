package com.tengzhi.business.project.projectStage.projectLine.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.project.projectStage.projectLine.model.EXmXmlc;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProjectLineDao extends BasedaoRepository<EXmXmlc,String> {



    @Modifying
    @Query(value = " delete EXmXmlc where lcNote = :lcNote ")
    void  deleteByNote(@Param(value ="lcNote" ) String lcNote);


}
