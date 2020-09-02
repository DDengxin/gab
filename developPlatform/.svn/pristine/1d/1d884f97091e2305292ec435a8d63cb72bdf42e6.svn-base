package com.tengzhi.business.sale.processProduct.incomingMaterialReturn.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;

import java.io.IOException;
import java.util.Map;

public interface IncomingMaterialReturnService extends BaseService {

    BasePage<Map<String,Object>>  getSrchTopList(BaseDto baseDto) throws Exception;

    BasePage<Map<String,Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

    Result saveData(ECkInVo eCkInVo) throws Exception;

    Result updateData(ECkInVo eCkInVo)throws Exception;

    ECkIn findByInNote(String inNote);

    void deleteById(String inNote);

    BasePage<Map<String, Object>> getReturnList(BaseDto baseDto) throws IOException;

    Result getFlag(String inNote,String flag);

    Result setFlag(String inNote,String flag);
}
