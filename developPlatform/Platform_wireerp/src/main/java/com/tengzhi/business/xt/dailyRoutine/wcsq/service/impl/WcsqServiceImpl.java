package com.tengzhi.business.xt.dailyRoutine.wcsq.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.dailyRoutine.wcsq.dao.WcsqDao;
import com.tengzhi.business.xt.dailyRoutine.wcsq.dao.WcsqZcDao;
import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsq;
import com.tengzhi.business.xt.dailyRoutine.wcsq.service.WcsqService;
import com.tengzhi.business.xt.dailyRoutine.wcsq.vo.EXtWcsqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class WcsqServiceImpl implements WcsqService {

    @Autowired
    private WcsqDao wcsqDao;

    @Autowired
    private WcsqZcDao wcsqZcDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();

        String where = SqlJoint.whereSuffixWhere(c->{
            c.ge("wc_rq",map.get("srchRq1"));
            c.le("wc_rq",map.get("srchRq2"));
            c.eq("wc_dept",map.get("srchDept"));
            c.eq("wc_man",map.get("srchMan"));
        });
        String sqlString = "select note,to_char(wc_rq,'yyyy-MM-dd')wc_rq,wc_dept,f_getname('GETDEPTNAME',wc_dept,'',data_corp)dept_name,wc_man,f_getname('GETUSERNAME',wc_man,'',data_corp)man_name,data_man," +
                "f_getname('USERNAMERWTOCOL',data_man,'','')data_man_name,wc_type,replace(wc_add,'其他',wc_addsm )wc_add,wc_addsm,wc_iszc,wc_yc,wc_ycsm,wc_sm," +
                "to_char(wc_tcrq,'yyyy-MM-dd hh24:mi')wc_tcrq,to_char(wc_thrq,'yyyy-MM-dd hh24:mi')wc_thrq,wc_lc,wc_fy,wc_fysm,wc_gzap,wc_zcsm,wc_flag,data_corp," +
                "f_getname('GETCORPEXP','','',data_corp)corpexp,wc_mcck,to_char(wc_mcrq,'yyyy-MM-dd hh24:mi')wc_mcrq,wc_mhck,to_char(wc_mhrq,'yyyy-MM-dd hh24:mi')wc_mhrq," +
                "wc_pcnote,wc_bznote,wc_sc from e_xt_wcsq  "+where;

        return wcsqDao.QueryMapPageList(baseDto,sqlString,true);
    }

    @Override
    public EXtWcsq save(EXtWcsqVo eXtWcsqVo) throws Exception {
        SessionUser sessionUser = SessionUser.SessionUser();
        String note = sysGenNoteService.getNote(EXtWcsq.class,"RYWC","yyMM",4);
        //sysGenNoteService.getyyMMNote4(EXtWcsq.class,"RYWC");
        eXtWcsqVo.geteXtWcsq().setDataMan(sessionUser.getUserId());
        eXtWcsqVo.geteXtWcsq().setWcPcnote("N");
        eXtWcsqVo.geteXtWcsq().setWcBznote("N");
        eXtWcsqVo.geteXtWcsq().setWcFlag("登记");
        eXtWcsqVo.geteXtWcsq().setNote(note);
        if(!eXtWcsqVo.getAddedList().isEmpty()){
            eXtWcsqVo.getAddedList().forEach(zc ->{
                zc.setNote(eXtWcsqVo.geteXtWcsq().getNote());
                zc.setDataCorp(sessionUser.getCorpId());
                String sidString = wcsqZcDao.getSingleResult("select max(sid) from e_xt_wcsq_zc");
                int num = (sidString==""||sidString ==null?0:Integer.parseInt(sidString))+1;
                //int num = Integer.parseInt(wcsqZcDao.getSingleResult("select max(sid) from e_xt_wcsq_zc"));
                zc.setSid(Integer.toString(num));
                wcsqZcDao.save(zc);
            });
        }
        if(!eXtWcsqVo.getDeletedList().isEmpty()){
            wcsqZcDao.deleteAll(eXtWcsqVo.getDeletedList());
        }
        if(!eXtWcsqVo.getModifyedList().isEmpty()){
            eXtWcsqVo.getModifyedList().forEach(zc->{
                wcsqZcDao.dynamicSave(zc,wcsqZcDao.findById(zc.getSid()).orElse(null));
            });
        }
        EXtWcsq eXtWcsq = wcsqDao.save(eXtWcsqVo.geteXtWcsq());
        return eXtWcsq;
    }


    @Override
    public EXtWcsq findByNote(String note) {
        return wcsqDao.QueryListModel(EXtWcsq.class,"select a.*,f_getname('GETDEPTNAME',a.wc_dept,'',a.data_corp)dept_name,f_getname('GETUSERNAME',a.wc_man,'',a.data_corp)man_name from e_xt_wcsq a where note= :1",note).get(0);
    }

    @Override
    public void update(EXtWcsqVo eXtWcsqVo) {
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtWcsqVo.geteXtWcsq().setDataMan(sessionUser.getUserId());
        eXtWcsqVo.geteXtWcsq().setWcPcnote("N");
        eXtWcsqVo.geteXtWcsq().setWcBznote("N");
        if(!eXtWcsqVo.getDeletedList().isEmpty()){
            wcsqZcDao.deleteAll(eXtWcsqVo.getDeletedList());
        }
        if(!eXtWcsqVo.getAddedList().isEmpty()){
            eXtWcsqVo.getAddedList().forEach(zc ->{
                zc.setNote(eXtWcsqVo.geteXtWcsq().getNote());
                zc.setDataCorp(sessionUser.getCorpId());
                int num = Integer.parseInt(wcsqZcDao.getSingleResult("select max(sid) from e_xt_wcsq_zc"))+1;
                zc.setSid(Integer.toString(num));
                wcsqZcDao.save(zc);
            });
        }
        if(!eXtWcsqVo.getModifyedList().isEmpty()){
            eXtWcsqVo.getModifyedList().forEach(zc->{
                wcsqZcDao.dynamicSave(zc,wcsqZcDao.findById(zc.getSid()).orElse(null));
            });
        }
        wcsqDao.dynamicSave(eXtWcsqVo.geteXtWcsq(),wcsqDao.findById(eXtWcsqVo.geteXtWcsq().getNote()).orElse(null));
    }

    @Override
    public void deleteByNote(String note) {
        wcsqDao.deleteByNote(note);
        wcsqZcDao.deleteByNote(note);
    }

    @Override
    public Result getFlag(String note, String flag) {
        String flagString = wcsqDao.getFlag(note);
        if(flag.equals(flagString)){
            return Result.resultOk("操作成功");
        }
        return Result.error("该单不是“" + flag + "”状态,不能操作！");
    }

    @Override
    public Result confirm(String note) {
        wcsqDao.updateFlag(note,"确认");
        return Result.resultOkMsg("确认");
    }

    @Override
    public Result cancle(String note) {
        wcsqDao.updateFlag(note,"登记");
        return Result.resultOkMsg("取消");
    }

    @Override
    public BasePage<Map<String, Object>> getZcmxBynote(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = "and a.note='"+map.get("note")+"'";
        String sql = "select a.sid,a.note,to_char(a.rq1,'yyyy-MM-dd')rq1,to_char(a.rq2,'yyyy-MM-dd')rq2,a.qzdd,a.zcfy,a.data_corp from e_xt_wcsq_zc a,e_xt_wcsq b where a.note=b.note "+ where ;
        return wcsqZcDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public BasePage<Map<String, Object>> getConfirmList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();

        String where = SqlJoint.whereSuffixAnd(c->{
            c.ge("wc_rq",map.get("srchRq1"));
            c.le("wc_rq",map.get("srchRq2"));
            c.eq("wc_dept",map.get("srchDept"));
            c.eq("wc_man",map.get("srchMan"));
        });
        String sqlString = "select note,to_char(wc_rq,'yyyy-MM-dd')wc_rq,wc_dept,f_getname('GETDEPTNAME',wc_dept,'',data_corp)dept_name,wc_man,f_getname('GETUSERNAME',wc_man,'',data_corp)man_name,data_man," +
                "wc_type,replace(wc_add,'其他',wc_addsm )wc_add,wc_addsm,wc_iszc,wc_yc,wc_ycsm,wc_sm," +
                "to_char(wc_tcrq,'yyyy-MM-dd hh24:mi')wc_tcrq,to_char(wc_thrq,'yyyy-MM-dd hh24:mi')wc_thrq,wc_lc,wc_fy,wc_fysm,wc_gzap,wc_zcsm,wc_flag,data_corp," +
                "f_getname('GETCORPEXP','','',data_corp)corpexp,wc_mcck,to_char(wc_mcrq,'yyyy-MM-dd hh24:mi')wc_mcrq,wc_mhck,to_char(wc_mhrq,'yyyy-MM-dd hh24:mi')wc_mhrq," +
                "wc_pcnote,wc_bznote,wc_sc from e_xt_wcsq where wc_flag in('确认','出厂') "+where;

        return wcsqDao.QueryMapPageList(baseDto,sqlString,true);
    }

    @Override
    public Result out(String note) {
        SessionUser sessionUser = SessionUser.SessionUser();
        String user = sessionUser.getUserId();
        String yc = wcsqDao.getSingleResult("select wc_yc from e_xt_wcsq where note = '"+note+"'");
        if(yc!="否"){
            wcsqDao.executeUpdate("update EXtClwc set wc_flag='车出',wc_sjcc='"+new Date()+"' where wcsq_note='"+note+"'");
        }
        wcsqDao.executeUpdate("update EXtWcsq set wc_flag='出厂',wc_mcck='"+user+"', wc_mcrq='"+new Date()+"' where note='"+note+"'");
        return Result.resultOkMsg("确认出厂");
    }

    @Override
    public Result back(String note) {
        SessionUser sessionUser = SessionUser.SessionUser();
        String user = sessionUser.getUserId();
        String yc = wcsqDao.getSingleResult("select wc_yc from e_xt_wcsq where note = '"+note+"'");
        if(yc!="否"){
            wcsqDao.executeUpdate("update EXtClwc set wc_flag='车回',wc_sjhc='"+new Date()+"' where wcsq_note='"+note+"'");
        }
        wcsqDao.executeUpdate("update EXtWcsq set wc_flag='回厂',wc_mhck='"+user+"', wc_mhrq='"+new Date()+"' where note='"+note+"'");
        return Result.resultOkMsg("确认回厂");
    }

    @Override
    public EXtWcsq save(EXtWcsq eXtWcsq) throws IOException {
        String note = sysGenNoteService.getNote(EXtWcsq.class,"RYWC","yyMM",4);
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtWcsq.setDataMan(sessionUser.getUserId());
        eXtWcsq.setNote(note);
        eXtWcsq.setWcBznote("N");
        eXtWcsq.setWcPcnote("N");
        eXtWcsq.setWcFlag("登记");
        return wcsqDao.save(eXtWcsq);
    }

    @Override
    public void update(EXtWcsq eXtWcsq) {
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtWcsq.setDataMan(sessionUser.getUserId());
        wcsqDao.update(eXtWcsq);
    }


}
