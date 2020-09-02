package com.tengzhi.business.project.projectProcess.projectTaskRecipt.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;
import com.tengzhi.business.project.projectProcess.projectTask.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/project/projectProcess/projectTaskRecipt")
public class ProjectTaskReciptController {

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

    @PutMapping(value = "/receipt/{rwNote}/{currentFlag}/{nextFlag}")
    public Result receipt(@PathVariable(value = "rwNote") String rwNote,@PathVariable(value = "currentFlag") String currentFlag,@PathVariable(value = "nextFlag") String nextFlag){
        return service.receipt(rwNote,currentFlag,nextFlag);
    }


    @PutMapping(value = "/qxjs/{rwNote}/{currentFlag}/{nextFlag}")
    public Result qxjs(@PathVariable(value = "rwNote") String rwNote,@PathVariable(value = "currentFlag") String currentFlag,@PathVariable(value = "nextFlag") String nextFlag){
        return service.qxjs(rwNote,currentFlag,nextFlag);
    }

    /**
     *获取一条记录
     * @param rwNote
     * */
    @GetMapping(value = "getById/{rwNote}")
    public Result getById(@PathVariable String rwNote) {
        return Result.resultOk(service.findById(rwNote));
    }

}
