package com.tengzhi.app.daily.service.impl;

import com.tengzhi.app.daily.dao.DailyDao;
import com.tengzhi.app.daily.service.DailyService;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class DailyServiceImpl implements DailyService {
    @Autowired
    DailyDao dailyDao;

    /**
     * 查询今天快讯
     */
    @Override
    public BasePage<Map<String, Object>> getTodayNews(BaseDto baseDto) throws IOException {
        String sql = "select sms_rq,sms_content  from e_xt_sms where sms_rq=(select max(sms_rq) from e_xt_sms)";
        return dailyDao.QueryToMapPage(baseDto, sql);
    }

    /**
     * 查询工作安排
     */
    @Override
    public BasePage<Map<String, Object>> getWorkPlan(BaseDto baseDto) throws IOException {
        String sql = "select note,to_char(start_rq,'yyyy-MM-dd hh24:mi')start_rq,to_char(end_rq,'yyyy-MM-dd hh24:mi')end_rq," +
                "to_char(gz_remind,'yyyy-MM-dd hh24:mi')gz_remind,to_char(data_date,'yyyy-MM-dd hh24:mi')data_date,gz_type," +
                "gz_address,gz_remarks,data_man,data_corp,f_getname('USERNAMERWTOCOL',data_man,'','')man_name from e_xt_workplan  order by note ";
        return dailyDao.QueryToMapPage(baseDto, sql);
    }

    /**
     * 查询外出申请
     */
    @Override
    public BasePage<Map<String, Object>> getGoOutApply(BaseDto baseDto) throws IOException {
        String sql = "select note,to_char(wc_rq,'yyyy-MM-dd')wc_rq,wc_dept,f_getname('GETDEPTNAME',wc_dept,'',data_corp)dept_name,wc_man," +
                "f_getname('GETUSERNAME',wc_man,'',data_corp)man_name,data_man,f_getname('USERNAMERWTOCOL',data_man,'','')data_man_name," +
                "wc_type,replace(wc_add,'其他',wc_addsm )wc_add,wc_addsm,wc_iszc,wc_yc,wc_ycsm,wc_sm,to_char(wc_tcrq,'MM-dd hh24:mi')wc_tcrq," +
                "to_char(wc_thrq,'MM-dd hh24:mi')wc_thrq,wc_lc,wc_fy,wc_fysm,wc_gzap,wc_zcsm,wc_flag,data_corp," +
                "f_getname('GETCORPEXP','','',data_corp)corpexp,wc_mcck,to_char(wc_mcrq,'MM-dd hh24:mi')wc_mcrq,wc_mhck," +
                "to_char(wc_mhrq,'MM-dd hh24:mi')wc_mhrq,wc_pcnote,wc_bznote,wc_sc from e_xt_wcsq  order by wc_rq ASC";
        return dailyDao.QueryToMapPage(baseDto, sql);
    }
}
