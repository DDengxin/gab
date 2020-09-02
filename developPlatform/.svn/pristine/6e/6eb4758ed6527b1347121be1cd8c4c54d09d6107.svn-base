package com.tengzhi.business.sc.da.leave.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.da.leave.service.EHrJbqjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/11 0011 14:23
 * @Description:
 */
@RestController
@RequestMapping(value="/sc/da/leave/")
public class EHrJbqjController {

    private EHrJbqjService eHrJbqjService;

    @Autowired
    public EHrJbqjController(EHrJbqjService eHrJbqjService) {
        this.eHrJbqjService = eHrJbqjService;
    }

    @GetMapping(value = "*.html")
    public ModelAndView index(ModelAndView modelAndView){
        return modelAndView;
    }

    @PostMapping(value = "findAll")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
        return eHrJbqjService.findAll(baseDto).getResult();
    }

    @RequestMapping(value = "updateFlag/{jbqjId}")
    public Result updateFlag(@PathVariable(name = "jbqjId") String jbqjId){
        Result result = eHrJbqjService.updateFlag(jbqjId);
        return result;
    }













}
