package com.tengzhi.business.finance.capitalManager.capitalCheck.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.capitalManager.capitalCheck.model.EFVoucherCapitalCheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CapitalCheckService extends BaseService<EFVoucherCapitalCheck,String> {

    EFVoucherCapitalCheck save(EFVoucherCapitalCheck model) throws Exception;

    BasePage<EFVoucherCapitalCheck> getList(BaseDto baseDto) throws IOException;

    EFVoucherCapitalCheck findById(String ksid);

    void update(EFVoucherCapitalCheck model);

    void deleteByNote(String ksid);

    Result jcOption();

    //excel 导出
    void getSrchTopListExportExcel(HttpServletResponse response, HttpServletRequest request);

    List<Map<String,Object>> getJcTree(BaseDto dto);
}
