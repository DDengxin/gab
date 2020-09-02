package com.tengzhi.business.xt.dailyRoutine.workPlan.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.dailyRoutine.workPlan.model.EXtWorkplan;
import com.tengzhi.business.xt.dailyRoutine.workPlan.service.WorkplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Author: czf
 * @Date:2020-08-20 14:26
 */
@RestController
@RequestMapping("/xt/dailyRoutine/workplan")
public class WorkplanController {

    @Autowired
    private WorkplanService workplanService;

    @GetMapping("/*")
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
    public Result getList(BaseDto baseDto) throws IOException{
        return workplanService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtWorkplan
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EXtWorkplan eXtWorkplan) throws Exception{
        EXtWorkplan workplan = workplanService.save(eXtWorkplan);
        return Result.resultOk(workplan.getNote());
    }

    /**
     * 编辑
     * @param eXtWorkplan
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtWorkplan eXtWorkplan){
        workplanService.update(eXtWorkplan);
        return Result.resultOkMsg("修改成功");
    }

    /**
     * 获取一条数据
     * @param note
     * @return
     */
    @GetMapping("getByNote/{note}")
    public Result getByNote(@PathVariable String note){
        return Result.resultOk(workplanService.findByNote(note));
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping("delete/{note}")
    public  Result deleteByNote(@PathVariable String note){
        workplanService.deleteByNote(note);
        return Result.resultOkMsg("删除成功");
    }



}
