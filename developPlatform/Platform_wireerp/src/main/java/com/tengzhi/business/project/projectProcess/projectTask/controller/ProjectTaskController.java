package com.tengzhi.business.project.projectProcess.projectTask.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;
import com.tengzhi.business.project.projectProcess.projectTask.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@RestController
@RequestMapping("/project/projectProcess/projectTask")
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService service;

    @GetMapping(value="/*.html")
    public ModelAndView pageForward(ModelAndView mv) {
        return mv;
    }

    /**
     * 新增一条记录
     * **/
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EXmXmrw mode) throws Exception{
        return Result.resultOk(service.save(mode));
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


    /**
     *获取一条记录
     * @param rwNote
     * */
    @GetMapping(value = "getById/{rwNote}")
    public Result getById(@PathVariable String rwNote) {
        return Result.resultOk(service.findById(rwNote));
    }

    /**
     *
     *修改数据
     * @param model
     * */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXmXmrw model) {
        service.update(model);
        return Result.resultOk();
    }


    /**
     * 删除对象数据
     * @param rwNote
     * **/
    @DeleteMapping(value = "deleteById/{rwNote}")
    public Result delete(@PathVariable(value = "rwNote") String rwNote) {
        service.deleteByNote(rwNote);
        return Result.resultOk();
    }

    @RequestMapping(value = "/getFlag/{rwNote}/{flag}")
    public Result getFlag(@PathVariable(value = "rwNote") String rwNote, @PathVariable(value = "flag") String flag) throws Exception {
        return service.getFlag(rwNote,flag);
    }

    @PutMapping(value = "/updateFlag/{rwNote}/{currentFlag}/{nextFlag}")
    public Result updateFlag(@PathVariable(value = "rwNote") String rwNote,@PathVariable(value = "currentFlag") String currentFlag,@PathVariable(value = "nextFlag") String nextFlag){
        return service.updateFlag(rwNote,currentFlag,nextFlag);
    }



}
