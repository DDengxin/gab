package com.tengzhi.business.finance.financialBooks.accountBalanceSheet.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.financialBooks.accountBalanceSheet.service.AccountBalanceSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/finance/financialBooks/accountBalanceSheet")
public class AccountBalanceSheetController {
    @Autowired
    private AccountBalanceSheetService accountBalanceSheetService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "accountBalanceSheetList.html")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
        return accountBalanceSheetService.findAll(baseDto).getResult();
    }
}
