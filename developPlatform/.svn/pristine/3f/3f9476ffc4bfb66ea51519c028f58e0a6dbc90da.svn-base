package com.tengzhi.business.ck.assets.idleAssets.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.ck.assets.idleAssets.model.ECkAssets;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-19 8:52
 */
public interface AssetsService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    ECkAssets save(ECkAssets eCkAssets) throws Exception;

    void update(ECkAssets eCkAssets);

    Map<String,Object> findById(String sid);

    void deleteById(String sid);

    Result updateFlag(String sid);
}
