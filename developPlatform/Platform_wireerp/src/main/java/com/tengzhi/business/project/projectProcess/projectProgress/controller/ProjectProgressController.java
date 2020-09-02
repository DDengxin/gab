package com.tengzhi.business.project.projectProcess.projectProgress.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;
import com.tengzhi.business.project.projectProcess.projectTask.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/project/projectProcess/projectProgress")
public class ProjectProgressController {

    @Autowired
    private ProjectTaskService service;

    @GetMapping(value="/*.html")
    public ModelAndView pageForward(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "list.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return service.getList(baseDto).getResult();
    }


    @PutMapping(value = "/qxwc/{rwNote}/{currentFlag}/{nextFlag}")
    public Result qxwc(@PathVariable(value = "rwNote") String rwNote,@PathVariable(value = "currentFlag") String currentFlag,@PathVariable(value = "nextFlag") String nextFlag){
        return service.qxwc(rwNote,currentFlag,nextFlag);
    }

    /**
     *获取一条记录
     * @param rwNote
     * */
    @GetMapping(value = "getById/{rwNote}")
    public Result getById(@PathVariable String rwNote) {
        return Result.resultOk(service.findById(rwNote));
    }

    @RequestMapping(value = "/getFlag/{rwNote}/{flag}")
    public Result getFlag(@PathVariable(value = "rwNote") String rwNote, @PathVariable(value = "flag") String flag) throws Exception {
        return service.getFlag(rwNote,flag);
    }

    /**
     *
     *修改数据
     * @param model
     * */
    @PutMapping("add.html")
    public Result wcOption(@RequestBody EXmXmrw model) {
        service.wcOption(model);
        return Result.resultOk();
    }

    @PutMapping(value = "/updateFlag/{rwNote}/{currentFlag}/{nextFlag}")
    public Result updateFlag(@PathVariable(value = "rwNote") String rwNote,@PathVariable(value = "currentFlag") String currentFlag,@PathVariable(value = "nextFlag") String nextFlag){
        return service.updateFlag(rwNote,currentFlag,nextFlag);
    }

    /**
     * 开始
     * @param rwNote
     * @return
     */
    @PutMapping(value = "/ksOption/{rwNote}/{currentFlag}/{nextFlag}")
    public Result ksOption(@PathVariable(value = "rwNote") String rwNote,@PathVariable(value = "currentFlag") String currentFlag,@PathVariable(value = "nextFlag") String nextFlag){
        return service.ksOption(rwNote , currentFlag,nextFlag);
    }
    /**
     * 取消开始
     * @param rwNote
     * @return
     */
    @PutMapping(value = "/qxks/{rwNote}/{currentFlag}/{nextFlag}")
    public Result qxks(@PathVariable(value = "rwNote") String rwNote,@PathVariable(value = "currentFlag") String currentFlag,@PathVariable(value = "nextFlag") String nextFlag){
        return service.qxks(rwNote , currentFlag,nextFlag);
    }

}
