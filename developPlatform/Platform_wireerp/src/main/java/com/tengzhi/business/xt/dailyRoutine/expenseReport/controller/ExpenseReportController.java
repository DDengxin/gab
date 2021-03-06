package com.tengzhi.business.xt.dailyRoutine.expenseReport.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.service.ExpenseReportService;
import com.tengzhi.business.xt.dailyRoutine.expenseReport.vo.ExpenseReportVo;
import com.tengzhi.business.xt.notice.model.EXtNotice;
import com.tengzhi.business.xt.notice.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/xt/dailyRoutine/expenseReport")
public class ExpenseReportController {


    @Autowired
    private ExpenseReportService expenseReportService;
    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
    /**
     * 区分模块的参数入口路径
     *
     * @param paramMod
     * @param paramType
     * @param request
     * @param mv
     * @return
     */
    @GetMapping(value = "/{paramMod}/list.html")
    public ModelAndView toPage(@PathVariable String paramMod, @RequestParam(required = false) String paramType, HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        mv.setView(new RedirectView(String.format("/xt/dailyRoutine/expenseReport/%s", servletPath)));
        request.getParameterMap().forEach((key, value) -> {
            mv.addObject(key, value);
        });
        mv.addObject("paramMod", paramMod);
        mv.addObject("paramType", paramType);
        return mv;
    }

    /**
     * 	查询数据列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "list.html")
    public Result getReportList(BaseDto baseDto){
        return expenseReportService.getReportList(baseDto).getResult();
    }

    @PostMapping(value = "getCostList")
    public Result getCostList(BaseDto baseDto){
        return expenseReportService.getCostList(baseDto).getResult();
    }

    @PostMapping(value = "getPaymentList")
    public Result getPaymentList(BaseDto baseDto){
        return expenseReportService.getPaymentList(baseDto).getResult();
    }

    @PostMapping(value = "add.html")
    public Result add(@RequestBody ExpenseReportVo vo) throws Exception {
        expenseReportService.saveData(ExpenseReportVo.initExpenseReportVo(vo));
        return Result.resultOk( );
    }

    @PutMapping(value = "add.html")
    public Result modify(@RequestBody ExpenseReportVo vo) throws Exception {
        expenseReportService.saveData(ExpenseReportVo.initExpenseReportVo(vo));
        return Result.resultOkMsg("修改成功");
    }

    @GetMapping(value = "getDataByNote/{bzNote}")
    public Result getById(@PathVariable(value = "bzNote") String bzNote) {
        return Result.resultOk(expenseReportService.getDataByNote(bzNote));
    }
    @PutMapping(value = "getFlag/{bzNote}/{bzFlag}")
    public Result getFlag(@PathVariable(value = "bzNote") String bzNote,@PathVariable(value = "bzFlag") String bzFlag) {
        return Result.resultOk(expenseReportService.getFlag(bzNote,bzFlag));
    }
    @PutMapping(value = "confirm/{bzNote}")
    public Result confirm(@PathVariable(value = "bzNote") String bzNote) {
        return Result.resultOk(expenseReportService.confirm(bzNote));
    }
    @PutMapping(value = "cancel/{bzNote}")
    public Result cancel(@PathVariable(value = "bzNote") String bzNote) {
        return Result.resultOk(expenseReportService.cancel(bzNote));
    }

    @DeleteMapping(value = "delete/{bzNote}")
    public Result delete(@PathVariable(value = "bzNote") String bzNote){
        expenseReportService.deleteByNote(bzNote);
        return Result.resultOkMsg("删除成功");
    }

}
