package com.tengzhi.business.finance.reportItem.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.reportItem.model.EFVoucherReportitem;
import com.tengzhi.business.finance.reportItem.vo.ReportItemVo;
import com.tengzhi.business.resouces.vo.SelectVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReportItemService   extends BaseService<EFVoucherReportitem,String> {


    BasePage<EFVoucherReportitem> getList(BaseDto baseDto) throws IOException;

    List<Map> getList();

    List<Map>  getReportList();

    Map<String,Object> getById(String freportitem,String freportid);

    Result saveData(ReportItemVo vo );
}
