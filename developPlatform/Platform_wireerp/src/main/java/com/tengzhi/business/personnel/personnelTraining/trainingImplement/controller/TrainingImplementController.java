package com.tengzhi.business.personnel.personnelTraining.trainingImplement.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.personnel.personnelTraining.trainingImplement.service.TrainingImplementService;
import com.tengzhi.business.personnel.personnelTraining.trainingImplement.vo.TrainingImplementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/personnel/personnelTraining/trainingImplement")
public class TrainingImplementController {
    @Autowired
    TrainingImplementService trainingImplementService;
    @GetMapping(value = "/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询 Personnel
     *
     * @param baseDto
     */
/*    @PostMapping(value = "pxzxAdd.html")
    public Result getPersonList(BaseDto baseDto) throws IOException {
        return trainingImplementService.getList(baseDto).getResult();
    }*/
    /**
     * 查询
     *
     * @param baseDto
     */
    @PostMapping(value = "pxzxList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return trainingImplementService.getList(baseDto).getResult();
    }
    /**
     *
     *保存对象信息
     * @param vo
     * */
    @PostMapping(value = "pxzxAdd.html")
    public Result getByNote(@RequestBody TrainingImplementVo vo) throws Exception {
        return trainingImplementService.save(vo);
    }
    /**
     * 删除对象数据
     *
     * @param jlNote
     */
    @DeleteMapping(value = "deleteByNote/{jlNote}")
    public Result delete(@PathVariable(value = "jlNote") String jlNote) {
        trainingImplementService.deleteByNote(jlNote);
        return Result.resultOk();
    }
    /**
     *获取一条记录
     * @param jlNote
     * */
    @GetMapping(value = "getByNote/{jlNote}")
    public Map<String,Object> getByNote(@PathVariable String jlNote) {
        return Result.resultOk(trainingImplementService.getByNote(jlNote));
    }
    /**
     *
     *修改数据
     * @param vo
     * */
    @PutMapping("pxzxAdd.html")
    public Result modify(@RequestBody TrainingImplementVo vo) {
      return  trainingImplementService.update(vo);
    }
    /**
     * 刷新Grid
     * @param baseDto
     * */
    @PostMapping(value = "/getRushGrid")
    public Result getAddGrid(BaseDto baseDto) throws IOException {
        return trainingImplementService.getRushGrid(baseDto).getResult();
    }
}
