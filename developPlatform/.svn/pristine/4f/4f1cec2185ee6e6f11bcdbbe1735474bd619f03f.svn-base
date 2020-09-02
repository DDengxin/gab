package com.tengzhi.business.sc.pd.bzgy.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.pd.bzgy.service.BzgyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 10:06
 * @Description:标准工艺
 */
@RestController
@RequestMapping(value="/sc/pd/bzgy/")
public class BzgyController {


    private BzgyService gccnService;

    @Autowired
    public BzgyController(BzgyService gccnService) {
        this.gccnService = gccnService;
    }

    @GetMapping(value="*.html")
    public ModelAndView index(ModelAndView modelAndView){
        return modelAndView;
    }

    /**
     *@role:
     *@Author:      龚行礼
     *@Date:       2020/8/21 0013 14:06
     *@Param:       [baseDto]
     *@return:
     *@Description: 查询所有数据
     **/
    @PostMapping(value = "/findAll")
    public Result findAll(BaseDto baseDto){
        Result resultAll = gccnService.findAll(baseDto).getResult();
        return resultAll;
    }
}
