package com.tengzhi.business.xt.getINOut.wlcl.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.getINOut.wlcl.model.EXtWlcl;

import java.io.IOException;
import java.util.Map;

public interface WlclService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EXtWlcl save(EXtWlcl eXtWlcl) throws Exception;

    void update(EXtWlcl eXtWlcl);

    Map<String,Object> findByNote(String note);

    void deleteByNote(String note);

    Result getFlag(String note,String flag);

    Result confirm(String note);

    Result cancle(String note);
}
