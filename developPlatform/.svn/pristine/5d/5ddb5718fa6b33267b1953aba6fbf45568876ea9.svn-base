package com.tengzhi.business.system.sms.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.sms.model.SysSmsLog;

import java.io.IOException;
import java.util.List;

public interface SysSmsService extends BaseService {

    /**
     * 查找所有信息
     * @param baseDto
     * @return
     * @throws IOException
     */
    BasePage<SysSmsLog> findAll(BaseDto baseDto) throws IOException;

    /**
     * 通过UUID查找记录
     * @param uuid
     * @return
     */
    SysSmsLog findByUuid(String uuid);

    /**
     * 发送邮件
     * @param sysSmsLog
     * @return
     */
    SysSmsLog send(SysSmsLog sysSmsLog);

    /**
     * 获取符合条件的短信记录
     * @param type 短信类型
     * @param corpId 账套ID
     * @param userId 用户ID
     * @param phone 手机号码
     * @param expired 是否过期(若为null，则不匹配该条件)
     * @param success 是否发送成功(若为null，则不匹配该条件)
     * @return
     */
    List<SysSmsLog> getSmsLog(String type, String corpId, String userId, String phone, Boolean expired, Boolean success);

    /**
     * 按照时间倒叙获取第一条符合条件的短信
     *  @param type 短信类型
     *      * @param corpId 账套ID
     *      * @param userId 用户ID
     *      * @param phone 手机号码
     *      * @param expired 是否过期(若为null，则不匹配该条件)
     *      * @param success 是否发送成功(若为null，则不匹配该条件)
     * @return
     */
    SysSmsLog getFristSmsLog(String type, String corpId, String userId, String phone, Boolean expired, Boolean success);

    /**
     * 查找记录
     * @param corpId
     * @param userId
     * @param phone
     * @return
     */
    SysSmsLog findByCorpIdAndUserIdAndPhone(String corpId,String userId,String phone);

    /**
     * 通过UUID删除记录
     * @param uuid
     */
    void deleteByUuid(String uuid);
    /**
     * 使得随机码失效
     * @param type
     * @param corpId
     * @param userId
     * @param phone
     * @return
     */
    void invalidSmsLog(String type,String corpId,String userId,String phone);

    /**
     * 使得验证码失效
     * @param sysSmsLog
     */
    void invalidSmsLog(SysSmsLog sysSmsLog);

}
