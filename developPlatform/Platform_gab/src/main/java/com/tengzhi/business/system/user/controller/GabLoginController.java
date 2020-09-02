package com.tengzhi.business.system.user.controller;

import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.business.system.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gabLogin")
public class GabLoginController {

    @Autowired
    LoginService loginService;

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping
    @Anonymous
    public ModelAndView login(ModelAndView mv,@RequestParam(required=false) String corp,@RequestParam(required=false,defaultValue = "/gab/index.html") String url) {
    	mv.setViewName("system/login");
        mv = loginService.getSetting(corp,mv);
        mv.addObject("url", url);
        return mv;
    }
    
}
