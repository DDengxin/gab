package com.tengzhi.business.personnel.personnelTraining.trainingPlan.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.model.EHrTrainingPlan;
import com.tengzhi.business.personnel.personnelTraining.trainingPlan.service.TrainingPlanService;
import com.tengzhi.business.project.projectArchives.xmda.model.EXmXmda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/personnel/personnelTraining/trainingPlan")
public class TrainingPlanController {
    @Autowired
    private TrainingPlanService trainingPlanService;
    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询
     * @param baseDto
     * */
    @PostMapping(value = "pxjhList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return trainingPlanService.getList(baseDto).getResult();
    }
    /**
     *
     *保存对象信息
     * @param eHrTrainingPlan
     * */
    @PostMapping(value = "pxjhAdd.html")
    public Result getById(@RequestBody EHrTrainingPlan eHrTrainingPlan) {
        trainingPlanService.saveData(eHrTrainingPlan);
        return Result.resultOk();
    }
    /**
     *获取一条记录
     * @param jhNote
     * */
    @GetMapping(value = "getByNote/{jhNote}")
    public Map<String,Object> getByNote(@PathVariable String jhNote) {
        return Result.resultOk(trainingPlanService.getByNote(jhNote));
    }
    /**
     *
     *修改数据
     * @param eHrTrainingPlan
     * */
    @PutMapping("pxjhAdd.html")
    public Result modify(@RequestBody EHrTrainingPlan eHrTrainingPlan) {
        trainingPlanService.updateData(eHrTrainingPlan);
        return Result.resultOk(eHrTrainingPlan.getJhNote());
    }

    /**
     * 删除对象数据
     * @param jhNote
     * */
    @DeleteMapping(value = "deleteByNote/{jhNote}")
    public Result delete(@PathVariable(value = "jhNote") String jhNote) {
        trainingPlanService.deleteByNote(jhNote);
        return Result.resultOk();
    }

    /**
     * 状态判断
     * @param baseDto
     * */

    @PutMapping(value = "/getJhFlag")
    public Result getJhFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return trainingPlanService.getJhFlag(String.valueOf(map.get("jhNote")), String.valueOf(map.get("jhFlag")));
    }
    /**
     * 状态切换至核准
     * @param jhNote
     * */
    @PutMapping(value = "/ok/{jhNote}")
    public Result ok(@PathVariable(value = "jhNote") String jhNote) throws Exception {
        return trainingPlanService.setJhFlag(jhNote, "核准");
    }

    /**
     *取消确认状态
     * @param jhNote
     * */
    @PutMapping(value = "/no/{jhNote}")
    public Result no(@PathVariable(value = "jhNote") String jhNote) throws Exception {
        return trainingPlanService.setJhFlag(jhNote, "登记");
    }
}
