package com.tengzhi.business.system.user.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.user.model.SysUser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SysUserService extends BaseService  {


    BasePage<SysUser> findAll(BaseDto baseDto) throws IOException;


    SysUser findByUserId(String userId);

    Map<String,Object> getAvatarUrl(String userId);

    SysUser save(SysUser sysUser);

    String  ReadPath(String id);

    void update(SysUser sysUser) throws Exception;

    /**
     * 更新用户密码
     * @param newPassWord
     * @param oldPassWord
     * @return
     */
    Result updatePassword(String newPassWord, String oldPassWord);

    Result deleteByUserId(List<SysUser> list);


    boolean judgeUnique(SysUser sysUser);

    /**
     * 保存用户-角色授权信息
     * @param map
     */
    void saveUserAuth(Map<String,Object> map);

    /**
     * 查询已经启用的角列表(可提供对用户赋予角色时候使用)
     * @param baseDto
     * @return
     */
    List<Map<String,Object>> findSysUserRightAll(BaseDto baseDto);

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
     * 获取用户账套信息
     * @return
     */
    Map<String,Object> getUserCorpInfo();

    /**
     * 设置用户默认账套信息
     * @param defaultOrgId 默认(登录)账套
     * @param currentOrgId 当前操作展涛
     * @return
     */
    Result setUserCorpInfo(String defaultOrgId, String currentOrgId);

    /**
     * 清除缓存
     */
    void removeCache();
}
