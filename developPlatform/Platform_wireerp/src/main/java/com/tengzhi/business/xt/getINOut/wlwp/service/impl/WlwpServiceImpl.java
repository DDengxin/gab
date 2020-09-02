package com.tengzhi.business.xt.getINOut.wlwp.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.getINOut.wlwp.dao.WlwpDao;
import com.tengzhi.business.xt.getINOut.wlwp.dao.WlwpMxDao;
import com.tengzhi.business.xt.getINOut.wlwp.model.EXtWlwp;
import com.tengzhi.business.xt.getINOut.wlwp.service.WlwpService;
import com.tengzhi.business.xt.getINOut.wlwp.vo.EXtWlwpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class WlwpServiceImpl implements WlwpService {

    @Autowired
    private WlwpDao wlwpDao;

    @Autowired
    private WlwpMxDao wlwpMxDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where  = SqlJoint.whereSuffixAnd(c->{
            c.ge("a.rc_rq",map.get("srchRq1"));
            c.le("a.rc_rq",map.get("srchRq2"));
            c.eq("a.note",map.get("srchNote"));
            c.eq("a.jb_dept",map.get("srchDept"));
            c.eq("a.jb_man",map.get("srchMan"));
        });
        String sql = "select a.note,to_char(a.rc_rq,'yyyy-MM-dd')rc_rq,a.wp_origin,a.wp_type,a.jb_dept,a.jb_man,b.is_out,b.cc_man,to_char(b.cc_rq,'yyyy-MM-dd')cc_rq," +
                "a.rc_sm,a.rc_flag,a.data_corp,a.data_man,b.sid,b.wp_name,b.wp_sl,b.wp_unit,b.wp_sm,f_getname('GETUSERNAME',a.jb_man,'',a.data_corp)man_name," +
                "f_getname('GETDEPTNAME',a.jb_dept,'',a.data_corp)dept_name,f_getname('GETCORPEXP','','',a.data_corp)corp_exp,f_getname('GETUSERNAME',b.cc_man,'',a.data_corp)cc_man_name " +
                "from e_xt_wlwp a,e_xt_wlwp_mx b where a.note = b.note "+where;
        return wlwpDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EXtWlwp save(EXtWlwpVo eXtWlwpVo) throws Exception {
        SessionUser sessionUser = SessionUser.SessionUser();
        String note = sysGenNoteService.getNote(EXtWlwp.class,"WLWP","yyMM",4);
        eXtWlwpVo.geteXtWlwp().setRcFlag("登记");
        eXtWlwpVo.geteXtWlwp().setDataMan(sessionUser.getUserId());
        eXtWlwpVo.geteXtWlwp().setNote(note);
        if(!eXtWlwpVo.getAddedList().isEmpty()){
            eXtWlwpVo.getAddedList().forEach(mx->{
                mx.setNote(note);
                String sidString = wlwpMxDao.getSingleResult("select max(sid) from e_xt_wlwp_mx");
                int num = (sidString ==""||sidString==null?0:Integer.parseInt(sidString))+1;
                mx.setSid(Integer.toString(num));
                wlwpMxDao.save(mx);
            });
        }
        if(!eXtWlwpVo.getRemovedList().isEmpty()){
            eXtWlwpVo.getRemovedList().forEach(mx->{
                wlwpMxDao.deleteAll(eXtWlwpVo.getRemovedList());
            });
        }
        if(!eXtWlwpVo.getModifyedList().isEmpty()){
            eXtWlwpVo.getModifyedList().forEach(mx->{
                wlwpMxDao.dynamicSave(mx,wlwpMxDao.findById(mx.getSid()).orElse(null));
            });
        }
        return wlwpDao.save(eXtWlwpVo.geteXtWlwp());
    }

    @Override
    public void update(EXtWlwpVo eXtWlwpVo) {
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtWlwpVo.geteXtWlwp().setDataMan(sessionUser.getUserId());
        if(!eXtWlwpVo.getAddedList().isEmpty()){
            eXtWlwpVo.getAddedList().forEach(mx->{
                mx.setNote(eXtWlwpVo.geteXtWlwp().getNote());
                String sidString = wlwpMxDao.getSingleResult("select max(sid) from e_xt_wlwp_mx");
                int num = (sidString==""||sidString==null?0:Integer.parseInt(sidString))+1;
                mx.setSid(Integer.toString(num));
                wlwpMxDao.save(mx);
            });
        }
        if(!eXtWlwpVo.getRemovedList().isEmpty()){
            eXtWlwpVo.getRemovedList().forEach(mx->{
                wlwpMxDao.deleteAll(eXtWlwpVo.getRemovedList());
            });
        }
        if(!eXtWlwpVo.getModifyedList().isEmpty()){
            eXtWlwpVo.getModifyedList().forEach(mx->{
                wlwpMxDao.dynamicSave(mx,wlwpMxDao.findById(mx.getSid()).orElse(null));
            });
        }
        wlwpDao.dynamicSave(eXtWlwpVo.geteXtWlwp(),wlwpDao.findById(eXtWlwpVo.geteXtWlwp().getNote()).orElse(null));
    }

    @Override
    public Map<String,Object> findByNote(String note) {
        return wlwpDao.QueryToFirstMap("select note,rc_rq,wp_origin,wp_type,jb_dept,jb_man,rc_sm," +
                "rc_flag,data_corp,data_man,f_getname('USERNAMERWTOCOL',jb_man,'','')manName,f_getname('GETDEPTNAME',jb_dept,'','')deptName," +
                "f_getname('GETCORPEXP','','',data_corp)corpExp from e_xt_wlwp where note =:1",note);
    }

    @Override
    public void deleteByNote(String note) {
        wlwpDao.deleteByNote(note);
        wlwpMxDao.deleteByNote(note);
    }

    @Override
    public Result getFlag(String note, String flag) {
        String flagString = wlwpDao.getFlag(note);
        if(flag.equals(flagString)){
            return Result.resultOk("操作成功");
        }
        return Result.error("该单不是“"+flag+"”状态,不能操作！");
    }

    @Override
    public Result confirm(String note) {
        wlwpDao.updateFlag(note,"确认");
        return Result.resultOk("确认");
    }

    @Override
    public Result cancle(String note) {
        wlwpDao.updateFlag(note,"登记");
        return Result.resultOk("取消确认");
    }

    @Override
    public BasePage<Map<String, Object>> getWlwpMx(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String where = " and a.note ='" + map.get("note") + "'";
        String sql = "select a.sid,a.note,a.wp_name,a.wp_sl,a.wp_unit,a.wp_sm,a.is_out,to_char(a.cc_rq,'yyyy-MM-dd')cc_rq,a.cc_man," +
                "f_getname('USERNAMERWTOCOL',a.cc_man,'','')cc_man_name from e_xt_wlwp_mx a,e_xt_wlwp b where a.note = b.note  " + where;
        return wlwpMxDao.QueryMapPageList(baseDto, sql, true);
    }
}
