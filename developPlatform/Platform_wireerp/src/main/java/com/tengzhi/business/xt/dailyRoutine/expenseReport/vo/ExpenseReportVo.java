package com.tengzhi.business.xt.dailyRoutine.expenseReport.vo;

import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.model.EXtExpenseReport;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.model.EXtExpenseReportPayment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class ExpenseReportVo implements Serializable {

    EXtExpenseReport report;
    List<EXtExpenseReport> reports = new ArrayList<>(),
            addedCostList = new ArrayList<>(),
            modifyedCostList = new ArrayList<>(),
            deletedCostList = new ArrayList<>();
    List<EXtExpenseReportPayment> paymentList = new ArrayList<>(),
            addedList = new ArrayList<>(),
            modifyedList = new ArrayList<>(),
            deletedList = new ArrayList<>();
    public EXtExpenseReport getReport() {
        return report;
    }

    public void setReport(EXtExpenseReport report) {
        this.report = report;
    }

    public List<EXtExpenseReport> getReports() {
        return reports;
    }

    public void setReports(List<EXtExpenseReport> reports) {
        this.reports = reports;
    }

    public List<EXtExpenseReportPayment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<EXtExpenseReportPayment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<EXtExpenseReport> getAddedCostList() {
        return addedCostList;
    }

    public void setAddedCostList(List<EXtExpenseReport> addedCostList) {
        this.addedCostList = addedCostList;
    }

    public List<EXtExpenseReport> getModifyedCostList() {
        return modifyedCostList;
    }

    public void setModifyedCostList(List<EXtExpenseReport> modifyedCostList) {
        this.modifyedCostList = modifyedCostList;
    }

    public List<EXtExpenseReport> getDeletedCostList() {
        return deletedCostList;
    }

    public void setDeletedCostList(List<EXtExpenseReport> deletedCostList) {
        this.deletedCostList = deletedCostList;
    }

    public List<EXtExpenseReportPayment> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<EXtExpenseReportPayment> addedList) {
        this.addedList = addedList;
    }

    public List<EXtExpenseReportPayment> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<EXtExpenseReportPayment> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<EXtExpenseReportPayment> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<EXtExpenseReportPayment> deletedList) {
        this.deletedList = deletedList;
    }

    public static ExpenseReportVo initExpenseReportVo(ExpenseReportVo expenseReportVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(expenseReportVo.getReports().spliterator(),false).forEach(model -> {
            switch (model.get_state()) {
                case "added":
                    model.setBzNote(expenseReportVo.getReport().getBzNote());
                    model.setBzRq(expenseReportVo.getReport().getBzRq());
                    model.setBzDept(expenseReportVo.getReport().getBzDept());
                    model.setBzMan(expenseReportVo.getReport().getBzMan());
                    model.setBzCurrency(expenseReportVo.getReport().getBzCurrency());
                    model.setBzInvoiceAmount(expenseReportVo.getReport().getBzInvoiceAmount());
                    model.setBzInvoiceMoney(expenseReportVo.getReport().getBzInvoiceMoney());
                    model.setBzInvoiceNumber(expenseReportVo.getReport().getBzInvoiceNumber());
                    model.setBzInvoiceType(expenseReportVo.getReport().getBzInvoiceType());
                    model.setBzInformation(expenseReportVo.getReport().getBzInformation());
                    model.setBzMoney(expenseReportVo.getReport().getBzMoney());
                    model.setBzMethod(expenseReportVo.getReport().getBzMethod());
                    model.setBzUnit(expenseReportVo.getReport().getBzUnit());
                    model.setBzCorp(expenseReportVo.getReport().getBzCorp());
                    model.setBzRemarks(expenseReportVo.getReport().getBzRemarks());
                    expenseReportVo.getAddedCostList().add(model);
                    break;
                case "modified":
                    model.setBzNote(expenseReportVo.getReport().getBzNote());
                    model.setBzRq(expenseReportVo.getReport().getBzRq());
                    model.setBzDept(expenseReportVo.getReport().getBzDept());
                    model.setBzMan(expenseReportVo.getReport().getBzMan());
                    model.setBzCurrency(expenseReportVo.getReport().getBzCurrency());
                    model.setBzInvoiceAmount(expenseReportVo.getReport().getBzInvoiceAmount());
                    model.setBzInvoiceMoney(expenseReportVo.getReport().getBzInvoiceMoney());
                    model.setBzInvoiceNumber(expenseReportVo.getReport().getBzInvoiceNumber());
                    model.setBzInvoiceType(expenseReportVo.getReport().getBzInvoiceType());
                    model.setBzInformation(expenseReportVo.getReport().getBzInformation());
                    model.setBzMoney(expenseReportVo.getReport().getBzMoney());
                    model.setBzMethod(expenseReportVo.getReport().getBzMethod());
                    model.setBzUnit(expenseReportVo.getReport().getBzUnit());
                    model.setBzRemarks(expenseReportVo.getReport().getBzRemarks());
                    model.setBzCorp(expenseReportVo.getReport().getBzCorp());
                    expenseReportVo.getModifyedCostList().add(model);
                    break;
                case "removed":
                    expenseReportVo.getDeletedCostList().add(model);
                    break;
            }
        });
        StreamSupport.stream(expenseReportVo.getPaymentList().spliterator(),false).forEach(model -> {
            switch (model.get_state()) {
                case "added":
                    model.setBzNote(expenseReportVo.getReport().getBzNote());
                    expenseReportVo.getAddedList().add(model);
                    break;
                case "modified":
                    expenseReportVo.getModifyedList().add(model);
                    break;
                case "removed":
                    expenseReportVo.getDeletedList().add(model);
                    break;
            }
        });
        return expenseReportVo;
    }
}
