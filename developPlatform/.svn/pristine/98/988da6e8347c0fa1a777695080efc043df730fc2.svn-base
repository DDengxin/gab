package com.tengzhi.business.demo.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.demo.service.ShowDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author: gaodu
 * @date: 2020/7/29 9:17
 **/
@RestController
@RequestMapping("demo/showdemo/")
public class ShowDemoController {
    @Autowired
    ShowDemoService showDemoService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     *
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/find")
    public Result findAllTable(BaseDto baseDto) {
        return showDemoService.find(baseDto);
    }
}
