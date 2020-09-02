package com.tengzhi.business.resouces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/resouces/**")
public class ResoucesController {
    @GetMapping
    public ModelAndView page(ModelAndView mv){
        return mv;
    }
}
