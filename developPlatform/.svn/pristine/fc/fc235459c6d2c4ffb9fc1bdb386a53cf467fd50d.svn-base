package com.tengzhi.business.system.role.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.role.model.SysRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SysRoleDao extends BasedaoRepository<SysRole, String> {

    SysRole findByRoleId(String roleId);

    /**
     * 新增角-菜单授权
     *
     * @param uuid        uuid
     * @param roleId      角色ID
     * @param rightId     权限(菜单)ID
     * @param rightModule 权限模块
     * @param dataCorp    公司账套
     * @return
     */
    @Modifying
    @Query(value = "INSERT INTO "
            + " sys_role_r_right(id,role_id,right_id,right_module,data_corp) "
            + " VALUES(:uuid,:roleId,:rightId,:rightModule,:dataCorp);"
            , nativeQuery = true)
    int saveUserAuth(@Param("uuid") String uuid, @Param("roleId") String roleId, @Param("rightId") String rightId, @Param("rightModule") String rightModule, @Param("dataCorp") String dataCorp);

    /**
     * 刪除角色-菜单授权
     *
     * @param roleId 角色ID
     * @param rightModule 权限(菜单)模块
     * @param dataCorp 公司账套
     * @return
     */
    @Modifying
    @Query(value = "DELETE FROM  sys_role_r_right "
            + " where role_id=:roleId and right_module=:rightModule and data_corp = :dataCorp ", nativeQuery = true)
    int deleteUserAuthByRoleIdAndRightModuleAndDataCorp(@Param("roleId") String roleId, @Param("rightModule") String rightModule, @Param("dataCorp") String dataCorp);


}
