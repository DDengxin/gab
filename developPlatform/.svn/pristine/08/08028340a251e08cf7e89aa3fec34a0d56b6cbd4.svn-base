package com.tengzhi.business.project.projectProcess.projectTask.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.project.projectArchives.projectTreams.model.EXmXmxz;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectTaskDao  extends BasedaoRepository<EXmXmrw,String> {


    @Query(value =" select rwFlag from EXmXmrw where rwNote = :rwNote ")
    String getFlag(@Param(value = "rwNote") String rwNote);


    @Modifying
    @Query(value = " update EXmXmrw set rwFlag= :flag where rwNote = :rwNote ")
    void  update(@Param(value ="rwNote" ) String rwNote,@Param(value = "flag")String flag);


    @Modifying
    @Query(value = " update EXmXmrw set rwFlag= :flag,rwJsMan= :rwJsMan,rwJsDate=now()  where rwNote = :rwNote ")
    void  receipt(@Param(value ="rwNote" ) String rwNote,@Param(value = "flag")String flag,@Param(value = "rwJsMan")String rwJsMan);

    @Modifying
    @Query(value = " update EXmXmrw set rwFlag= :flag,rwJsMan=:rwJsMan,rwJsDate=:rwJsDate where rwNote = :rwNote ")
    void  qxjs(@Param(value ="rwNote" ) String rwNote,@Param(value = "flag")String flag,@Param(value = "rwJsMan")String rwJsMan,@Param(value = "rwJsDate")String rwJsDate);

    @Modifying
    @Query(value = " update EXmXmrw set rwFlag= :flag,rwWcDate= :rwWcDate,rwWcGs= :rwWcGs,rwWcResult=:rwWcResult,rwWcRemarks=:rwWcRemarks,wcMan=:wcMan  where rwNote = :rwNote ")
    void  qxwc(@Param(value ="rwNote" ) String rwNote,@Param(value = "flag")String flag,@Param(value = "rwWcDate")String rwWcDate,@Param(value = "rwWcGs")String rwWcGs,@Param(value = "rwWcResult")String rwWcResult,@Param(value = "rwWcRemarks")String rwWcRemarks,@Param(value = "wcMan")String wcMan);

    @Modifying
    @Query(value = " update EXmXmrw set rwFlag= :flag,rwKsMan= :rwJsMan,rwKsDate=now()  where rwNote = :rwNote ")
    void  ksOption(@Param(value ="rwNote" ) String rwNote,@Param(value = "flag")String flag,@Param(value = "rwJsMan")String rwJsMan);

    @Modifying
    @Query(value = " update EXmXmrw set rwFlag= :flag,rwKsMan= :rwJsMan,rwKsDate= :rwKsDate  where rwNote = :rwNote ")
    void  qxks(@Param(value ="rwNote" ) String rwNote,@Param(value = "flag")String flag,@Param(value = "rwJsMan")String rwJsMan,@Param(value = "rwKsDate")String rwKsDate);


    @Modifying
    @Query(value = " update EXmXmrw set rwFlag= :flag,rwKhMan=:rwKhMan,rwKhDate=:rwKhDate,rwProcess=:rwProcess,rwPz=:rwPz,rwGs=:rwGs,rwCb=:rwCb,rwKhRemarks=:rwKhRemarks  where rwNote = :rwNote ")
    void  qxkp(@Param(value ="rwNote" ) String rwNote,@Param(value = "flag")String flag,@Param(value = "rwKhMan")String rwKhMan,@Param(value = "rwKhDate")String rwKhDate
            ,@Param(value = "rwProcess")String rwProcess,@Param(value = "rwPz")String rwPz,@Param(value = "rwGs")String rwGs,@Param(value = "rwCb")String rwCb,@Param(value = "rwKhRemarks")String rwKhRemarks);



}
