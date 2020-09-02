package com.tengzhi.business.xt.getINOut.wpwc.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.getINOut.wpwc.dao.WpwcDao;
import com.tengzhi.business.xt.getINOut.wpwc.dao.WpwcMxDao;
import com.tengzhi.business.xt.getINOut.wpwc.model.EXtWpwc;
import com.tengzhi.business.xt.getINOut.wpwc.service.WpwcService;
import com.tengzhi.business.xt.getINOut.wpwc.vo.EXtWpwcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class WpwcServiceImpl implements WpwcService {

    @Autowired
    private WpwcDao wpwcDao;

    @Autowired
    private WpwcMxDao wpwcMxDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd(c->{
           c.ge("a.cc_rq",map.get("srchRq1"));
           c.le("a.cc_rq",map.get("srchRq2"));
           c.eq("a.note",map.get("srchNote"));
           c.eq("a.jb_dept",map.get("srchDept"));
           c.eq("a.jb_man",map.get("srchMan"));
        });
        String sql = "select a.note,to_char(a.cc_rq,'yyyy-MM-dd')cc_rq,a.jb_dept,f_getname('GETDEPTNAME',a.jb_dept,'',a.data_corp)dept_name,a.jb_man,f_getname('GETUSERNAME',a.jb_man,'',a.data_corp)man_name,a.cc_add,a.wc_type,b.is_back,b.hc_man,f_getname('GETUSERNAME',b.hc_man,'',a.data_corp)hc_man_name," +
                "to_char(b.hc_rq,'yyyy-MM-dd')hc_rq,a.cc_sm,a.cc_flag,a.data_corp,f_getname('GETCORPEXP','','',a.data_corp)corp_exp,a.mcc_man,a.mcc_rq,a.mjc_man,a.mjc_rq,b.sid,b.cc_wp,b.wp_sl,b.wp_unit,b.wp_sm" +
                " from e_xt_wpwc a,e_xt_wpwc_mx b where a.note = b.note  "+where;
        return wpwcDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EXtWpwc save(EXtWpwcVo eXtWpwcVo) throws Exception {
        SessionUser sessionUser = SessionUser.SessionUser();
        String note = sysGenNoteService.getNote(EXtWpwc.class,"WPWC","yyMM",4);
        eXtWpwcVo.geteXtWpwc().setNote(note);
        eXtWpwcVo.geteXtWpwc().setCcFlag("登记");
        if(!eXtWpwcVo.getAddedList().isEmpty()){
            eXtWpwcVo.getAddedList().forEach(mx->{
                mx.setNote(note);
                String sidString = wpwcMxDao.getSingleResult("select max(sid) from e_xt_wpwc_mx");
                int num = (sidString==""||sidString==null?0:Integer.parseInt(sidString))+1;
                mx.setSid(Integer.toString(num));
                wpwcMxDao.save(mx);
            });
        }
        if(!eXtWpwcVo.getDeletedList().isEmpty()){
            eXtWpwcVo.getDeletedList().forEach(mx->{
                wpwcMxDao.deleteAll(eXtWpwcVo.getDeletedList());
            });
        }
        if(!eXtWpwcVo.getModifyedList().isEmpty()){
            eXtWpwcVo.getModifyedList().forEach(mx->{
                wpwcMxDao.dynamicSave(mx,wpwcMxDao.findById(mx.getSid()).orElse(null));
            });
        }
        EXtWpwc eXtWpwc = wpwcDao.save(eXtWpwcVo.geteXtWpwc());
        return eXtWpwc;
    }

    @Override
    public void update(EXtWpwcVo eXtWpwcVo) {
        if(!eXtWpwcVo.getAddedList().isEmpty()){
            eXtWpwcVo.getAddedList().forEach(mx->{
                mx.setNote(eXtWpwcVo.geteXtWpwc().getNote());
                String sidString = wpwcMxDao.getSingleResult("select max(sid) from e_xt_wpwc_mx");
                int num = (sidString==""||sidString==null?0:Integer.parseInt(sidString))+1;
                mx.setSid(Integer.toString(num));
                wpwcMxDao.save(mx);
            });
        }
        if(!eXtWpwcVo.getDeletedList().isEmpty()){
            eXtWpwcVo.getDeletedList().forEach(mx->{
                wpwcMxDao.deleteAll(eXtWpwcVo.getDeletedList());
            });
        }
        if(!eXtWpwcVo.getModifyedList().isEmpty()){
            eXtWpwcVo.getModifyedList().forEach(mx->{
                wpwcMxDao.dynamicSave(mx,wpwcMxDao.findById(mx.getSid()).orElse(null));
            });
        }
        wpwcDao.dynamicSave(eXtWpwcVo.geteXtWpwc(),wpwcDao.findById(eXtWpwcVo.geteXtWpwc().getNote()).orElse(null));
    }

    @Override
    public EXtWpwc findByNote(String note) {
        return wpwcDao.QueryListModel(EXtWpwc.class,"select note,cc_rq,jb_dept,f_getname('GETDEPTNAME',jb_dept,'',data_corp)dept_name,jb_man,f_getname('GETUSERNAME',jb_man,'',data_corp)man_name," +
                "cc_add,wc_type,cc_sm,cc_flag,data_corp,f_getname('GETCORPEXP','','',data_corp)corp_exp,mcc_man,mcc_rq,mjc_man,mjc_rq from e_xt_wpwc where note=:1",note).get(0);
    }

    @Override
    public void deleteByNote(String note) {
        wpwcDao.deleteByNote(note);
        wpwcMxDao.deleteByNote(note);
    }

    @Override
    public Result getFlag(String note, String flag) {
        String flagString = wpwcDao.getFlag(note);
        if(flag.equals(flagString)){
            return Result.resultOkMsg("操作成功");
        }
        return Result.error("该单不是“" + flag + "”状态,不能操作！");
    }

    @Override
    public Result confirm(String note) {
        wpwcDao.updateFlag(note,"确认");
        return Result.resultOk("确认");
    }

    @Override
    public Result cancle(String note) {
        wpwcDao.updateFlag(note,"登记");
        return Result.resultOk("取消");
    }

    @Override
    public BasePage<Map<String, Object>> getWpwcMx(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = " and a.note = '"+map.get("note")+"'";
        String sql = "select a.sid,a.note,a.cc_wp,a.wp_sl,a.wp_unit,a.wp_sm,a.is_back,a.hc_man,f_getname('TransformUser',a.hc_man,'','')hc_man_name,to_char(a.hc_rq,'yyyy-MM-dd')hc_rq from e_xt_wpwc_mx a,e_xt_wpwc b where a.note = b.note "+where;
        return wpwcMxDao.QueryMapPageList(baseDto,sql,true);
    }
}
