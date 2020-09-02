package com.tengzhi.business.xt.dailyRoutine.expenseReport.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.model.EXtExpenseReport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseReportDao extends BasedaoRepository<EXtExpenseReport, String> {

    @Query(value=" select  bz_flag from e_xt_expense_report where bz_note= :bzNote limit 1   ",nativeQuery = true)
    String getFlag(@Param("bzNote")String bzNote);

    @Modifying
    @Query(value =" update e_xt_expense_report set bz_flag=:bzFlag  where bz_note = :bzNote ",nativeQuery = true)
    void updateFlag(@Param(value = "bzNote") String bzNote,@Param(value = "bzFlag") String bzFlag);

    @Modifying
    @Query(value =" delete from e_xt_expense_report where bz_note = :bzNote ",nativeQuery = true)
    void deleteByzNoteo(@Param(value = "bzNote") String bzNote);
}
