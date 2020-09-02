package com.tengzhi.business.xt.notice.deptmeeting.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.notice.deptmeeting.model.DeptMeeting;
import com.tengzhi.business.xt.notice.deptmeeting.service.DeptMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/xt/notice/deptMeeting")
public class DeptMeetingController {

    @Autowired
    private DeptMeetingService deptMeetingService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "sreach")
    public Result search_option(BaseDto baseDto) throws IOException {
        return deptMeetingService.getNotice(baseDto).getResult();
    }
    /**
     * 新增
     *
     * @param DeptMeeting
     * @return
     * @throws Exception
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody DeptMeeting DeptMeeting) throws Exception {
        DeptMeeting = deptMeetingService.save(DeptMeeting);
        return Result.resultOk(DeptMeeting.getNote());
    }

    /**
     * 修改
     **/
    @PutMapping("add.html")
    public Result modify(@RequestBody DeptMeeting DeptMeeting) {
        deptMeetingService.update(DeptMeeting);
        return Result.resultOk(DeptMeeting.getNote());
    }
    /**
     * 通过id获取对象
     **/
    @GetMapping(value = "add.html/{note}")
    public Result getById(@PathVariable String note) {
        return Result.resultOk(deptMeetingService.findByNote(note));
    }

    /**
     * 删除
     **/
    @DeleteMapping(value = "list.html/{note}")
    public Result delete(@PathVariable(value = "note") String note) {
        try {
            deptMeetingService.deleteByNoticeNo(note);
            return Result.resultOk();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }
}
