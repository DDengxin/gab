package com.tengzhi.business.platform.evaluation.controller;


import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.evaluation.model.GEvalutaion;
import com.tengzhi.business.platform.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("platform/evaluation")
public class EvaluationController {
    @Autowired
    EvaluationService evaluationService;

    @PostMapping("/addEvaluation")
    public Result addEvaluation(@RequestBody GEvalutaion gEvalutaion) {
        String productNum = gEvalutaion.getProductNum();
        if (evaluationService.getEvaluation(productNum).size() > 0) {
            return Result.error("您已评价过，不能重复评价");
        } else {
            return evaluationService.addEvaluation(gEvalutaion);
        }
    }
}
