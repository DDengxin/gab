package com.tengzhi.business.xt.logistics.express.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.logistics.express.model.EXtExpress;

import java.io.IOException;
import java.util.Map;

public interface ExpressSerive extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EXtExpress save(EXtExpress eXtExpress) throws  Exception;

    void update(EXtExpress eXtExpress);

    Map<String,Object> findByNote(String note);

    void delete(String note);

}
