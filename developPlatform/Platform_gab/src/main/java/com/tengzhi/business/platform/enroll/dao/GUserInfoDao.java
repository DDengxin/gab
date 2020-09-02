package com.tengzhi.business.platform.enroll.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GUserInfoDao extends BasedaoRepository<GUserInfo, String>  {
    /**
     * 通过用户ID获取实体
     * @param userId
     * @return
     */
    GUserInfo findByUserId(String userId);
    /**
     * 通过手机号码获取实体
     * @param userMtel
     * @return
     */
    GUserInfo findByUserMtel(String userMtel);


    @Modifying
    @Query(value = " update  GUserInfo  set flag = :flag  where userId= :userId")
    int updateFlag(@Param(value = "userId") String userId, @Param(value = "flag") String flag);

    @Query(value = "SELECT  flag  from  GUserInfo   where  userId =:userId and  isPlatform =true   ")
    String getGUserInfoStatus(@Param("userId") String userId);


    /**
     * 通过ERP用户ID以及ERP公司账套获取实体
     *
     * @param erpUserId ERP用户ID
     * @param erpCorp   ERP公司账套
     * @return
     */
    GUserInfo findByErpUseridAndErpCorp(String erpUserId, String erpCorp);


    /*@Modifying
    @Query(value = " update  GUserInfo  set erpUserid = :flag    ,    where userId= :userId")
    int updateErpDataAndErpUserid(@Param(value = "userId") String userId, @Param(value = "flag") String flag);
    */




}
