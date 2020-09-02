package com.tengzhi.business.finance.capitalManager.capitalRegister.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.capitalManager.capitalRegister.model.EFVoucherCapitalRegister;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CapitalRegisterService extends BaseService<EFVoucherCapitalRegister,String> {

    EFVoucherCapitalRegister save(EFVoucherCapitalRegister model) throws Exception;

    BasePage<EFVoucherCapitalRegister> getList(BaseDto baseDto) throws IOException;

    EFVoucherCapitalRegister findById(String fcardid);

    void update(EFVoucherCapitalRegister model);


    void deleteByNote(String fcardid);

    List<Map<String,Object>> getDeviceSelectList();

}
