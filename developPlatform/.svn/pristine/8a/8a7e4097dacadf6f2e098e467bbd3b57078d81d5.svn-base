package com.tengzhi.business.xt.dailyRoutine.travelReport.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.dailyRoutine.travelReport.model.EXtTravelReport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: czf
 * @Date:2020-08-21 10:35
 */
public interface TravelReportDao extends BasedaoRepository<EXtTravelReport,String> {

    @Query(value="select bz_flag from e_xt_travel_report where bz_note=:bzNote" ,nativeQuery=true)
    String getFlag(@Param(value="bzNote")String bzNote);

    @Modifying
    @Query(value=" update EXtTravelReport set bzFlag =:bzFlag  where bzNote = :bzNote")
    void updateFlag(@Param(value="bzNote")String bzNote,@Param(value="bzFlag")String bzFlag);

    @Modifying
    @Query(value="delete from EXtTravelReport  where bzNote =:bzNote")
    void deleteByNote(@Param(value="bzNote")String bzNote);


}
