package com.tengzhi.business.finance.reportItem.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.reportItem.service.ReportItemService;
import com.tengzhi.business.finance.reportItem.vo.ReportItemVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance/reportItem/")
public class ReportItemController {


    @Autowired
    private ReportItemService service;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "getList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return service.getList(baseDto).getResult();
    }

    /**
     * 获取报表列下拉选
     * @return
     */
    @RequestMapping("getSelectList.html")
    public List<Map> getSelectList(){
        return service.getList();
    }

    /***
     * 获取报表类型下拉选
     * @return
     */
    @RequestMapping("getReportList.html")
    public List<Map>  getReportList(){
        return service.getReportList();
    }

    /**
     * 查询已设置的公式
     * @param freportitem
     * @param freportid
     * @return
     */
    @GetMapping("getById/{freportitem}/{freportid}")
    public Result getById(@PathVariable(value = "freportitem") String freportitem,@PathVariable(value = "freportid") String freportid){
        return Result.resultOk(service.getById(freportitem,freportid));
    }

    /**
     * 保存设置的公式
     */
    @PostMapping("add.html")
    public Result saveData(@RequestBody ReportItemVo vo){
        return service.saveData(vo);
    }


}
