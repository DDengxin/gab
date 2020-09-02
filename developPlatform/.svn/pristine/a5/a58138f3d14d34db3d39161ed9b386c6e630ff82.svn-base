package com.tengzhi.business.project.projectStage.projectLine.controller;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;
import com.tengzhi.business.project.projectProcess.projectTask.service.ProjectTaskService;
import com.tengzhi.business.project.projectStage.projectLine.model.EXmXmlc;
import com.tengzhi.business.project.projectStage.projectLine.service.ProjectLineService;
import com.tengzhi.business.project.projectStage.projectLine.vo.EXmXmlcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project/projectStage/projectLine")
public class ProjectLineController {

    @Autowired
    private ProjectLineService service;

    @GetMapping(value="/*.html")
    public ModelAndView pageForward(ModelAndView mv) {
        return mv;
    }

    /**
     * 新增记录
     * **/
    @PostMapping(value = "add.html")
    @Log("项目流程新增")
    public Result add(@RequestBody EXmXmlcVo vo) throws Exception{
        return Result.resultOk(service.save(EXmXmlcVo.initVo(vo)));
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
     * 查询明细列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchBottomList")
    public Result getSrchBottomList(BaseDto baseDto) throws IOException {
        return service.getSrchBottomList(baseDto).getResult();
    }

    /**
     *获取一条记录
     * @param lcNote
     * */
    @GetMapping(value = "getById/{lcNote}")
    public Result getById(@PathVariable String lcNote) {
        return Result.resultOk(service.findById(lcNote));
    }

    /**
     *
     *修改数据
     * @param vo
     * */
    @PutMapping("add.html")
    @Log("项目流程修改")
    public Result modify(@RequestBody EXmXmlcVo vo) {
        return Result.resultOk(service.update(vo.initVo(vo)));
    }


    /**
     * 删除对象数据
     * @param lcNote
     * **/
    @DeleteMapping(value = "deleteByNote/{lcNote}")
    public Result delete(@PathVariable(value = "lcNote") String lcNote) {
        service.deleteByNote(lcNote);
        return Result.resultOk();
    }

    @RequestMapping("/getXmlcByXm/{xmId}")
    public List<Map<String,Object>> getXmlcByXm(@PathVariable(value = "xmId") String xmId){
        return service.getXmlcByXm(xmId);
    }

    @RequestMapping("/getXmNodeByJd/{xmId}/{xmStage}")
    public List<Map<String,Object>> getXmNodeByJd(@PathVariable(value = "xmId") String xmId,@PathVariable(value = "xmStage") String xmStage){
        return service.getXmNodeByJd(xmId,xmStage);
    }


}
