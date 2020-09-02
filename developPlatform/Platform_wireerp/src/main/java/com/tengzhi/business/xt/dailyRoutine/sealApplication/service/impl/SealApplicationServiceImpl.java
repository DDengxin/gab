package com.tengzhi.business.xt.dailyRoutine.sealApplication.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.dailyRoutine.sealApplication.dao.SealApplicationDao;
import com.tengzhi.business.xt.dailyRoutine.sealApplication.model.EXtSealApplication;
import com.tengzhi.business.xt.dailyRoutine.sealApplication.service.SealApplicaitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-20 19:14
 */
@Service
@Transactional
public class SealApplicationServiceImpl implements SealApplicaitionService {

    @Autowired
    private SealApplicationDao applicationDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
           c.ge("rq",map.get("srchRq1"));
           c.le("rq",map.get("srchRq2"));
           c.eq("note",map.get("srchNote"));
           c.eq("use_corp",map.get("srchUseCorp"));
           c.eq("seal_type",map.get("srchType"));
           c.eq("use_category",map.get("srchCategory"));
        });
        String sql = "select note,to_char(rq,'yyyy-MM-dd')rq,use_corp,use_dept,deliver_dept,is_out,out_addr,to_char(estimated_time,'yyyy-MM-dd hh24:mi')estimated_time," +
                "to_char(real_time,'yyyy-MM-dd hh24:mi')real_time,seal_page,seal_type,use_category,use_description,flag,data_man,seal_corp,f_getname('GETCORPEXP','','',use_corp)use_corp_name," +
                "f_getname('GETDEPTNAME',use_dept,'','')use_dept_name,f_getname('GETDEPTNAME',deliver_dept,'','')deliver_dept_name,f_getname('GETCORPEXP','','',seal_corp)seal_corp_name," +
                "f_getname('USERNAMERWTOCOL',data_man,'','')data_man_name from e_xt_seal_application "+where;
        return applicationDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EXtSealApplication save(EXtSealApplication application) throws Exception {
        SessionUser sessionUser = SessionUser.SessionUser();
        String note = sysGenNoteService.getNote(EXtSealApplication.class,"GZSQ","yyMM",3);
        application.setNote(note);
        application.setDataMan(sessionUser.getUserId());
        application.setFlag("登记");

        return applicationDao.save(application);
    }

    @Override
    public void update(EXtSealApplication application) {
        SessionUser sessionUser = SessionUser.SessionUser();
        application.setDataMan(sessionUser.getUserId());
        applicationDao.update(application);
    }

    @Override
    public Map<String, Object> findByNote(String note) {
        return applicationDao.QueryToFirstMap("select note,to_char(rq,'yyyy-MM-dd')rq,use_corp,use_dept,deliver_dept,is_out,out_addr,to_char(estimated_time,'yyyy-MM-dd hh24:mi')estimated_time," +
               "to_char(real_time,'yyyy-MM-dd hh24:mi')real_time,seal_page,seal_type,use_category,use_description,flag,data_man,seal_corp,f_getname('GETCORPEXP','','',use_corp)use_corp_name,"+
               "f_getname('GETDEPTNAME',use_dept,'','')use_dept_name,f_getname('GETDEPTNAME',deliver_dept,'','')deliver_dept_name,f_getname('GETCORPEXP','','',seal_corp)seal_corp_name," +
               "f_getname('USERNAMERWTOCOL',data_man,'','')data_man_name from e_xt_seal_application where note=:1",note);
    }

    @Override
    public void deleteByNote(String note) {
        applicationDao.deleteByNote(note);
    }

    @Override
    public Result getFlag(String note, String flag) {
        String flagString = applicationDao.getFlag(note);
        if(flag.equals(flagString)){
            return Result.resultOkMsg("操作成功");
        }
        return Result.error("该单不是“"+flag+"”状态，不能操作!");
    }

    @Override
    public Result confirm(String note) {
        applicationDao.updateFlag(note,"确认");
        return Result.resultOkMsg("确认");
    }

    @Override
    public Result cancle(String note) {
        applicationDao.updateFlag(note,"登记");
        return Result.resultOkMsg("取消确认");
    }
}
