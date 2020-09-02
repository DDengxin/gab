package com.tengzhi.business.system.archiveLog.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.service.Logservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/system/archiveLog")
public class ArchiveLogController {

    @Autowired
    private Logservice logservice;


    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public Result getList(BaseDto baseDto) {
        return logservice.getArchiveLogList(baseDto).getResult();
    }

}
