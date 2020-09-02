package com.tengzhi.business.platform.demo;

import com.tengzhi.base.security.common.annotations.Anonymous;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: gaodu
 * @date: 2020/8/27 11:29
 **/

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Anonymous
    @GetMapping(value = {"/*.html", "/**/*"})
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
}
