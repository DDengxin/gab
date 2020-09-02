package com.tengzhi.business.production.subcontract.wwkc.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.ck.yw.kctj.service.WarehouseKcService;
import com.tengzhi.business.production.subcontract.wwkc.service.WwkcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@RestController
@RequestMapping("/production/subcontract/wwkc")
public class WwkcController {

    @Autowired
    private WwkcService service;

    String typeC="";

    @GetMapping("/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    @GetMapping("/table.html")
    public ModelAndView table(ModelAndView mv,@RequestParam(value="incontract",required = false)String incontract,@RequestParam(value="pack",required = false)String pack,@RequestParam(value="code",required = false)String code,@RequestParam(value="type",required = false)String type) {
        mv = service.table(incontract,pack,code,type,mv);
        return mv;
    }

    @PostMapping(value = "/exportExcel")
    public Result exportExcel(HttpServletResponse response, HttpServletRequest request,String parms) throws IOException {
        return service.exportExcel(response, request,typeC,parms);
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchMxList")
    public Result getSrchMxList(BaseDto baseDto) throws Exception {
        typeC="mx";
        return service.getSrchTopList("mx",baseDto).getResult();
    }

    @PostMapping(value = "/getSrchFlList")
    public Result getSrchFlList(BaseDto baseDto) throws Exception {
        typeC="fl";
        return service.getSrchTopList("fl",baseDto).getResult();
    }

    @PostMapping(value = "/getSrchKlList")
    public Result getSrchKlList(BaseDto baseDto) throws Exception {
        typeC="kl";
        return service.getSrchTopList("kl",baseDto).getResult();
    }

    /**
     * 获取二维码
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/getQr")
    public void getQr(@RequestParam(value = "content") String content,HttpServletResponse response) throws IOException{
        // 设置响应流信息
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream stream = response.getOutputStream();
        // 获取一个二维码图片
        QrCodeUtil.generate(content,1000,1000,"JPG",stream);
        //操作人：lgl
        //QR qr = new QR();
        //qr.getCode(content,1000,1000,response);
    }

    /**
     * 获取v_stock视图中的信息
     * @param baseDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getVStock")
    public Result getVStock(BaseDto baseDto) throws Exception {
        return service.getVStock(baseDto).getResult();
    }
    /**
     * 标签打印
     * @param packs
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getPrintPackList/{packs}")
    public Result getInList(@PathVariable(value = "packs") String packs) throws Exception {
        return service.getPrintPackList(packs);
    }

}