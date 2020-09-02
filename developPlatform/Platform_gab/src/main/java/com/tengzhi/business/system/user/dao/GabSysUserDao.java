package com.tengzhi.business.system.user.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Primary
public interface GabSysUserDao extends SysUserDao {
	/**
	 * 哥爱帮 供应商，专家，需求方 审批通过 ，修改机构id,为审核人所在机构id
	 *
	 * @param userId 用户id
	 * @param orgId  机构id
	 * @return 影响的记录条数
	 */
	@Override
	@Modifying
	@Query(value = " update  SysUser  set orgId = :orgId  where userId= :userId  ")
	int updateOrgId(@Param(value = "userId") String userId, @Param(value = "orgId") String orgId);


//	  @Query(value="select supply_id from sys_user where user_id=:user_id",nativeQuery = true)
//	  String findBySupplyId(@Param(value = "user_id") String user_id);

//	  @Query(value="select user_id from sys_user   where supply_id=:supply_id limit 1",nativeQuery = true)
//	  String findUserId(@Param(value = "supply_id") String supply_id);


	@Modifying
	@Query(" DELETE FROM SysUser  WHERE userId = :userId")
	void deleteByUserIdTrue(@Param("userId") String userId);


}
