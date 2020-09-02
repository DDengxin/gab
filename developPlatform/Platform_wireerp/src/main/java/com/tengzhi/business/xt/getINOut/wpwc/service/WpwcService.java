package com.tengzhi.business.xt.getINOut.wpwc.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.getINOut.wpwc.model.EXtWpwc;
import com.tengzhi.business.xt.getINOut.wpwc.vo.EXtWpwcVo;

import java.io.IOException;
import java.util.Map;

public interface WpwcService extends BaseService {

    BasePage<Map<String,Object>>  findAll(BaseDto baseDto) throws IOException;

    EXtWpwc save(EXtWpwcVo eXtWpwcVo) throws Exception;

    void  update(EXtWpwcVo eXtWpwcVo);

    EXtWpwc findByNote(String note);

    void deleteByNote(String note);

    Result getFlag(String note,String flag);

    Result confirm(String note);

    Result cancle(String note);

    BasePage<Map<String,Object>>  getWpwcMx(BaseDto baseDto) throws IOException;

}
