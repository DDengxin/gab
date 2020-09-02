package com.tengzhi.business.project.projectArchives.xmda.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.project.projectArchives.xmda.dao.XmdaDao;
import com.tengzhi.business.project.projectArchives.xmda.model.EXmXmda;
import com.tengzhi.business.project.projectArchives.xmda.service.XmdaService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class XmdaServiceImpl implements XmdaService {

    @Autowired
    private XmdaDao xmdaDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public BasePage<EXmXmda> findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String sqlshere = " where data_corp in ('"+ SessionUser.SessionUser().getSysUser().getOrgIds().replaceAll(",", "','")+"') ";

        StringBuffer sqlwhere = SqlJoint.where(c->{
           c.contains("xm_id",map.get("xmId"));
           c.contains("xm_man",map.get("xmMan"));
        });
        String sql = "select xm_id,xm_name,xm_xtype,xm_type,f_getname('GETDEPTNAME',xm_dept,'',data_corp)xm_dept,xm_man,f_getname('GETUSERNAME',xm_man,'',data_corp) xm_man_name,xm_sm,xm_code,xm_flag,data_man,f_getname('GETUSERNAME',data_man,'',data_corp)data_man_name,data_date," +
                " f_getname('GETCORPEXP',data_corp,'',data_corp)data_corp_name,start_date,end_date,f_get_param_name('项目类型',xm_xtype,data_corp) xm_xtype_name,f_get_param_name('项目大类',xm_type,data_corp) xm_type_name," +
                " f_getname('GETDWEXP',xm_customer,'',data_corp) xm_customer_name,xm_enable_flag,xm_customer,xm_stage,f_get_param_name('项目阶段',xm_stage,data_corp) xm_stage_name" +
                " from e_xm_xmda " +sqlshere + sqlwhere;
        return xmdaDao.QueryPageLists(baseDto,sql);
    }

    /**
     * 保存对象信息
     * @param eXmXmda
     * */
    @Override
    public EXmXmda save(EXmXmda eXmXmda) throws Exception {
        SessionUser securityUser=SessionUser.SessionUser();
        eXmXmda.setXmId(sysGenNoteService.getNote(SysCustomer.class, "ZCL", "", 4));
        eXmXmda.setDataCorp(securityUser.getCorpId());
        eXmXmda.setDataMan(securityUser.getUsername());
        eXmXmda.setDataDate(new Date());
        eXmXmda.setXmFlag("登记");
        return xmdaDao.save(eXmXmda);
    }
    /**
     * 根据xmId获取对象数据
     * @param xmId
     * */
    @Override
    public Map<String,Object> findByXmId(String xmId) {
        String sql = " select *,f_getname('GETDWEXP',xm_customer,'',data_corp) xm_customer_name,f_getname('GETUSERNAME',xm_man,'',data_corp) xm_man_name from e_xm_xmda where xm_id='"+xmId+"' ";
        return xmdaDao.QueryToFirstMap(sql);
    }
    /**
     * 删除
     * @param xmId
     * */
    @Override
    public void deleteByXmId(String xmId) {

        xmdaDao.deleteById(xmId);
    }

    /**
     * 状态判断
     * @param xmId
     * */
    @Override
    public Result getXmFlag(String xmId, String xmFlag) {
        String flagString=xmdaDao.getxmFlag(xmId);
        if(xmFlag.equals(flagString)) {
            return  Result.resultOk("操作成功！");
        }
        return  Result.error("该单不是“"+xmFlag+"”状态,不能操作！");
    }

    /**
     * 状态切换
     * @param xmFlag
     * */
    @Override
    public Result setxmFlag(String xmId, String xmFlag) {
        xmdaDao.setxmFlag(xmId,xmFlag);
        return  Result.resultOk("操作成功！");
    }

    /**
     * 修改数据
     * @param eXmXmda
     * */
    @Override
    public void update(EXmXmda eXmXmda) {
        xmdaDao.dynamicSave(eXmXmda, xmdaDao.findById(eXmXmda.getXmId()).orElse(null));
    }
}
