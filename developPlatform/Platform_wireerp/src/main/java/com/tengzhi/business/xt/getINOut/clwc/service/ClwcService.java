package com.tengzhi.business.xt.getINOut.clwc.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.getINOut.clwc.model.EXtClwc;
import com.tengzhi.business.xt.getINOut.clwc.vo.EXtClwcVo;

import java.io.IOException;
import java.util.Map;

public interface ClwcService extends BaseService {

    BasePage<Map<String,Object>>  findAll(BaseDto baseDto) throws IOException;

    Result save(EXtClwcVo eXtClwcVo) throws Exception;

    BasePage<Map<String,Object>> findByNote(BaseDto baseDto);

    Result update(EXtClwcVo eXtClwcVo);

    void deleteByNote(String note);

    Result getFlag(String note,String flag);

    Result confirm(String note);

    Result cancle(String note);

    BasePage<Map<String,Object>> getWcsqList(BaseDto baseDto) throws IOException;
}
