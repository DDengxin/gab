package com.tengzhi.business.xt.reception.visitors.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.reception.visitors.model.Visitors;

import java.util.Map;

public interface VisitorsService extends BaseService<Visitors,String> {

    BasePage<Map<String, Object>> getList(BaseDto baseDto);

    Visitors save(Visitors Visitors) throws Exception;

    Visitors findBynote(String note);

    void update(Visitors Visitors);

    void deleteById(String note);

}
