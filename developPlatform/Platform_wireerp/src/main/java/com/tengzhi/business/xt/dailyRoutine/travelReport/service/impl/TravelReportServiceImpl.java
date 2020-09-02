package com.tengzhi.business.xt.dailyRoutine.travelReport.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.dailyRoutine.travelReport.dao.TravelReportDao;
import com.tengzhi.business.xt.dailyRoutine.travelReport.model.EXtTravelReport;
import com.tengzhi.business.xt.dailyRoutine.travelReport.service.TravelReportService;
import com.tengzhi.business.xt.dailyRoutine.travelReport.vo.EXtTravelReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-21 11:13
 */
@Service
@Transactional
public class TravelReportServiceImpl  implements TravelReportService {

    @Autowired
    private TravelReportDao reportDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.ge("bz_rq",map.get("srchRq1"));
            c.le("bz_rq",map.get("srchRq2"));
            c.eq("bz_corp",map.get("srchCorp"));
            c.eq("bz_dept",map.get("srchDept"));
            c.eq("bz_man",map.get("srchMan"));
            c.eq("bz_flag",map.get("srchFlag"));
        }) ;
        String sql = "select bz_note,wc_note,to_char(bz_rq,'yyyy-MM-dd')bz_rq,bz_man,bz_dept,bz_post,bz_cost_type,bz_pay_method,bz_cost_borrow,wc_start_rq,wc_end_rq,wc_days," +
                "bz_cost_traffic9,bz_invoice_traffic9,bz_cost_traffic3,bz_invoice_traffic3,bz_cost_traffic,bz_sub_meals,bz_cost_meals,bz_invoice_meals," +
                "bz_cost_hotel6,bz_invoice_hotel6,bz_cost_hotel3,bz_invoice_hotel3,bz_cost_hotel1,bz_invoice_hotel1,bz_sub_hotel,bz_cost_hotel,bz_invoice_hotel," +
                "bz_cost_total,bz_kj,bz_digested,bz_bank,bz_card,bz_flag,bz_corp,data_man,data_date,data_corp,f_getname('GETDEPTNAME',bz_dept,'','')bz_dept_name," +
                "f_getname('GETUSERNAME',bz_man,'',bz_corp)bz_man_name from e_xt_travel_report "+where;

        return reportDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public Result save(EXtTravelReportVo eXtTravelReportVo) throws Exception {
        List<EXtTravelReport> addModel = new ArrayList<EXtTravelReport>();
        List<EXtTravelReport> modifyedModel = new ArrayList<EXtTravelReport>();
        List<EXtTravelReport> deleteModel = new ArrayList<EXtTravelReport>();
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtTravelReportVo.getHeaddata().setBzFlag("登记");
        eXtTravelReportVo.getHeaddata().setDataCorp(sessionUser.getCorpId());
        eXtTravelReportVo.getHeaddata().setDataMan(sessionUser.getUserId());
        eXtTravelReportVo.getHeaddata().setDataDate(new Date());
        if(eXtTravelReportVo.getEnterydata().size()>0){
            eXtTravelReportVo.getEnterydata().forEach(item->{
                if("added".equals(item.get_state())){
                    String note = sysGenNoteService.getNote(EXtTravelReport.class,"FY","yyMM",3);
                    item.setBzNote(note);
                    item.setBzFlag(eXtTravelReportVo.getHeaddata().getBzFlag());
                    item.setDataCorp(eXtTravelReportVo.getHeaddata().getDataCorp());
                    item.setDataMan(eXtTravelReportVo.getHeaddata().getDataMan());
                    item.setDataDate(eXtTravelReportVo.getHeaddata().getDataDate());
                    item.setBzRq(eXtTravelReportVo.getHeaddata().getBzRq());
                    item.setBzMan(eXtTravelReportVo.getHeaddata().getBzMan());
                    item.setBzDept(eXtTravelReportVo.getHeaddata().getBzDept());
                    item.setBzPost(eXtTravelReportVo.getHeaddata().getBzPost());
                    item.setBzCostType(eXtTravelReportVo.getHeaddata().getBzCostType());
                    item.setBzCostBorrow(eXtTravelReportVo.getHeaddata().getBzCostBorrow());
                    item.setBzPayMethod(eXtTravelReportVo.getHeaddata().getBzPayMethod());
                    item.setBzBank(eXtTravelReportVo.getHeaddata().getBzBank());
                    item.setBzCard(eXtTravelReportVo.getHeaddata().getBzCard());
                    item.setWcNote(eXtTravelReportVo.getHeaddata().getWcNote());
                    item.setBzCorp(eXtTravelReportVo.getHeaddata().getBzCorp());
                    addModel.add(item);
                }else if("modified".equals(item.get_state())){
                    modifyedModel.add(item);
                }else if("removed".equals(item.get_state())){
                    deleteModel.add(item);
                }
            });
        }
        //保存到数据库
        if(addModel.size()>0){
            addModel.forEach(model->{
                reportDao.save(model);
                String[] note = model.getWcNote().split(",");
                for(int i=0;i<note.length;i++){
                     String wcnote = note[i];
                     int num = Integer.parseInt(reportDao.getSingleResult("select count(*) from e_xt_wcsq where note = '"+wcnote+"'"));
                    if(num>0){
                        reportDao.executeUpdateSql("update e_xt_wcsq set wc_bznote='"+model.getBzNote()+"' where note='"+wcnote+"'");
                    }else{
                        reportDao.executeUpdateSql("update e_xt_clwc set bz_note='"+model.getBzNote()+"' where note='"+wcnote+"'");
                    }
                }
            });
        }
        if(modifyedModel.size()>0){
            modifyedModel.forEach(model->{
                reportDao.dynamicSave(model,reportDao.findById(model.getBzNote()).orElse(null));
            });
        }
        if(deleteModel.size()>0){
            deleteModel.forEach(model->{
                reportDao.deleteById(model.getBzNote());
            });
        }

        return Result.resultOk("操作成功");
    }

    @Override
    public Result update(EXtTravelReportVo eXtTravelReportVo) {
        List<EXtTravelReport> addModel = new ArrayList<EXtTravelReport>();
        List<EXtTravelReport> modifyedModel = new ArrayList<EXtTravelReport>();
        List<EXtTravelReport> deleteModel = new ArrayList<EXtTravelReport>();
        SessionUser sessionUser = SessionUser.SessionUser();
        eXtTravelReportVo.getHeaddata().setDataCorp(sessionUser.getCorpId());
        eXtTravelReportVo.getHeaddata().setDataMan(sessionUser.getUserId());
        eXtTravelReportVo.getHeaddata().setDataDate(new Date());
        if(eXtTravelReportVo.getEnterydata().size()>0){
            eXtTravelReportVo.getEnterydata().forEach(item->{
                if("added".equals(item.get_state())){
                    item.setBzFlag(eXtTravelReportVo.getHeaddata().getBzFlag());
                    item.setDataCorp(eXtTravelReportVo.getHeaddata().getDataCorp());
                    item.setDataMan(eXtTravelReportVo.getHeaddata().getDataMan());
                    item.setDataDate(eXtTravelReportVo.getHeaddata().getDataDate());
                    item.setBzRq(eXtTravelReportVo.getHeaddata().getBzRq());
                    item.setBzMan(eXtTravelReportVo.getHeaddata().getBzMan());
                    item.setBzDept(eXtTravelReportVo.getHeaddata().getBzDept());
                    item.setBzPost(eXtTravelReportVo.getHeaddata().getBzPost());
                    item.setBzCostType(eXtTravelReportVo.getHeaddata().getBzCostType());
                    item.setBzCostBorrow(eXtTravelReportVo.getHeaddata().getBzCostBorrow());
                    item.setBzPayMethod(eXtTravelReportVo.getHeaddata().getBzPayMethod());
                    item.setBzBank(eXtTravelReportVo.getHeaddata().getBzBank());
                    item.setBzCard(eXtTravelReportVo.getHeaddata().getBzCard());
                    item.setWcNote(eXtTravelReportVo.getHeaddata().getWcNote());
                    item.setBzCorp(eXtTravelReportVo.getHeaddata().getBzCorp());
                    addModel.add(item);
                }else if("modified".equals(item.get_state())){
                    modifyedModel.add(item);
                }else if("removed".equals(item.get_state())){
                    deleteModel.add(item);
                }
            });
        }
        //保存到数据库
        if(addModel.size()>0){
            addModel.forEach(model->{
                reportDao.save(model);
            });
        }
        if(modifyedModel.size()>0){
            modifyedModel.forEach(model->{
                reportDao.dynamicSave(model,reportDao.findById(model.getBzNote()).orElse(null));
            });
        }
        if(deleteModel.size()>0){
            deleteModel.forEach(model->{
                reportDao.deleteById(model.getBzNote());
            });
        }

        return Result.resultOk("操作成功");
    }

    @Override
    public Map<String, Object> findByNote(String bzNote) {
        return reportDao.QueryToFirstMap("select bz_note,wc_note,bz_rq,bz_man,bz_dept,bz_post,bz_cost_type,bz_pay_method,bz_cost_borrow,bz_flag," +
                "bz_corp,data_man,to_char(data_date,'yyyy-MM-dd')data_date,data_corp,bz_bank,bz_card,f_getname('GETDEPTNAME',bz_dept,'','')bz_dept_name," +
                "f_getname('GETUSERNAME',bz_man,'',bz_corp)bz_man_name from e_xt_travel_report where bz_note=:1",bzNote);
    }

    @Override
    public void delete(String bzNote) {
        boolean bb = checkBzNote(bzNote);
        String wcnote = reportDao.getSingleResult("select wc_note from e_xt_travel_report where bz_note='"+bzNote+"'");
        String[] note = wcnote.split(",");
        for(int i=0;i<note.length;i++){
            String wcNote = note[i];
            if(bb){
                reportDao.executeUpdateSql("update e_xt_wcsq set wc_bznote='N' where note='"+wcNote+"'");
            }else{
                reportDao.executeUpdateSql("update e_xt_clwc set bz_note='N' where note='"+wcNote+"'");
            }
        }
        reportDao.deleteByNote(bzNote);
    }

    @Override
    public Result getFlag(String bzNote, String bzFlag) {
        String flagString = reportDao.getFlag(bzNote);
        if(bzFlag.equals(flagString)){
            return Result.resultOkMsg("操作成功");
        }
        return Result.error("该单不是“"+bzFlag+"”状态,不能操作");
    }

    @Override
    public Result confirm(String bzNote) {
        reportDao.updateFlag(bzNote,"确认");
        return Result.resultOkMsg("操作成功");
    }

    @Override
    public Result cancle(String bzNote) {
        reportDao.updateFlag(bzNote,"登记");
        return Result.resultOkMsg("操作成功");
    }

    @Override
    public BasePage<Map<String, Object>> getReportList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
           c.eq("bz_note",map.get("bzNote"));
        });
        String sql = "select bz_note,wc_note,to_char(bz_rq,'yyyy-MM-dd')bz_rq,bz_man,bz_dept,bz_post,bz_cost_type,bz_pay_method,bz_cost_borrow,to_char(wc_start_rq,'yyyy-MM-dd')wc_start_rq," +
                "to_char(wc_end_rq,'yyyy-MM-dd')wc_end_rq,wc_days,bz_cost_traffic9,bz_invoice_traffic9,bz_cost_traffic3,bz_invoice_traffic3,bz_cost_traffic,bz_invoice_traffic,bz_sub_meals,bz_cost_meals,bz_invoice_meals," +
                "bz_cost_hotel6,bz_invoice_hotel6,bz_cost_hotel3,bz_invoice_hotel3,bz_cost_hotel1,bz_invoice_hotel1,bz_sub_hotel,bz_cost_hotel,bz_invoice_hotel," +
                "bz_cost_total,bz_kj,bz_digested,bz_bank,bz_card,bz_flag,bz_corp,data_man,to_char(data_date,'yyyy-MM-dd')data_date,data_corp from e_xt_travel_report "+where;
        return reportDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public BasePage<Map<String, Object>> getWcsqList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where =" and wc_man='"+map.get("wcMan")+"'";
        String sql = "select note,to_char(wc_rq,'yyyy-MM-dd')wc_rq,wc_dept,wc_man,wc_type,replace(wc_add,'其他',wc_addsm )wc_add,wc_iszc,to_char(wc_tcrq,'yyyy-MM-dd hh24:mi')wc_tcrq," +
                "to_char(wc_thrq,'yyyy-MM-dd hh24:mi')wc_thrq,wc_flag,f_getname('GETDEPTNAME',wc_dept,'','')dept_name,wc_sm from e_xt_wcsq where wc_flag !='登记' and wc_bznote in('N','') "+where ;
        return reportDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public BasePage<Map<String, Object>> getClwcList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = " and wc_man='"+map.get("wcMan")+"'";
        String sql = "select note,to_char(wc_rq,'yyyy-MM-dd')wc_rq,wc_dept,f_getname('GETDEPTNAME',wc_dept,'','')dept_name,wc_man,wc_add,to_char(wc_sjcc,'yyyy-MM-dd hh24:mi')wc_sjcc," +
                "to_char(wc_sjhc,'yyyy-MM-dd hh24:mi')wc_sjhc,wc_flag,wc_sm,wc_yc from e_xt_clwc where wc_flag ='车回' and bz_note in ('N','') "+ where;

        return reportDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public boolean checkBzNote(String bzNote) {
        int num = Integer.parseInt(reportDao.getSingleResult("select count(*) from e_xt_wcsq where wc_bznote='"+bzNote+"'"));
        if(num>0){
            return true;
        }else{
            return false;
        }
    }
}
