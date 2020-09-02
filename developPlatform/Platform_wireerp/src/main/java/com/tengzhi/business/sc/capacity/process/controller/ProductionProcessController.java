package com.tengzhi.business.sc.capacity.process.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.capacity.process.service.ProductionProcessService;
import com.tengzhi.business.sc.capacity.specification.model.MScGgclVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/13 0013 19:02
 * @Description:工序产能
 */
@SuppressWarnings("all")
@RestController
@RequestMapping(value = "/sc/capacity/process/")
public class ProductionProcessController {


    private ProductionProcessService productionProcessService;

    @Autowired
    public ProductionProcessController(ProductionProcessService productionProcessService) {
        this.productionProcessService = productionProcessService;
    }

    @GetMapping(value="*.html")
    public ModelAndView index(ModelAndView modelAndView){
        return modelAndView;
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/13 0013 9:36
     *@Param:       [baseDto]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 查询所有数据
     **/
    @PostMapping(value = "/findAll")
    public Result findAll(BaseDto baseDto){
        Result resultAll = productionProcessService.findAll(baseDto).getResult();
        return resultAll;
    }

}
