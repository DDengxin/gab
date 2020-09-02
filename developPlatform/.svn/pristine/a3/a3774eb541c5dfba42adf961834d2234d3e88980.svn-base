package com.tengzhi.business.xt.dailyRoutine.workPlan.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.dailyRoutine.workPlan.model.EXtWorkplan;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-20 14:22
 */
public interface WorkplanService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EXtWorkplan save(EXtWorkplan eXtWorkplan) throws Exception;

    void update(EXtWorkplan eXtWorkplan);

    Map<String,Object> findByNote(String note);

    void deleteByNote(String note);


}
