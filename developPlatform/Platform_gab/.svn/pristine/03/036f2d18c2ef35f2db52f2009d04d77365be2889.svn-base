package com.tengzhi.base.security.common.model;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.property.Property;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import com.tengzhi.business.system.setting.model.SysSet;
import com.tengzhi.business.system.user.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author: gaodu
 **/
public class SessionUser extends SecurityUser {
    private SysUser sysUser;
    private GUserInfo gUserInfo;


    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 获取哥爱帮平台用户基础信息
     *
     * @return
     */
    public GUserInfo getgUserInfo() {
        return gUserInfo;
    }

    public void setgUserInfo(GUserInfo gUserInfo) {
        this.gUserInfo = gUserInfo;
    }

    /**
     * 判断当前用户是否为哥爱帮平台用户
     *
     * @return
     */
    public static boolean isGabUser() {
        return null != SessionUser() && null != SessionUser().getgUserInfo();
    }
    /**
     * 获取哥爱帮用户信息ID
     * @return
     */
    public static String getGabUserId() {
        return  isGabUser() ? SessionUser().getgUserInfo().getUserId() : null;
    }

    /**
     * 构造函数
     *
     * @param username
     * @param password
     * @param authorities
     */
    public SessionUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     * 构造函数
     *
     * @param username
     * @param password
     * @param authorities
     */
    public SessionUser(String username, String userid, String password,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, userid, password, authorities);
    }

    /**
     * 构造参数
     *
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    public SessionUser(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    /**
     * 获取SessionUser对象
     * 该对象能获得当前登陆者的关联信息
     *
     * @return
     */
    public static SessionUser SessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || null == authentication.getPrincipal()) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            if (request.getSession().getAttribute("authentication") != null) {
                return (SessionUser) request.getSession().getAttribute("authentication");
            } else {
                return null;
            }
        } else {
            SessionUser user = null;
            if (authentication.getPrincipal() != null && !"anonymousUser".equals(authentication.getPrincipal())) {
                user = ((SessionUser) authentication.getPrincipal());
            }
            return user;
        }
    }


    /**
     * 获取当前登录用户的【操作】账套
     *
     * @return
     */
    public static String getCurrentCorpId() {
        return SessionUser().getCorpId();
    }

    /*************************************不建议使用，以后将会移除************************************/
    //参数配置
    private SysSet cgsqSysSet;

    public SysSet getCgsqSysSet() {
        return cgsqSysSet;
    }

    public void setCgsqSysSet(SysSet cgsqSysSet) {
        this.cgsqSysSet = cgsqSysSet;
    }

    /*************************************END 不建议使用，以后将会移除************************************/

    /**
     * 超级管理员数组
     */
    private static String[] superUserIds = null;
    /**
     * 默认账套
     */
    private static String defaultCorp = null;

    /**
     * 获取当前项目默认配置的超级管理员用户数组
     *
     * @return
     */
    public static String[] getSuperUserIds() {
        if (null == superUserIds) {
            initDefaultProperty();
        }
        return superUserIds;
    }

    private static void setSuperUserIds(String[] superUserIds) {
        SessionUser.superUserIds = superUserIds;
    }

    /**
     * 获取当前项目默认配置的公司账套
     *
     * @return
     */
    public static String getDefaultCorp() {
        if (null == defaultCorp) {
            initDefaultProperty();
        }
        return defaultCorp;
    }

    private static void setDefaultCorp(String defaultCorp) {
        SessionUser.defaultCorp = defaultCorp;
    }

    /**
     * 初始化默认配置
     */
    private static void initDefaultProperty() {
        Property pro = SpringUtil.getBean(Property.class);
        if (pro.getBusiness() != null) {
            setSuperUserIds(ArrayUtil.removeBlank(StrUtil.blankToDefault(pro.getBusiness().getUserName(), StrUtil.EMPTY).split(",")));
            setDefaultCorp(StrUtil.blankToDefault(pro.getBusiness().getDefaultCorp(), StrUtil.EMPTY));
        }
    }

    /**
     * 【业务】判断当前登陆用户是为超级用户
     *
     * @return boolean
     */
    public static boolean isSuperUser() {
        return Arrays.asList(getSuperUserIds()).contains(SessionUser().getUserId());
    }

    /**
     * 【业务】获取当前登录关联的业务人员
     *
     * @return
     */
    public List<String> getBusinessPersonnelIdList() {
        return StrUtil.split(StrUtil.nullToDefault(getBusinessPersonnelIds(), StringUtils.EMPTY), ',', true, true);
    }


}
