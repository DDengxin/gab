package com.tengzhi.business.sc.pd.jdcn.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;

import java.util.Map;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/21 0013 14:06
 * @Description:接单产能
 */

public interface JdcnService {

    BasePage<Map<String, Object>> findAll(BaseDto baseDto);

}
