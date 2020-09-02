package com.tengzhi.business.personnel.personnelTraining.trainingReport.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.personnel.personnelTraining.trainingReport.model.EHrTrainingReport;
import com.tengzhi.business.personnel.personnelTraining.trainingReport.service.TrainingReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/personnel/personnelTraining/trainingReport")
public class TrainingReportController {
    @Autowired
    TrainingReportService trainingReportService;
    @GetMapping(value = "/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询
     *
     * @param baseDto
     */
    @PostMapping(value = "pxbgList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return trainingReportService.getList(baseDto).getResult();
    }
    /**
     *获取一条记录
     * @param jlNote
     * */
    @GetMapping(value = "getByNote/{jlNote}")
    public Map<String,Object> getByNote(@PathVariable String jlNote) {
        return Result.resultOk(trainingReportService.getByNote(jlNote));
    }
    /**
     *
     *修改数据
     * @param eHrTrainingReport
     * */
    @PutMapping("pxbgAdd.html")
    public Result modify(@RequestBody EHrTrainingReport eHrTrainingReport) {
        trainingReportService.update(eHrTrainingReport);
        return Result.resultOk();
    }
}
