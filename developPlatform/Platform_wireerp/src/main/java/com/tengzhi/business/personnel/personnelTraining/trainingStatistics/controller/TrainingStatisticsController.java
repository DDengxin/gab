package com.tengzhi.business.personnel.personnelTraining.trainingStatistics.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.personnel.personnelTraining.trainingStatistics.service.TrainingStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/personnel/personnelTraining/trainingStatistics")
public class TrainingStatisticsController {
    @Autowired
    TrainingStatisticsService trainingStatisticsService;
    @GetMapping(value = "/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询
     *
     * @param baseDto
     */
    @PostMapping(value = "pxtjList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return trainingStatisticsService.getList(baseDto).getResult();
    }
    /**
     * 数据导出
     * @param response
     * @param request
     * */
    @PostMapping("/getXls")
    public void getXls(HttpServletResponse response, HttpServletRequest request){
        trainingStatisticsService.getXls(response,request);
    }
}
