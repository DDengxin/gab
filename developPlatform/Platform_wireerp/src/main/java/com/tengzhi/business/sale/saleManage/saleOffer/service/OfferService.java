package com.tengzhi.business.sale.saleManage.saleOffer.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOffer;
import com.tengzhi.business.sale.saleManage.saleOffer.vo.EXsOfferVo;

import java.io.IOException;
import java.util.Map;

public interface OfferService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EXsOffer save(EXsOfferVo eXsOfferVo)throws Exception;

    void update(EXsOfferVo eXsOfferVo);

    Map<String,Object> findByNote(String note);

    void deleteByNte(String note);

    BasePage<Map<String,Object>> getOfferDetailList(BaseDto baseDto)throws IOException;

    Result getFlag(String note,String flag);

    Result confirm(String note);

    Result cancle(String note);

    void updateDetailJs(EXsOfferVo eXsOfferVo);

    BasePage<Map<String,Object>> getHistoryList(BaseDto baseDto) throws IOException;

}
