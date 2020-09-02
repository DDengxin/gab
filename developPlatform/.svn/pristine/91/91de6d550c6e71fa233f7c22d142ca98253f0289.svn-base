package com.tengzhi.business.xt.getINOut.wlwp.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.getINOut.wlwp.model.EXtWlwp;
import com.tengzhi.business.xt.getINOut.wlwp.vo.EXtWlwpVo;

import java.io.IOException;
import java.util.Map;

public interface WlwpService extends BaseService {

    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;

    EXtWlwp  save(EXtWlwpVo eXtWlwpVo) throws Exception;

    void update(EXtWlwpVo eXtWlwpVo);

    Map<String,Object> findByNote(String note);

    void deleteByNote(String note);

    Result getFlag(String note ,String flag);

    Result confirm(String note);

    Result cancle(String note);

    BasePage<Map<String,Object>>  getWlwpMx(BaseDto baseDto) throws IOException;
}
