package com.tengzhi.business.system.dept.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.dept.model.SysDept;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysDeptDao extends BasedaoRepository<SysDept,String> {

     SysDept findByDeptId(String deptId);

     //SysDept findByDeptNameAndDeleteLogo(String deptName,Boolean bool);

     @Query(value = "select sysDept from SysDept sysDept where state = ?1 and ( dept_id = ?2 or parent_id = ?2) order by dept_id asc")
     List<SysDept> findProductionDepartment(@Param("state") Boolean state, @Param("pepartmentId") String pepartmentId);

     SysDept findByDeptIdAndDeleteLogo(String deptId, Boolean bool);

     @Modifying
     @Query("update SysDept set deleteLogo=true,state = false where tierId like :tierId")
     void deleteByTierId(@Param("tierId") String tierId);

     /**
      * 通过模糊匹配层级码和删除状态获取匹配的数据总数
      *
      * @param orgId     公司账套(模糊匹配需要%)
      * @param deleteLog 删除状态
      * @return
      */
     int countByTierIdLikeAndDeleteLogo(String orgId, Boolean deleteLog);

     /**
      * 通过模糊匹配层级码和删除状态获取匹配的数据总数
      *
      * @param orgId 公司账套(模糊匹配需要%)
      * @param state 启用状态
      * @return
      */
     int countByTierIdLikeAndState(String orgId, Boolean state);

}
