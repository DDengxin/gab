package com.tengzhi.business.system.user.dao;

import com.tengzhi.business.personnel.eHrWorker.dao.eHrWorkerDao;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Primary
public interface GabeHrWorkerDao extends eHrWorkerDao {
    @Modifying
    @Query(value = " DELETE FROM e_hr_worker  WHERE worker_id= :workerId", nativeQuery = true)
    int deleteByWorkIdTrue(@Param("workerId") String workerId);

}
