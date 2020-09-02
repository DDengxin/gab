package com.tengzhi.business.system.user.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.user.model.SysUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserDao extends BasedaoRepository<SysUser, String> {

     SysUser findByUserId(String userId);

     /**
      * 通过用户ID和公司账获取实体
      *
      * @param userId 用户ID
      * @param orgId  公司账套
      * @return
      */
     SysUser findByUserIdAndOrgId(String userId, String orgId);

     SysUser findByJobNumber(String JobNumber);

     SysUser findByJobNumberAndDeleteLogo(String JobNumber, boolean deleteLogo);

     @Modifying
     @Query("update SysUser set deleteLogo=true where userId = :userId")
     void deleteByUserId(@Param("userId") String userId);

     /**
      * 新增用戶-角色授權
      * @param uuid
      * @param userId
      * @param roleId
      * @param dataCorp
      * @return
      */
     @Modifying
     @Query(value = "insert into sys_user_r_role(id,user_id,role_id,data_corp) "
                    +" values(:uuid,:userId,:roleId,:dataCorp);"
             ,nativeQuery = true)
     int saveUserAuth(@Param("uuid") String uuid, @Param("userId") String userId, @Param("roleId") String roleId,@Param("dataCorp") String dataCorp);

     /**
      * 刪除用戶-角色授權
      * @param userId
      * @param dataCorp
      * @return
      */
     @Modifying
     @Query(value = "delete from sys_user_r_role "
             +" where user_id = :userId and data_corp = :dataCorp "
             ,nativeQuery = true)
     int deleteRoleAuthByUserIdAndDataCorp(@Param("userId") String userId,@Param("dataCorp") String dataCorp);


     int countByTierIdLikeAndDeleteLogo(String positionId,Boolean bool);



     @Modifying
     @Query("update SysUser set tierId=?1,deptId=?2,deptName=?3,positionName=?4 where tierId like ?5")
     int updateTier(String tierId,String deptId,String deptName,String positionName,String id);


     /**
      * 通过手机号码查询用户列表
      * @return
      */
     List<SysUser> findByMobile(String mobile);

     /**
      * 通过手机号码以及公司账套查询用户列表
      * @return
      */
     List<SysUser> findByMobileAndOrgId(String mobile,String orgId);
     
     /**
      * 通过查询 jobNumber 查询用户的数量
      * */
     @Query(value="select count(*) from sys_user where delete_logo=false and job_number = :jobNumber",nativeQuery = true)
     int findByUnique(@Param("jobNumber") String jobNumber);
     
     /**
      * 哥爱帮 供应商，专家，需求方 审批通过 ，修改机构id,为审核人所在机构id
      * @param user_id 用户id
      * @param org_id 机构id
      * @return 影响的记录条数
      */
 	@Modifying
 	@Query(value = " update  SysUser  set org_id = :org_id  where user_id= :user_id  ")
 	int updateOrgId(@Param(value = "user_id") String user_id,@Param(value = "org_id") String org_id);

}
