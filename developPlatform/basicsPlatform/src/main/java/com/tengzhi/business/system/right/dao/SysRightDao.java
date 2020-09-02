package com.tengzhi.business.system.right.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.right.model.SysRight;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysRightDao extends BasedaoRepository<SysRight, String> {

    /**
     * 通过rightId和rightType获取数据
     *
     * @param parentId
     * @param rightType
     * @return
     */
    @Query(value = " from SysRight where parentId = :parentId and rightType = :rightType order by rightOrd asc ")
    List<SysRight> findByParentIdAndRightType(@Param("parentId") String parentId, @Param("rightType")  String rightType);

    SysRight findByRightId(String rightId);

    List<SysRight> findByParentIdAndIsForbidden(String parentId,Boolean IsForbidden);

    /**
     * 删除权限节点(连带子节点)
     * @param rightId
     * @return
     */
    @Modifying
    @Query(value = "delete from SysRight where rightId = :rightId")
    int deleteByRightId(@Param(value = "rightId") String rightId);


    /**
     * 通过权限模块筛选
     * @param rightModule
     * @param isForbidden
     * @return
     */
    List<SysRight> findByRightModuleAndIsForbiddenOrderByRightOrdAsc(String rightModule,Boolean isForbidden);



    List<SysRight> findByParentId(String ParentId);

    @Modifying
    @Query(value = "update sys_right set right_id=?1 where right_id=?2 ",nativeQuery = true)
    int modifyRigthId(String newid,String oldid);

    @Modifying
    @Query(value = " update sys_right set parent_id=?1 where parent_id=?2 ",nativeQuery = true)
    int recursionRigthId(String newid,String oldid);

}