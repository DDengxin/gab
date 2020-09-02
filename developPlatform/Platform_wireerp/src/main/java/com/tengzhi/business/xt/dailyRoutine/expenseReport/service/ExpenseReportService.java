package com.tengzhi.business.xt.dailyRoutine.expenseReport.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.model.EXtExpenseReport;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.vo.ExpenseReportVo;

import java.util.Map;

public interface ExpenseReportService extends BaseService{

    /**
     * 费用报支主列表
     * @param baseDto
     * @return
     */
    BasePage<Map<String,Object>> getReportList(BaseDto baseDto);

    /**
     * 费用报支费用列表
     * @param baseDto
     * @return
     */
    BasePage<Map<String,Object>> getCostList(BaseDto baseDto);

    /**
     * 费用报支计划付款列表
     * @param baseDto
     * @return
     */
    BasePage<Map<String,Object>> getPaymentList(BaseDto baseDto);

    /**
     * 保存数据
     * @param vo
     * @return
     */
    Result saveData(ExpenseReportVo vo);

    /**
     * 根据单号获取数据
     * @param note
     * @return
     */
    Map<String,Object> getDataByNote(String note);

    /**
     * 更新数据
     * @param vo
     * @return
     */
    Result updateData(ExpenseReportVo vo);


    /**
     * 判读状态是否符合
     * @param bzNote
     * @param bzflag
     * @return
     */
    Result getFlag(String bzNote,String bzflag);

    /**
     * 确认
     * @param bzNote
     * @return
     */
    Result confirm(String bzNote);

    /**
     * 取消
     * @param bzNote
     * @return
     */
    Result cancel(String bzNote);

    void deleteByNote(String bzNote);
}
