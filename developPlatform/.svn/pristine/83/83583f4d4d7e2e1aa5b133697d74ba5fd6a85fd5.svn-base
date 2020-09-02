package com.tengzhi.business.xt.dailyRoutine.expenseReport.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.model.EXtExpenseReportPayment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseReportPaymentDao extends BasedaoRepository<EXtExpenseReportPayment, String> {
    @Modifying
    @Query(value = "delete  from EXtExpenseReportPayment where bzNote= :bzNote")
    void deleteByNote(@Param(value = "bzNote") String bzNote);
}
