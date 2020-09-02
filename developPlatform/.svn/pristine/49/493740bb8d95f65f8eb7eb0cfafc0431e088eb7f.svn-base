package com.tengzhi.business.ck.assets.idleAssets.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.ck.assets.idleAssets.model.ECkAssets;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: czf
 * @Date:2020-08-19 8:59
 */
public interface AssetsDao extends BasedaoRepository<ECkAssets,String> {

    @Modifying
    @Query(value="delete from ECkAssets where sid =:sid")
    void deleteById(@Param(value="sid")String sid);

    @Modifying
    @Query(value="update ECkAssets set zcFlag=(case when zcFlag='闲置' then '启用' else '闲置' end) where sid=:sid")
    void updateFlag(@Param(value="sid")String sid);
}
