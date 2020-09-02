package com.tengzhi.business.platform.user.controller.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.property.Property;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.base.tools.MD5.MD5Util;
import com.tengzhi.business.platform.enroll.constant.GabUserTypeEnum;
import com.tengzhi.business.platform.enroll.dao.ExpertDao;
import com.tengzhi.business.platform.enroll.dao.GUserInfoDao;
import com.tengzhi.business.platform.enroll.dao.PersonalDao;
import com.tengzhi.business.platform.enroll.dao.SupplyDao;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import com.tengzhi.business.platform.enroll.model.G_Expert;
import com.tengzhi.business.platform.enroll.model.G_Supply;
import com.tengzhi.business.platform.enroll.model.G_personal;
import com.tengzhi.business.platform.enroll.vo.modelVo;
import com.tengzhi.business.platform.user.controller.service.PlatformService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.sms.constant.SmsType;
import com.tengzhi.business.system.sms.model.SysSmsLog;
import com.tengzhi.business.system.sms.service.SysSmsService;
import com.tengzhi.business.system.user.dao.GabSysUserDao;
import com.tengzhi.business.system.user.dao.GabeHrWorkerDao;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.user.service.SysUserService;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class PlatformServiceImpl implements PlatformService {

@Autowired
private  GUserInfoDao gUserInfoDao;

    @Override
    public BasePage<Map<String, Object>> getList(BaseDto dto) {
        // Map<String, String> map = dto.getParamsMap();
        String sql = " select \n" +
                "(case when sys_workflow_applyfor.id is null then '未启动'  when sys_workflow_applyfor.process_type  =  '1' then   '已驳回' \n" +
                "  when sys_workflow_applyfor.process_type  ='2'  then    '审批中'  when sys_workflow_applyfor.process_type  =  '3' then   '已结束' \n" +
                "else '未知' end)  approve_status,\n" +
                "\n" +
                "a.user_id guser_id ,a.user_name,a.user_type,a.user_mtel,a.erp_corp,a.erp_userid,a.data_date ,\n" +
                "b.user_id,b.nick_name,b.email,b.org_name,b.dept_name\n" +
                "from g_userinfo  a  left join  sys_workflow_applyfor on a.user_id = sys_workflow_applyfor.business_id  \n" +
                "left JOIN  sys_user  b   on  b.user_id = a.erp_userid    ";
        Map<String, String> map = dto.getParamsMap();
     String  sqlWhere  = " where  1=1  ";
        if (!ObjectUtil.isNull(map.get("srchRq1"))) {
            sqlWhere += " and a.data_date >='" + map.get("srchRq1") + "'";
        }
        if (!ObjectUtil.isNull(map.get("srchRq2"))) {
            sqlWhere += " and a.data_date  <='" + map.get("srchRq2") + "'";
        }
        if (!ObjectUtil.isNull(map.get("guserId"))) {
            sqlWhere += " and a.user_id  like  '%"+map.get("guserId")+"%'";
        }
        if (!ObjectUtil.isNull(map.get("userName"))) {
            sqlWhere += " and a.user_name  like   '%"+map.get("userName")+"%'";
        }





        return gUserInfoDao.QueryToMapPage(dto, sql+sqlWhere);
    }
}
