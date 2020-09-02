package com.tengzhi.business.platform.quotation.controller;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.quotation.model.QuotationCostItems;
import com.tengzhi.business.platform.quotation.service.QuotationCostService;
import com.tengzhi.business.platform.quotation.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("platform/quotation")
public class QuotationController {
    @Autowired
    QuotationService quotationService;
    @Autowired
    QuotationCostService quotationCostService;


    @GetMapping("*.html")
    public ModelAndView index(ModelAndView mv) {
        return mv;
    }


    @PostMapping("quotationManagement.html")
    public Result getList(BaseDto baseDto) throws Exception {
        return quotationService.findAll(baseDto).getResult();

    }

//    @PutMapping("add")
//    public Result modify(@RequestBody String voItem) {
//        Vo vo = JSON.parseObject(voItem, Vo.class);
//        quotationService.update(vo);
//        return Result.resultOk();
//    }
//
//    @PostMapping(value = "add")
//    public Result add(@RequestBody String voItem) throws Exception {
//        Vo vo = JSON.parseObject(voItem, Vo.class);
//        return quotationService.save(vo);
//    }

    @DeleteMapping("del/{id}")
    public Result delete(@PathVariable String id) {
        quotationService.deleteById(id);
        return Result.resultOk();
    }

    @GetMapping(value = "quotationManagement/{id}")
    public Result getById(@PathVariable String id) throws Exception {
        return Result.resultOk(quotationService.findById(id));
    }



    @PostMapping(value = "getDirectCost")
    public Result getDirectCost(BaseDto baseDto) throws Exception {
        BasePage<QuotationCostItems> quotationCost = quotationCostService.getQuotationCost(baseDto);
        return quotationCost.getResult();
    }

}