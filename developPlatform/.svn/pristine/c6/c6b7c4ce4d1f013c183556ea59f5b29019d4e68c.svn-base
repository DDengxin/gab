package com.tengzhi.business.sc.capacity.product.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.capacity.product.service.ProductService;
import com.tengzhi.business.sc.capacity.specification.model.MScGgclVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/13 0013 18:49
 * @Description:生产产能
 */
@RestController
@RequestMapping(value="/sc/capacity/product/")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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
        Result resultAll = productService.findAll(baseDto).getResult();
        return resultAll;
    }
}
