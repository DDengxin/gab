package com.tengzhi.business.project.projectArchives.projectTreams.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.project.projectArchives.projectTreams.model.EXmXmxz;
import com.tengzhi.business.project.projectArchives.projectTreams.service.ProjectTreamService;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/project/projectArchives/projectTream")
public class ProjectTreamController {
    @Autowired
    private ProjectTreamService projectTreamService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForward(ModelAndView mv) {
        return mv;
    }

    @PostMapping(value = "/getList")
    public Result getList(BaseDto baseDto){
        return projectTreamService.findAll(baseDto).getResult();
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody EXmXmxz eXmXmxz){
        return  Result.resultOk(projectTreamService.save(eXmXmxz));
    }

    @GetMapping(value = "/byXmId/{xzId}")
    public Result getByXmId(@PathVariable String xzId){
        return Result.resultOk(projectTreamService.getByXmId(xzId));
    }

    @PutMapping(value = "/add")
    public Result Modify(@RequestBody EXmXmxz eXmXmxz){
        projectTreamService.update(eXmXmxz);
        return Result.resultOk(eXmXmxz.getXmId());
    }

    @DeleteMapping(value = "/delete/{xzId}")
    public Result delete(@PathVariable String xzId){
        projectTreamService.deleteByXmId(xzId);
        return Result.resultOk();
    }


    @RequestMapping(value = "/getTeamListByXm/{xmId}")
    public List<SelectVo> getTeamListByXm(@PathVariable String xmId){
        return  projectTreamService.getTeamListByXm(xmId);
    }




}
