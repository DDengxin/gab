package com.tengzhi.business.personnel.personnelTraining.trainingNotice.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.model.EHrTrainingNotice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainingNoticeDao extends BasedaoRepository<EHrTrainingNotice, String> {

/*    @Modifying
    @Query(value =" delete from EHrTrainingNotice where tzSid = :tzSid ")
    void deleteById(@Param(value = "tzNote") Integer tzSid);*/
    @Modifying
    @Query(value =" delete from EHrTrainingNotice where tzNote = :tzNote ")
    void deleteByNote(@Param(value = "tzNote") String tzNote);

   @Modifying
    @Query(value =" update EHrTrainingNotice set tzAddress=:tzAddress where tzNote =:tzNote ")
    void updateDataByNote(@Param(value = "tzAddress") String tzAddress,@Param(value = "tzNote") String tzNote);
}
