package com.tengzhi.business.personnel.personnelTraining.trainingPlan.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.model.EHrTrainingPlan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrainingPlanDao extends BasedaoRepository<EHrTrainingPlan, String> {

    @Modifying
    @Query(value =" delete from EHrTrainingPlan where jhNote = :jhNote ")
    void deleteNote(@Param(value = "jhNote") String jhNote);

    @Query(value =" select distinct jhFlag from EHrTrainingPlan where jhNote = :jhNote ")
    String getJhFlag(@Param(value = "jhNote") String jhNote);

    @Modifying
    @Query(value =" update EHrTrainingPlan set jhFlag=:jhFlag  where jhNote = :jhNote ")
    void setJhFlag(@Param(value = "jhNote") String jhNote,@Param(value = "jhFlag") String jhFlag);
}