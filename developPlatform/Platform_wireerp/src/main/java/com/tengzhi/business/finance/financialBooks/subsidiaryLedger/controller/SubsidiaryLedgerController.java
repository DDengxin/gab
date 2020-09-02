package com.tengzhi.business.finance.financialBooks.subsidiaryLedger.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.financialBooks.subsidiaryLedger.service.SubsidiaryLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/finance/financialBooks/subsidiaryLedger")
public class SubsidiaryLedgerController {
    @Autowired
    private SubsidiaryLedgerService subsidiaryLedgerService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "subsidiaryLedgerList.html")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
        return subsidiaryLedgerService.findAll(baseDto).getResult();
    }
}
