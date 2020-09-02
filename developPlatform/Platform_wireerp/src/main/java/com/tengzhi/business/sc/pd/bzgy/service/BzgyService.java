package com.tengzhi.business.sc.pd.bzgy.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;

import java.util.Map;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 10:06
 * @Description:标准工艺
 */

public interface BzgyService {

    BasePage<Map<String, Object>> findAll(BaseDto baseDto);

}
