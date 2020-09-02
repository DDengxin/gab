package com.tengzhi.business.finance.financialBooks.accountBalanceSheet.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.payment.model.payment;

import java.io.IOException;
import java.text.ParseException;

public interface AccountBalanceSheetService extends BaseService {

    BasePage<payment> findAll(BaseDto baseDto) throws IOException, ParseException;
}
