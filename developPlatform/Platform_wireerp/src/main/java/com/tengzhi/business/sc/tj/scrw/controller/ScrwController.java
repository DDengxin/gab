package com.tengzhi.business.sc.tj.scrw.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.tj.scrw.service.ScrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/sc/tj/scrw")
public class ScrwController {
    @Autowired
    private ScrwService scrwService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     * 	查询数据列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/list.html")
    public Result getSrchTopList(BaseDto baseDto){
        return scrwService.findAll(baseDto).getResult();
    }

    /**
     * 	查询数据列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/gxlist.html")
    public Result getSrchTopGxList(BaseDto baseDto){
        return scrwService.findGxAll(baseDto).getResult();
    }


    /**
     * 	查询数据列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/jtlist.html")
    public Result getSrchTopJtList(BaseDto baseDto) {
        return scrwService.findJtAll(baseDto).getResult();
    }
}