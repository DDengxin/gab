package com.tengzhi.business.system.org.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.org.model.SysOrg;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author 王翔
 * @create 2020-04-12
 */
public interface SysOrgDao extends BasedaoRepository<SysOrg,String> {


     SysOrg findByOrgId(String orgId);

     SysOrg findByOrgNameAndDeleteLogo(String orgName, Boolean bool);

     SysOrg findByOrgIdAndDeleteLogo(String orgId, Boolean bool);

     @Modifying
     @Query("update SysOrg set deleteLogo=true where tierId like :orgId")
     void delLogByTierId(@Param("orgId") String orgId);

     /**
      * 通过模糊匹配层级码和启用状态获取符合条件的数据总数
      *
      * @param orgId 公司账套(模糊匹配需要增加%)
      * @param state 启用状态
      * @return
      */
     int countByTierIdLikeAndState(String orgId, Boolean state);

}
