package com.tengzhi.business.xt.getINOut.rylf.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.getINOut.rylf.dao.RylfDao;
import com.tengzhi.business.xt.getINOut.rylf.model.EXtRylf;
import com.tengzhi.business.xt.getINOut.rylf.service.RylfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;
@Service
@Transactional
public class RylfServiceImpl implements RylfService {

    @Autowired
    private RylfDao rylfDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.ge("lf_rq",map.get("srchRq1"));
            c.le("lf_rq",map.get("srchRq2"));
            c.eq("lf_name",map.get("srchName"));
            c.eq("jd_man",map.get("srchJdMan"));
            c.eq("jd_dept",map.get("srchJdDept"));
        });
        String sql = "select note,to_char(lf_sqrq,'yyyy-MM-dd')lf_sqrq,to_char(lf_rq,'yyyy-MM-dd')lf_rq,lf_name,lf_rs,lf_isyc,lf_ycsm,lf_flag,lf_sm,jd_man,jd_dept,data_corp," +
                "f_getname('GETUSERNAME',jd_man,'',data_corp)jd_man_name,f_getname('GETDEPTNAME',jd_dept,'',data_corp)dept_name,f_getname('GETCORPEXP','','',data_corp)corp_exp from e_xt_rylf "+where;

        return rylfDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EXtRylf save(EXtRylf eXtRylf) throws Exception {
        SessionUser sessionUser = SessionUser.SessionUser();
        String note = sysGenNoteService.getNote(EXtRylf.class,"LF","yyMM",4);
        eXtRylf.setNote(note);
        eXtRylf.setLfFlag("登记");
        return rylfDao.save(eXtRylf);
    }

    @Override
    public Map<String, Object> finById(String note) {

        return rylfDao.QueryToFirstMap("select note,to_char(lf_sqrq,'yyyy-MM-dd')lf_sqrq,to_char(lf_rq,'yyyy-MM-dd')lf_rq,lf_name,lf_rs,lf_isyc,lf_ycsm,lf_flag,lf_sm,jd_man,jd_dept,data_corp,"
                +"f_getname('GETUSERNAME',jd_man,'',data_corp)jdManName,f_getname('GETDEPTNAME',jd_dept,'',data_corp)deptName,f_getname('GETCORPEXP','','',data_corp)corpExp from e_xt_rylf where note=:1",note);
    }

    @Override
    public void update(EXtRylf eXtRylf) {
        SessionUser sessionUser = SessionUser.SessionUser();
        rylfDao.update(eXtRylf);
    }

    @Override
    public void deleteByNote(String note) {
        rylfDao.deleteByNote(note);
    }

    @Override
    public Result getFlag(String note, String flag) {
        String flagString = rylfDao.getFlag(note);
        if(flag.equals(flagString)){
            return Result.resultOkMsg("操作成功");
        }
        return Result.error("该单不是“"+flag+"”状态，不能操作");
    }

    @Override
    public Result confirm(String note) {
        rylfDao.updateFlag(note,"确认");
        return Result.resultOkMsg("确认");
    }

    @Override
    public Result cancle(String note) {
        rylfDao.updateFlag(note,"登记");
        return Result.resultOkMsg("取消确认");
    }
}
