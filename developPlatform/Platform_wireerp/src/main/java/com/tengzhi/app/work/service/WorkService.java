package com.tengzhi.app.work.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;

import java.io.IOException;
import java.util.Map;

public interface WorkService {
    /**
     * 查询客户档案
     */
    BasePage<Map<String, Object>> getCustomerProfile(BaseDto baseDto) throws IOException;
}
