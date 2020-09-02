package com.tengzhi.business.system.user.service.Impl;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.business.system.setting.service.Impl.SysSetServiceImpl;
import com.tengzhi.business.system.sms.constant.SmsType;
import com.tengzhi.business.system.sms.model.SysSmsLog;
import com.tengzhi.business.system.sms.service.SysSmsService;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.user.service.LoginService;
import com.tengzhi.business.system.user.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: gaodu
 * @date: 2020/4/29 8:58
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysSmsService sysSmsService;
    @Autowired
    SysSetServiceImpl sysSetServiceImpl;

    @Override
    public boolean sendLoginSms(String mobile, String corpId) throws Exception {
        // 获取登录用户
        SysUser sysUser = getLoginUserByPhoneAndCorpId(mobile, corpId);
        // 发送短信
        SysSmsLog sysSmsLog = sysSmsService.send(SysSmsLog.GenSms(sysUser, SmsType.LOGIN, RandomUtil.randomNumbers(6)));
        if (sysSmsLog.getSuccess()) {
            return true;
        } else {
            throw new Exception("短信发送失败");
        }
    }

    @Override
    public SysUser getLoginUserByPhoneAndCorpId(String mobile, String corpId) throws Exception {
        List<SysUser> sysUserList;
        // 获取用户
        if (!Validator.isMobile(mobile)) {
            throw new Exception("请输入正确的手机号码格式!");
        } else if (StringUtils.isNotEmpty(corpId)) {
            sysUserList = sysUserService.findByMobileAndOrgId(mobile, corpId);
        } else {
            sysUserList = sysUserService.findByMobile(mobile);
        }

        // 验证用户
        if (sysUserList.isEmpty()) {
            throw new Exception("用户不存在");
        } else {
            sysUserList = sysUserList.stream().filter(sysUser -> Objects.equals(sysUser.getDeleteLogo(), false) && Objects.equals(sysUser.getIsForbidden(), false)).collect(Collectors.toList());
            if (sysUserList.size() <= 0) {
                throw new Exception("用户不存在");
            } else if (sysUserList.size() > 1) {
                throw new Exception("该手机号码绑定了多个用户,无法发送验证码");
            } else {
                return sysUserList.get(0);
            }
        }
    }

    @Override
    public ModelAndView getSetting(String corp, ModelAndView mv) {
        List<Map> setting = sysSetServiceImpl.getSetting(corp);
        for (int i = 0; i < setting.size(); i++) {
            String paramId = StrUtil.nullToDefault((CharSequence) setting.get(i).get("param_id"), null);
            String sysValue = StrUtil.nullToDefault((CharSequence) setting.get(i).get("sys_value"), null);
            String sysFile = StrUtil.nullToDefault((CharSequence) setting.get(i).get("sys_file"), null);

            if ("SYS_SETTING_DLTP1".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "system/setting/image?line_primary=" + sysFile);
            } else if ("SYS_SETTING_DLTP2".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "system/setting/image?line_primary=" + sysFile);
            } else if ("SYS_SETTING_DLTP3".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "system/setting/image?line_primary=" + sysFile);
            } else if ("SYS_SETTING_DLTB".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "system/setting/image?line_primary=" + sysFile);
            } else if ("SYS_SETTING_XTMC".equals(paramId) && StringUtils.isNotBlank(sysValue)) {
                mv.addObject(paramId, setting.get(i).get("sys_value"));
            } else if ("SYS_SETTING_XTTB".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "system/setting/image?line_primary=" + sysFile);
            } else if ("SYS_SETTING_LOGIN_BG".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "system/setting/image?line_primary=" + sysFile);
            }
        }
        return mv;
    }
}
