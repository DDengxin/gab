package com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.service.TrainingEvaluationService;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.vo.trainingEvaluationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/personnel/personnelTraining/trainingEvaluation")
public class TrainingEvaluationController {
    @Autowired
    TrainingEvaluationService trainingEvaluationService;
    @GetMapping(value = "/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询
     *
     * @param baseDto
     */
    @PostMapping(value = "pxkpList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return trainingEvaluationService.getList(baseDto).getResult();
    }
    /**
     *
     *保存对象信息
     * @param vo
     * */
    @PostMapping(value = "pxkpAdd.html")
    public Result getByNote(@RequestBody trainingEvaluationVo vo) throws Exception {
        return trainingEvaluationService.save(vo);
    }
    /**
     * 删除对象数据
     *
     * @param jlNote
     */
    @DeleteMapping(value = "deleteByNote/{jlNote}")
    public Result delete(@PathVariable(value = "jlNote") String jlNote) {
        trainingEvaluationService.deleteByNote(jlNote);
        return Result.resultOk();
    }
    /**
     *获取一条记录
     * @param jlNote
     * */
    @GetMapping(value = "getByNote/{jlNote}")
    public Map<String,Object> getByNote(@PathVariable String jlNote) {
        return Result.resultOk(trainingEvaluationService.getByNote(jlNote));
    }
    /**
     *
     *修改数据
     * @param vo
     * */
    @PutMapping("pxkpAdd.html")
    public Result modify(@RequestBody trainingEvaluationVo vo) {
      return  trainingEvaluationService.update(vo);
    }
    /**
     * 刷新Grid
     * @param baseDto
     * */
    @PostMapping(value = "/getRushGrid")
    public Result getAddGrid(BaseDto baseDto) throws IOException {
        return trainingEvaluationService.getRushGrid(baseDto).getResult();
    }
}
