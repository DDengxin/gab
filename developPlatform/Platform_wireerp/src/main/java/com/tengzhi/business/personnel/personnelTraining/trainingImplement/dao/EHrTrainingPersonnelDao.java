package com.tengzhi.business.personnel.personnelTraining.trainingImplement.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.personnelTraining.trainingImplement.model.EHrTrainingPersonnel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EHrTrainingPersonnelDao extends BasedaoRepository<EHrTrainingPersonnel, String> {

}
