package com.tengzhi.business.xt.dailyRoutine.expenseReport.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.dao.ExpenseReportDao;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.dao.ExpenseReportPaymentDao;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.model.EXtExpenseReport;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.model.EXtExpenseReportPayment;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.service.ExpenseReportService;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.vo.ExpenseReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class ExpenseReportServiceImpl implements ExpenseReportService {

    @Autowired
    private ExpenseReportDao expenseReportDao;

    @Autowired
    private ExpenseReportPaymentDao expenseReportPaymentDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public BasePage<Map<String, Object>> getReportList(BaseDto baseDto) {
        Map<String,String > map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(e->{
            e.between("bz_rq",map.get("srchRq1"),map.get("srchRq2"));
            e.eq("bz_note",map.get("bzNote"));

        });
        String sql = " select bz_note,bz_mo,to_char(bz_rq,'yyyy-mm-dd')bz_rq,bz_method,bz_man,bz_dept,f_getname('GETUSERNAME',bz_man,'',data_corp)man_name,f_getname('GETDWNAME',bz_dept,'',data_corp)dept_name,bz_currency,f_get_param_name('交易币种',bz_currency,data_corp) bz_currency_name,f_get_param_name('财务参数',bz_method,data_corp) bz_method_name,f_get_param_name('发票类型',bz_invoice_type,data_corp) bz_invoice_type_name," +
                " bz_invoice_type,bz_invoice_number,bz_invoice_amount,bz_invoice_money,bz_money,bz_remarks,bz_information from e_xt_expense_report "+where+" group by bz_note,bz_rq,bz_method,bz_man,bz_dept,bz_currency,bz_mo," +
                " bz_invoice_type,bz_invoice_number,bz_invoice_amount,bz_invoice_money,bz_money,bz_remarks,bz_information,data_corp ";
        return expenseReportDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public BasePage<Map<String, Object>> getCostList(BaseDto baseDto) {
        Map<String,String > map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(e->{
            e.eq("bz_note",map.get("bzNote"));
        });
        String sql =" select bz_note,bz_mo,bz_cost_corp,bz_cost_dept,bz_cost_item_type,bz_cost_type_value,f_getname('GETCORPEXP','','',bz_cost_corp)cost_corp_name,bz_cost_type,bz_cost_money,bz_cost_remarks from e_xt_expense_report "+where;
        return expenseReportDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public BasePage<Map<String, Object>> getPaymentList(BaseDto baseDto) {
        Map<String,String > map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(e->{
            e.eq("bz_note",map.get("bzNote"),true);
        });
        String sql =" select bz_mo,bz_note,to_char(bz_plan_rq,'yyyy-mm-dd')plan_rq_str,bz_plan_money,bz_plan_flag from e_xt_expense_report_payment "+where;
        return expenseReportDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public Result saveData(ExpenseReportVo vo) {

        String note =  sysGenNoteService.getNote(EXtExpenseReport.class,"BZ","yyMM",4);
        SessionUser securityUser = SessionUser.SessionUser();
        vo.getReport().setBzNote(note);
        vo.getReport().setDataMan(securityUser.getUserId());
        vo.getReport().setDataDate(new Date());
        vo.getReport().setDataCorp(securityUser.getCorpId());
        Integer sid = 1;
        if (!vo.getAddedCostList().isEmpty()) {
            for(EXtExpenseReport item :vo.getAddedCostList()){
                item.setBzNote(vo.getReport().getBzNote());
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                item.setBzMo(vo.getReport().getBzNote()+"-"+sid);
                sid++;
                expenseReportDao.save(item);
            };
        }
        if (!vo.getModifyedCostList().isEmpty()) {
            vo.getModifyedCostList().forEach(item -> {
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                expenseReportDao.dynamicSave(item,expenseReportDao.findById(item.getBzMo()).orElse(null));
            });
        }
        if (!vo.getDeletedCostList().isEmpty()) {
            expenseReportDao.deleteAll(vo.getDeletedCostList());
        }

        //计划付款表
        Integer sidPayment = 1;
        if (!vo.getAddedList().isEmpty()) {
            for(EXtExpenseReportPayment item: vo.getAddedList()){
                item.setBzNote(vo.getReport().getBzNote());
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                item.setBzMo(vo.getReport().getBzNote()+"-"+sidPayment);
                sidPayment++;
                expenseReportPaymentDao.save(item);
            };
        }
        if (!vo.getModifyedList().isEmpty()) {
            vo.getModifyedList().forEach(item -> {
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                expenseReportPaymentDao.dynamicSave(item,expenseReportPaymentDao.findById(item.getBzMo()).orElse(null));
            });
        }
        if (!vo.getDeletedList().isEmpty()) {
            expenseReportPaymentDao.deleteAll(vo.getDeletedList());
        }
        return Result.resultOk();
    }

    @Override
    public Map<String, Object> getDataByNote(String note) {
        //return expenseReportDao.QueryListModel(EXtExpenseReport.class, "select a.*,f_getname('GETDWEXP',a.bz_unit,'',a.data_corp)bz_unit_name,f_getname('GETUSERNAME',a.bz_man,'',a.data_corp)bz_man_name,f_getname('GETDWNAME',a.bz_dept,'',data_corp)deptname from e_xt_expense_report a where bz_note= :1 ", note).get(0);
        return expenseReportDao.QueryToMap( "select a.*,f_getname('GETDWEXP',a.bz_unit,'',a.data_corp)bz_unit_name,f_getname('GETUSERNAME',a.bz_man,'',a.data_corp)bz_man_name,f_getname('GETDWNAME',a.bz_dept,'',data_corp)deptname from e_xt_expense_report a where bz_note= :1 ", note).get(0);

    }

    @Override
    public Result updateData(ExpenseReportVo vo) {
        SessionUser securityUser = SessionUser.SessionUser();
        vo.getReport().setDataMan(securityUser.getUserId());
        vo.getReport().setDataDate(new Date());
        vo.getReport().setDataCorp(securityUser.getCorpId());
        Integer sid = Integer.parseInt(expenseReportDao.getSingleResult("select max(to_number(substr(bz_mo,length(bz_note)+2,2),'999')) from e_xt_expense_report where bz_note=1? ",vo.getReport().getBzNote()));
        if (!vo.getAddedCostList().isEmpty()) {
            for(EXtExpenseReport item :vo.getAddedCostList()){
                item.setBzNote(vo.getReport().getBzNote());
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                item.setBzMo(vo.getReport().getBzNote()+"-"+sid);
                sid++;
                expenseReportDao.save(item);
            };
        }
        if (!vo.getModifyedCostList().isEmpty()) {
            vo.getModifyedCostList().forEach(item -> {
                item.setBzNote(vo.getReport().getBzNote());
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                expenseReportDao.dynamicSave(item,expenseReportDao.findById(item.getBzMo()).orElse(null));
            });
        }
        if (!vo.getDeletedCostList().isEmpty()) {
            expenseReportDao.deleteAll(vo.getDeletedCostList());
        }

        //计划付款表
        Integer sidPayment = Integer.parseInt(expenseReportDao.getSingleResult("select max(to_number(substr(bz_mo,length(bz_note)+2,2),'999')) from e_xt_expense_report_payment where bz_note=1? ",vo.getReport().getBzNote()));
        if (!vo.getAddedList().isEmpty()) {
            for(EXtExpenseReportPayment item: vo.getAddedList()){
                item.setBzNote(vo.getReport().getBzNote());
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                item.setBzMo(vo.getReport().getBzNote()+"-"+sidPayment);
                sidPayment++;
                expenseReportPaymentDao.save(item);
            };
        }
        if (!vo.getModifyedList().isEmpty()) {
            vo.getModifyedList().forEach(item -> {
                item.setBzNote(vo.getReport().getBzNote());
                item.setDataCorp(vo.getReport().getDataCorp());
                item.setDataDate(vo.getReport().getDataDate());
                item.setDataMan(vo.getReport().getDataMan());
                expenseReportPaymentDao.dynamicSave(item,expenseReportPaymentDao.findById(item.getBzMo()).orElse(null));
            });
        }
        if (!vo.getDeletedList().isEmpty()) {
            expenseReportPaymentDao.deleteAll(vo.getDeletedList());
        }
        return Result.resultOk();
    }

    @Override
    public Result getFlag(String bzNote, String bzFlag) {
        String flagString = expenseReportDao.getFlag(bzNote);
        if (bzFlag.equals(flagString)) {
            return Result.resultOk("操作成功！");
        }
        return Result.error("该单不是“" + bzFlag + "”状态,不能操作！");
    }

    @Override
    public Result confirm(String bzNote) {
        expenseReportDao.updateFlag(bzNote, "核准");
        return Result.resultOkMsg("确认");
    }

    @Override
    public Result cancel(String bzNote) {
        expenseReportDao.updateFlag(bzNote, "登记");
        return Result.resultOkMsg("取消");
    }

    @Override
    public void deleteByNote(String bzNote) {
        expenseReportDao.deleteByzNoteo(bzNote);
        expenseReportPaymentDao.deleteByNote(bzNote);
    }

}
