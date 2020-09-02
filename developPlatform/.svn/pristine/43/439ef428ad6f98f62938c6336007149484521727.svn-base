package com.tengzhi.business.sc.tj.scrw.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;

import java.io.IOException;
import java.util.Map;

public interface ScrwService extends BaseService<MScScrw, MScScrw_PK> {
    BasePage<Map<String, Object>> findAll(BaseDto basedto);

    /***
     * 工序统计
     * @param basedto
     * @return
     * @throws IOException
     */
    BasePage<Map<String, Object>> findGxAll(BaseDto basedto) ;

    /**
     * 机台统计
     * @param basedto
     * @return
     * @throws IOException
     */
    BasePage<Map<String, Object>> findJtAll(BaseDto basedto);
}
