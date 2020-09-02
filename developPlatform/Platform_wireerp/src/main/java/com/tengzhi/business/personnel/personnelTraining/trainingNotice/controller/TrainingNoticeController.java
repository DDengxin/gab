package com.tengzhi.business.personnel.personnelTraining.trainingNotice.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mSbJt.inspectionRecord.vo.InspectionVo;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.dao.TrainingNoticeDao;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.service.TrainingNoticeService;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.vo.TrainingNoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/personnel/personnelTraining/trainingNotice")
public class TrainingNoticeController {

    @Autowired
    private TrainingNoticeService trainingNoticeService;

    @GetMapping(value = "/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询
     *
     * @param baseDto
     */
    @PostMapping(value = "pxtzList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return trainingNoticeService.getList(baseDto).getResult();
    }

    /**
     * 新增
     *
     * @param vo
     */
    @PostMapping(value = "pxtzAdd.html")
    public Result add(@RequestBody TrainingNoticeVo vo) throws Exception {
        return trainingNoticeService.save(vo);
    }
    /**
     *
     *修改数据
     * @param vo
     * */
    @PutMapping(value = "pxtzAdd.html")
    public Result modify(@RequestBody TrainingNoticeVo vo) throws Exception {
        return trainingNoticeService.update(vo);
    }

    /**
     * 删除对象数据
     *
     * @param tzNote
     */
    @DeleteMapping(value = "deleteByNote/{tzNote}")
    public Result delete(@PathVariable(value = "tzNote") String tzNote) {
        trainingNoticeService.deleteByNote(tzNote);
        return Result.resultOk();
    }
    /**
     *获取一条记录
     * @param tzNote
     * */
    @GetMapping(value = "getByNote/{tzNote}")
    public Map<String,Object> getByNote(@PathVariable String tzNote) {
        return Result.resultOk(trainingNoticeService.getByNote(tzNote));
    }

    /**
     * 刷新Grid
     * @param baseDto
     * */
    @PostMapping(value = "/getRushGrid")
    public Result getAddGrid(BaseDto baseDto) throws IOException {
        return trainingNoticeService.getRushGrid(baseDto).getResult();
    }
}
