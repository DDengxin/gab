package com.tengzhi.business.platform.specialist.chamberactivities.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.specialist.chamberactivities.model.chamberActivities;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface chamberActivitiesDao extends BasedaoRepository<chamberActivities,String> {

    chamberActivities findByActivitiesId(String articleId);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update chamberActivities set homeShow=true where activitiesId=?1")
    int setShowUpdate(String id);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update chamberActivities set homeShow=false where activitiesId <> ?1")
    int setShowUpdateModifyNo(String id);

}
