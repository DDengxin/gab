package com.tengzhi.business.xt.dailyRoutine.workPlan.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.dailyRoutine.workPlan.dao.WorkplanDao;
import com.tengzhi.business.xt.dailyRoutine.workPlan.model.EXtWorkplan;
import com.tengzhi.business.xt.dailyRoutine.workPlan.service.WorkplanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-20 14:24
 */
@Service
@Transactional
public class WorkplanServiceImpl implements WorkplanService {

    @Autowired
    private WorkplanDao workplanDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = "where 1=1";
        if(StringUtils.isNotEmpty(map.get("srchRq1"))){
            if(map.get("srchZt").equals("start")){
                where +=" and to_char(start_rq,'yyyy-MM-dd') >='"+map.get("srchRq1")+"'";
            }else{
                where +=" and to_char(end_rq,'yyyy-MM-dd') >='"+map.get("srchRq1")+"'";
            }
        }
        if(StringUtils.isNotEmpty(map.get("srchRq2"))){
            if(map.get("srchZt").equals("start")){
                where +=" and to_char(start_rq,'yyyy-MM-dd') <='"+map.get("srchRq2")+"'";
            }else{
                where +=" and to_char(end_rq,'yyyy-MM-dd') <='"+map.get("srchRq2")+"'";
            }
        }
        if(StringUtils.isNotEmpty(map.get("srchMan"))){
            where +=" and data_man ='"+map.get("srchMan")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("srchType"))){
            where +=" and gz_type ='"+map.get("srchType")+"'";
        }
        String sql = "select note,to_char(start_rq,'yyyy-MM-dd hh24:mi')start_rq,to_char(end_rq,'yyyy-MM-dd hh24:mi')end_rq,to_char(gz_remind,'yyyy-MM-dd hh24:mi')gz_remind," +
                "to_char(data_date,'yyyy-MM-dd hh24:mi')data_date,gz_type,gz_address,gz_remarks,data_man,data_corp,f_getname('USERNAMERWTOCOL',data_man,'','')man_name from e_xt_workplan "+where;

        return workplanDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EXtWorkplan save(EXtWorkplan eXtWorkplan) throws Exception {
        String note = sysGenNoteService.getNote(EXtWorkplan.class,"JH","yyMM",3);
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtWorkplan.setNote(note);
        eXtWorkplan.setDataCorp(sessionUser.getCorpId());
        eXtWorkplan.setDataDate(new Date());
        eXtWorkplan.setDataMan(sessionUser.getUserId());
        return workplanDao.save(eXtWorkplan);
    }

    @Override
    public void update(EXtWorkplan eXtWorkplan) {
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtWorkplan.setDataCorp(sessionUser.getCorpId());
        eXtWorkplan.setDataDate(new Date());
        eXtWorkplan.setDataMan(sessionUser.getUserId());
        workplanDao.update(eXtWorkplan);
    }

    @Override
    public Map<String, Object> findByNote(String note) {
        return workplanDao.QueryToFirstMap("select note,to_char(start_rq,'yyyy-MM-dd hh24:mi')start_rq,to_char(end_rq,'yyyy-MM-dd hh24:mi')end_rq,to_char(gz_remind,'yyyy-MM-dd hh24:mi')gz_remind,"+
                "to_char(data_date,'yyyy-MM-dd hh24:mi')data_date,gz_type,gz_address,gz_remarks,data_man,data_corp from e_xt_workplan where note =:1",note);
    }

    @Override
    public void deleteByNote(String note) {
        workplanDao.deleteByNote(note);
    }
}
