package com.tengzhi.business.personnel.personnelTraining.trainingReport.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.personnelTraining.trainingImplement.model.EHrTrainingRecord;
import com.tengzhi.business.personnel.personnelTraining.trainingReport.model.EHrTrainingReport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EHrTrainingReportDao extends BasedaoRepository<EHrTrainingReport, String> {

}
