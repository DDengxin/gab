package com.tengzhi.business.mSbJt.inspectionRecord.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseRequisition.vo.EcgRequisitiontVo;
import com.tengzhi.business.mSbJt.inspectionRecord.model.MSbInspection;
import com.tengzhi.business.mSbJt.inspectionRecord.service.InspectionRecordService;
import com.tengzhi.business.mSbJt.inspectionRecord.vo.InspectionVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("mSbJt/inspectionRecord")
public class InspectionRecordController {
    @Autowired
    private InspectionRecordService inspectionRecordService;

    @GetMapping("/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

   @PostMapping(value = "list.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return inspectionRecordService.findAll(baseDto).getResult();
    }

    /**
     *获取一条记录
     * @param inspectionSid
     * */
    @GetMapping(value = "getById/{inspectionSid}")
    public Result getById(@PathVariable String inspectionSid) {
        return Result.resultOk(inspectionRecordService.findByInspectionSid(inspectionSid));
    }

    /**
     *
     *修改数据
     * @param vo
     * */
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody InspectionVo vo) throws Exception {
        return inspectionRecordService.update(vo);
    }

    /**
     * 新增
     *
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody InspectionVo vo) throws Exception {
        return inspectionRecordService.save(vo);
    }


    /**
     * 新增Grid
     * @param baseDto
     * */
    @PostMapping(value = "/getAddGrid")
    public Result getAddGrid(BaseDto baseDto) throws IOException {
        return inspectionRecordService.getAddGrid(baseDto).getResult();
    }

    /**
     * 删除对象数据
     * @param inspectionNote
     * **/
    @DeleteMapping(value = "deleteByNote/{inspectionNote}")
    public Result delete(@PathVariable(value = "inspectionNote") String inspectionNote) {
        inspectionRecordService.deleteByNote(inspectionNote);
        return Result.resultOk();
    }
}
