package com.tengzhi.business.js.tlcz.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.js.tlcz.model.EJsTlcz;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-20 9:20
 */
public interface TlczService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EJsTlcz save(EJsTlcz eJsTlcz) throws Exception;

    void update(EJsTlcz eJsTlcz);

    Map<String,Object> findByCode(String code);

    void deleteByCode(String code);

}
