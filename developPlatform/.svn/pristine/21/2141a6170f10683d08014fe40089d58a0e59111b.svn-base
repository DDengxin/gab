package com.tengzhi.business.project.projectArchives.projectTreams.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.project.projectArchives.projectTreams.model.EXmXmxz;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectTreamDao extends BasedaoRepository<EXmXmxz,String> {

    @Modifying
    @Query(value = "delete from  EXmXmxz where xmId = :xmId")
    void deleteByXmId(@Param("xmId") String xmId);

}
