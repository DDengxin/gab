package com.tengzhi.app.work.service.impl;

import com.tengzhi.app.daily.dao.DailyDao;
import com.tengzhi.app.work.dao.WorkDao;
import com.tengzhi.app.work.service.WorkService;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkDao workDao;
    /**
     * 查询客户档案
     */
    @Override
    public BasePage<Map<String, Object>> getCustomerProfile(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        SecurityUser securityUser=SessionUser.SessionUser();
        String sqlwhere = " where data_corp='"+securityUser.getCorpId()+"' and customer_type='"+map.get("stype")+"' ";
        String sql="select customer_id,customer_name,customer_exp,f_get_param_name('业务员',customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','')customer_buyer,customer_province,customer_uid,customer_flag,data_man,data_rq,f_get_param_name('业务员',customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','') customerbuyername,"
                + "customer_je,customer_day,f_get_param_name('客户等级',customer_level,'"+   SessionUser.getCurrentCorpId()   +"','')customer_level,f_getname('GETUSERNAME', data_man, '', data_corp) datamanname,"
                + "f_getname('GETDWEXP', customer_uid, '',data_corp) lastcustomername,f_get_param_name('区域城市',customer_province,'"+   SessionUser.getCurrentCorpId()   +"','') customerprovincename from sys_customer"+sqlwhere+" order by  customer_id";
        return workDao.QueryToMapPage(baseDto,sql);
    }
}
