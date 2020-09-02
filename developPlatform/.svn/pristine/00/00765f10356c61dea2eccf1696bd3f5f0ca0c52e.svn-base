package com.tengzhi.business.xt.dailyRoutine.travelReport.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.dailyRoutine.travelReport.service.TravelReportService;
import com.tengzhi.business.xt.dailyRoutine.travelReport.vo.EXtTravelReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Author: czf
 * @Date:2020-08-21 11:15
 */
@RestController
@RequestMapping("/xt/dailyRoutine/travelReport")
public class TravelReportController {

    @Autowired
    private TravelReportService reportService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getList")
    public Result getList(BaseDto baseDto)throws IOException{
        return reportService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtTravelReportVo
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EXtTravelReportVo eXtTravelReportVo)throws Exception{
        return reportService.save(eXtTravelReportVo);
    }

    /**
     * 编辑
     * @param eXtTravelReportVo
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtTravelReportVo eXtTravelReportVo){
        return reportService.update(eXtTravelReportVo);
    }

    /**
     * 获取数据/form
     * @param bzNote
     * @return
     */
    @GetMapping("getByNote/{bzNote}")
    public Result getByNote(@PathVariable String bzNote){
        return Result.resultOk(reportService.findByNote(bzNote));
    }

    /**
     * 删除
     * @param bzNote
     * @return
     */
    @DeleteMapping("delete/{bzNote}")
    public Result delete(@PathVariable String bzNote){
        reportService.delete(bzNote);
        return Result.resultOkMsg("删除成功");
    }

    @PutMapping("getFlag/{bzNote}/{bzFlag}")
    public Result getFlag(@PathVariable String bzNote,@PathVariable String bzFlag){
        return reportService.getFlag(bzNote,bzFlag);
    }

    /**
     * 确认数据
     * @param bzNote
     * @return
     */
    @PutMapping("confirm/{bzNote}")
    public Result confirm(@PathVariable String bzNote){
        return reportService.confirm(bzNote);
    }

    /**
     * 取消确认
     * @param bzNote
     * @return
     */
    @PutMapping("cancle/{bzNote}")
    public Result cancle(@PathVariable String bzNote){
        return reportService. cancle(bzNote);
    }

    /**
     * 查询报支数据/grid
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getReportList")
    public Result getReportList(BaseDto baseDto)throws IOException{
        return reportService.getReportList(baseDto).getResult();
    }

    /**
     * 获取报支人外出记录
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getWcsqList")
    public Result getWcsqList(BaseDto baseDto)throws IOException{
        return reportService.getWcsqList(baseDto).getResult();
    }

    /**
     * 获取车辆外出数据
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getClwcList")
    public Result getClwcList(BaseDto baseDto)throws IOException{
        return reportService.getClwcList(baseDto).getResult();
    }

    /**
     * 判断选中数据是人员外出/车辆外出
     * @param bzNote
     * @return
     */
    @PutMapping("checkBzNote/{bzNote}")
    public Result checkBzNote(@PathVariable String bzNote){
        return Result.resultOk(reportService.checkBzNote(bzNote));
    }
}
