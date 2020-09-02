package com.tengzhi.business.finance.capitalManager.capitalChange.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.capitalManager.capitalChange.model.EFVoucherCapitalChange;
import java.io.IOException;

public interface CapitalChangeService extends BaseService<EFVoucherCapitalChange,String> {

    EFVoucherCapitalChange save(EFVoucherCapitalChange model) throws Exception;

    BasePage<EFVoucherCapitalChange> getList(BaseDto baseDto) throws IOException;

    EFVoucherCapitalChange findById(String ksid);

    void update(EFVoucherCapitalChange model);


    void deleteByNote(String ksid);


}
