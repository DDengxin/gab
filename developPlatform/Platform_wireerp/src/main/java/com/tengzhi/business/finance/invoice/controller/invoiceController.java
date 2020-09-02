package com.tengzhi.business.finance.invoice.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.invoice.service.invoiceService;
import com.tengzhi.business.finance.invoice.vo.invoiceVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/finance/invoice/")
public class invoiceController {
    @Autowired
    private invoiceService invoiceService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "invoiceList.html")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
        return invoiceService.findAll(baseDto).getResult();
    }


    /**
     * 区分模块的参数入口路径
     *
     * @param fpCgtype
     * @param cwStype
	 * @param fpDtype
     * @param request
	 * @param rt
     * @param mv
     * @return
     */
    @GetMapping(value = {"{fpCgtype}/{cwStype}/{fpDtype}/invoiceList.html"})
    public ModelAndView toPage(@PathVariable String fpCgtype, @PathVariable String cwStype, @PathVariable String fpDtype,
                               HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        mv.setView(new RedirectView(String.format("/finance/invoice/%s", servletPath)));
        request.getParameterMap().forEach((key, value) -> {
            mv.addObject(key, value);
        });
        mv.addObject("fpCgtype", fpCgtype);
        mv.addObject("cwStype", cwStype);
        mv.addObject("fpDtype", fpDtype);
        //是否定向
        mv.addObject("orient", true);
        return mv;
    }


    /**
     * 查询新增数据列表
     *
     * @return
     */
    // @GetMapping("/staffRecordList. html")
    @PostMapping(value = "addinvoiceList")
    public Result getAddList(BaseDto baseDto) throws IOException, ParseException {
        return invoiceService.findAllByAdd(baseDto).getResult();
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody invoiceVo vo) throws Exception {
        //Result invoice = invoiceService.save(vo);
        /* return Result.resultOk(invoice); */
        return invoiceService.save(vo);
    }


    @PutMapping(value = "add.html")
    public Result modify(@RequestBody invoiceVo vo) throws Exception {
        invoiceService.update(vo);
        return Result.resultOkMsg("修改成功");
    }


    /**
     * 删除数据
     *
     * @return
     */
    @DeleteMapping(value = "invoiceList.html/{fpNote}")
    public Result delete(@PathVariable(value = "fpNote") String fpNote) {
        invoiceService.deleteByFpId(fpNote);
        return Result.resultOk();
    }

    /**
     * 确认操作修改状态
     *
     * @return
     */
    @PutMapping(value = "ok/{fpId}")
    public Result ok(@PathVariable(value = "fpId") String fpId) {
        invoiceService.ok(fpId);
        return Result.resultOk();
    }

    /**
     * 确认月结审核修改状态
     *
     * @return
     */
    @PutMapping(value = "yok/{fpId}")
    public Result yok(@PathVariable(value = "fpId") String fpId) {
        invoiceService.yok(fpId);
        return Result.resultOk();
    }

    /**
     * 取消操作修改状态
     *
     * @return
     */
    @PutMapping(value = "qx/{fpId}")
    public Result qx(@PathVariable(value = "fpId") String fpId) {
        invoiceService.qx(fpId);
        return Result.resultOk();
    }

    /**
     * 取消月结审核修改状态
     *
     * @return
     */
    @PutMapping(value = "yqx/{fpId}")
    public Result yqx(@PathVariable(value = "fpId") String fpId) {
        invoiceService.yqx(fpId);
        return Result.resultOk();
    }

    /**
     * 通过ID获取对象
     *
     * @param fpId
     * @return
     */

    @GetMapping(value = "invoiceList.html/{fpId}")
    public Result getById(@PathVariable String fpId) {
        return Result.resultOk(invoiceService.findByFpId(fpId));
    }

    /**
     * 修改
     *
     * @return
     */
    /*
     * @PutMapping("add.html") public Result modify(@RequestBody invoice invoice) {
     *
     * invoiceService.update(invoice); return Result.resultOk(invoice.getFpId()); }
     */

    /**
     * @Param: [vo]
     * @description:发票类型
     */
    @GetMapping("/getHtFplx")
    public List<SelectVo> getHtFplx(@RequestParam(value = "t", required = false) String trueText,
                                    @RequestParam(value = "f", required = false) String falseText) {
        return invoiceService.getHtFplx(trueText, falseText);
    }

    /**
     * @Param: [vo]
     * @description:发票税率
     */
    @GetMapping("/getHtFpsl")
    public List<SelectVo> getHtFpsl(@RequestParam(value = "t", required = false) String trueText,
                                    @RequestParam(value = "f", required = false) String falseText) {
        return invoiceService.getHtFpsl(trueText, falseText);
    }


    /**
     * @Param: [vo]
     * @description:原料物料
     */
    @GetMapping("getCplx")
    public List<SelectVo> getCplx(@RequestParam(value = "t", required = false) String trueText,
                                  @RequestParam(value = "f", required = false) String falseText) {
        return invoiceService.getCplx(trueText, falseText);
    }

    /**
     * 导出excel
     */
    @PostMapping(value = "exportExcel")
    public void xsExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
        invoiceService.exportExcel(response, request);
    }

}
