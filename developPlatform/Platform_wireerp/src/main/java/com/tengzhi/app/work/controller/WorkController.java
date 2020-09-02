package com.tengzhi.app.work.controller;

import com.tengzhi.app.daily.service.impl.DailyServiceImpl;
import com.tengzhi.app.work.service.impl.WorkServiceImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;
import com.tengzhi.business.project.projectProcess.projectTask.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/app/work")
public class WorkController {
    @Autowired
    private WorkServiceImpl workService;

    @Autowired
    private ProjectTaskService projectTaskService;

    /**
     * 查询客户档案
     */
    @PostMapping(value = "getCustomerProfile")
    public Result getCustomerProfile(BaseDto baseDto) throws IOException {
        return workService.getCustomerProfile(baseDto).getResult();
    }

    /**
     * 查询任务发布
     */
    @PostMapping(value = "getTaskRelease")
    public Result getTaskRelease(BaseDto baseDto) throws IOException {
        return projectTaskService.getList(baseDto).getResult();
    }
    /**
     * 任务发布/撤销
     */
    @PostMapping(value = "taskRelease")
    public Result updateFlag(String rwNote,String currentFlag,String nextFlag){
        return projectTaskService.updateFlag(rwNote,currentFlag,nextFlag);
    }

    /**
     * 任务删除
     * **/
    @PostMapping(value = "taskDelete")
    public Result taskDelete(String rwNote) {
        projectTaskService.deleteByNote(rwNote);
        return Result.resultOk();
    }
    /**
     * 任务接收
     * **/
    @PostMapping(value = "/taskAccept")
    public Result taskAccept(String rwNote,String currentFlag,String nextFlag){
        return projectTaskService.receipt(rwNote,currentFlag,nextFlag);
    }
    /**
     * 任务取消接收
     * **/
    @PostMapping(value = "/taskCancelAccept")
    public Result taskCancelAccept(String rwNote,String currentFlag,String nextFlag){
        return projectTaskService.qxjs(rwNote,currentFlag,nextFlag);
    }

    /**
     * 任务开始
     */
    @PostMapping(value = "/taskStart")
    public Result taskStart(String rwNote,String currentFlag,String nextFlag){
        return projectTaskService.ksOption(rwNote , currentFlag,nextFlag);
    }

    /**
     * 取消任务开始
     */
    @PostMapping(value = "/taskCancelStart")
    public Result taskCancelStart(String rwNote,String currentFlag,String nextFlag){
        return projectTaskService.qxks(rwNote , currentFlag,nextFlag);
    }
    /**
     *
     * 任务完成
     * */
    @PostMapping(value = "/taskComplete")
    public Result taskComplete(String rwNote,String gs,String result,String remarks) throws Exception {
        EXmXmrw eXmXmrw=new EXmXmrw();
        eXmXmrw.setRwNote(rwNote);
        eXmXmrw.setRwGs(BigDecimal.valueOf(Double.valueOf(gs)));
        eXmXmrw.setRwWcResult(result);
        eXmXmrw.setRwWcRemarks(remarks);
        projectTaskService.wcOption(eXmXmrw);
        return Result.resultOk();
    }

    /**
     * 取消任务完成
     */
    @PostMapping(value = "/taskCancelComplete")
    public Result taskCancelComplete(String rwNote,String currentFlag,String nextFlag){
        return projectTaskService.qxwc(rwNote,currentFlag,nextFlag);
    }

    /**
     *
     *任务考评
     * */
    @PostMapping("/taskEvaluation")
    public Result taskEvaluation(String rwNote,String gs,String jd,String zl,String cb,String remarks) {
        EXmXmrw eXmXmrw=new EXmXmrw();
        eXmXmrw.setRwNote(rwNote);
        eXmXmrw.setRwGs(BigDecimal.valueOf(Double.valueOf(gs)));
        eXmXmrw.setRwProcess(jd);
        eXmXmrw.setRwPz(zl);
        eXmXmrw.setRwCb(cb);
        eXmXmrw.setRwKhRemarks(remarks);
        projectTaskService.kpOption(eXmXmrw);
        return Result.resultOk();
    }

    /**
     * 取消任务考评
     */
    @PostMapping(value = "/taskCancelEvaluation")
    public Result taskCancelEvaluation(String rwNote,String currentFlag,String nextFlag){
        return projectTaskService.qxkp(rwNote,currentFlag,nextFlag);
    }
}
