package com.tengzhi.business.finance.capitalManager.capitalType.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.capitalManager.capitalType.model.EFVoucherCapitaltype;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CapitalTypeService   extends BaseService<EFVoucherCapitaltype,String> {

    EFVoucherCapitaltype save(EFVoucherCapitaltype model) throws Exception;

    BasePage<EFVoucherCapitaltype> getList(BaseDto baseDto) throws IOException;

    EFVoucherCapitaltype findById(String sid);

    void update(EFVoucherCapitaltype model);


    void deleteByNote(String cId);

    List<Map<String,Object>> getCapitalSelectList();

}
