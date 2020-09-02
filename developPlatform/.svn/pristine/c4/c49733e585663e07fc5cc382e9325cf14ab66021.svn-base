package com.tengzhi.business.xt.dailyRoutine.sealApplication.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.dailyRoutine.sealApplication.model.EXtSealApplication;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-20 19:07
 */
public interface SealApplicaitionService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EXtSealApplication save(EXtSealApplication application) throws Exception;

    void update(EXtSealApplication application);

    Map<String,Object> findByNote(String note);

    void deleteByNote(String note);

    Result getFlag(String note,String flag);

    Result confirm(String note);

    Result cancle(String note);

}
