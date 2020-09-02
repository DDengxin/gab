package com.tengzhi.business.platform.paysdk.buyproduct.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.business.platform.paysdk.buyproduct.model.GProductNotes;
import com.tengzhi.business.platform.paysdk.buyproduct.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 鱼游浅水
 * @create 2020-07-17
 */

@RestController
@RequestMapping("/platform/paysdk/buyproduct/")
public class BuyControl {

    @Autowired
    private BuyService buyService;

    @RequestMapping("*")
    public ModelAndView Home(ModelAndView modelAndView){
        return  modelAndView;
    }

    @RequestMapping("findAll")
    public Result findAll(BaseDto baseDto){
        return buyService.BuyProductFindAll(baseDto).getResult();
    }

    @Anonymous
    @RequestMapping("buySava")
    public ModelAndView buySava(GProductNotes buyProduct){
         buyService.BuyProductNoteSava(buyProduct);
         ModelAndView mv=new ModelAndView();
         mv.setViewName("/gab/goodsList");
        return mv;
    }


}
