package com.tengzhi.business.mSbJt.inspectionRecord.dao;


import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mSbJt.inspectionRecord.model.MSbInspection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InspectionRecordDao extends BasedaoRepository<MSbInspection,String> {
    @Modifying
    @Query(value =" delete from MSbInspection where inspectionNote = :inspectionNote ")
    void deleteNote(@Param(value = "inspectionNote") String inspectionNote);
}
