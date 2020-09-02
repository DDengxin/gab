package com.tengzhi.business.personnel.personnelTraining.trainingImplement.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.personnelTraining.trainingImplement.model.EHrTrainingRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EHrTrainingRecordDao extends BasedaoRepository<EHrTrainingRecord, String> {
    @Modifying
    @Query(value =" delete from EHrtrainingStatistics where jlNote = :jlNote ")
    void deleteByNote(@Param(value = "jlNote") String jlNote);
}
