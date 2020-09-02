package com.tengzhi.business.sc.da.staffOvertime.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mesPersonnel.checkWorker.overtime.service.OvertimeService;
import com.tengzhi.business.personnel.checkWorkAttendance.leave.service.LeaveService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.service.WorkOvertimeService;
import com.tengzhi.business.sc.da.staffOvertime.model.StaffOvertime;
import com.tengzhi.business.sc.da.staffOvertime.service.StaffOvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/4 0004 17:33
 * @Description:
 */

@RestController
@RequestMapping(value = "/sc/da/staffOvertime/")
public class StaffOvertimeController {

    private StaffOvertimeService staffOvertimeService;

    private WorkOvertimeService workOvertimeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    public StaffOvertimeController(StaffOvertimeService staffOvertimeService,WorkOvertimeService workOvertimeService) {
        this.staffOvertimeService = staffOvertimeService;
        this.workOvertimeService = workOvertimeService;
    }

    @GetMapping(value = "*.html")
    public ModelAndView pageForwart(ModelAndView modelAndView){
        return modelAndView;
    }

    @DeleteMapping(value = "/deleteStaff/{workId}")
    public Result getList(@PathVariable(name = "workId",required = false) String workId) throws IOException, ParseException {
        Result result = staffOvertimeService.deleteById(workId);
        return result;
    }

    @GetMapping(value = "/staffOvertime.html/{jbqjId}")
    public Result getById(@PathVariable String jbqjId){
        Result result = Result.resultOk(workOvertimeService.findByqjId(jbqjId));
        return result;
    }

    @PostMapping(value = "/findStaffOvertimeAll")
    public Result findAllOvertimeStaff(BaseDto baseDto){
        BasePage<StaffOvertime> allStaffOvertime = staffOvertimeService.findAll(baseDto);
        return allStaffOvertime.getResult();
    }

    @PostMapping(value = "add.html")
    public Result add(@RequestBody CheckWorkAttendance checkWorkAttendance) throws Exception{
        checkWorkAttendance = workOvertimeService.save(checkWorkAttendance);
        return Result.resultOk(checkWorkAttendance.getJbqjId());
    }

    @PutMapping("add.html")
    public Result modify(@RequestBody CheckWorkAttendance checkWorkAttendance) {
        workOvertimeService.update(checkWorkAttendance);
        return Result.resultOk(checkWorkAttendance.getJbqjId());
    }

    @PutMapping(value = "getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable(value = "note") String note,@PathVariable(value = "flag") String flag)throws Exception {
        return leaveService.getFlag(note,flag);
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/5 0005 9:50
     *@Param:       [baseDto]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 员工选择数据拉取
     **/
    @PostMapping(value = "/findByProductionList")
    public Result getList(BaseDto baseDto) throws IOException {
        Result result = staffOvertimeService.findProudction(baseDto).getResult();
        return result;
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/7 0007 20:27
     *@Param:       [sid, flag]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 更改状态
     **/
    @PutMapping(value = "/updateFlag/{jbqjId}")
    public Result updateFlag(@PathVariable(value = "jbqjId") String jbqjId){
        Result result = staffOvertimeService.updateFlag(jbqjId);
        return result;
    }














}
