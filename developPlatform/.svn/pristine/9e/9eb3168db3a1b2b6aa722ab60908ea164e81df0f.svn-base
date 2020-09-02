
package com.tengzhi.business.system.user.service.Impl;

import com.tengzhi.base.security.common.enums.AuthorityRoleEnum;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.setting.dao.SysSetDao;
import com.tengzhi.business.system.setting.model.SysSet;
import com.tengzhi.business.system.user.dao.SysUserDao;
import com.tengzhi.business.system.user.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysSetDao sysSetDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SysUser user = sysUserDao.findByJobNumberAndDeleteLogo(username, false);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在或用户被禁用");
        }
        grantedAuthorities.add(AuthorityRoleEnum.PLATFORM_USER.toSimpleGrantedAuthority());
        grantedAuthorities.add(AuthorityRoleEnum.ACTIVITI_USER.toSimpleGrantedAuthority());
        grantedAuthorities.add(AuthorityRoleEnum.ACTIVITI_ADMIN.toSimpleGrantedAuthority());

        // 扩展自定义用户类
        SessionUser employee = new SessionUser(user.getJobNumber(), user.getPassword(), grantedAuthorities);
        employee.setUserId(user.getUserId());
        employee.setJobNumber(user.getJobNumber());
        employee.setNickName(user.getNickName());
        employee.setMobile(user.getMobile());
        employee.setBusinessPersonnelIds(user.getBusinessPersonnelIds());
        //设置用户档案所在账套
        employee.setOriginalCorpId(user.getOrgId());
        //优先设置默认账套
        if (StringUtils.isNotEmpty(user.getDefaultOrgId())) {
            employee.setCorpId(user.getDefaultOrgId());
        } else {
            employee.setCorpId(user.getOrgId());
        }

        employee.setEmail(user.getEmail());
        employee.setGenTime(user.getGenTime());
        employee.setGender(user.getGender());
        employee.setRealName(user.getRealName());
        employee.setOrgName(user.getOrgName());
        employee.setSysUser(user);
        /*获取配置项*/
        employee.setCgsqSysSet(sysSetDao.findBySysSecodeAndSysCorp("DGSQ", employee.getCorpId()));
        if (null == employee.getCgsqSysSet()) {
            SysSet sysSet = new SysSet();
            sysSet.setSysCorp(employee.getCorpId());
            sysSet.setSysSecode("DGSQ");
            sysSet.setSysValue("NO");
            employee.setCgsqSysSet(sysSet);
        }

        return employee;
    }
}