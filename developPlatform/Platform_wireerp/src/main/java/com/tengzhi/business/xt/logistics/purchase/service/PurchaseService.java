package com.tengzhi.business.xt.logistics.purchase.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.logistics.purchase.model.Purchase;
import com.tengzhi.business.xt.logistics.purchase.vo.PurchaseVo;

import java.io.IOException;
import java.util.Map;

public interface PurchaseService extends BaseService{

    /**
     * 获取收货通知列表
     * @param baseDto
     * @return
     * @throws Exception
     */
    BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto);
    /**
     * 明细
     * @param baseDto
     * @return
     * @throws IOException
     */
    BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

    Result save(PurchaseVo PurchaseVo) throws Exception;
    Result update(PurchaseVo PurchaseVo)throws Exception;

    Purchase findBySqnote(String Note);

    void deleteById(String Note);

    Result getFlag(String note, String flag);

    Result setFlag(String note, String flag);


}
