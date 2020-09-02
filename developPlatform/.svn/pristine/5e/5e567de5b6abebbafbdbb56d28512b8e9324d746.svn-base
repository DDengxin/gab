package com.tengzhi.business.finance.capitalManager.capitalDepletion.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.capitalManager.capitalDepletion.model.EFVoucherCapitalDepletion;

import java.io.IOException;

public interface CapitalDepletionService extends BaseService<EFVoucherCapitalDepletion,String> {

    EFVoucherCapitalDepletion save(EFVoucherCapitalDepletion model) throws Exception;

    BasePage<EFVoucherCapitalDepletion> getList(BaseDto baseDto) throws IOException;

    EFVoucherCapitalDepletion findById(String ksid);

    void update(EFVoucherCapitalDepletion model);


    void deleteByNote(String ksid);


}
