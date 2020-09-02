package com.tengzhi.business.sc.da.leave.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.da.leave.model.EHrJbqj;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/11 0011 14:23
 * @Description:
 */

public interface EHrJbqjService {

    BasePage<EHrJbqj> findAll(BaseDto baseDto);


    Result updateFlag(String jbqjId);

}
