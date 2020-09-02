package com.tengzhi.business.sc.da.productionStaff.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.da.productionStaff.model.ProductionStaff;
import com.tengzhi.business.sc.da.productionStaff.service.ProductionStaffServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/1 0001 14:52
 * @Description:
 */
@RestController
@RequestMapping(value = "/sc/da/productionStaff/")
public class ProductionStaffController {

    private final ProductionStaffServie productionStaffServie;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    @Autowired
    private ProductionStaffController(ProductionStaffServie productionStaffServie){
        this.productionStaffServie = productionStaffServie;
    }

    @RequestMapping(value = "/saveProductionStaff",method = RequestMethod.POST)
    public Result saveProductionStaff(ProductionStaff productionStaff){
        return Result.resultOk(productionStaffServie.saveProductionStaff(productionStaff));
    }

    @RequestMapping(value = "/deleteProductionStaffById",method = RequestMethod.DELETE)
    public String deleteProductionStaffById(ProductionStaff productionStaff){
        return productionStaffServie.updateByUserId(productionStaff);
    }

    @RequestMapping(value = "/UpdateProductionStaff",method = RequestMethod.POST)
    public String UpdateProductionStaff(@RequestBody ProductionStaff productionStaff){
        System.out.println("更新操作:"+productionStaff.toString());
        return productionStaffServie.updateByUserId(productionStaff);
    }

    @RequestMapping(value = "/findProductionStaffById/{id}",method = RequestMethod.GET)
    public ProductionStaff findProductionStaffById(@PathVariable String id){
        return productionStaffServie.findPersonProductionStaffById(id);
    }

    @PostMapping(value = "/findProductionStaffAll")
    public Result findProductionStaffAll(BaseDto baseDto){
        return productionStaffServie.findProductionStaffAll(baseDto).getResult();
    }


}
