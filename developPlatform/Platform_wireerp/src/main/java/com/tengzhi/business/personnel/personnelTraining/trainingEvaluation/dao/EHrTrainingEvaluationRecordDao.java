package com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.model.EHrTrainingEvaluationRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EHrTrainingEvaluationRecordDao extends BasedaoRepository<EHrTrainingEvaluationRecord, String> {
    @Modifying
    @Query(value =" delete from EHrtrainingStatistics where jlNote = :jlNote ")
    void deleteByNote(@Param(value = "jlNote") String jlNote);
}
