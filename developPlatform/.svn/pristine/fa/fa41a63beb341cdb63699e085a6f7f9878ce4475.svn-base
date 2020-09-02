package com.tengzhi.business.xt.dailyRoutine.sealApplication.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.dailyRoutine.sealApplication.model.EXtSealApplication;
import com.tengzhi.business.xt.dailyRoutine.sealApplication.service.SealApplicaitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Author: czf
 * @Date:2020-08-20 19:15
 */
@RestController
@RequestMapping("/xt/dailyRoutine/sealApplication")
public class SealApplicationController {

    @Autowired
    private SealApplicaitionService applicaitionService;

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
    public Result getList(BaseDto baseDto)throws IOException{
        return applicaitionService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param application
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EXtSealApplication application)throws Exception{
        EXtSealApplication eXtSealApplication = applicaitionService.save(application);
        return Result.resultOk(eXtSealApplication.getNote());
    }

    /**
     * 编辑
     * @param application
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtSealApplication application){
        applicaitionService.update(application);
        return Result.resultOkMsg("修改成功");
    }

    /**
     * 获取一条数据
     * @param note
     * @return
     */
    @GetMapping("getByNote/{note}")
    public Result getByNote(@PathVariable String note){
        return Result.resultOk(applicaitionService.findByNote(note));
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping("delete/{note}")
    public Result deleteByNote(@PathVariable String note){
        applicaitionService.deleteByNote(note);
        return Result.resultOkMsg("删除成功");
    }

    /**
     * 查询状态
     * @param note
     * @param flag
     * @return
     */
    @PutMapping("getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable String note,@PathVariable String flag){
        return applicaitionService.getFlag(note,flag);
    }

    /**
     * 确认
     * @param note
     * @return
     */
    @PutMapping("confirm/{note}")
    public Result confirm(@PathVariable String note){
        return applicaitionService.confirm(note);
    }

    /**
     * 取消确认
     * @param note
     * @return
     */
    @PutMapping("cancle/{note}")
    public Result cancle(@PathVariable String note){
        return applicaitionService.cancle(note);
    }

}
