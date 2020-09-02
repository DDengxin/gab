package com.tengzhi.app.daily.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.telbook.model.TelBook;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DailyService  {

    /**
     * 查询今天快讯
     */
    BasePage<Map<String, Object>> getTodayNews(BaseDto baseDto) throws IOException;

    /**
     * 查询工作安排
     */
    BasePage<Map<String, Object>> getWorkPlan(BaseDto baseDto) throws IOException;

    /**
     * 查询外出申请
     */
    BasePage<Map<String, Object>> getGoOutApply(BaseDto baseDto) throws IOException;
}
