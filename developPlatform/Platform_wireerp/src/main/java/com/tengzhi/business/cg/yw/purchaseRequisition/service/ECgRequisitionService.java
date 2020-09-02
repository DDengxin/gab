package com.tengzhi.business.cg.yw.purchaseRequisition.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.cg.yw.purchaseRequisition.vo.EcgRequisitiontVo;

import java.io.IOException;
import java.util.Map;


public interface ECgRequisitionService extends BaseService {
    BasePage<EcgRequisition> getSrchTopList(BaseDto baseDto) throws IOException;

    BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

    Result save(EcgRequisitiontVo ecgRequisitiontVo) throws Exception;

    Result update(EcgRequisitiontVo ecgRequisitiontVo) throws Exception;

    EcgRequisition findBySqnote(String sqNote);

    void deleteById(String note);

    Result getFlag(String note, String flag);

    Result setFlag(String note, String flag);

    Map<String, Object> getCgsqSelectList(BaseDto baseDto) throws IOException;

    Result a(BaseDto baseDto) throws IOException;

    void avacsaca(String a,String note, String code) ;
}
