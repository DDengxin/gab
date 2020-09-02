package com.tengzhi.business.xt.getINOut.rylf.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.getINOut.rylf.model.EXtRylf;

import java.io.IOException;
import java.util.Map;

public interface RylfService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EXtRylf save(EXtRylf eXtRylf) throws Exception;

    Map<String,Object> finById(String note);

    void update(EXtRylf eXtRylf);

    void deleteByNote(String note);

    Result getFlag(String note,String flag);

    Result confirm(String note);

    Result cancle(String note);

}
