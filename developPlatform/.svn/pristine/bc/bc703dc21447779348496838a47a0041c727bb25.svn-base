package com.tengzhi.business.system.sms.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.sms.model.SysSmsLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SysSmsDao extends BasedaoRepository<SysSmsLog, String> {

    SysSmsLog findByCorpIdAndUserIdAndPhone(String corpId, String userId, String phone);

    /**
     * 使得符合条件的条目失效
     *
     * @param type   类型
     * @param corpId 机构
     * @param userId 用户
     * @param phone  手机号
     */
    @Modifying
    @Query(value = "update SysSmsLog  set expired = true where type = :type and corpId = :corpId and userId = :userId and phone = :phone ")
    void invalidLog(@Param("type") String type, @Param("corpId") String corpId, @Param("userId") String userId, @Param("phone") String phone);
}