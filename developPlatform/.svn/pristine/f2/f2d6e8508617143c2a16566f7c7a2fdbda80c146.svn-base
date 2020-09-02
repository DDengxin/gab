package com.tengzhi.business.production.subcontract.wwhx.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseContract.service.PurchaseContractService;
import com.tengzhi.business.production.subcontract.wwhx.service.WwhxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/production/subcontract/wwhx")
public class WwhxController {

    @Autowired
    private WwhxService service;

    @GetMapping("/*")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    @RequestMapping(value = "listwriteoff")
    public Result getContractDetailed(BaseDto baseDto) throws IOException {
        return service.getContractDetailed(baseDto);
    }

    @RequestMapping(value = "/getFlag/{htMo}/{flag}/{code}")
    public Result getFlag(@PathVariable(value = "htMo") String htMo, @PathVariable(value = "flag") String flag, @PathVariable(value = "code") String code) throws Exception {
        return service.getFlagH(htMo,flag,code);
    }
    @RequestMapping(value = "/getCancelFlag/{htMo}/{flag}/{code}")
    public Result getCancelFlag(@PathVariable(value = "htMo") String htMo,@PathVariable(value = "flag") String flag,@PathVariable(value = "code") String code) throws Exception {
        return service.getCancelFlagH(htMo,flag,code);
    }

}
