package com.tengzhi.business.system.position.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.position.model.SysPosition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 岗位已弃用，请不要使用
 */
@Deprecated
public interface SysPositionDao extends BasedaoRepository<SysPosition,String> {
     SysPosition findByPositionId(String deptId);

     SysPosition findByPositionIdAndDeleteLogo(String positionId,Boolean bool);

     @Modifying
     @Query("update SysPosition set deleteLogo=true where positionId = :positionId")
     void delLogByPositionId(@Param("positionId") String positionId);

     /**
      * @Param: [uuid, roleId, rightId]
      * @return: int
      * @Author: 王翔
      * @Date: 2020/4/21 20:10
      * @description: 原生Sql授权角色常规新增
      */
     @Modifying
     @Query(value = "INSERT INTO sys_position_r_role(id,role_id,position_id)VALUES(:uuid,:role_id,:position_id);",nativeQuery = true)
     int PositionAuthAdd(@Param("uuid") String uuid, @Param("role_id") String roleId, @Param("position_id") String positionId);

     /**
      * @Param: [uuid, roleId, rightId]
      * @return: int
      * @Author: 王翔
      * @Date: 2020/4/21 20:10
      * @description: 原生Sql授权角色常规删除
      */
     @Modifying
     @Query(value = "DELETE FROM sys_position_r_role where position_id = :positionId ",nativeQuery = true)
     int PositionAuthDelete(@Param("positionId") String positionId);


     int countByTierIdLikeAndDeleteLogo(String deptId,Boolean bool);

}
